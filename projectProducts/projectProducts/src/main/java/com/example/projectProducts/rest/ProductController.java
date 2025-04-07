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
    public List<ProductWithShopsDTO> getProductsWithShops() {
        List<ProductWithShopsDTO> producto = new ArrayList<>();

        // Producto 1: Agua
        List<ShopInfoDTO> aguaShops = List.of(
                new ShopInfoDTO(1, "10.5"),
                new ShopInfoDTO(2, "9.5")
        );
        producto.add(new ProductWithShopsDTO(2, "Agua", aguaShops));

        // Producto 2: Pizza con piña
        List<ShopInfoDTO> pizzaShops = List.of(
                new ShopInfoDTO(1, "10.5")
        );
        producto.add(new ProductWithShopsDTO(10, "Pizza con piña", pizzaShops));

        return producto;
    }


    @GetMapping("/{id}")
    public List<ProductWithShopsDTO> getProductsWithid(@PathVariable("id") Integer id) {
        List<ProductWithShopsDTO> producto = new ArrayList<>();

        // Producto 1: Agua
        List<ShopInfoDTO> aguaShops = List.of(
                new ShopInfoDTO(1, "10.5"),
                new ShopInfoDTO(2, "9.5")
        );
        producto.add(new ProductWithShopsDTO(2, "Agua", aguaShops));

        // Producto 2: Pizza con piña
        List<ShopInfoDTO> pizzaShops = List.of(
                new ShopInfoDTO(1, "10.5")
        );
        producto.add(new ProductWithShopsDTO(10, "Pizza con piña", pizzaShops));

        for(ProductWithShopsDTO p : producto){
            if(p.getProductId() == id) {
                return List.of(p); // Retornar solo el producto con el id solicitado
            }
        }
        return new ArrayList<>(); // Si no se encuentra, devolver todos los productos
    }

    @GetMapping("/{name}")
    public List<ProductModel> getProduct(@PathVariable String name){
        List<ProductModel> result = new ArrayList<>();
        for(ProductModel product : products){
            if(product.getName().toLowerCase().contains(name.toLowerCase())){
                result.add(product);
            }
        }

        return result;
    }

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
