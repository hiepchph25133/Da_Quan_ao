package com.example.shopclothes.service;

import com.example.shopclothes.dto.AddressRequestDto;
import com.example.shopclothes.entity.Address;

import java.util.List;

public interface AddressServiceIPL {

    public Address findAddressesByUserIdAnDeletedTrue(Long userId);

    public List<Address> getAddressesByUserId(Long userId);

    public Boolean createAddress(AddressRequestDto addressRequestDto);

    public Boolean deleteAddress(Long id);

    public Boolean updateAddress(Long id, AddressRequestDto addressRequestDto);
}
