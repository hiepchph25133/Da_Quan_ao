package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.Propertis;
import com.example.shopclothes.entity.propertis.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Color")
public class Color  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String colorName;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "ghi_chu")
    private String ghi_chu;

    @Column(name = "dateUpdate")
    private Date dateUpdate;

    @Column(name = "dateCreate")
    private Date dateCreate;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idColor")
    List<ProductDetail> productDetails;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idColor")
//    List<ProductDetail> productDetails;

}
