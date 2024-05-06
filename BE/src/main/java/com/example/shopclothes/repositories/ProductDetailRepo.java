package com.example.shopclothes.repositories;

import com.example.shopclothes.dto.ProductDeatilsDTO;
import com.example.shopclothes.dto.ProductDetailResponseDto;
import com.example.shopclothes.entity.ProductDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository

public interface ProductDetailRepo extends JpaRepository<ProductDetail, Long> {



    @Query(value = "select sp from ProductDetail sp where sp.idProduct.id = :id")
    List<ProductDetail> detailByIdSP(Long id);

    @Transactional
    @Modifying
    @Query(value = "update ProductDetail c set c.quantity = :quantity where c.id = :id")
    void update(Integer quantity, Long id);

    @Query("SELECT new com.example.shopclothes.dto.ProductDetailResponseDto(  pd.id, p.productName,i.imageLink, c.colorName,s.sizeName,m.materialName,pd.quantity,pd.price, pd.status )" +
            "from ProductDetail pd " +
            "join Product p on pd.idProduct.id = p.id " +
            "join Imege i on i.product.id = p.id " +
            "join Color c on pd.idColor.id  = c.id " +
            "join Size s on pd.idSize.id = s.id " +
            "join Material m on pd.idMaterial.id = m.id " +
            "where pd.idProduct.id = :productId " +
            "and i.status = 'DANG_HOAT_DONG' " +
            "group by pd.id, i.imageLink, p.productName, c.colorName, m.materialName,s.sizeName,pd.quantity,pd.price, pd.status "+
            "ORDER BY pd.dateUpdate DESC")
    Page<ProductDetailResponseDto> findAllByProductId(@Param("productId") Long productId, Pageable pageable);

    @Query("SELECT new com.example.shopclothes.dto.ProductDetailResponseDto(" +
            "pd.id, p.productName, i.imageLink, c.colorName, s.sizeName, m.materialName, pd.quantity, pd.price, pd.status) " +
            "FROM ProductDetail pd " +
            "JOIN pd.idProduct p " +
            "JOIN pd.idColor c " +
            "JOIN pd.idSize s " +
            "JOIN pd.idMaterial m " +
            "JOIN Imege i ON i.product.id = p.id AND i.status = 'DANG_HOAT_DONG' " +
            "WHERE (:colorId IS NULL OR pd.idColor.id = :colorId) " +
            "AND (:sizeId IS NULL OR pd.idSize.id = :sizeId) " +
            "AND (:materialId IS NULL OR pd.idMaterial.id = :materialId) " +
            "AND (:priceMin IS NULL OR pd.price >= :priceMin) " +
            "AND (:priceMax IS NULL OR pd.price <= :priceMax) " +
            "AND (:categoryId IS NULL OR p.idCategory.id = :categoryId) " +
            "AND ((:keyword IS NULL) OR (p.productName LIKE %:keyword%) OR CAST(p.id AS STRING) = :keyword) " +
            "AND pd.status = 'DANG_HOAT_DONG' " +
            "ORDER BY pd.dateCreate DESC")
    Page<ProductDetailResponseDto> getProductDetails(
            @Param("colorId") Long colorId,
            @Param("sizeId") Long sizeId,
            @Param("materialId") Long materialId,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax,
            @Param("categoryId") Long categoryId,
            @Param("keyword") String keyword,
            Pageable pageable);



}
