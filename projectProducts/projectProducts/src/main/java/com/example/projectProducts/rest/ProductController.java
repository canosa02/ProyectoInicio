package com.example.projectProducts.rest;

import com.example.projectProducts.modelo.ProductModel;
import com.example.projectProducts.modelo.ProductNameDTO;
import com.example.projectProducts.modelo.ProductWithShopsDTO;
import com.example.projectProducts.modelo.ShopInfoDTO;
import org.springframework.http.HttpStatus;
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
    private List<ProductNameDTO> productsUpdate = new ArrayList<>();
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

        for (ProductModel productModel : products) {
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
    public ResponseEntity<Object> addProduct(@RequestBody ProductNameDTO productNameDTO) {
        if (productNameDTO.getName() == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("That field does not exist");
        }

        ProductModel newProduct = new ProductModel();
        newProduct.setProductId(ProductModel.getNextId());
        newProduct.setName(productNameDTO.getName());
        products.add(newProduct);

        return ResponseEntity.ok(newProduct);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId) {
        boolean removed = products.removeIf(product -> product.getProductId() == productId);

        if (removed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<Object> updateProducts(@PathVariable int productId, @RequestBody ProductNameDTO productNameDTO) {
        for (int i = 0; i < products.size(); i++) {
            ProductModel currentProduct = products.get(i);
            if (currentProduct.getProductId() == productId) {
                if (productNameDTO.getName() == null) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("That field does not exist");
                }
                currentProduct.setName(productNameDTO.getName());
                return ResponseEntity.ok(currentProduct);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
