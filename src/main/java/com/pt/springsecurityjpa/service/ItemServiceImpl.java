package com.pt.springsecurityjpa.service;

import com.pt.springsecurityjpa.model.Item;
import com.pt.springsecurityjpa.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public <S extends Item> S saveItem(S item) {
        return itemRepository.save(item);
    }

    @Override
    public Item getItemById(long id) {
        return itemRepository.getReferenceById(id);
    }

    @Override
    public void deleteItemById(long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Page<Item> findPaginated(int pageNo, int pageSize) {
        Pageable pagable = PageRequest.of(pageNo-1, pageSize);
        return itemRepository.findAll(pagable);
    }

}
