package com.example.shopclothes.dto;

import com.example.shopclothes.entity.Vocher;
import com.example.shopclothes.entity.propertis.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.sql.Date;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoucherResponseDto {

    private Long id;

    private String code;

    private String name;

    private Date startTime;

    private Date endTime;

    private Integer orderMinimum;

    private Integer maxReduce;

    private Integer quantity;

    private Integer discountRate;

    private String note;

    private Integer deleted;

    private Integer status;


    public VoucherResponseDto( VoucherRequestDto requestDto) {
        BeanUtils.copyProperties(requestDto, this);
    }
    public VoucherResponseDto(Vocher voucher) {
        BeanUtils.copyProperties(voucher, this);
    }
}
