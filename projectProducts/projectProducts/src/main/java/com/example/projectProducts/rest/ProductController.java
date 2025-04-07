package com.example.projectProducts.rest;

import com.example.projectProducts.modelo.ProductModel;
import com.example.projectProducts.modelo.ProductPriceModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

@RestController
@RequestMapping("/product")
public class ProductController {

    private List<ProductModel> products = new ArrayList<>();
    private List<ProductPriceModel> productprices = new ArrayList<>();

    public ProductController(){
        products.add(new ProductModel(1,"Pizza con piña"));
        products.add(new ProductModel(2,"Agua"));
        products.add(new ProductModel(1,"Agua con gas"));
        products.add(new ProductModel(1,"Naranjas"));
        productprices.add(new ProductPriceModel(new BigDecimal("7.40"), "A1", 1));
    }

    @GetMapping("/{id}")
    public ProductModel getProduct(@PathVariable int id){
        for(ProductModel product : products){
            if(product.getProductId() == id){
                return product;
            }
        }
        return null;
    }

    @GetMapping("")
    public List<ProductModel> getAllProducts() {return products;}

    @PostMapping("")
    public String addProduct(@RequestBody ProductModel productModel){
        products.add(productModel);
        return "OK";
    }


    @DeleteMapping({"/{id}"})
    public  String deleteProduct(@PathVariable int id){
        for(ProductModel product : products){
            if(product.getProductId() == id){
                products.remove(product);
                return "HTTP/1.1 200 OK";
            }
        }
        return null;
    }

}
