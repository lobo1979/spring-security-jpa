package com.pt.springsecurityjpa.controller;

import com.pt.springsecurityjpa.model.User;
import com.pt.springsecurityjpa.service.RolesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebConrtoller {

    @Autowired
    private RolesServiceImpl rolesService;



    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/employee")
    public String employeePage(){
        return "employee";
    }

    @GetMapping("/department")
    public String departmentPage(){
        return "department";
    }

    @GetMapping("/admin")
    public String configurationPage(){
        return "admin";
    }

    @GetMapping("/admin/showNewUserForm")
    public String viewNewEmployeePage(Model model){
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("listOfRoles", rolesService.getAllRoles());
        return "add-user";
    }

}
