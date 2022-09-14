package com.pt.springsecurityjpa.repository;

import com.pt.springsecurityjpa.model.Basket;
import com.pt.springsecurityjpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket,Long> {


    @Query(value = "SELECT b.* FROM basket b left join user u on u.user_id = b.user_id where u.user_name=?1", nativeQuery = true)
    List<Basket> getBasketItemsByUserName(String userName);



}




