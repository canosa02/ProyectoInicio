package com.example.projectProducts.rest;



import com.example.projectProducts.modelo.LocationModel;
import com.example.projectProducts.modelo.ShopLocation;
import com.example.projectProducts.modelo.ShopModel;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private List<ShopModel> shops = new ArrayList<>();
    private List<ShopLocation> shopLocations = new ArrayList<>();

    public ShopController() {
        shops.add(new ShopModel(1,"A1"));
        shops.add(new ShopModel(2,"A2"));
        shops.add(new ShopModel(3,"E1"));
        shops.add(new ShopModel(4,"F1"));
        shops.add(new ShopModel(5,"P1"));
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
    public List<ShopModel> getAllShops(){
        return shops;
    }

    @PostMapping("")
    public String addShop(@RequestBody ShopModel shopModel){
        shops.add(shopModel);
        return "OK";
    }

    @DeleteMapping("/{id}")
    public String deleteShop(@PathVariable int id){
        for(ShopModel shop : shops){
            if(shop.getShopId() == id){
                shops.remove(shop);
                return "HTTP/1.1 200 OK";
            }
        }

        return null;
    }
}
