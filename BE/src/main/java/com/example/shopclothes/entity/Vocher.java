package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.Status;
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
@Table(name = "Vocher")
public class Vocher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "dateCreate")
    private Date dateCreate;

    @Column(name = "dateUpdate")
    private Date dateUpdate;

    @Column(name = "peplerCreate")
    private String peplerCreate;

    @Column(name = "peopleUpdate")
    private String peopleUpdate;

    @Column(name = "status")
    private Status status;

    @Column(name = "describe")
    private String describe;

    @Column(name = "startTime")
    private Date startTime;

    @Column(name = "endTime")
    private Date endTime;

    @Column(name = "minMoney")
    private BigDecimal minMoney;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vocher")
    List<VocherDetail> vocherDetails;
}
