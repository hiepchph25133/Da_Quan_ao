package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "VocherDetail")

public class VocherDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVor")
    private Vocher vocher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBill")
    private Bill idBill;

}
