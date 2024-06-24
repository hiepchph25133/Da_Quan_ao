package com.example.shopclothes.dto;


import com.example.shopclothes.entity.propertis.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VoucherRequestDto {

    @JsonIgnore
    private Long id;

    @NotBlank(message = "Mã giảm giá không được để trống!")
    private String code;

    @NotBlank(message = "Tên giảm giá không được để trống!")
    private String name;

    private Date startTime;

    private Date endTime;

    @NotNull(message = "Đơn tối thiểu không được để trống!")
    private Integer orderMinimum;  //Đơn tối thiểu

    @NotNull(message = "Giảm tối đa không được để trống!")
    private Integer maxReduce;  //Giảm tối đa

    @NotNull(message = "Số lượng không được để trống!")
    private Integer quantity;

    @NotNull(message = "Tỉ lệ chiết khấu không được để trống!")
    private Integer discountRate;


    private Integer status;

    private Integer deleted; // Sửa lại kiểu dữ liệu của deleted thành Status
}