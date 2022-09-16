package com.pt.springsecurityjpa.controller;

import com.pt.springsecurityjpa.model.Basket;
import com.pt.springsecurityjpa.model.Item;
import com.pt.springsecurityjpa.model.User;
import com.pt.springsecurityjpa.repository.UserRepository;
import com.pt.springsecurityjpa.service.BasketServiceImpl;
import com.pt.springsecurityjpa.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/basket")
public class BasketConrtoller {

    @Autowired
    private ItemServiceImpl itemService;

    @Autowired
    private BasketServiceImpl basketService;


    @Autowired
    private UserRepository userRepository;


    @GetMapping("/{userName}")
    public String homePage(@PathVariable String userName, Model model){
        model.addAttribute("basketList", basketService.getBasketItemsByUserName(userName));
        return "basket";
    }

    @GetMapping("/all")
    public String homeItemsPage(Model model){
        model.addAttribute("basketList", basketService.getAllBaskets());
        return "basket";
    }

    @GetMapping("/item/{userName}/{itemId}")
    public String showFormForUpdate( @PathVariable String userName, @PathVariable long itemId, Model model) {
        model.addAttribute("item", itemService.getItemById(itemId));
        return "item";
    }

    @PostMapping("/item/save/{userName}")
    public String showFormForAddingItemToBasket(@PathVariable String userName, @ModelAttribute("Item") Item item) {
        // set employee as a model attribute to pre-populate the form

        Optional<User> userlst = userRepository.findByUserName(userName);
        Basket basket = new Basket();
        basket.setUser(userlst.get());

        Item itemOrig = itemService.getItemById(item.getId());
        basket.setItems(itemOrig);

        basket.setPcs(item.getPocet());
        basket.setColor(item.getColor());
        basket.setSize(item.getSize());

        basketService.saveBasket(basket);
        return "redirect:/item/all";
    }

    @GetMapping("/403")
    public String error403() {
        return "403";
    }

}
