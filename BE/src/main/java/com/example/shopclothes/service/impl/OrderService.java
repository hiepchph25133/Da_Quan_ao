package com.example.shopclothes.service.impl;

import com.example.shopclothes.dto.OrderInStoreRequestDto;
import com.example.shopclothes.dto.OrderStatusRequestDto;
import com.example.shopclothes.entity.*;
import com.example.shopclothes.exception.ResourceNotFoundException;
import com.example.shopclothes.repositories.*;
import com.example.shopclothes.service.OrderServiceIPL;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class OrderService implements OrderServiceIPL {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ProductDetailRepo productDetailRepository;

    @Autowired
    private VocherRepo vocherRepo;

    @Override
    public Order createOrderInStore() {
        OrderStatus orderStatus = orderStatusRepository.findByStatusName("Tạo đơn hàng").orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy trạng thái hóa đơn này!"));
        Order order = new Order();
        order.setOrderStatus(orderStatus);
        order.setOrderType("Tại quầy");
        orderRepository.save(order);
        OrderHistory timeLine = new OrderHistory();
        timeLine.setOrder(order);
        timeLine.setStatus(orderStatus);
        orderHistoryRepository.save(timeLine);

        return order;
    }

    @Override
    public List<Order> findAllOrderByStatusName() {
        return orderRepository.findAllOrderByStatusName();
    }

    @Override
    public Boolean DeleteOrder(Long id){

        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy hóa đơn này"));

        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(id);

        for (OrderDetail orderDetail : orderDetails){
            orderDetailRepository.delete(orderDetail);
        }

        OrderHistory orderHistory = orderHistoryRepository.findByOrderId(order.getId());
        orderHistoryRepository.delete(orderHistory);
        // Xóa hóa đơn
        orderRepository.delete(order);
        return  true;

    }

    @Override
    public Order findOrderById(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id hóa đơn này"));

        return order;
    }



    @Override
    public Order updateOrder(OrderInStoreRequestDto requestDto) {
        Order order = orderRepository.findById(requestDto.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id hóa đơn này!"));

        OrderStatus orderStatus = (requestDto.getStatusName() != null) ?
                orderStatusRepository.findByStatusName(requestDto.getStatusName()).orElse(null) : null;

        boolean hasException = false;

        for (OrderDetail orderDetail : order.getOrderDetails()) {
            ProductDetail productDetail = orderDetail.getProductDetail();

            if (productDetail.getQuantity() < orderDetail.getQuantity()) {
                hasException = true;
                throw new ResourceNotFoundException("Số lượng sản phẩm vượt quá số lượng trong kho!");
            }
        }

        if (!hasException) {
            order.setOrderStatus(orderStatus);
            order.setOrderTotal(requestDto.getOrderTotal());
            order.setNote(requestDto.getNote());
            order.setTransportFee(requestDto.getTransportFee());
            order.setRecipientName(requestDto.getRecipientName());
            order.setPhoneNumber(requestDto.getPhoneNumber());
            order.setAddressDetail(requestDto.getAddressDetail());
            order.setWard(requestDto.getWard());
            order.setDistrict(requestDto.getDistrict());
            order.setCity(requestDto.getCity());

            orderRepository.save(order);

            // Tạo đối tượng OrderHistory và lưu vào cơ sở dữ liệu
            OrderHistory timeLine = new OrderHistory();
            timeLine.setOrder(order);
            timeLine.setNote(requestDto.getNote());
            timeLine.setStatus(orderStatus);
            orderHistoryRepository.save(timeLine);
        }

        return order;
    }


    @Override
    public Order updateOrderUser(Long orderId, Long userId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id hóa đơn này!"));

        User user = (userId != null) ? userRepository.findById(userId).orElse(null) : null;
        order.setUser(user);
        orderRepository.save(order);
        return order;
    }
    

    @Override
    public Page<Order> getAllOrders(String orderStatusName, String orderId, String orderType,
                                    LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        Long convertedOrderId = null;
        try {
            convertedOrderId = orderId != null ? Long.parseLong(orderId) : null;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return orderRepository.findAllByStatusNameAndDeletedIsTrue(
                orderStatusName,
                convertedOrderId,
                orderType,
                startDate,
                endDate,
                pageable);
    }
//    public Page<Order> getAllOrders(String orderStatusName, String orderId, String orderType,
//                                    LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
//        Date convertedStartDate = startDate != null ? Date.from(startDate.atZone(ZoneId.systemDefault()).toInstant()) : null;
//        Date convertedEndDate = endDate != null ? Date.from(endDate.atZone(ZoneId.systemDefault()).toInstant()) : null;
//        Long convertedOrderId = null;
//        try {
//            convertedOrderId = orderId != null ? Long.parseLong(orderId) : null;
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
//
//        return orderRepository.findAllByStatusNameAndDeletedIsTrue(
//                orderStatusName,
//                convertedOrderId,
//                orderType,
//                convertedStartDate,
//                convertedEndDate,
//                pageable);
//    }

    @Override
    public Boolean updateOrderStatus(OrderStatusRequestDto orderStatusRequestDto) {
        Order order = orderRepository.findById(orderStatusRequestDto.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đơn hàng này!"));

        OrderStatus orderStatus = orderStatusRepository.findByStatusName(orderStatusRequestDto.getNewStatusName()).orElse(null);

        // Kiểm tra nếu trạng thái mới là "Đã hủy"
        if (orderStatusRequestDto.getNewStatusName().equals("Đã hủy")) {
            // Thực hiện các thao tác cộng lại số lượng sản phẩm
            List<OrderDetail> orderDetails = order.getOrderDetails();
            for (OrderDetail orderDetail : orderDetails) {
                ProductDetail productDetail = orderDetail.getProductDetail();
                productDetail.setQuantity(productDetail.getQuantity() + orderDetail.getQuantity());
                productDetailRepository.save(productDetail);
            }
        }

        order.setNote(orderStatusRequestDto.getNote());
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        // Xét lịch sử đơn hàng
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrder(order);
        orderHistory.setStatus(orderStatus);
        orderHistory.setNote(orderStatusRequestDto.getNote());
        orderHistoryRepository.save(orderHistory);

        return true;
    }

    @Override
    public void generateExcel(HttpServletResponse response) throws IOException {
        List<Order> orders = orderRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Orders Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Mã HD");
        row.createCell(1).setCellValue("Tên khách hàng");
        row.createCell(2).setCellValue("Loại đơn hàng");
        row.createCell(3).setCellValue("Ngày tạo");
        row.createCell(4).setCellValue("Tiền giảm");
        row.createCell(5).setCellValue("Phí giao hàng");
        row.createCell(6).setCellValue("Tổng tiền");
        row.createCell(7).setCellValue("Địa chỉ giao");
        row.createCell(8).setCellValue("Ghi chú");

        int dataRowIndex = 1;

        for (Order order : orders) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(order.getId());
            dataRow.createCell(1).setCellValue(order.getUser() != null ? order.getUser().getUsersName() : "Khách lẻ");
            dataRow.createCell(2).setCellValue(order.getOrderType());
            dataRow.createCell(3).setCellValue(order.getCreatedAt().toString());
//            dataRow.createCell(4).setCellValue(order.getVoucher() != null ? order.getVoucher().getDiscountRate() : 0);
            dataRow.createCell(5).setCellValue(order.getTransportFee() == null ? 0 : order.getTransportFee());
            dataRow.createCell(6).setCellValue(order.getOrderTotal() == null ? 0 : order.getOrderTotal());
            dataRow.createCell(7).setCellValue(order.getRecipientName() + order.getPhoneNumber() + order.getAddressDetail() + order.getWard() + order.getDistrict() + order.getCity());
            dataRow.createCell(8).setCellValue(order.getNote());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }


    @Override
    public Order updateOrderVoucher(Long orderId, String code) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id hóa đơn này!"));

        Vocher voucher = (code != null) ? vocherRepo.findByCode(code) : null;

        order.setVoucher(voucher);
        orderRepository.save(order);
        return order;
    }



//    @Override
//    public Order updateOderVocher(Long orderId, String voucherCode) {
//        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id hóa đơn này!"));
//
//        Vocher voucher = (voucherCode != null) ? vocherRepo.findByCode(voucherCode) : null;
//
//        order.setVoucher(voucher);
//        orderRepository.save(order);
//        return order;
//    }

}
