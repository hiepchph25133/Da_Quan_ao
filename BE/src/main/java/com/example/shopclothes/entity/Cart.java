package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Cart")

public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "dateCreate")
    private Date dateCreate;

    @Column(name = "datePayment")
    private Date datePayment;

    @Column(name = "status")
    private Status status;

    @Column(name = "nameReceiver")
    private String nameReceiver;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idCart")
    List<CartDetail> cartDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAcc")
    private Account idAcc;
}
