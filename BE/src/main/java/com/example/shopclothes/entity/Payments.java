package com.example.shopclothes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Payment")
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order orders;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "amount")
    private Double amount ;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "note")
    private String note;

    @Column(name = "payment_status")
    private String status;


    @CreatedDate
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by",updatable = false)
    private String  createdBy;

    @LastModifiedDate
    @Column(name = "update_date")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;


    @Column(name = "code")
    private String code;

//    @Column(name = "status")
//    private String status;
//
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "payments")
    List<PaymentsDetail> paymentsDetails;
//
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orders;
}
