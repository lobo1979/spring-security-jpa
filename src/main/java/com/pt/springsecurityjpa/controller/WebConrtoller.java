package com.pt.springsecurityjpa.controller;

import com.pt.springsecurityjpa.model.Basket;
import com.pt.springsecurityjpa.model.Item;
import com.pt.springsecurityjpa.model.Role;
import com.pt.springsecurityjpa.model.User;
import com.pt.springsecurityjpa.service.BasketServiceImpl;
import com.pt.springsecurityjpa.service.ItemServiceImpl;
import com.pt.springsecurityjpa.service.RolesServiceImpl;
import com.pt.springsecurityjpa.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class WebConrtoller {

    @Autowired
    private ItemServiceImpl itemService;

    @Autowired
    private BasketServiceImpl basketService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RolesServiceImpl rolesService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
        int itemsPerPage = 3;

        Page<Item> page = itemService.findPaginated(pageNo, itemsPerPage);
        List<Item> userList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listOfItems", userList);
        return "index";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }

    @GetMapping("/register")
    public String viewAddUsersPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/users_add";
    }

    @PostMapping("/user/save")
    public String addUserByAdmin(@ModelAttribute("User") User user) {
        String pwd = user.getPassword();
        String encryptPwd = passwordEncoder.encode(pwd);
        user.setPassword(encryptPwd);
        user.setActive(true);

        Role r = rolesService.getRoleById(4); //TO-DO - najst na zaklade nazvu nie ID

        List<Role> lr = new ArrayList<>();
        lr.add(r);

        user.setRoles(lr);
        userService.saveUser(user);
        return "index";
    }


}
