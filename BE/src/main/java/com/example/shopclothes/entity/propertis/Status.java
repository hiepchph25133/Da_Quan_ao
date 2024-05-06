package com.example.shopclothes.entity.propertis;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = StatusSerializer.class)
public enum Status {
    @JsonProperty("DANG_HOAT_DONG")
    DANG_HOAT_DONG,
    NGUNG_HOAT_DONG
}
