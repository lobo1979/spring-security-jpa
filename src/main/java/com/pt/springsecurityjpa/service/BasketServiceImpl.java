package com.pt.springsecurityjpa.service;

import com.pt.springsecurityjpa.model.Basket;
import com.pt.springsecurityjpa.repository.BasketRepository;
import com.pt.springsecurityjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class BasketServiceImpl implements BasketService{

    @Autowired
    private BasketRepository basketRepository;

    @Override
    public List<Basket> getAllBaskets() {
        return basketRepository.findAll();
    }

    @Override
    public void saveBasket(Basket basket) {
        basketRepository.save(basket);
    }

    @Override
    public Basket getBasketById(long id) {
        return basketRepository.getReferenceById(id);
    }

    @Override
    public void deleteBasketById(long id) {
        basketRepository.deleteById(id);
    }

    public List<Basket> getBasketItemsByUserName(String username) {
        List<Basket> bas = null;
        try {
             bas = basketRepository.getBasketItemsByUserName(username);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return bas ;
    }


}
