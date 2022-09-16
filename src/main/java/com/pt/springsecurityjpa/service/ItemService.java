package com.pt.springsecurityjpa.service;

import com.pt.springsecurityjpa.model.Basket;
import com.pt.springsecurityjpa.model.Item;
import com.pt.springsecurityjpa.model.Role;
import com.pt.springsecurityjpa.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemService {

    List<Item> getAllItems();

    <S extends Item> S saveItem(S item);

    Item getItemById(long id);

    void deleteItemById(long id);

    Page<Item> findPaginated(int pageNo, int pageSize);

}
