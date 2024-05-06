package com.example.shopclothes.service.impl;

import com.example.shopclothes.dto.AddressRequestDto;
import com.example.shopclothes.entity.Address;
import com.example.shopclothes.entity.User;
import com.example.shopclothes.exception.ResourceNotFoundException;
import com.example.shopclothes.repositories.AddressRepo;
import com.example.shopclothes.repositories.UserRepo;
import com.example.shopclothes.service.AddressServiceIPL;
import com.example.shopclothes.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements AddressServiceIPL {

    @Autowired
    private AddressRepo addressRepository;

    @Autowired
    private UserRepo userRepository;

    @Override
    public Address findAddressesByUserIdAnDeletedTrue(Long userId) {
        return addressRepository.findAddressesByUserIdAndStatusTrue(userId);
    }

    @Override
    public List<Address> getAddressesByUserId(Long userId) {
        return addressRepository.findByUserId(userId);
    }


    @Override
    public Boolean createAddress(AddressRequestDto addressRequestDto) {
        User user = userRepository.findById(addressRequestDto.getUsersId())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id người dùng này!"));

        Address address = new Address();
        address.setIdUser(user);
        if (addressRequestDto.getStatus() != null && addressRequestDto.getStatus()) {
            // Nếu deleted là true, cập nhật địa chỉ mặc định và set deleted
            addressRepository.updateDefaultAddress(user.getId());
            address.setStatus(true);
        }
        mapAddressRequestDtoToEntity(addressRequestDto, address);
        addressRepository.save(address);
        return true;
    }

    private void mapAddressRequestDtoToEntity(AddressRequestDto addressRequestDto, Address address) {

        address.setRecipientName(addressRequestDto.getRecipientName());
        address.setPhoneNumber(addressRequestDto.getPhoneNumber());
        address.setDistrict(addressRequestDto.getDistrict());
        address.setCity(addressRequestDto.getCity());
        address.setSpecificAddress(addressRequestDto.getAddressDetail());
        address.setWard(addressRequestDto.getWard());

    }

    @Override
    public Boolean deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id địa chỉ này"));

        // Xóa địa chỉ
        addressRepository.delete(address);

        return true;
    }

    @Override
    public Boolean updateAddress(Long id, AddressRequestDto addressRequestDto) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy id địa chỉ này"));

        if (addressRequestDto.getStatus() != null && addressRequestDto.getStatus()) {
            // Nếu deleted là true, cập nhật địa chỉ mặc định và set deleted
            addressRepository.updateDefaultAddress(address.getIdUser().getId());
            address.setStatus(true);
        }

        mapAddressRequestDtoToEntity(addressRequestDto, address);
        addressRepository.save(address);
        return true;
    }
}
