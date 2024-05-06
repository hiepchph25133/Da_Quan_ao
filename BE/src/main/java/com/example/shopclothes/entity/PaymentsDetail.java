package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.StatusPayment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PaymentsDetail")
public class PaymentsDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "PaymentDetail")
    private StatusPayment status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPayment")
    private Payments payments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBill")
    private Bill idBill;
}
