package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.StatusBill;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BillHistory")

public class BillHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "peopleCreate")
    private String peopleCreate;

    @Column(name = "status")
    private StatusBill status;

    @Column(name = "note")
    private String note;

    @Column(name = "dateCreate")
    private Date dateCreate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBill")
    private Bill idBill;
}
