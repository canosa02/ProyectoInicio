package com.example.projectProducts.rest;



import com.example.projectProducts.modelo.ShopLocation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    
    private List<ShopLocation> shopLocations = new ArrayList<>();

    public ShopController() {

        shopLocations.add(new ShopLocation(1, "E2", "España", "A Coruña", "Los Mallos, 10", 40));
        shopLocations.add(new ShopLocation(2, "A2", "Argentina", "Buenos Aires", "Dirección inventada", 2));
    }

    @GetMapping("/{id}")
    public ShopLocation getShopLocation(@PathVariable int id){
        for(ShopLocation shopLocation : shopLocations){
            if(shopLocation.getShopId() == id){
                return shopLocation;
            }
        }

        return null;
    }
    @GetMapping("/")
    public List<ShopLocation> getAllShops(){
        return shopLocations;
    }

    @PostMapping("")
    public String addShop(@RequestBody ShopLocation shopModel){
        shopLocations.add(shopModel);
        return "OK";
    }

    @DeleteMapping("/{id}")
    public String deleteShop(@PathVariable int id){
        for(ShopLocation shop : shopLocations){
            if(shop.getShopId() == id){
                shopLocations.remove(shop);
                return "HTTP/1.1 200 OK";
            }
        }

        return null;
    }
}
