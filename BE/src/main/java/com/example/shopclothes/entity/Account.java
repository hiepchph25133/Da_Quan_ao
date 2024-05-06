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
@Table(name = "Account")
public class Account {

    @Id
    @Column(name = "userName")
    private String userName;

    @Column(name = "code")
    private String code;

    @Column(name = "dateCreate")
    private Date dateCreate;

    @Column(name = "dateUpdate")
    private Date dateUpdate;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRole")
    private Role idRole;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "idAcc")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idAcc")
    List<Return> returns;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idAcc")
    List<CartDetail> cartDetails;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "idAcc")
    private Cart idCart;


}
