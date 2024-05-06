package com.example.shopclothes.service.impl;

import com.example.shopclothes.dto.ImageRequestDto;
import com.example.shopclothes.entity.Imege;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.exception.ResourceNotFoundException;
import com.example.shopclothes.repositories.ImageRepo;
import com.example.shopclothes.repositories.ProductDetailRepo;
import com.example.shopclothes.repositories.ProductRepo;
import com.example.shopclothes.service.IService;
import com.example.shopclothes.service.ImgaeServices;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ImageService implements ImgaeServices {

    @Autowired
    private ImageRepo imageRepo;

    @Autowired
    private ProductDetailRepo productDetailRepo;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public List<Imege> getImages() {
        return imageRepo.findAll();
    }

//    @Override
//    public Boolean createImages(List<ImageRequestDto> imageRequestDtos) {
//        List<Imege> images = new ArrayList<>();
//
//        boolean isFirstImageSet = false; // Biến flag để kiểm tra xem đã đặt deleted cho ảnh đầu tiên chưa
//
//        for (ImageRequestDto imageRequestDto : imageRequestDtos) {
//            Imege image = new Imege();
//            image.setProduct(productRepo.findById(imageRequestDto.getProductId()).orElse(null));
//            image.setName(imageRequestDto.getImageName());
//            image.setImageLink(imageRequestDto.getImageLink());
//            image.setImageType(imageRequestDto.getImageType());
//
//            // Nếu chưa đặt deleted cho ảnh đầu tiên, đặt giá trị deleted thành true
//            if (!isFirstImageSet) {
//                image.setStatus(Status.DANG_HOAT_DONG);
//                isFirstImageSet = true; // Đặt lại flag để không còn là ảnh đầu tiên
//            } else {
//                // Nếu không phải ảnh đầu tiên, kiểm tra xem deleted được cung cấp hay không
//                if (imageRequestDto.getStatus() != null) {
//                    image.setStatus(imageRequestDto.getStatus());
//                } else {
//                    // Nếu không được cung cấp, đặt giá trị mặc định là false
//                    image.setStatus(Status.NGUNG_HOAT_DONG);
//                }
//            }
//
//            images.add(image);
//        }
//
//        imageRepo.saveAll(images);
//        return true;
//    }

    @Override
    public Boolean createImages(List<ImageRequestDto> imageRequestDtos) {
        List<Imege> images = new ArrayList<>();

        for (int i = 0; i < imageRequestDtos.size(); i++) {
            Imege image = new Imege();
            image.setProduct(productRepo.findById(imageRequestDtos.get(i).getProductId()).orElse(null));
            image.setName(imageRequestDtos.get(i).getImageName());
            image.setImageLink(imageRequestDtos.get(i).getImageLink());
            image.setImageType(imageRequestDtos.get(i).getImageType());

            // Nếu là ảnh đầu tiên, đặt giá trị Status.DANG_HOAT_DONG
            if (i == 0) {
                image.setStatus(Status.DANG_HOAT_DONG);
            } else {
                // Nếu không phải ảnh đầu tiên, kiểm tra xem có giá trị Status được cung cấp hay không
                if (imageRequestDtos.get(i).getStatus() != null) {
                    image.setStatus(imageRequestDtos.get(i).getStatus());
                } else {
                    // Nếu không có giá trị Status được cung cấp, đặt giá trị mặc định là Status.NGUNG_HOAT_DONG
                    image.setStatus(Status.NGUNG_HOAT_DONG);
                }
            }

            images.add(image);
        }

        imageRepo.saveAll(images);
        return true;
    }

    @Override
    public Boolean updateImage(ImageRequestDto imageRequestDto, Long id) {
        Imege image = imageRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Không tìm thấy id ảnh này!"));

        image.setName(imageRequestDto.getImageName());
        image.setImageLink(imageRequestDto.getImageLink());
        image.setImageType(imageRequestDto.getImageType());

        imageRepo.save(image);
        return true;
    }

    @Override
    public Boolean deleteImage(Long id) {
        Imege image = imageRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Không tìm thấy id ảnh này!"));
        imageRepo.deleteById(image.getId());
        return true;
    }

    @Override
    public List<Imege> findImageByProductId(Long productId) {

        List<Imege> imageList = imageRepo.findImegeByIdCtsp(productId);

        return imageList;
    }


    @Override
    public List<Imege> findByImageLink(String imageLink) {
        return imageRepo.findByImageLink(imageLink);
    }
//    @Override
//    public void save(Imege object) {
//        imageRepo.save(object);
//    }
//
//    @Override
//    public void update(Imege object, Long id) {
//        imageRepo.save(object);
//    }
//
//    @Override
//    public void delete(Long id) {
//        imageRepo.delete(id);
//    }
//
//    @Override
//    public void search(Long id) {
//        imageRepo.findById(id).orElse(null);
//    }
//
//    @Override
//    public List<Imege> select() {
//        return imageRepo.findAll();
//    }
}
