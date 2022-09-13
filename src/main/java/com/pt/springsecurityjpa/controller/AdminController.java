package com.pt.springsecurityjpa.controller;

import com.pt.springsecurityjpa.model.Item;
import com.pt.springsecurityjpa.model.Role;
import com.pt.springsecurityjpa.model.User;
import com.pt.springsecurityjpa.repository.ItemRepository;
import com.pt.springsecurityjpa.repository.RoleRepository;
import com.pt.springsecurityjpa.repository.UserRepository;
import com.pt.springsecurityjpa.service.ItemServiceImpl;
import com.pt.springsecurityjpa.service.RolesServiceImpl;
import com.pt.springsecurityjpa.service.UserServiceImpl;
import com.pt.springsecurityjpa.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/backbone")
public class AdminController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemServiceImpl itemService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RolesServiceImpl rolesService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String homePage() {
        return "/backbone/admin";
    }

    @GetMapping("/item/all")
    public String viewAllProductsPage(Model model) {
        model.addAttribute("listOfItems", itemService.getAllItems());
        return "/backbone/items";
    }

    @GetMapping("/item/add")
    public String viewAddItemPage(Model model) {
        Item item = new Item();
        model.addAttribute("item", item);
        return "/backbone/item_add";
    }

    @PostMapping("/user/save")
    public String addUserByAdmin(@ModelAttribute("User") User user) {
        String pwd = user.getPassword();
        String encryptPwd = passwordEncoder.encode(pwd);
        user.setPassword(encryptPwd);
        userRepository.save(user);
        return "redirect:/backbone/user/all";
    }

    @GetMapping("/user/all")
    public String viewAllUsersPage(Model model) {
        model.addAttribute("listOfUsers", userService.getAllUsers());
        return "/backbone/users";
    }

    @GetMapping("/user/add")
    public String viewAddUsersPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("listOfRoles", rolesService.getAllRoles());
        return "/backbone/users_add";
    }

    @GetMapping("/user/update/{id}")
    public String showFormForUpdate(@PathVariable long id, Model model) {
        // set employee as a model attribute to pre-populate the form
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listOfRoles", rolesService.getAllRoles());
        return "/backbone/users_update";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteEmployee(@PathVariable (value="id") long id) {
        this.userService.deleteUserById(id);
        return "redirect:/backbone/user/all";
    }

    @PostMapping("/item/save")
    public RedirectView saveItem(Item item, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        item.setPhoto(fileName);

        Item savedItem = itemRepository.save(item);

        String uploadDir = "item-photos/" + savedItem.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return new RedirectView("/backbone/item/all", true);
    }



    /*@PostMapping("/item/save")
    public String addItemByAdmin(@ModelAttribute("Item") Item item) {
        itemRepository.save(item);
        return "redirect:/backbone/item/all";
    }*/

}
