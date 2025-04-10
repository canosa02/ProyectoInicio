package com.example.projectProducts.rest;


import com.example.projectProducts.modelo.DTO.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("")
public class ShopController {

    private List<ShopLocationDTO> shopLocationDTOS = new ArrayList<>();
    private List<ProductPriceModelDTO> productPriceModelDTOS = new ArrayList<>();


    public ShopController() {

        shopLocationDTOS.add(new ShopLocationDTO("España", "A Coruña", "Los Mallos, 10"));
        shopLocationDTOS.add(new ShopLocationDTO("Argentina", "Buenos Aires", "Dirección inventada"));
        shopLocationDTOS.add(new ShopLocationDTO("España", "Santiago", "Av. Toledo"));

        productPriceModelDTOS.add(new ProductPriceModelDTO(1, new BigDecimal("25.50")));
        productPriceModelDTOS.add(new ProductPriceModelDTO(2, new BigDecimal("25.50")));
        productPriceModelDTOS.add(new ProductPriceModelDTO(3, new BigDecimal("15.00")));
    }


    @GetMapping("/shops")
    public List<ShopLocationDTO> getAllShops() {
        return shopLocationDTOS;
    }

    @GetMapping("/shop/{shopId}")
    public List<ShopLocationDTO> getShopLocationDTO(@PathVariable Integer shopId) {

        for (ShopLocationDTO shopLocationDTO : shopLocationDTOS) {
            if (shopLocationDTO.getShopId() == shopId) {
                return List.of(new ShopLocationDTO(
                        shopLocationDTO.getShopId(),
                        shopLocationDTO.getCountry(),
                        shopLocationDTO.getCity(),
                        shopLocationDTO.getAddress()
                ));
            }
        }

        return Collections.emptyList();  // Devuelve una lista vacía si no se encuentra la tienda
    }


    @PostMapping("/shop")
    public ResponseEntity<ShopLocationDTO> addShop(@RequestBody ShopAddDTO newShopDTO) {

        ShopLocationDTO newShopLocation = new ShopLocationDTO();

        for (ShopLocationDTO shop : shopLocationDTOS) {
//           Comprobación para que la calle no exista dos veces,para ello se comprueba que sea en la misma ciudad y país.

            if (shop.getCountry().equalsIgnoreCase(newShopDTO.getCountry()) && shop.getCity().equalsIgnoreCase(newShopDTO.getCity()) && shop.getAddress().equalsIgnoreCase(newShopDTO.getAddress())) {
                String message = "That field already exists";
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        newShopLocation.setShopId(ShopLocationDTO.getNextId());
        newShopLocation.setCountry(newShopDTO.getCountry());
        newShopLocation.setCity(newShopDTO.getCity());
        newShopLocation.setAddress(newShopDTO.getAddress());
        shopLocationDTOS.add(newShopLocation);

        return ResponseEntity.status(HttpStatus.CREATED).body(newShopLocation);
    }


    @PostMapping("/shop/addProduct/{productId}")
    public ResponseEntity<ProductPriceModelDTO> addProductShop(@PathVariable int productId, @RequestBody AddProductShopDTO product) {
        BigDecimal price = product.getPrice();


        if (price == null) {
            return ResponseEntity.badRequest().build();
        }

        if (price.compareTo(BigDecimal.ZERO) < 0) {
//            return ResponseEntity.badRequest().body("The price cannot be negative");
            return ResponseEntity.badRequest().build();
        }

        boolean found = false;
        for (ProductPriceModelDTO productsList : productPriceModelDTOS) {
            if (productId == productsList.getProductId()) {
                found = true;
                break;
            }
        }

        if (!found) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        for (ProductPriceModelDTO existingProduct : productPriceModelDTOS) {
            if (existingProduct.getProductId() == productId && existingProduct.getPrice().equals(price)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        for (ShopLocationDTO currentShop : shopLocationDTOS) {
            if (currentShop.getShopId() == productId) {
                ProductPriceModelDTO newProduct = new ProductPriceModelDTO();
                newProduct.setProductId(productId);
                newProduct.setPrice(price);

                productPriceModelDTOS.add(newProduct);

                return ResponseEntity.ok(newProduct);
            }
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/shop/{shopId}")
    public ResponseEntity<ShopLocationDTO> deleteShop(@PathVariable int shopId) {
        boolean removed = shopLocationDTOS.removeIf(shop -> shop.getShopId() == shopId);

        if (removed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/shop/{shopId}")
    public ResponseEntity<ShopLocationDTO> updateShop(@PathVariable int shopId, @Validated @RequestBody UpdateShopDTO updateShopDTO) {
        for (int i = 0; i < shopLocationDTOS.size(); i++) {
            ShopLocationDTO currentShop = shopLocationDTOS.get(i);

            if (currentShop.getShopId() == shopId) {

                if (updateShopDTO.getCountry() == null ||
                        updateShopDTO.getCity() == null || updateShopDTO.getAddress() == null) {
//                    return ResponseEntity.badRequest().body("The fields cannot be empty");
                    return ResponseEntity.badRequest().build();
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
    public ResponseEntity<ShopLocationDTO> partialUpdateShop(@PathVariable int shopId, @RequestBody UpdateShopDTO updateShopDTO) {
        ShopLocationDTO shopLocation = null;
        for (ShopLocationDTO shop : shopLocationDTOS) {
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
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Country cannot be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (updateShopDTO.getCity() != null && updateShopDTO.getCity().trim().isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("City cannot be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if (updateShopDTO.getAddress() != null && updateShopDTO.getAddress().trim().isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Address cannot be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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


    @PatchMapping("/shop/{shopId}/product/{productId}")
    public ResponseEntity<ProductPriceModelDTO> updateProductPrice(@PathVariable int shopId, @PathVariable int productId, @RequestBody ProductPricePatchDTO productPricePatchDTO) {

        if (productPricePatchDTO.getPrice() == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Price cannot be empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        ShopLocationDTO shopLocation = shopLocationDTOS.stream()
                .filter(shop -> shop.getShopId() == shopId)
                .findFirst()
                .orElse(null);

        if (shopLocation == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shop not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        for (ProductPriceModelDTO product : productPriceModelDTOS) {
            if (product.getProductId() == productId) {
                product.setPrice(productPricePatchDTO.getPrice());
                return ResponseEntity.ok(product);
            }
        }

        return ResponseEntity.notFound().build();
    }


    @GetMapping("/shop/filter")
    public ResponseEntity<List<ShopLocationDTO>> getShopLocationWithFilters(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String address) {

        List<ShopLocationDTO> shops = new ArrayList<>(shopLocationDTOS);
        if(city == null && country == null && address == null){
            return ResponseEntity.badRequest().build();
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

        if(shops.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shops);
    }


}

