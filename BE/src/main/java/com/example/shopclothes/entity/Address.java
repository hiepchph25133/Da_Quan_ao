package com.example.shopclothes.entity;

import com.example.shopclothes.dto.AddressRequestDto;
import com.example.shopclothes.entity.propertis.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "wards")
    private String ward;

    @Column(name = "specificAddress")
    private String specificAddress;

    @Column(name = "dateCreate")
    private Date dateCreate;

    @Column(name = "DateUpdate")
    private Date DateUpdate;

//    @Column(name = "status")
//    private Status status;

    @Column(name = "status")
    private Boolean status = false;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    private User idUser;

    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "phone_number")
    private String phoneNumber;


}

