package com.example.shopclothes.service;

import com.example.shopclothes.dto.VoucherFilterRequestDto;
import com.example.shopclothes.dto.VoucherRequestDto;
import com.example.shopclothes.entity.Vocher;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VocherServiceIPL {

    public List<Vocher> select();

    public Page<Vocher> getVoucherByFilter(VoucherFilterRequestDto requestDto);

    public Boolean createVoucher(VoucherRequestDto voucherRequestDto);

    public Boolean deleteVoucher(Long id);

    public Boolean UpdateVocher(VoucherRequestDto DTO, Long id);

    public Vocher findByVocher(Long id);

    public Vocher findByVoucherCode(String code);
}
