package com.example.shopclothes.controller;

import com.example.shopclothes.constants.NotificationConstants;
import com.example.shopclothes.dto.*;
import com.example.shopclothes.entity.Vocher;
import com.example.shopclothes.service.impl.VocherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/vouchers/")
@Tag(name = "Vouchers", description = "( Rest API Hiển thị, thêm, sửa, xóa giảm giá )")
@Validated

public class VocherController {

    @Autowired
    private VocherService vocherService;


    @GetMapping("findAllVoucherByDeletedTrue")
    public ResponseEntity<List<Vocher>> findAllVoucherByDeletedTrue() {
        List<Vocher> voucherList = vocherService.select();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(voucherList);
    }


    @PostMapping("getVoucherByFilter")
    public ResponseEntity<?> getVoucherByFilter(@RequestBody VoucherFilterRequestDto requestDto) {


        Page<Vocher> voucherPage = vocherService.getVoucherByFilter(requestDto);

        List<VoucherResponseDto> voucherResponseDtoList = voucherPage.getContent().stream().map(VoucherResponseDto::new).collect(Collectors.toList());

        return ResponseHandler.generateResponse(HttpStatus.OK, voucherResponseDtoList, voucherPage);
    }

    @PostMapping("create")
    public ResponseEntity<ResponseDto> createVoucher(@Valid @RequestBody VoucherRequestDto voucherRequestDto) {
        Boolean isCreated = vocherService.createVoucher(voucherRequestDto);

        if (isCreated) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto(NotificationConstants.STATUS_201, NotificationConstants.MESSAGE_201));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(NotificationConstants.STATUS_500, NotificationConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("delete")
    public ResponseEntity<ResponseDto> deleteVoucher(@RequestParam Long id) {
        Boolean isDeleted = vocherService.deleteVoucher(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(NotificationConstants.STATUS_200, NotificationConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(NotificationConstants.STATUS_500, NotificationConstants.MESSAGE_500));
        }
    }

    @PutMapping("update")
    public ResponseEntity<ResponseDto> updateVocher(@RequestBody VoucherRequestDto DTO,@RequestParam Long id){
        Boolean isupdate = vocherService.UpdateVocher(DTO,id);

        if (isupdate){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(NotificationConstants.STATUS_200, NotificationConstants.MESSAGE_200));
        }else {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(NotificationConstants.STATUS_500,NotificationConstants.MESSAGE_500));
        }
    }


    @GetMapping("findVoucherById")
    public ResponseEntity<Vocher> findVoucherById(@RequestParam Long id){
        Vocher vocher = vocherService.findByVocher(id);
        return  ResponseEntity.status(HttpStatus.OK).body(vocher);

    }

    @GetMapping("findByVoucherCode")
    public ResponseEntity<Vocher> findByVoucherCode(@RequestParam String code) {
        Vocher  voucher= vocherService.findByVoucherCode(code);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(voucher);
    }

}
