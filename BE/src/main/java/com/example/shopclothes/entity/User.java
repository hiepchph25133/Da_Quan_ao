package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.Propertis;
import com.example.shopclothes.entity.propertis.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "User")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String usersName;

    @Column(name = "sex")
    private Boolean sex;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "birthday")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date birthday;

    @Column(name = "password")
    private String password;


    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

//    @Column(name = "status")
//    private Boolean status = false;

    @Column(name = "dateUpdate")
    private LocalDateTime dateUpdate;

    @Column(name = "dateCreate")
    private LocalDateTime dateCreate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAcc")
    private Account idAcc;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idUser")
    List<Address> addresses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idUser")
    List<Bill> bills;

//    @Enumerated(EnumType.STRING)
//    private Role role;

}
