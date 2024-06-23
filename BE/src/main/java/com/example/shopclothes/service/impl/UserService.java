package com.example.shopclothes.service.impl;

import com.example.shopclothes.dto.UserFilterRequestDto;
import com.example.shopclothes.dto.UserRequestDto;
import com.example.shopclothes.dto.UserResponseDto;
import com.example.shopclothes.entity.User;
import com.example.shopclothes.repositories.UserRepo;
import com.example.shopclothes.service.UserServiceIPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service

public class UserService implements UserServiceIPL {

    @Autowired
    private UserRepo userRepository;

//    @Autowired
//    private  PasswordEncoder passwordEncoder;


    public Page<UserResponseDto> getUsersByFilter(UserFilterRequestDto requestDto) {
        Pageable pageable = PageRequest.of(requestDto.getPageNo(), requestDto.getPageSize());
        String formattedBirthday = formatDate(requestDto.getBirthday());
        Page<User> userPage = userRepository.getUsersByFilter(
                requestDto.getKeyword(),
                formattedBirthday,
                requestDto.getSex(),
                requestDto.getStatus(),
                pageable
        );
        return userPage.map(this::mapUserToDto);
    }

    private UserResponseDto mapUserToDto(User user) {
        return new UserResponseDto(user.getId(),
                user.getUsersName(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getSex(),
                user.getBirthday(),
                user.getStatus(),
                user.getDateCreate());

    }

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return date != null ? formatter.format(date) : null;
    }

//    @Override
//    public Boolean createUser(UserRequestDto requestDto) {
//
////        List<Role> roleList = roleRepository.findAllByRoleNameIn(requestDto.getRoleList());
//
//        User user = new User();
//        user.setUsersName(requestDto.getUserName());
//        user.setPhoneNumber(requestDto.getPhoneNumber());
//        user.setEmail(requestDto.getEmail());
//        user.setSex(requestDto.getGender());
//        user.setBirthday(requestDto.getBirthOfDay());
//        if (requestDto.getPassword() != null) {
//            user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
//        }
//        user.setStatus(requestDto.getStatus());
//        user.setRole(requestDto.getRole());
//
//        userRepository.save(user);
//        return true;
//    }
}
