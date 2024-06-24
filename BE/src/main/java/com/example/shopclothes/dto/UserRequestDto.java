package com.example.shopclothes.dto;

import com.example.shopclothes.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

    @JsonIgnore
    private Long id;

    private String userName;

    private String phoneNumber;

    private String email;

    private Boolean gender;

    private Date birthOfDay;

    private String password;

    private Boolean status;

    private Role role;

}
