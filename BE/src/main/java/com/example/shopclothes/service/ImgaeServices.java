package com.example.shopclothes.service;

import com.example.shopclothes.dto.ImageRequestDto;
import com.example.shopclothes.entity.Imege;

import java.util.List;

public interface ImgaeServices {
    public List<Imege> getImages();

    public Boolean createImages(List<ImageRequestDto> imageRequestDtos);

    public Boolean updateImage(ImageRequestDto imageRequestDto, Long id);

    public Boolean deleteImage(Long id);

    public List<Imege> findImageByProductId(Long productId);

    public List<Imege> findByImageLink(String imageLink);
}
