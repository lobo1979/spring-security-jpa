package com.pt.springsecurityjpa.service;

import com.pt.springsecurityjpa.model.Basket;


import java.util.List;

public interface BasketService {

    List<Basket> getAllBaskets();

    void saveBasket(Basket basket);

    Basket getBasketById(long id);

    void deleteBasketById(long id);

}
