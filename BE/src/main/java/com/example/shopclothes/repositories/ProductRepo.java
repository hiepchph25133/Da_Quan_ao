package com.example.shopclothes.repositories;

import com.example.shopclothes.dto.ProductFilterResponseDto;
import com.example.shopclothes.entity.Category;
import com.example.shopclothes.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository

public interface ProductRepo extends JpaRepository<Product, Long> {

    @Modifying
    @Transactional
    @Query(value = "update Product set status = 0 where id = ?1", nativeQuery = true)
    void delete(Long id);

    @Query("SELECT c FROM Product c WHERE c.status = 'DANG_HOAT_DONG' ORDER BY c.dateCreate DESC")
    List<Product> findByName();

    Boolean existsByProductName(String productName);

//    @Query("SELECT new com.example.shopclothes.dto( " +
//            "p.id,i.imageLink, p.name, c.name, s.name, p.discribe, SUM(pd.quantity), p.status, p.dateCreate, p.dateUpdate) " +
//            "FROM Product p " +
//            "JOIN Imege  i ON i.product.id = p.id " +
//            "JOIN ProductDetail pd ON pd.idProduct.id = p.id " +
//            "JOIN Category c ON c.id = p.idCategory.id " +
//            "JOIN Producer s ON s.id = p.idProducer.id " + // Fixed the join condition
//            "WHERE (:colorId IS NULL OR pd.idColor.id = :colorId) " +
//            "AND (:sizeId IS NULL OR pd.idSize.id = :sizeId) " +
//            "AND i.status = 'DANG_HOAT_DONG'  " +
//            "AND (:materialId IS NULL OR pd.idMaterial.id = :materialId) " +
//            "AND (:priceMin IS NULL OR pd.price >= :priceMin) " +
//            "AND (:priceMax IS NULL OR pd.price <= :priceMax) " +
//            "AND (:categoryId IS NULL OR p.idCategory.id = :categoryId) " +
//            "AND ((:keyword IS NULL) OR (p.name LIKE %:keyword%) OR CAST(p.id AS STRING) = :keyword) " +
//            "GROUP BY p.id,i.imageLink, p.name, c.name, s.name, p.discribe, p.status, p.dateCreate, p.dateUpdate " +
//            "ORDER BY p.dateCreate DESC ")
//    Page<ProductFilterResponseDto> findProductsAdminByFilters(
//            @Param("colorId") Long colorId,
//            @Param("sizeId") Long sizeId,
//            @Param("materialId") Long materialId,
//            @Param("brandId") Long brandId,
//            @Param("priceMin") Double priceMin,
//            @Param("priceMax") Double priceMax,
//            @Param("categoryId") Long categoryId,
//            @Param("keyword") String keyword,
//            Pageable pageable);

//    @Query("SELECT new com.example.shopclothes.dto.ProductFilterResponseDto(" +
//            "p.id, i.imageLink, p.name, c.name, s.name, p.discribe, SUM(pd.quantity) AS quantityTotal, p.status, p.dateCreate, p.dateUpdate) " +
//            "FROM Product p " +
//            "JOIN Imege i ON i.product.id = p.id " +
//            "JOIN ProductDetail pd ON pd.idProduct.id = p.id " +
//            "JOIN Category c ON c.id = p.idCategory.id " +
//            "JOIN Producer s ON s.id = p.idProducer.id " +
//            "WHERE (:colorId IS NULL OR pd.idColor.id = :colorId) " +
//            "AND (:sizeId IS NULL OR pd.idSize.id = :sizeId) " +
//            "AND i.status = 'DANG_HOAT_DONG' " +
//            "AND (:materialId IS NULL OR pd.idMaterial.id = :materialId) " +
//            "AND (:priceMin IS NULL OR pd.price >= :priceMin) " +
//            "AND (:priceMax IS NULL OR pd.price <= :priceMax) " +
//            "AND (:categoryId IS NULL OR p.idCategory.id = :categoryId) " +
//            "AND ((:keyword IS NULL) OR (p.name LIKE %:keyword%) OR CAST(p.id AS STRING) = :keyword) " +
//            "GROUP BY p.id, i.imageLink, p.name, c.name, s.name, p.discribe, p.status, p.dateCreate, p.dateUpdate " +
//            "ORDER BY p.dateCreate DESC")
//    Page<ProductFilterResponseDto> findProductsAdminByFilters(
//            @Param("colorId") Long colorId,
//            @Param("sizeId") Long sizeId,
//            @Param("materialId") Long materialId,
//            @Param("priceMin") Double priceMin,
//            @Param("priceMax") Double priceMax,
//            @Param("categoryId") Long categoryId,
//            @Param("keyword") String keyword,
//            Pageable pageable);

//    @Query("SELECT new com.example.shopclothes.dto.ProductFilterResponseDto(" +
//            "p.id, i.imageLink, p.productName, c.categoryName, s.producerName, p.discribe, SUM(pd.quantity) AS quantityTotal, p.status, p.dateCreate, p.dateUpdate) " +
//            "FROM Product p " +
//            "JOIN Imege i ON i.product.id = p.id " +
//            "JOIN ProductDetail pd ON pd.idProduct.id = p.id " +
//            "JOIN Category c ON c.id = p.idCategory.id " +
//            "JOIN Producer s ON s.id = p.idProducer.id " +
//            "WHERE (:colorId IS NULL OR pd.idColor.id = :colorId) " +
//            "AND (:sizeId IS NULL OR pd.idSize.id = :sizeId) " +
//            "AND i.status = 'DANG_HOAT_DONG' " +
//            "AND (:materialId IS NULL OR pd.idMaterial.id = :materialId) " +
//            "AND (:priceMin IS NULL OR pd.price >= :priceMin) " +
//            "AND (:priceMax IS NULL OR pd.price <= :priceMax) " +
//            "AND (:categoryId IS NULL OR p.idCategory.id = :categoryId) " +
//            "AND ((:keyword IS NULL) OR (p.productName LIKE %:keyword%) OR CAST(p.id AS STRING) = :keyword) " +
//            "GROUP BY p.id, i.imageLink, p.productName, c.categoryName, s.producerName, p.discribe, p.status, p.dateCreate, p.dateUpdate " +
//            "ORDER BY p.dateCreate DESC")
//    Page<ProductFilterResponseDto> findProductsAdminByFilters(
//            @Param("colorId") Long colorId,
//            @Param("sizeId") Long sizeId,
//            @Param("materialId") Long materialId,
//            @Param("priceMin") Double priceMin,
//            @Param("priceMax") Double priceMax,
//            @Param("categoryId") Long categoryId,
//            @Param("keyword") String keyword,
//            Pageable pageable);

