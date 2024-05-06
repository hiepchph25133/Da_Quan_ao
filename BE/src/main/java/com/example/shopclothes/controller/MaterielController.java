package com.example.shopclothes.controller;

import com.example.shopclothes.entity.Color;
import com.example.shopclothes.entity.Material;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.repositories.MaterielRepo;
import com.example.shopclothes.service.impl.MaterialService;
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
import java.util.Random;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/Material")
@Tag(name = "Material", description = "( Rest API Hiển thị, thêm, sửa, xóa mau sac )")
@Validated
public class MaterielController {

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MaterielRepo materielRepo;


    @GetMapping("/hien-thi")
    public ResponseEntity<?> hienThi(){
        return ResponseEntity.ok(materialService.getALL());
    }

    @GetMapping("/hien-thi-page")
    public ResponseEntity<?> hienThiPage(@RequestParam(defaultValue = "0") Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Material> producerPage = materielRepo.getAllByStatus(Status.DANG_HOAT_DONG, pageable);
        return ResponseEntity.ok(producerPage.getContent());
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Material mt){
        String ma = "MT" + new Random().nextInt(100000);
        mt.setCode(ma);
        mt.setDateCreate(new Date());
        return ResponseEntity.ok(materialService.add(mt));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable Long  id){
        return ResponseEntity.ok(materialService.detail(id));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestBody(required = false) Material mt) {
        try {
            Material existingProducer = materielRepo.findById(id).orElse(null);
            if (existingProducer != null) {
                existingProducer.setDateCreate(existingProducer.getDateCreate());
                existingProducer.setDateUpdate(new Date());
                existingProducer.setStatus(Status.NGUNG_HOAT_DONG);
                return ResponseEntity.ok(materialService.xoa(id));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Material material) {
        Material c = materialService.detail(id);

        if (c != null) {
            material.setId(id);
            material.setCode(c.getCode());
            material.setDateCreate(c.getDateCreate()); // Sử dụng ngày tạo của bản gốc
            material.setDateUpdate(new Date());

            // Thực hiện cập nhật
            return ResponseEntity.ok(materialService.add(material));
        } else {
            // Trả về một phản hồi khi không tìm thấy nhà sản xuất
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/hien-thi")
//    public List<Material> hienThi(){
//        return materialService.select();
//    }
//
//    @GetMapping("/delete/{id}")
//    public void delete(@PathVariable Long id){
//        materialService.delete(id);
//    }
//
//    @GetMapping("/search/{id}")
//    public void search(@PathVariable Long id){
//        materialService.search(id);
//    }
//
//    @PostMapping("/add")
//    public void add(Material material){
//        materialService.save(material);
//    }
//
//    @PostMapping("/update/{id}")
//    public void update(Material material, @PathVariable Long id){
//        materialService.update(material, id);
//    }
}
