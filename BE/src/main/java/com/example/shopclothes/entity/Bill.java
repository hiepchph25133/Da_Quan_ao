package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.StatusBill;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Bill")

public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "datePayment")
    private Date datePayment;

    @Column(name = "totalMoney")
    private BigDecimal totalMoney;

    @Column(name = "status")
    private StatusBill status;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "dateShip")
    private Date dateShip;

    @Column(name = "moneyShip")
    private BigDecimal moneyShip;

    @Column(name = "dateCreate")
    private Date dateCreate;

    @Column(name = "dateUpdate")
    private Date dateUpdate;

    @Column(name = "peopleCreate")
    private String peopleCreate;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "dateReceiver")
    private Date dateReceiver;

    @Column(name = "note")
    private String note;

    @Column(name = "billCategory")
    private int billCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User idUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idBill")
    List<VocherDetail> vocherDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idBill")
    List<BillDetail> billDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idBill")
    List<BillHistory> billHistories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idBill")
    List<PaymentsDetail> paymentsDetails;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idBill")
    List<Return> returns;
}


