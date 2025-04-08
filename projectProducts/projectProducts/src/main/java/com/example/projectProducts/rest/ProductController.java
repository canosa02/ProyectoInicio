package com.example.projectProducts.rest;

import com.example.projectProducts.modelo.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("")

public class ProductController {

    private List<ProductModel> products = new ArrayList<>();
    private List<ShopInfoDTO> shopInfoDTOS = new ArrayList<>();
    private List<ProductNameDTO> productNameDTOS = new ArrayList<>();
    private List<ProductWithShopsDTO> productWithShopsDTOS = new ArrayList<>();

    public ProductController() {
        products.add(new ProductModel("Pizza con piña"));
        products.add(new ProductModel("Agua"));
        products.add(new ProductModel("Agua con gas"));
        products.add(new ProductModel("Naranjas"));

        shopInfoDTOS.add(new ShopInfoDTO(1, new BigDecimal("10.5")));
        shopInfoDTOS.add(new ShopInfoDTO(2, new BigDecimal("9.5")));
        shopInfoDTOS.add(new ShopInfoDTO(3, new BigDecimal("11")));
        shopInfoDTOS.add(new ShopInfoDTO(4, new BigDecimal("10")));
    }

    @GetMapping("/products")
    public List<ProductWithShopsDTO> getProductsWithShops() {
        List<ProductWithShopsDTO> producto = new ArrayList<>();

        for(ProductModel productModel : products) {
            producto.add(new ProductWithShopsDTO(productModel.getProductId(), productModel.getName(), shopInfoDTOS));
        }

        return producto;
    }


    @GetMapping("/product/{productId}")
    public List<ProductWithShopsDTO> getProductsWithId(@PathVariable Integer productId) {

        for (ProductModel productModel : products) {
            if (Integer.valueOf(productModel.getProductId()).equals(productId)) {
                return List.of(new ProductWithShopsDTO(
                        productModel.getProductId(),
                        productModel.getName(),
                        shopInfoDTOS
                ));
            }
        }
        return Collections.emptyList();
    }

    @GetMapping("/product/filter")
    public List<ProductWithShopsDTO> getProductsWithFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal priceMin,
            @RequestParam(required = false) BigDecimal priceMax) {

        List<ProductWithShopsDTO> filteredProducts = new ArrayList<>();


        for (ProductModel product : products) {
            List<ShopInfoDTO> filteredShops = new ArrayList<>(shopInfoDTOS);

            if (priceMin != null) {
                filteredShops.removeIf(shop -> shop.getPrice().compareTo(priceMin) < 0);
            }

            if (priceMax != null) {
                filteredShops.removeIf(shop -> shop.getPrice().compareTo(priceMax) > 0);
            }

            if (!filteredShops.isEmpty()) {
                filteredProducts.add(new ProductWithShopsDTO(
                        product.getProductId(),
                        product.getName(),
                        filteredShops
                ));
            }
        }
        if (name != null) {
            filteredProducts.removeIf(p -> !p.getName().toLowerCase().contains(name.toLowerCase()));
        }

        return filteredProducts;
    }


    @PostMapping("/product")
    public ProductModel addProduct(@RequestBody ProductNameDTO productNameDTO) {

        ProductModel newProduct = new ProductModel();
        newProduct.setProductId(ProductModel.getNextId());
        newProduct.setName(productNameDTO.getName());
        products.add(newProduct);

        return newProduct;
    }


    @DeleteMapping({"/product/{productId}"})
    public void deleteProduct(@PathVariable int id) {
        for (ProductModel product : products) {
            if (product.getProductId() == id) {
                products.remove(product);
            }
        }
    }

    @PutMapping("/product/{productId}") //no funciona pero es acorde a la documentación
    public ResponseEntity<ProductModel> updateProducts(@PathVariable int productId, @RequestBody ProductNameDTO productNameDTO) {
        for (int i = 0; i < products.size(); i++) {
            ProductModel currentProduct = products.get(i);
            if (currentProduct.getProductId() == productId) {
                if (productNameDTO.getName() == null) {
                    return ResponseEntity.badRequest().build();
                }
                currentProduct.setName(productNameDTO.getName());
                return ResponseEntity.ok(currentProduct);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
