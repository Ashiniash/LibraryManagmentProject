package org.example.service.implementation;

import org.example.dao.repository.AddressRepository;
import org.example.dto.AddressDTO;
import org.example.dto.UserDTO;
import org.example.model.Address;
import org.example.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Override
    public void addAddress(AddressDTO addressDTO, int userId, UserDTO userDTO) {
        Address addressObj = new Address();
        addressObj.setAddressId(addressDTO.getAddressId());
        addressObj.setCountry(addressDTO.getCountry());
        addressObj.setDistrict(addressDTO.getDistrict());
        addressObj.setHouseNumber(addressDTO.getHouseNumber());
        addressObj.setTown(addressDTO.getTown());
        addressObj.setStreet(addressDTO.getStreet());
        addressObj.setPhoneNumber(addressDTO.getPhoneNumber());
        addressObj.setPinCode(addressDTO.getPinCode());
        addressObj.setUserId(addressDTO.getUserId());
        userDTO.setUserId(addressDTO.getUserId());
        addressRepository.save(addressObj);
    }


    @Override
    public List<Address> displayAddressById(int userId) {
        return addressRepository.displayAddressById(userId);
    }

    @Override
    public Address findAddressById(int addressId) {
        return addressRepository.findById(addressId).get();
    }

    @Override
    public void addressUpdate(AddressDTO addressDTO) {
        Address address = new Address();
        address.setAddressId(addressDTO.getAddressId());
        address.setCountry(addressDTO.getCountry());
        address.setDistrict(addressDTO.getDistrict());
        address.setHouseNumber(addressDTO.getHouseNumber());
        address.setTown(addressDTO.getTown());
        address.setStreet(addressDTO.getStreet());
        address.setPhoneNumber(addressDTO.getPhoneNumber());
        address.setPinCode(addressDTO.getPinCode());
        address.setUserId(addressDTO.getUserId());
        addressRepository.save(address);

    }
}
