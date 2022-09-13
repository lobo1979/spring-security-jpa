package com.pt.springsecurityjpa.service;

import com.pt.springsecurityjpa.model.Item;
import com.pt.springsecurityjpa.model.Role;
import com.pt.springsecurityjpa.repository.ItemRepository;
import com.pt.springsecurityjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public Item getItemById(long id) {
        return itemRepository.getReferenceById(id);
    }

    @Override
    public void deleteItemById(long id) {
        itemRepository.deleteById(id);
    }

}
