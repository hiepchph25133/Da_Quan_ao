package com.example.shopclothes.dto;

import com.example.shopclothes.entity.propertis.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserFilterRequestDto {
    private String keyword;
    private Date birthday;
    private Boolean sex;
    private Status status;
    private Integer pageNo;
    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo != null ? pageNo : 0; // Thiết lập giá trị mặc định nếu null
    }

    public Integer getPageSize() {
        return pageSize != null ? pageSize : 10; // Thiết lập giá trị mặc định nếu null
    }
}
