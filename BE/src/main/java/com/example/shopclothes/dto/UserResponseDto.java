package com.example.shopclothes.dto;

import com.example.shopclothes.entity.propertis.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;

    private String usersName;

    private String phoneNumber;

    private String email;

    private Boolean sex;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date birthday;

    private Status status;

    private LocalDateTime dateCreate;
}
