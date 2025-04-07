package com.example.projectProducts.rest;

import com.example.projectProducts.modelo.ProductModel;
import com.example.projectProducts.modelo.ProductWithShopsDTO;
import com.example.projectProducts.modelo.ShopInfoDTO;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private List<ProductModel> products = new ArrayList<>();

    public ProductController(){
        products.add(new ProductModel(1,"Pizza con piña"));
        products.add(new ProductModel(2,"Agua"));
        products.add(new ProductModel(3,"Agua con gas"));
        products.add(new ProductModel(4,"Naranjas"));

    }

    @GetMapping("")
    public List<ProductWithShopsDTO> getProducts() {
        List<ProductWithShopsDTO> producto = new ArrayList<>();

        List<ShopInfoDTO> aguaShops = List.of(
                new ShopInfoDTO(1, "10.5€"),
                new ShopInfoDTO(2, "9.5$")
        );
        producto.add(new ProductWithShopsDTO(2, "Agua", aguaShops));

        List<ShopInfoDTO> pizzaShops = List.of(
                new ShopInfoDTO(1, "10.5€")
        );
        producto.add(new ProductWithShopsDTO(10, "Pizza con piña", pizzaShops));

        return producto;
    }
    @PostMapping("")
    public String addProduct(@RequestBody ProductModel productModel){
        products.add(productModel);
        return "OK";
    }


   // @GetMapping("/{id}");



}
