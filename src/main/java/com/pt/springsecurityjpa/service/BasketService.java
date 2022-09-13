package com.pt.springsecurityjpa.service;

import com.pt.springsecurityjpa.model.Basket;


import java.util.List;

public interface BasketService {

    List<Basket> getAllItems();

    void saveItem(Basket item);

    Basket getItemById(long id);

    void deleteItemById(long id);

}
