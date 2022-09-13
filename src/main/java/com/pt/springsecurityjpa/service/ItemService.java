package com.pt.springsecurityjpa.service;

import com.pt.springsecurityjpa.model.Item;
import com.pt.springsecurityjpa.model.Role;

import java.util.List;

public interface ItemService {

    List<Item> getAllItems();

    void saveItem(Item item);

    Item getItemById(long id);

    void deleteItemById(long id);


}
