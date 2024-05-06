package com.example.shopclothes.service.impl;

import com.example.shopclothes.dto.*;
import com.example.shopclothes.entity.*;
import com.example.shopclothes.entity.propertis.Status;
import com.example.shopclothes.exception.ResourceNotFoundException;
import com.example.shopclothes.repositories.*;
import com.example.shopclothes.service.IService;
import com.example.shopclothes.service.ProductdetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class ProductDetailService implements ProductdetailsServices {

    @Autowired
    private ProductDetailRepo productDetailRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ColorRepo colorRepo;

    @Autowired
    private SizeRepo sizeRepo;

    @Autowired
    private MaterielRepo materielRepo;




    @Override
    public List<ProductDetail> detailByIdSP(Long id) {
        return productDetailRepo.detailByIdSP(id);
    }

    @Override
    public void update(Integer quantity, Long id) {
        productDetailRepo.update(quantity, id);
    }

    @Override
    public ProductDetail add(ProductDetail chiTietSanPham) {
        return productDetailRepo.save(chiTietSanPham);
    }

    @Override
    public Boolean createProductDetails(List<ProductDetailRequestDto> productDetailRequestDtos) {
        List<ProductDetail> productDetails = new ArrayList<>();

        for (ProductDetailRequestDto productDetailRequestDto : productDetailRequestDtos) {
            ProductDetail productDetail = new ProductDetail();

            productDetail.setIdProduct(productRepo.findById(productDetailRequestDto.getProductId()).orElse(null));
//            Color
            Optional<Color> ColorOptional = colorRepo.findFirstByColorName(productDetailRequestDto.getColorName());
            Color color = ColorOptional.get();
            productDetail.setIdColor(color);

//            productDetail.setIdColor(colorRepo.findFirstByColorName(productDetailRequestDto.getColorName()));
//            Size
                Optional<Size> SizeOptional = sizeRepo.findFirstBySizeName(productDetailRequestDto.getSizeName());
            Size size = SizeOptional.get();
            productDetail.setIdSize(size);
//            productDetail.setIdSize(sizeRepo.findFirstBySizeName(productDetailRequestDto.getSizeName()));
//            Chat lieu
            Optional<Material> MateOptional = materielRepo.findFirstByMaterialName(productDetailRequestDto.getMaterialName());
            Material mate = MateOptional.get();
            productDetail.setIdMaterial(mate);

//            productDetail.setIdMaterial(materielRepo.findFirstByMaterialName(productDetailRequestDto.getMaterialName()));
            productDetail.setQuantity(productDetailRequestDto.getQuantity());
            productDetail.setPrice(productDetailRequestDto.getPrice());
            productDetail.setStatus(Status.DANG_HOAT_DONG);

            productDetails.add(productDetail);
        }

        productDetailRepo.saveAll(productDetails);
        return true;
    }

    @Override
    public Boolean updateProductDetail(ProductDetailRequestDto requestDto, Long id) {
        ProductDetail productDetail = productDetailRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id sản phẩm chi tiết này!"));

        productDetail.setIdProduct(productRepo.findById(requestDto.getProductId()).orElse(null));
//        Color
        Optional<Color> producerOptional = colorRepo.findFirstByColorName(requestDto.getColorName());
        System.out.println("ColorName from ProductRequestDto: " + requestDto.getColorName());
        Color color = producerOptional.get();
        productDetail.setIdColor(color);
//        productDetail.setIdColor(colorRepo.findFirstByColorName(requestDto.getColorName()));
//        Size
        Optional<Size> SizeOptional = sizeRepo.findFirstBySizeName(requestDto.getSizeName());
        Size size = SizeOptional.get();
        productDetail.setIdSize(size);

//        productDetail.setIdSize(sizeRepo.findFirstBySizeName(requestDto.getSizeName()));
//        Chat lieu
        Optional<Material> MateOptional = materielRepo.findFirstByMaterialName(requestDto.getMaterialName());
        Material mate = MateOptional.get();
        productDetail.setIdMaterial(mate);
//        productDetail.setIdMaterial(materielRepo.findFirstByMaterialName(requestDto.getMaterialName()));
        productDetail.setQuantity(requestDto.getQuantity());
        productDetail.setPrice(requestDto.getPrice());
        productDetail.setStatus(Status.DANG_HOAT_DONG);

        productDetailRepo.save(productDetail);
        return true;
    }

    @Override
    public Page<ProductDetailResponseDto> findAllByProductId(Long id, Pageable pageable) {
        return productDetailRepo.findAllByProductId(id,pageable);
    }

    @Override
    public Boolean deleteProduct(Long id) {

        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id sản phẩm này!"));

        product.setStatus(Status.NGUNG_HOAT_DONG);
        productRepo.save(product);
        return true;
    }

    @Override
    public Page<ProductDetailResponseDto> getProductDetails(ProductDetailFilterRequestDto requestDto) {

        Pageable pageable = PageRequest.of(requestDto.getPageNo(), requestDto.getPageSize());

        return productDetailRepo
                .getProductDetails(requestDto.getColorId(),
                        requestDto.getSizeId(),
                        requestDto.getMaterialId(),
                        requestDto.getPriceMin(),
                        requestDto.getPriceMax(),
                        requestDto.getCategoryId(),
                        requestDto.getKeyword(),
                        pageable);
    }
}
