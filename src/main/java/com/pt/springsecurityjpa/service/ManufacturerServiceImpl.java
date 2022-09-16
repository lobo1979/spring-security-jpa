package com.pt.springsecurityjpa.service;

import com.pt.springsecurityjpa.model.Manufacturer;
import com.pt.springsecurityjpa.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    @Override
    public void saveManufacturer(Manufacturer man) {
        manufacturerRepository.save(man);
    }

    @Override
    public Manufacturer getManufacturerById(long id) {
        return manufacturerRepository.getReferenceById(id);
    }

    @Override
    public void deleteManufacturerById(long id) {
        manufacturerRepository.deleteById(id);
    }

    @Override
    public Page<Manufacturer> findPaginated(int pageNo, int pageSize) {
        Pageable pagable = PageRequest.of(pageNo-1, pageSize);
        return manufacturerRepository.findAll(pagable);
    }
}
