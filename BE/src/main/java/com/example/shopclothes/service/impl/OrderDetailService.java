package com.example.shopclothes.service.impl;

import com.example.shopclothes.dto.OrderDetailInStoreRequestDto;
import com.example.shopclothes.dto.OrderDetailResponseDto;
import com.example.shopclothes.entity.Order;
import com.example.shopclothes.entity.OrderDetail;
import com.example.shopclothes.entity.ProductDetail;
import com.example.shopclothes.exception.ResourceNotFoundException;
import com.example.shopclothes.repositories.OrderDetailRepository;
import com.example.shopclothes.repositories.OrderRepository;
import com.example.shopclothes.repositories.ProductDetailRepo;
import com.example.shopclothes.service.OrderDetailServiceIPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailService implements OrderDetailServiceIPL {

    @Autowired
    private ProductDetailRepo productDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    public Boolean createOrderDetail(OrderDetailInStoreRequestDto orderDetailRequestDto) {
        // Tìm ProductDetail theo ID
        ProductDetail productDetail = productDetailRepository.findById(orderDetailRequestDto.getProductDetailId()).orElse(null);

        // Tìm Order theo ID
        Order order = orderRepository.findById(orderDetailRequestDto.getOrderId()).orElse(null);

        if (productDetail != null && order != null) {
            // Kiểm tra xem ProductDetail đã tồn tại trong Order chưa
            Optional<OrderDetail> existingOrderDetail = orderDetailRepository.findByOrderIdAndProductDetailId(order.getId(), productDetail.getId());

            if (existingOrderDetail.isPresent()) {
                // Nếu ProductDetail đã tồn tại trong Order, cộng thêm số lượng
                OrderDetail orderDetail = existingOrderDetail.get();
                int requestedQuantity = orderDetailRequestDto.getQuantity();

                // Kiểm tra xem có đủ số lượng để cộng không
                if (productDetail.getQuantity() >= requestedQuantity) {
                    // Cộng thêm số lượng vào OrderDetail
                    orderDetail.setQuantity(orderDetail.getQuantity() + requestedQuantity);
                    // Tính tổng giá trị (số lượng * giá) và cập nhật vào OrderDetail
                    orderDetail.setPrice(productDetail.getPrice());

                    // Giảm số lượng trong ProductDetail
                    productDetail.setQuantity(productDetail.getQuantity() - requestedQuantity);
                    // Lưu cập nhật ProductDetail
                    productDetailRepository.save(productDetail);

                    // Lưu cập nhật OrderDetail
                    orderDetailRepository.save(orderDetail);
                } else {
                    // Xử lý trường hợp không đủ số lượng
                    throw new ResourceNotFoundException("Số lượng sản phẩm không đủ");
                }
            } else {
                // Nếu ProductDetail chưa tồn tại trong Order, tạo mới OrderDetail
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setProductDetail(productDetail);
                orderDetail.setQuantity(orderDetailRequestDto.getQuantity());
                orderDetail.setPrice(orderDetailRequestDto.getPrice());

                // Giảm số lượng trong ProductDetail
                productDetail.setQuantity(productDetail.getQuantity() - orderDetailRequestDto.getQuantity());
                // Lưu cập nhật ProductDetail
                productDetailRepository.save(productDetail);

                // Lưu OrderDetail
                orderDetailRepository.save(orderDetail);
            }
            return true;
        } else {
            throw new ResourceNotFoundException("Không tìm thấy ProductDetail hoặc Order");
        }
    }

    @Override
    public Page<OrderDetailResponseDto> getOrderDetailByOrderId(Long orderId, Pageable pageable) {
        return orderDetailRepository.findOrderDetailByOrderId(orderId,pageable);
    }

    @Override
    public Boolean deleteOrderDetail(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy hóa đơn chi tiết"));

        // Get the associated ProductDetail
        ProductDetail productDetail = orderDetail.getProductDetail();

        // Xóa OrderDetail
        orderDetailRepository.delete(orderDetail);

        if (productDetail != null) {
            productDetail.setQuantity(productDetail.getQuantity() + orderDetail.getQuantity());
            productDetailRepository.save(productDetail);
        }

        return true;
    }

    @Override
    public Boolean updateQuantityOrderDetail(Integer quantity, Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id hóa đơn chi tiết này!"));
        // Get the associated ProductDetail
        ProductDetail productDetail = orderDetail.getProductDetail();


        System.out.println("quantity" + quantity);
        System.out.println("productDetail.getQuantity" + productDetail.getQuantity());
        System.out.println("(productDetail.getQuantity) " + (productDetail.getQuantity() - quantity));
        if ( productDetail.getQuantity() - quantity <= 0 ){
            throw new IllegalArgumentException("Số lượng sản phẩm không đủ");
        }else {
            // Calculate the difference in quantity
            int quantityDifference = quantity - orderDetail.getQuantity() + quantity;

            // Update the quantity in OrderDetail
            orderDetail.setQuantity(quantity);
            orderDetailRepository.save(orderDetail);

            // Check if ProductDetail is not null (just to be safe)
            if (productDetail != null) {
                // Update the quantity in ProductDetail
                productDetail.setQuantity(productDetail.getQuantity() - quantityDifference);
                // Save the updated ProductDetail
                productDetailRepository.save(productDetail);
            }
        }

        return true;
    }
}
