package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Producer;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.ProducerRepo;
import com.example.shopclothes.service.impl.ProducerServiceIPL;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import java.util.List;
import java.util.Random;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/Producer")
@Tag(name = "Producer", description = "( Rest API Hiển thị, thêm, sửa, xóa nhà cung cấp )")
@Validated
public class ProducerController {

    @Autowired
    private ProducerServiceIPL producerService;
    @Autowired
    private ProducerRepo producerRepo;

    @GetMapping("/hien-thi")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(producerService.getAllNSX());
    }


//    @GetMapping("getAll")
//    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
//                                          @RequestParam(defaultValue = "4") Integer pageSize,
//                                          @RequestParam(required = false) String name,
//                                          @RequestParam(required = false) String status,
//                                    @RequestParam(required = false) Date dateCreate,
//                                          @RequestParam(required = false) Date dateUpdate,
//                                          @RequestParam(required = false) List<Boolean> deleted) {
//        Pageable pageable = PageRequest.of(pageNo, pageSize);
//        Page<Producer> supplierPage = producerService.getSuppliers(supplierName,phoneNumber,email,deleted,pageable);
//        List<Producer> supplierList = supplierPage.getContent();
//        return ResponseHandler
//                .generateResponse(
//                        HttpStatus.OK,
//                        supplierList,
//                        supplierPage);
//    }
@GetMapping("/hien-thi-page")
public ResponseEntity<?> hienThiPage(@RequestParam(defaultValue = "0") Integer page) {
    Pageable pageable = PageRequest.of(page, 10);
    Page<Producer> producerPage = producerRepo.getAllByStatus(Status.DANG_HOAT_DONG, pageable);
    return ResponseEntity.ok(producerPage.getContent());
}


    @GetMapping("/hien-thi-page-search")
    public ResponseEntity<?> hienThiPageSearch(String key, Integer trangThai, @RequestParam (defaultValue = "0") Integer page){
        Pageable pageable = PageRequest.of(page,5);
        return ResponseEntity.ok(producerService.pageSearchNSX(key,trangThai, pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Producer nhaSanXuat){
        String ma = "NSX" + new Random().nextInt(100000);
        nhaSanXuat.setCode(ma);
        nhaSanXuat.setDateCreate(new Date());
        return ResponseEntity.ok(producerService.add(nhaSanXuat));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable Long  id){
        return ResponseEntity.ok(producerService.detail(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Producer nhaSanXuat) {
        Producer c = producerService.detail(id);

        if (c != null) {
            nhaSanXuat.setId(id);
            nhaSanXuat.setCode(c.getCode());
            nhaSanXuat.setDateCreate(c.getDateCreate()); // Sử dụng ngày tạo của bản gốc
            nhaSanXuat.setDateUpdate(new Date());

            // Thực hiện cập nhật
            return ResponseEntity.ok(producerService.add(nhaSanXuat));
        } else {
            // Trả về một phản hồi khi không tìm thấy nhà sản xuất
            return ResponseEntity.notFound().build();
        }
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Producer nhaSanXuat){
//        Producer c = producerService.detail(id);
//        nhaSanXuat.setId(id);
//        nhaSanXuat.setCode(c.getCode());
//        nhaSanXuat.setDateCreate(c.getDateCreate()); // Sử dụng ngày tạo của bản gốc
//        nhaSanXuat.setDateUpdate(new Date());
//        return ResponseEntity.ok(producerService.add(nhaSanXuat));
//    }


//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id, @RequestBody(required = false) Producer nhaSanXuat) {
//        try {
//            if (nhaSanXuat != null) {
//                nhaSanXuat.setId(id);
//                return ResponseEntity.ok(producerService.xoa(id));
//            } else {
//                // Xử lý khi nhaSanXuat là null, có thể trả về 400 Bad Request hoặc một ResponseEntity khác tùy thuộc vào logic của b
//                System.out.println("Lôi xóa");
//                return ResponseEntity.badRequest().body("Producer not found");
//            }
//        } catch (Exception e) {
//            // Log lỗi để biết nguyên nhân chi tiết
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
//        }
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestBody(required = false) Producer nhaSanXuat) {
        try {
            Producer existingProducer = producerRepo.findById(id).orElse(null);
            if (existingProducer != null) {
                existingProducer.setDateCreate(existingProducer.getDateCreate());
                existingProducer.setDateUpdate(new Date());
                existingProducer.setStatus(Status.NGUNG_HOAT_DONG);
                return ResponseEntity.ok(producerService.xoa(id));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }


    @GetMapping("findAllByDeletedTrue")
    public ResponseEntity<List<Producer>> findByDeletedTrue() {

        List<Producer> supplierList = producerService.findByDeletedTrue();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(supplierList);
    }
}
