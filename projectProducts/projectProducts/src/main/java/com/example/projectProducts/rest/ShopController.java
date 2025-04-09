package com.example.projectProducts.rest;


import com.example.projectProducts.modelo.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("")
public class ShopController {

    private List<ShopLocation> shopLocations = new ArrayList<>();
    private List<ProductPriceModel> productPrices = new ArrayList<>();


    public ShopController() {

        shopLocations.add(new ShopLocation("España", "A Coruña", "Los Mallos, 10"));
        shopLocations.add(new ShopLocation("Argentina", "Buenos Aires", "Dirección inventada"));
        shopLocations.add(new ShopLocation("España", "Santiago", "Av. Toledo"));

        productPrices.add(new ProductPriceModel(new BigDecimal("25.50"), "E2", 1));
        productPrices.add(new ProductPriceModel(new BigDecimal("25.50"), "A2", 2));
        productPrices.add(new ProductPriceModel(new BigDecimal("15.00"), "E3", 3));
    }


    @GetMapping("/shops")
    public List<ShopLocation> getAllShops() {
        return shopLocations;
    }

    @GetMapping("/shop/{shopId}")
    public List<Object> getShopLocation(@PathVariable int shopId) {
        List<ShopLocation> foundShops = new ArrayList<>();

        for (ShopLocation shopLocation : shopLocations) {
            if (shopLocation.getShopId() == shopId) {
                foundShops.add(shopLocation);
                return Collections.singletonList(foundShops);  // Devuelve la tienda encontrada en una lista
            }
        }

        return Collections.emptyList();  // Devuelve una lista vacía si no se encuentra la tienda
    }



    @PostMapping("/shop")
    public ResponseEntity<Object> addShop(@RequestBody ShopAddDTO newShopDTO) {

        ShopLocation newShop = new ShopLocation();

        for (ShopLocation shop : shopLocations) {
//           Comprobación para que la calle no exista dos veces,para ello se comprueba que sea en la misma ciudad y país.

            if (shop.getCountry().equalsIgnoreCase(newShopDTO.getCountry()) && shop.getCity().equalsIgnoreCase(newShopDTO.getCity()) && shop.getAddress().equalsIgnoreCase(newShopDTO.getAddress())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("That address alredy exists");
            }
        }
        newShop.setShopId(ShopLocation.getNextId());
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
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String address) {

        List<ShopLocation> shops = new ArrayList<>(shopLocations);

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
    public ResponseEntity<Object> updateShop(@PathVariable int shopId, @RequestBody UpdateShopDTO updateShopDTO) {
        for (int i = 0; i < shopLocations.size(); i++) {
            ShopLocation currentShop = shopLocations.get(i);

            if (currentShop.getShopId() == shopId) {

                if ( updateShopDTO.getCountry() == null ||
                        updateShopDTO.getCity() == null || updateShopDTO.getAddress() == null) {
                    return ResponseEntity.badRequest().body("The fields cannot be empty");
                }
                currentShop.setCountry(updateShopDTO.getCountry());
                currentShop.setCity(updateShopDTO.getCity());
                currentShop.setAddress(updateShopDTO.getAddress());

                return ResponseEntity.ok(currentShop);
            }
        }
        return ResponseEntity.status(404).build();

    }

    @PatchMapping("/shop/{shopId}")
    public ResponseEntity<Object> partialUpdateShop(@PathVariable int shopId, @RequestBody UpdateShopDTO updateShopDTO) {
        ShopLocation shopLocation = null;
        for (ShopLocation shop : shopLocations) {
            if (shop.getShopId() == shopId) {
                shopLocation = shop;
                break;
            }
        }

        if (shopLocation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Validar los campos del DTO
        if (updateShopDTO.getCountry() != null && updateShopDTO.getCountry().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Country cannot be empty");
        }

        if (updateShopDTO.getCity() != null && updateShopDTO.getCity().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City cannot be empty");
        }

        if (updateShopDTO.getAddress() != null && updateShopDTO.getAddress().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Address cannot be empty");
        }

        // Actualizar solo los campos no nulos
        if (updateShopDTO.getCountry() != null) {
            shopLocation.setCountry(updateShopDTO.getCountry());
        }
        if (updateShopDTO.getCity() != null) {
            shopLocation.setCity(updateShopDTO.getCity());
        }
        if (updateShopDTO.getAddress() != null) {
            shopLocation.setAddress(updateShopDTO.getAddress());
        }

        return ResponseEntity.ok(shopLocation);
    }



    @PostMapping("/shop/addProduct/{productId}")
    public ResponseEntity<Object> addProductShop(@PathVariable int productId, @RequestBody AddProductShopDTO product) {
        BigDecimal price = product.getPrice();
        boolean found = false;
        if (price == null) {
            return ResponseEntity.badRequest().build();
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
                .anyMatch(p -> p.getProductId() == productId);

        if (alreadyAdded) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This product already exists in that shop");
        }


        for (ShopLocation currentShop : shopLocations) {
            if (currentShop.getShopId() == productId) {
                ProductPriceModel newProduct = new ProductPriceModel();
                newProduct.setProductId(productId);
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

        for (ProductPriceModel product : productPrices) {
            if (product.getProductId() == productId) {
                product.setPrice(productPricePatchDTO.getPrice());
                return ResponseEntity.ok(product);
            }
        }

        return ResponseEntity.notFound().build();
    }

}

