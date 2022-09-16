package com.pt.springsecurityjpa.service;

import com.pt.springsecurityjpa.model.User;
import com.pt.springsecurityjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {

        return userRepository.getReferenceById(id);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize) {
        Pageable pagable = PageRequest.of(pageNo-1, pageSize);
        return this.userRepository.findAll(pagable);
    }

}
