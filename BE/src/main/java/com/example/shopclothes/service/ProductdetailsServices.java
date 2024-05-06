package com.example.shopclothes.service;

import com.example.shopclothes.dto.ProductDeatilsDTO;
import com.example.shopclothes.dto.ProductDetailFilterRequestDto;
import com.example.shopclothes.dto.ProductDetailRequestDto;
import com.example.shopclothes.dto.ProductDetailResponseDto;
import com.example.shopclothes.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductdetailsServices {

//    public List<ProductDeatilsDTO> getAll();

    public List<ProductDetail> detailByIdSP(Long id);

    public void update(Integer quantity, Long id);

    public ProductDetail add(ProductDetail chiTietSanPham);

    public Boolean createProductDetails(List<ProductDetailRequestDto> productDetailRequestDtos);

    public Boolean updateProductDetail(ProductDetailRequestDto requestDto, Long id);

    public Page<ProductDetailResponseDto> findAllByProductId(Long id, Pageable pageable);

    public Boolean deleteProduct(Long id);

    Page<ProductDetailResponseDto> getProductDetails(ProductDetailFilterRequestDto requestDto);
}
