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
@Table(name = "Producer")
public class Producer  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "producer_name")
    private String producerName;

    @Column(name = "email")
    private String email;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "dia_chi")
    private String dia_chi;

    @Column(name = "ghi_chu")
    private String ghi_chu;

    @Column(name = "dateCreate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCreate;

    @Column(name = "dateUpdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateUpdate;




    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;


//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idProducer")
//    List<ProductDetail> productDetails;
}
