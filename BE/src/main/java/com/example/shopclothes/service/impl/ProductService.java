package com.example.shopclothes.service.impl;

import com.example.shopclothes.dto.ProductDetailFilterRequestDto;
import com.example.shopclothes.dto.ProductFilterResponseDto;
import com.example.shopclothes.dto.ProductRequestDto;
import com.example.shopclothes.entity.Category;
import com.example.shopclothes.entity.Producer;
import com.example.shopclothes.entity.Product;
import com.example.shopclothes.entity.ProductDetail;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.exception.ResourceNotFoundException;
import com.example.shopclothes.repositories.*;
import com.example.shopclothes.service.IService;
import com.example.shopclothes.service.ProducerServices;
import com.example.shopclothes.service.ProductServices;
import com.google.api.gax.rpc.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class ProductService implements ProductServices {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private MaterielRepo materielRepo;

    @Autowired
    private ProducerRepo producerRepo;

    @Autowired
    private ProductDetailRepo productDetailRepo;

    @Override
    public List<Product> finByName() {
        return productRepo.findByName();
    }


    @Override
    public Page<ProductFilterResponseDto> findProductsAdminByFilters(ProductDetailFilterRequestDto requestDto, Pageable pageable) {
        return productRepo.findProductsAdminByFilters(requestDto.getColorId(),
                requestDto.getSizeId(),
                requestDto.getMaterialId(),
                requestDto.getPriceMin(),
                requestDto.getPriceMax(),
                requestDto.getCategoryId(),
                requestDto.getKeyword(),
                pageable);
    }


    @Override
    public Product add(Product sanPham) {
        return productRepo.save(sanPham);
    }

    @Override
    public Product detail(Long id) {
        return productRepo.findById(id).orElse(null);
    }


//    private Product mapToProductRequest(ProductRequestDto productRequestDto, Product product) {
//
//        product.setProductName(productRequestDto.getProductName());
//        Optional<Category> categoryOptional = categoryRepo.findByCategoryName(productRequestDto.getCategoryName());
//        categoryOptional.ifPresent(category -> {
//            product.setIdCategory(category);
//            // Các thao tác khác với category (nếu cần)
//        });
////        product.setIdCategory(categoryRepo.findByCategoryName(productRequestDto.getCategoryName()));
//        product.setIdProducer(producerRepo.findByProducerName(productRequestDto.getProductName()));
//        product.setStatus(Status.DANG_HOAT_DONG);
//
//        product.setDiscribe(productRequestDto.getProductDescribe());
//
//        return product;
//    }

    private Product mapToProductRequest(ProductRequestDto productRequestDto, Product product) {
        product.setProductName(productRequestDto.getProductName());
//        System.out.println("CategoryName from ProductRequestDto: " + productRequestDto.getCategoryName());

        Optional<Category> categoryOptional = categoryRepo.findByCategoryName(productRequestDto.getCategoryName());
        Category category = categoryOptional.get();
        product.setIdCategory(category);
//        Producer
        Optional<Producer> producerOptional = producerRepo.findByProducerName(productRequestDto.getProducerName());
        System.out.println("producerName from ProductRequestDto: " + productRequestDto.getProducerName());
        Producer producer = producerOptional.get();
        product.setIdProducer(producer);
//        product.setIdProducer(producerRepo.findByProducerName(productRequestDto.getProductName()));
        product.setStatus(Status.DANG_HOAT_DONG);
        product.setDiscribe(productRequestDto.getProductDescribe());

        return product;
    }

    @Override
    public Product createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        mapToProductRequest(productRequestDto,product);

        productRepo.save(product);
        return product;
    }

    @Override
    public Boolean updateProduct(ProductRequestDto productRequestDto, Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id sản phẩm này!"));

        mapToProductRequest(productRequestDto,product);

        productRepo.save(product);
        return true;
    }

    @Override
    public Product findProductById(Long productId) {
        return productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Không tìm thấy ihaamr này!"));

    }

    @Override
    public Boolean deleteProduct(Long id) {

        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id sản phẩm này!"));

        product.setStatus(product.getStatus() == Status.DANG_HOAT_DONG ? Status.NGUNG_HOAT_DONG : Status.DANG_HOAT_DONG);
        productRepo.save(product);
        return true;
    }

    @Override
    public List<Product> findAllByDeletedTrue() {
        return productRepo.findAllByDeletedTrue();
    }
}
