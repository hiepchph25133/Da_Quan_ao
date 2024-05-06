package com.example.shopclothes.dto;

import com.example.shopclothes.entity.propertis.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageRequestDto {

    private Long productId;

    private String imageName;

    private String imageLink;

    private String imageType;

    private Status status;
}
