package org.example.dao.repository;

import org.example.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Query("SELECT address FROM Address address WHERE address.userId=:userId")
    List<Address> displayAddressById(@Param("userId") int userId);
}
