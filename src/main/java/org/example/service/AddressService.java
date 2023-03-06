package org.example.service;

import org.example.dto.AddressDTO;
import org.example.dto.UserDTO;
import org.example.model.Address;

import java.util.List;

public interface AddressService {
    void addAddress(AddressDTO addressDTO, int userId, UserDTO userDTO);

    List<Address> displayAddressById(int userId);

    Address findAddressById(int addressId);

    void addressUpdate(AddressDTO address);
}
