package com.example.shopclothes.service.impl;

import com.example.shopclothes.dto.VoucherFilterRequestDto;
import com.example.shopclothes.dto.VoucherRequestDto;
import com.example.shopclothes.entity.Vocher;
import com.example.shopclothes.exception.ResourceNotFoundException;
import com.example.shopclothes.repositories.VocherRepo;
import com.example.shopclothes.service.IService;
import com.example.shopclothes.service.VocherServiceIPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class VocherService implements VocherServiceIPL {

    @Autowired
    private VocherRepo vocherRepo;

    @Override
    public List<Vocher> select() {
        return vocherRepo.findAll();
    }



    @Override
    public Page<Vocher> getVoucherByFilter(VoucherFilterRequestDto requestDto) {
        // Kiểm tra nếu pageNo hoặc pageSize là null, sử dụng giá trị mặc định
        int pageNo = requestDto.getPageNo() != null ? requestDto.getPageNo() : 0;
        int pageSize = requestDto.getPageSize() != null ? requestDto.getPageSize() : 10;

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return vocherRepo.getVoucherByFilter(
                requestDto.getKeyword(),
                requestDto.getCreatedAtStart(),
                requestDto.getCreatedAtEnd(),
                requestDto.getStatus(),
                pageable);
    }

    @Override
    public Boolean createVoucher(VoucherRequestDto voucherRequestDto) {

        Vocher voucher = new Vocher();
        mapToVoucherRequest(voucher, voucherRequestDto);
        vocherRepo.save(voucher);
        return true;
    }

    private   Vocher mapToVoucherRequest(Vocher voucher, VoucherRequestDto voucherRequestDto){

        voucher.setCode(voucherRequestDto.getCode());
        voucher.setName(voucherRequestDto.getName());
        voucher.setStartTime(voucherRequestDto.getStartTime());
        voucher.setEndTime(voucherRequestDto.getEndTime());
        voucher.setQuantity(voucherRequestDto.getQuantity());
        voucher.setMaxReduce(voucherRequestDto.getMaxReduce());
        voucher.setOrderMinimum(voucherRequestDto.getOrderMinimum());
        voucher.setDiscountRate(voucherRequestDto.getDiscountRate());
        voucher.setStatus(voucherRequestDto.getStatus());
        voucher.setStatus(voucherRequestDto.getDeleted());

        return voucher;
    }

    @Override
    public Boolean deleteVoucher(Long id) {
        Vocher voucher = vocherRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id giảm giá này!"));

        voucher.setStatus(voucher.getStatus() == 0 ? 1 : 0);

        vocherRepo.save(voucher);

        return true;
    }

        @Override
    public Boolean UpdateVocher(VoucherRequestDto DTO, Long id){
        Vocher voucher = vocherRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id giảm giá này!"));
        mapVocherRequest(voucher,DTO);
        vocherRepo.save(voucher);
        return true;
    }

    public Vocher mapVocherRequest(Vocher vocher,VoucherRequestDto DTO){
            vocher.setCode(DTO.getCode());
        vocher.setName(DTO.getName());
        vocher.setStartTime(DTO.getStartTime());
        vocher.setEndTime(DTO.getEndTime());
        vocher.setMaxReduce(DTO.getMaxReduce());
        vocher.setQuantity(DTO.getQuantity());
        vocher.setDiscountRate(DTO.getDiscountRate());
        vocher.setOrderMinimum(DTO.getOrderMinimum());
        vocher.setStatus(DTO.getStatus());
        return vocher;
    }

    @Override
    public Vocher findByVocher(Long id){
        Vocher vocher = vocherRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("hóa đơn"));
        return vocher;
    }

    @Override
    public Vocher findByVoucherCode(String code) {
        return vocherRepo.findByCode(code);
    }

}
