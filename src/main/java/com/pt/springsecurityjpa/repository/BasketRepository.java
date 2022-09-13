package com.pt.springsecurityjpa.repository;

import com.pt.springsecurityjpa.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket,Long> {


}




