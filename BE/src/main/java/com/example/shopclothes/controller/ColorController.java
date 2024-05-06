package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Color;
import com.example.shopclothes.entity.Producer;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.ColorRepo;
import com.example.shopclothes.service.impl.ColorService;
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
@RequestMapping("/api/v1/coler")
@Tag(name = "Coler", description = "( Rest API Hiển thị, thêm, sửa, xóa mau sac )")
@Validated
public class ColorController {

    @Autowired
    private ColorService colorService;

    @Autowired
    private ColorRepo colorRepo;

    @GetMapping("/hien-thi")
    public ResponseEntity<?> hienThi(){
        return ResponseEntity.ok(colorService.getALLColor());
    }

    @GetMapping("/hien-thi-page")
    public ResponseEntity<?> hienThiPage(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Color> producerPage = colorRepo.getAllByStatus(Status.DANG_HOAT_DONG, pageable);
        return ResponseEntity.ok(producerPage.getContent());
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Color Color){
        String ma = "MS" + new Random().nextInt(100000);
        Color.setCode(ma);
        Color.setDateCreate(new Date());
        return ResponseEntity.ok(colorService.add(Color));
    }


    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable Long  id){
        return ResponseEntity.ok(colorService.detail(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestBody(required = false) Color cl) {
        try {
            Color existingProducer = colorRepo.findById(id).orElse(null);
            if (existingProducer != null) {
                existingProducer.setDateCreate(existingProducer.getDateCreate());
                existingProducer.setDateUpdate(new Date());
                existingProducer.setStatus(Status.NGUNG_HOAT_DONG);
                return ResponseEntity.ok(colorService.xoa(id));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Color MS) {
        Color c = colorService.detail(id);

        if (c != null) {
            MS.setId(id);
            MS.setCode(c.getCode());
            MS.setDateCreate(c.getDateCreate()); // Sử dụng ngày tạo của bản gốc
            MS.setDateUpdate(new Date());

            // Thực hiện cập nhật
            return ResponseEntity.ok(colorService.add(MS));
        } else {
            // Trả về một phản hồi khi không tìm thấy nhà sản xuất
            return ResponseEntity.notFound().build();
        }
    }

}
