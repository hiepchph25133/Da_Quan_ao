package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Category;
import com.example.shopclothes.entity.Color;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.CategoryRepo;
import com.example.shopclothes.service.impl.CategoryService;
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
@RequestMapping("/api/v1/Category")
@Tag(name = "Category", description = "( Rest API Hiển thị, thêm, sửa, xóa danh muc )")
@Validated
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping("/hien-thi")
    public List<Category> hienThi(){
        return categoryService.select();
    }

    @GetMapping("/hien-thi-page")
    public ResponseEntity<?> hienThiPage(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Category> producerPage = categoryRepo.getAllByStatus(Status.DANG_HOAT_DONG, pageable);
        return ResponseEntity.ok(producerPage.getContent());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Category ct){
        String ma = "MS" + new Random().nextInt(100000);
        ct.setCode(ma);
        ct.setDateCreate(new Date());
        return ResponseEntity.ok(categoryService.add(ct));
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable Long  id){
        return ResponseEntity.ok(categoryService.detail(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestBody(required = false) Color nhaSanXuat) {
        try {
            Category existingProducer = categoryRepo.findById(id).orElse(null);
            if (existingProducer != null) {
                existingProducer.setDateCreate(existingProducer.getDateCreate());
                existingProducer.setDateUpdate(new Date());
                existingProducer.setStatus(Status.NGUNG_HOAT_DONG);
                return ResponseEntity.ok(categoryService.xoa(id));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category ct) {
        Category c = categoryService.detail(id);

        if (c != null) {
            ct.setId(id);
            ct.setCode(c.getCode());
            ct.setDateCreate(c.getDateCreate()); // Sử dụng ngày tạo của bản gốc
            ct.setDateUpdate(new Date());

            // Thực hiện cập nhật
            return ResponseEntity.ok(categoryService.add(ct));
        } else {
            // Trả về một phản hồi khi không tìm thấy nhà sản xuất
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("findAllByDeletedTrue")
    public ResponseEntity<List<Category>> findAllByDeletedTrue() {

        List<Category> categoryList = categoryService.findAllByDeletedTrue();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryList);
    }
}
