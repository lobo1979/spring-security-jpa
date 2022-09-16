package com.pt.springsecurityjpa.controller;

import com.pt.springsecurityjpa.model.Item;
import com.pt.springsecurityjpa.model.Manufacturer;
import com.pt.springsecurityjpa.model.Role;
import com.pt.springsecurityjpa.model.User;
import com.pt.springsecurityjpa.repository.ItemRepository;
import com.pt.springsecurityjpa.repository.ManufacturerRepository;
import com.pt.springsecurityjpa.repository.RoleRepository;
import com.pt.springsecurityjpa.repository.UserRepository;
import com.pt.springsecurityjpa.service.ItemServiceImpl;
import com.pt.springsecurityjpa.service.ManufacturerService;
import com.pt.springsecurityjpa.service.RolesServiceImpl;
import com.pt.springsecurityjpa.service.UserServiceImpl;
import com.pt.springsecurityjpa.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
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
    private ManufacturerService manufacturerService;

    @Autowired
    private ItemServiceImpl itemService;

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

    //********************************  Item  ********************************
    @GetMapping("/item/all")
    public String viewAllProductsPage(Model model) {
        return viewAllItemsByPage(1, model);
    }

    @GetMapping("/item/page/{pageNo}")
    public String viewAllItemsByPage(@PathVariable (value = "pageNo") int pageNo,   Model model) {
        int ItemsPerPage = 3;

        Page<Item> page = itemService.findPaginated(pageNo, ItemsPerPage);
        List<Item> itemList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listOfItems", itemList);
        return "/backbone/items";
    }

    @GetMapping("/item/add")
    public String viewAddItemPage(Model model) {
        Item item = new Item();
        model.addAttribute("item", item);
        model.addAttribute("listOfManufacturers", manufacturerService.getAllManufacturers());
        return "/backbone/item_add";
    }

    @GetMapping("/item/update/{id}")
    public String showFormForUpdateItem(@PathVariable long id, Model model) {
        // set employee as a model attribute to pre-populate the form
        model.addAttribute("item", itemService.getItemById(id));
        model.addAttribute("listOfManufacturers", manufacturerService.getAllManufacturers());
        return "/backbone/item_update";
    }

    @PostMapping("/item/save")
    public RedirectView saveItem(Item item, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        item.setPhoto(fileName);
        Item savedItem =  itemService.saveItem(item);     //itemRepository.save(item);
        String uploadDir = "item-photos/" + savedItem.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return new RedirectView("/backbone/item/all", true);
    }

    @GetMapping("/item/delete/{id}")
    public String deleteItem(@PathVariable (value="id") long id) {
        this.itemService.deleteItemById(id);
        return "redirect:/backbone/item/all";
    }


    //********************************  Manufacturer  ********************************
    @GetMapping("/manufacturer/all")
    public String viewAllManufacturerByPage(Model model) {
        return viewAllManufacturerByPage(1, model);
    }

    @GetMapping("/manufacturer/page/{pageNo}")
    public String viewAllManufacturerByPage(@PathVariable (value = "pageNo") int pageNo,  Model model) {
        int manufacturersPerPage = 7;

        Page<Manufacturer> page = manufacturerService.findPaginated(pageNo, manufacturersPerPage);
        List<Manufacturer> manufacturerList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listOfManufacturers", manufacturerList);
        return "/backbone/manufacturers";
    }

    @GetMapping("/manufacturer/add")
    public String viewAddManufacturerPage(Model model) {
        Manufacturer man = new Manufacturer();
        model.addAttribute("manufacturer", man);
        return "/backbone/manufacturer_add";
    }

    @GetMapping("/manufacturer/update/{id}")
    public String showFormForUpdateManufacturer(@PathVariable long id, Model model) {
        // set employee as a model attribute to pre-populate the form
        model.addAttribute("manufacturer", manufacturerService.getManufacturerById(id));
        return "/backbone/manufacturer_update";
    }

    @PostMapping("/manufacturer/save")
    public RedirectView saveManufacturer(@ModelAttribute("Manufacturer") Manufacturer man ){
        manufacturerService.saveManufacturer(man);
        return new RedirectView("/backbone/manufacturer/all", true);
    }

    @GetMapping("/manufacturer/delete/{id}")
    public String deleteManufacturer(@PathVariable (value="id") long id) {
        manufacturerService.deleteManufacturerById(id);
        return "redirect:/backbone/manufacturer/all";
    }

    //********************************  User  ********************************

    @PostMapping("/user/save")
    public String addUserByAdmin(@ModelAttribute("User") User user) {
        String pwd = user.getPassword();
        String encryptPwd = passwordEncoder.encode(pwd);
        user.setPassword(encryptPwd);

        userService.saveUser(user);
        return "redirect:/backbone/user/all";
    }

    @GetMapping("/user/all")
    public String viewAllUsersPage(Model model) {
        return viewAllUsersByPage(1,model);
    }

    @GetMapping("/user/page/{pageNo}")
    public String viewAllUsersByPage(@PathVariable (value = "pageNo") int pageNo,   Model model) {
        int usersPerPage = 5;

        Page<User> page = userService.findPaginated(pageNo, usersPerPage);
        List<User> userList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listOfUsers", userList);
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

    //********************************  General  ********************************

    @GetMapping("/403")
    public String error403() {
        return "403";
    }


    /*@PostMapping("/item/save")
    public String addItemByAdmin(@ModelAttribute("Item") Item item) {
        itemRepository.save(item);
        return "redirect:/backbone/item/all";
    }*/

}
