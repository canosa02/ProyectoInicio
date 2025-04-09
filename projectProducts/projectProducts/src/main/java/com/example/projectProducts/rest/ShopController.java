package com.example.projectProducts.rest;


import com.example.projectProducts.modelo.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class ShopController {

    private List<ShopLocation> shopLocations = new ArrayList<>();
    private List<ProductPriceModel> productPrices = new ArrayList<>();


    public ShopController() {

        shopLocations.add(new ShopLocation("E2", "España", "A Coruña", "Los Mallos, 10"));
        shopLocations.add(new ShopLocation("A2", "Argentina", "Buenos Aires", "Dirección inventada"));
        shopLocations.add(new ShopLocation("E3", "España", "Santiago", "Av. Toledo"));

        productPrices.add(new ProductPriceModel(new BigDecimal("25.50"), "E2", 1));
        productPrices.add(new ProductPriceModel(new BigDecimal("25.50"), "A2", 2));
        productPrices.add(new ProductPriceModel(new BigDecimal("15.00"), "E3", 3));
    }


    @GetMapping("/shops")
    public List<ShopLocation> getAllShops() {
        return shopLocations;
    }

    @GetMapping("/shop/{shopId}")
    public ResponseEntity<ShopLocation> getShopLocation(@PathVariable int shopId) {
        for (ShopLocation shopLocation : shopLocations) {
            if (shopLocation.getShopId() == shopId) {
                return ResponseEntity.ok(shopLocation);
            }
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping("/shop")
    public ResponseEntity<Object> addShop(@RequestBody ShopAddDTO newShopDTO) {

        ShopLocation newShop = new ShopLocation();

        for (ShopLocation shop : shopLocations) {
            if (shop.getLocationId().equals(newShopDTO.getLocationId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("That locationId alredy exists");
            }
//           Comprobación para que la calle no exista dos veces,para ello se comprueba que sea en la misma ciudad y país.

            if (shop.getCountry().equalsIgnoreCase(newShopDTO.getCountry()) && shop.getCity().equalsIgnoreCase(newShopDTO.getCity()) && shop.getAddress().equalsIgnoreCase(newShopDTO.getAddress())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("That address alredy exists");
            }
        }
        newShop.setShopId(ShopLocation.getNextId());
        newShop.setLocationId(newShopDTO.getLocationId());
        newShop.setCountry(newShopDTO.getCountry());
        newShop.setCity(newShopDTO.getCity());
        newShop.setAddress(newShopDTO.getAddress());

        shopLocations.add(newShop);

        return ResponseEntity.ok(newShop);
    }

    @DeleteMapping("/shop/{shopId}")
    public ResponseEntity<String> deleteShop(@PathVariable int shopId) {
        boolean removed = shopLocations.removeIf(shop -> shop.getShopId() == shopId);

        if (removed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }


    @GetMapping("/shop/filter")
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

    @PutMapping("/shop/{shopId}")
    public ResponseEntity<Object> updateshop(@PathVariable int shopId, @RequestBody UpdateShopDTO updateShopDTO) {
        for (int i = 0; i < shopLocations.size(); i++) {
            ShopLocation currentShop = shopLocations.get(i);

            if (currentShop.getShopId() == shopId) {

                if (updateShopDTO.getLocationId() == null || updateShopDTO.getCountry() == null ||
                        updateShopDTO.getCity() == null || updateShopDTO.getAddress() == null) {
                    return ResponseEntity.badRequest().body("The fields cannot be empty");
                }
                currentShop.setLocationId(updateShopDTO.getLocationId());
                currentShop.setCountry(updateShopDTO.getCountry());
                currentShop.setCity(updateShopDTO.getCity());
                currentShop.setAddress(updateShopDTO.getAddress());

                return ResponseEntity.ok(currentShop);
            }
        }
        return ResponseEntity.status(404).build();

    }

    @PatchMapping("/shop/{shopId}") //no se como meter un badRequest aquí
    public ResponseEntity<String> partialUpdateShop(@PathVariable int shopId, @RequestBody UpdateShopDTO updateShopDTO){
        for(ShopLocation shop : shopLocations) {
            if (shop.getShopId() == shopId){
                if (updateShopDTO.getLocationId() != null){
                    shop.setLocationId(updateShopDTO.getLocationId());
                }
                if (updateShopDTO.getCountry() != null){
                    shop.setCountry(updateShopDTO.getCountry());
                }
                if (updateShopDTO.getCity() != null){
                    shop.setCity(updateShopDTO.getCity());
                }
                if (updateShopDTO.getAddress() != null){
                    shop.setAddress(updateShopDTO.getAddress());
                }
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(404).build();
    }


    @PostMapping("/shop/addProduct/{productId}")
    public ResponseEntity<Object> addProductShop(@PathVariable int productId, @RequestBody AddProductShopDTO product) {
        String locationId = product.getLocationId();
        BigDecimal price = product.getPrice();
        boolean found = false;
        if (locationId == null || price == null) {
            return ResponseEntity.badRequest().build();
        }

        if (productId <= 0) {
            return ResponseEntity.badRequest().body("LocationId is empty");
        }
        if (locationId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("LocationId is empty");
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.badRequest().body("The price cannot be negative");
        }


        for (ProductPriceModel productsList : productPrices) {
            if (productId == productsList.getProductId()) {
                found = true;
            }
        }
        if (!found) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product does not exist");
        }

        boolean alreadyAdded = productPrices.stream()
                .anyMatch(p -> p.getProductId() == productId && p.getLocationId().equals(locationId));

        if (alreadyAdded) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This product already exists in that shop");
        }


        for (ShopLocation currentShop : shopLocations) {
            if (currentShop.getLocationId().equals(product.getLocationId())) {
                ProductPriceModel newProduct = new ProductPriceModel();
                newProduct.setProductId(productId);
                newProduct.setLocationId(locationId);
                newProduct.setPrice(price);

                productPrices.add(newProduct);

                return ResponseEntity.ok(newProduct);
            }
        }

        return ResponseEntity.notFound().build();

    }

    @PatchMapping("/shop/{shopId}/product/{productId}")
    public ResponseEntity<Object> updateProductPrice(@PathVariable int shopId, @PathVariable int productId, @RequestBody ProductPricePatchDTO productPricePatchDTO) {

        if (productPricePatchDTO.getPrice() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Price cannot be empty");
        }

        ShopLocation shopLocation = shopLocations.stream()
                .filter(shop -> shop.getShopId() == shopId)
                .findFirst()
                .orElse(null);

        if (shopLocation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop not found");
        }

        String locationId = shopLocation.getLocationId();

        // Buscar el producto en la tienda con esa locationId
        for (ProductPriceModel product : productPrices) {
            if (product.getProductId() == productId && product.getLocationId().equals(locationId)) {
                product.setPrice(productPricePatchDTO.getPrice());
                return ResponseEntity.ok(product);
            }
        }

        return ResponseEntity.notFound().build();
    }

}

