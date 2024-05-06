package com.example.shopclothes.controller;

import com.example.shopclothes.constants.NotificationConstants;
import com.example.shopclothes.dto.ImageRequestDto;
import com.example.shopclothes.dto.ResponseDto;
import com.example.shopclothes.entity.Imege;
import com.example.shopclothes.service.impl.ImageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/Image")
@Tag(name = "Image",description = "( Rest API Hiển thị, thêm, sửa, xóa ảnh )")
@Validated
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Imege>> getImages() {
        List<Imege> imageList = imageService.getImages();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(imageList);
    }

    @GetMapping("findImageByProductId")
    public ResponseEntity<List<Imege>> findImageByProductId(@RequestParam Long productId) {

        List<Imege> imageList = imageService.findImageByProductId(productId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(imageList);
    }

    @GetMapping("findImageByImageLink")
    public ResponseEntity<List<Imege>> findImageByImageLink(@RequestParam String imageLink) {

        List<Imege> imageList = imageService.findByImageLink(imageLink);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(imageList);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> creatImage(@RequestBody List<ImageRequestDto> imageRequestDto) {

        Boolean isCreated = imageService.createImages(imageRequestDto);

        if(isCreated){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto(NotificationConstants.STATUS_201,NotificationConstants.MESSAGE_201));
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(NotificationConstants.STATUS_500,NotificationConstants.MESSAGE_500));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateImage(@Valid @RequestBody ImageRequestDto imageRequestDto, @RequestParam Long id) {

        Boolean isUpdated = imageService.updateImage(imageRequestDto, id);

        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(NotificationConstants.STATUS_200,NotificationConstants.MESSAGE_200));
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(NotificationConstants.STATUS_500,NotificationConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteImage(@RequestParam Long id) {

        Boolean isDeleted = imageService.deleteImage(id);

        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(NotificationConstants.STATUS_200,NotificationConstants.MESSAGE_200));
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(NotificationConstants.STATUS_500,NotificationConstants.MESSAGE_500));
        }
    }

}
