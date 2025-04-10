package com.example.projectProducts.rest;

import com.example.projectProducts.modelo.DTO.ProductModelDTO;
import com.example.projectProducts.modelo.ProductModel;
import com.example.projectProducts.modelo.DTO.ProductNameDTO;
import com.example.projectProducts.modelo.DTO.ProductWithShopsDTO;
import com.example.projectProducts.modelo.DTO.ShopInfoDTO;
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

public class ProductController {

    private List<ProductModelDTO> products = new ArrayList<>();
    private List<ShopInfoDTO> shopInfoDTOS = new ArrayList<>();


    public ProductController() {
        products.add(new ProductModelDTO("Pizza con piña"));
        products.add(new ProductModelDTO("Agua"));
        products.add(new ProductModelDTO("Agua con gas"));
        products.add(new ProductModelDTO("Naranjas"));

        shopInfoDTOS.add(new ShopInfoDTO(1, new BigDecimal("10.5")));
        shopInfoDTOS.add(new ShopInfoDTO(2, new BigDecimal("9.5")));
        shopInfoDTOS.add(new ShopInfoDTO(3, new BigDecimal("11")));
        shopInfoDTOS.add(new ShopInfoDTO(4, new BigDecimal("10")));
    }

    @GetMapping("/products")
    public List<ProductWithShopsDTO> getProductsWithShops() {
        List<ProductWithShopsDTO> producto = new ArrayList<>();

        for (ProductModelDTO productModel : products) {
            producto.add(new ProductWithShopsDTO(productModel.getProductId(), productModel.getName(), shopInfoDTOS));
        }

        return producto;
    }


    @GetMapping("/product/{productId}")
    public List<ProductWithShopsDTO> getProductsWithId(@PathVariable Integer productId) {
        for (ProductModelDTO productModel : products) {
            if (Integer.valueOf(productModel.getProductId()).equals(productId)) {
                return List.of(new ProductWithShopsDTO(
                        productModel.getProductId(),
                        productModel.getName(),
                        shopInfoDTOS
                ));
            }
        }
        return Collections.emptyList(); //debería devolver 404
    }


    @PostMapping("/product")
    public ResponseEntity<ProductModelDTO> addProduct(@RequestBody ProductNameDTO productNameDTO) {
        if (productNameDTO.getName() == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("That field does not exist");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        ProductModelDTO newProduct = new ProductModelDTO();
        newProduct.setProductId(ProductModelDTO.getNextId());
        newProduct.setName(productNameDTO.getName()); //hay que poner límite al name
        products.add(newProduct);

        return ResponseEntity.ok(newProduct);
    }


    @DeleteMapping("/product/{productId}")
    public ResponseEntity<ProductModelDTO> deleteProduct(@PathVariable int productId) {
        boolean removed = products.removeIf(product -> product.getProductId() == productId);

        if (removed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }


    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductModelDTO> updateProducts(@Validated @PathVariable int productId, @RequestBody ProductNameDTO productNameDTO) {
        for (int i = 0; i < products.size(); i++) {
            ProductModelDTO currentProduct = products.get(i);
            if (currentProduct.getProductId() == productId) {
                if (productNameDTO.getName() == null) {
//                    return ResponseEntity.status(HttpStatus.CONFLICT).body("That field does not exist");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                } else if (productNameDTO.getName().isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                currentProduct.setName(productNameDTO.getName());
                return ResponseEntity.ok(currentProduct);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/products/filter")
    public List<ProductWithShopsDTO> getProductsWithFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal priceMin,
            @RequestParam(required = false) BigDecimal priceMax) {

        List<ProductWithShopsDTO> filteredProducts = new ArrayList<>();


        for (ProductModelDTO product : products) {
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

        if (filteredProducts.isEmpty()){
            return Collections.emptyList();

        }else{
            return filteredProducts;
        }

    }



}
