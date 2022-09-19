package com.pt.springsecurityjpa.service;

import com.pt.springsecurityjpa.model.Basket;
import com.pt.springsecurityjpa.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BasketServiceImpl implements BasketService {

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
        try {
            Optional<Basket> bas = basketRepository.findById(id);
            return bas.get();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void deleteBasketById(long id) {
        basketRepository.deleteById(id);
    }

    @Override
    public Page<Basket> findPaginated(int pageNo, int pageSize) {
        Pageable pagable = PageRequest.of(pageNo - 1, pageSize);
        return this.basketRepository.findAll(pagable);
    }

    public List<Basket> getBasketItemsByUserName(String username) {
        List<Basket> bas = null;
        try {
            bas = basketRepository.getBasketItemsByUserName(username);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return bas;
    }


}
