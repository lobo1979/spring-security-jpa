package com.pt.springsecurityjpa.service;

import com.pt.springsecurityjpa.model.Basket;
import com.pt.springsecurityjpa.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void saveUser(User user);

    User getUserById(long id);

    void deleteUserById(long id);

    Page<User> findPaginated(int pageNo, int pageSize);

}
