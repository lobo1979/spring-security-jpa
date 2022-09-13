package com.pt.springsecurityjpa.controller;

import com.pt.springsecurityjpa.model.User;
import com.pt.springsecurityjpa.service.ItemServiceImpl;
import com.pt.springsecurityjpa.service.RolesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebConrtoller {

    @Autowired
    private ItemServiceImpl itemService;

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("listOfItems", itemService.getAllItems());

        return "index";
    }


   /* @GetMapping("/backbone")
    public String configurationPage(){


        return "/backbone/admin";
    }*/



}
