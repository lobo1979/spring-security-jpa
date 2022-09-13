package com.pt.springsecurityjpa.service;

import com.pt.springsecurityjpa.model.Basket;
import com.pt.springsecurityjpa.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketServiceImpl implements BasketService{


    @Override
    public List<Basket> getAllItems() {
        return null;
    }

    @Override
    public void saveItem(Basket item) {

    }

    @Override
    public Basket getItemById(long id) {
        return null;
    }

    @Override
    public void deleteItemById(long id) {

    }
}
