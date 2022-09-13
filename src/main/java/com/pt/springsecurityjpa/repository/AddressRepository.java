package com.pt.springsecurityjpa.repository;

import com.pt.springsecurityjpa.model.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<DeliveryAddress,Long> {


}
