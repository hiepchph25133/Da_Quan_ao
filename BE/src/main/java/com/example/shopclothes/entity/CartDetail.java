package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CartDetail")

public class CartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "totalMoney")
    private BigDecimal totalMoney;

    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCart")
    private Cart idCart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAcc")
    private Account idAcc;

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idCartDetail")
//    List<ProductDetail> productDetails;

    @ManyToOne
    @JoinColumn(name = "id_product_detail", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private ProductDetail productDetails;
}
