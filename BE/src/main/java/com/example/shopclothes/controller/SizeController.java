package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Color;
import com.example.shopclothes.entity.Size;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.SizeRepo;
import com.example.shopclothes.service.impl.SizeService;
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
@RequestMapping("/api/v1/Size")
@Tag(name = "Size", description = "( Rest API Hiển thị, thêm, sửa, xóa Size )")
@Validated
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @Autowired
    private SizeRepo sizeRepo;

    @GetMapping("/hien-thi")
    public ResponseEntity<?> hienThi(){
        return ResponseEntity.ok(sizeService.getAllSize());
    }

    @GetMapping("/hien-thi-page")
    public ResponseEntity<?> hienThiPage(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Size> producerPage = sizeRepo.getAllByStatus(Status.DANG_HOAT_DONG, pageable);
        return ResponseEntity.ok(producerPage.getContent());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Size size){
        String ma = "SZ" + new Random().nextInt(100000);
        size.setCode(ma);
        size.setDateCreate(new Date());
        return ResponseEntity.ok(sizeService.add(size));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable Long  id){
        return ResponseEntity.ok(sizeService.detail(id));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestBody(required = false) Size size) {
        try {
            Size existingProducer = sizeRepo.findById(id).orElse(null);
            if (existingProducer != null) {
                existingProducer.setDateCreate(existingProducer.getDateCreate());
                existingProducer.setDateUpdate(new Date());
                existingProducer.setStatus(Status.NGUNG_HOAT_DONG);
                return ResponseEntity.ok(sizeService.xoa(id));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Size sz) {
        Size c = sizeService.detail(id);

        if (c != null) {
            sz.setId(id);
            sz.setCode(c.getCode());
            sz.setDateCreate(c.getDateCreate()); // Sử dụng ngày tạo của bản gốc
            sz.setDateUpdate(new Date());

            // Thực hiện cập nhật
            return ResponseEntity.ok(sizeService.add(sz));
        } else {
            // Trả về một phản hồi khi không tìm thấy nhà sản xuất
            return ResponseEntity.notFound().build();
        }
    }
}
