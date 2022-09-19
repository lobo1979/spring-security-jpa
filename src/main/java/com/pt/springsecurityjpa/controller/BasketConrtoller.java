package com.pt.springsecurityjpa.controller;

import com.pt.springsecurityjpa.model.Basket;
import com.pt.springsecurityjpa.model.Item;
import com.pt.springsecurityjpa.model.User;
import com.pt.springsecurityjpa.repository.UserRepository;
import com.pt.springsecurityjpa.service.BasketServiceImpl;
import com.pt.springsecurityjpa.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/item/delete/{userName}/{itemId}")
    public String removeItemFromBasket(@PathVariable String userName, @PathVariable long itemId, Model model) {
        this.basketService.deleteBasketById(itemId);
        return "redirect:/basket/" + userName;
    }

    @GetMapping("/item/{userName}/{itemId}")
    public String addItemToBasket( @PathVariable String userName, @PathVariable long itemId, Model model) {
        model.addAttribute("item", itemService.getItemById(itemId));
        return "item";
    }

    @GetMapping("/item/update/{userName}/{itemId}")
    public String showFormForUpdateBasket( @PathVariable String userName, @PathVariable long itemId, Model model) {
        model.addAttribute("itemInBasket", basketService.getBasketById(itemId));
        return "item_update";
    }


    @PostMapping("/item/update/save/{userName}")
    public String saveUpdatedItemInBasket(@PathVariable String userName, @ModelAttribute("itemInBasket") Basket itemInBasket) {

        Basket basket = basketService.getBasketById(itemInBasket.getId());
        basket.setPcs(itemInBasket.getPcs());
        basket.setSize(itemInBasket.getSize());
        basket.setColor(itemInBasket.getColor());

        basketService.saveBasket(basket);
        return "redirect:/basket/" + userName;
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