    @Query("SELECT new com.example.shopclothes.dto.ProductFilterResponseDto(" +
            "p.id, i.imageLink, p.productName, c.categoryName, s.producerName, p.discribe, SUM(pd.quantity) AS quantityTotal, pd.price, p.status, p.dateCreate, p.dateUpdate) " +
            "FROM Product p " +
            "JOIN Imege i ON i.product.id = p.id " +
            "JOIN ProductDetail pd ON pd.idProduct.id = p.id " +
            "JOIN Category c ON c.id = p.idCategory.id " +
            "JOIN Producer s ON s.id = p.idProducer.id " +
            "WHERE (:colorId IS NULL OR pd.idColor.id = :colorId) " +
            "AND (:sizeId IS NULL OR pd.idSize.id = :sizeId) " +
            "AND i.status = 'DANG_HOAT_DONG' " +
            "AND (:materialId IS NULL OR pd.idMaterial.id = :materialId) " +
            "AND (:priceMin IS NULL OR pd.price >= :priceMin) " +
            "AND (:priceMax IS NULL OR pd.price <= :priceMax) " +
            "AND (:categoryId IS NULL OR p.idCategory.id = :categoryId) " +
            "AND ((:keyword IS NULL) OR (p.productName LIKE %:keyword%) OR CAST(p.id AS STRING) = :keyword) " +
            "GROUP BY p.id, i.imageLink, p.productName, c.categoryName, s.producerName,pd.price,p.discribe, p.status, p.dateCreate, p.dateUpdate " +
            "ORDER BY p.dateCreate DESC")
    Page<ProductFilterResponseDto> findProductsAdminByFilters(
            @Param("colorId") Long colorId,
            @Param("sizeId") Long sizeId,
            @Param("materialId") Long materialId,
            @Param("priceMin") Double priceMin,
            @Param("priceMax") Double priceMax,
            @Param("categoryId") Long categoryId,
            @Param("keyword") String keyword,
            Pageable pageable);


    @Query("SELECT p FROM Product p WHERE p.status =  'DANG_HOAT_DONG' ORDER BY p.dateCreate DESC")
    List<Product> findAllByDeletedTrue();


}
