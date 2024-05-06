//package com.example.shopclothes.controller;
//
//import com.example.shopclothes.dto.ResponseHandler;
//import com.example.shopclothes.dto.UserFilterRequestDto;
//import com.example.shopclothes.dto.UserResponseDto;
//import com.example.shopclothes.entity.User;
//import com.example.shopclothes.service.UserServiceIPL;
//import com.example.shopclothes.service.impl.UserService;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:3000")
//@RestController
//@Validated
//@Tag(name = "Users",description = "( Rest API Hiển thị, thêm, sửa, xóa, tìm kiếm, phân trang nhân viên )")
//@RequestMapping("/api/v1/users/")
//public class UserController {
//
//    @Autowired
//    private UserServiceIPL userService;
//
//
//    @PostMapping("getAllUserByFilter")
////    @PreAuthorize("hasAuthority('admin:read')")
//    public ResponseEntity<?> getUsers(@RequestBody UserFilterRequestDto requestDto){
//
//
//        Page<UserResponseDto>  userResponseDtoPage = userService.getUsersByFilter(requestDto);
//
//        List<UserResponseDto> userResponseDtoList = userResponseDtoPage.getContent();
//
//        return ResponseHandler.generateResponse(HttpStatus.OK,userResponseDtoList,userResponseDtoPage);
//    }
//
//
//}
