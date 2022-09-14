package com.pt.springsecurityjpa.controller;

import com.pt.springsecurityjpa.model.Basket;
import com.pt.springsecurityjpa.model.User;
import com.pt.springsecurityjpa.service.BasketServiceImpl;
import com.pt.springsecurityjpa.service.ItemServiceImpl;
import com.pt.springsecurityjpa.service.RolesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class WebConrtoller {

    @Autowired
    private ItemServiceImpl itemService;

    @Autowired
    private BasketServiceImpl basketService;


    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("listOfItems", itemService.getAllItems());

        return "index";
    }

    @GetMapping("/item/all")
    public String homeItemsPage(Model model){
        model.addAttribute("listOfItems", itemService.getAllItems());

        return "index";
    }

    @GetMapping("/item/{userName}/{itemId}")
    public String showFormForUpdate(@PathVariable String userName, @PathVariable long itemId, Model model) {
        // set employee as a model attribute to pre-populate the form

        //basketService.getBasketByUserName()

        System.out.println(userName);
        System.out.println(itemId);

        /*model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listOfRoles", rolesService.getAllRoles());*/
        return "index";
    }


}
