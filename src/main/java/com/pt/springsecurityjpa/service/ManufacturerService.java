package com.pt.springsecurityjpa.service;

import com.pt.springsecurityjpa.model.Item;
import com.pt.springsecurityjpa.model.Manufacturer;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ManufacturerService {

    List<Manufacturer> getAllManufacturers();

    void saveManufacturer(Manufacturer man);

    Manufacturer getManufacturerById(long id);

    void deleteManufacturerById(long id);

    Page<Manufacturer> findPaginated(int pageNo, int pageSize);

}
