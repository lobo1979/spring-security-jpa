package com.pt.springsecurityjpa.controller;

import com.pt.springsecurityjpa.model.Role;
import com.pt.springsecurityjpa.model.User;
import com.pt.springsecurityjpa.repository.RoleRepository;
import com.pt.springsecurityjpa.repository.UserRepository;
import com.pt.springsecurityjpa.service.RolesServiceImpl;
import com.pt.springsecurityjpa.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/admin/saveNewUser")
    public String addUserByAdmin(@ModelAttribute("User") User user) {
        String pwd = user.getPassword();
        String encryptPwd = passwordEncoder.encode(pwd);
        user.setPassword(encryptPwd);
        userRepository.save(user);
        return "redirect:/";
    }

   /* @Test
    private void addAdminUser(){

        User user = new User();
        user.setUserName("Admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setEmail("peter.tipul@gmail");

        Role role = new Role();
        role.setRole("Admin");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);
    }*/




}
