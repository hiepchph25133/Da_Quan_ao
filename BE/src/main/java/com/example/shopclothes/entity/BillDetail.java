package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.StatusBill;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BillDetail")

public class BillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status")
    private StatusBill status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBill")
    private Bill idBill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCtsp")
    private ProductDetail idCtsp;

}

