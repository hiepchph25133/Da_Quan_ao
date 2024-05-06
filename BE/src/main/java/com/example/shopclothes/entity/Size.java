package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.Propertis;
import com.example.shopclothes.entity.propertis.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Size")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String sizeName;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "ghi_chu")
    private String ghi_chu;

    @Column(name = "dateUpdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateUpdate;

    @Column(name = "dateCreate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCreate;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idSize")
    List<ProductDetail> productDetails;
}
