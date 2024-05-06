package com.example.shopclothes.service;

import com.example.shopclothes.dto.ProductDetailFilterRequestDto;
import com.example.shopclothes.dto.ProductFilterResponseDto;
import com.example.shopclothes.dto.ProductRequestDto;
import com.example.shopclothes.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductServices {
    public Product add(Product sanPham);

    public Product detail(Long id);

    public List<Product> finByName();

    public Page<ProductFilterResponseDto> findProductsAdminByFilters(ProductDetailFilterRequestDto requestDto, Pageable pageable);

    public Product createProduct(ProductRequestDto productRequestDto);

    public Boolean updateProduct(ProductRequestDto productRequestDto, Long id);

    public Product findProductById(Long productId);

    public Boolean deleteProduct(Long id);

    public List<Product> findAllByDeletedTrue();
}
