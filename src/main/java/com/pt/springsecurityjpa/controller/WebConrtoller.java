package com.pt.springsecurityjpa.controller;

import com.pt.springsecurityjpa.model.Basket;
import com.pt.springsecurityjpa.model.Item;
import com.pt.springsecurityjpa.model.User;
import com.pt.springsecurityjpa.service.BasketServiceImpl;
import com.pt.springsecurityjpa.service.ItemServiceImpl;
import com.pt.springsecurityjpa.service.RolesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class WebConrtoller {

    @Autowired
    private ItemServiceImpl itemService;

    @Autowired
    private BasketServiceImpl basketService;


    @GetMapping("/")
    public String homePage(Model model){
        return viewAllItemsByPage(1, model);
    }

    @GetMapping("/item/all")
    public String homeItemsPage(Model model){
        return viewAllItemsByPage(1, model);
    }

      @GetMapping("/item/page/{pageNo}")
    public String viewAllItemsByPage(@PathVariable (value = "pageNo") int pageNo,   Model model) {
        int usersPerPage = 3;

        Page<Item> page = itemService.findPaginated(pageNo, usersPerPage);
        List<Item> userList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listOfItems", itemService.getAllItems());
        return "index";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }


}
