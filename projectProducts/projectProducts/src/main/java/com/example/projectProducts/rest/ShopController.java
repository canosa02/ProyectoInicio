package com.example.projectproducts.project_products.rest;


import com.example.projectproducts.project_products.modelo.ShopModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private List<ShopModel> shops = new ArrayList<>();

    public ShopController() {
        shops.add(new ShopModel(1,"A1"));
        shops.add(new ShopModel(2,"A2"));
        shops.add(new ShopModel(3,"E1"));
        shops.add(new ShopModel(4,"F1"));
        shops.add(new ShopModel(5,"P1"));
    }

    @GetMapping("/{id}")
    public ShopModel getShop(@PathVariable int id){
        for(ShopModel shop : shops){
            if(shop.getIdShop() == id){
                return shop;
            }
        }

        return null;
    }
    @GetMapping("/")
    public List<ShopModel> getAllShops(){
        return shops;
    }

    @PostMapping("")
    public String addShop(@RequestBody ShopModel shopModel){
        shops.add(shopModel);
        return "OK";
    }

    @DeleteMapping("/{id}")
    public String deleteShop(@PathVariable Long id){
        for(ShopModel shop : shops){
            if(shop.getIdShop() == id){
                shops.remove(shop);
                return "HTTP/1.1 200 OK";
            }
        }

        return null;
    }
}
