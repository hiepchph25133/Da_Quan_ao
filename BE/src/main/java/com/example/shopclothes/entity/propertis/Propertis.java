package com.example.shopclothes.entity.propertis;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Propertis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "dateUpdate")
    private Date dateUpdate;

    @Column(name = "dateCreate")
    private Date dateCreate;
}
