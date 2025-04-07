package com.example.projectProducts.rest;


import com.example.projectProducts.modelo.ProductModel;
import com.example.projectProducts.modelo.ShopLocation;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class ShopController {

    private List<ShopLocation> shopLocations = new ArrayList<>();

    public ShopController() {

        shopLocations.add(new ShopLocation("E2", "España", "A Coruña", "Los Mallos, 10"));
        shopLocations.add(new ShopLocation("A2", "Argentina", "Buenos Aires", "Dirección inventada"));
        shopLocations.add(new ShopLocation("E3", "España", "Santiago", "Av. Toledo"));

    }

    @GetMapping("/shops")
    public List<ShopLocation> getAllShops() {
        return shopLocations;
    }

    @GetMapping("/shop/{id}")
    public ShopLocation getShopLocation(@PathVariable int id) {
        for (ShopLocation shopLocation : shopLocations) {
            if (shopLocation.getShopId() == id) {
                return shopLocation;
            }
        }

        return null;
    }


    @PostMapping("/shop")
    public String addShop(@RequestBody ShopLocation shopModel) {
        shopModel.setShopId(ShopLocation.getNextId());
        shopLocations.add(shopModel);
        return "HTTP/1.1 200 OK";
    }

    @DeleteMapping("/shop/{id}")
    public String deleteShop(@PathVariable int id) {
        for (ShopLocation shop : shopLocations) {
            if (shop.getShopId() == id) {
                shopLocations.remove(shop);
                return "HTTP/1.1 200 OK";
            }
        }

        return null;
    }


    @GetMapping("/shops/filter")
    public List<ShopLocation> getShopLocationWithFilters(
            @RequestParam(required = false) String locationId,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String address) {

        List<ShopLocation> shops = new ArrayList<>(shopLocations);


        if (locationId != null) {
            shops.removeIf(shop -> !shop.getLocationId().equals(locationId));

        }

        if (country != null) {
            shops.removeIf(shop -> !shop.getCountry().toLowerCase().contains(country.toLowerCase()));

        }
        if (city != null) {
            shops.removeIf(shop -> !shop.getCity().toLowerCase().contains(city.toLowerCase()));
        }

        if (address != null) {
            shops.removeIf(shop -> !shop.getAddress().toLowerCase().contains(address.toLowerCase()));
        }
        return shops;

    }


}
