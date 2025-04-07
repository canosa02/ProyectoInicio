package com.example.projectProducts.rest;

import com.example.projectProducts.modelo.ProductModel;
import com.example.projectProducts.modelo.ProductWithShopsDTO;
import com.example.projectProducts.modelo.ShopInfoDTO;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")

public class ProductController {

    private List<ProductModel> products = new ArrayList<>();

    public ProductController(){
        products.add(new ProductModel("Pizza con piña"));
        products.add(new ProductModel("Agua"));
        products.add(new ProductModel("Agua con gas"));
        products.add(new ProductModel("Naranjas"));
    }

    @GetMapping("")
    public List<ProductWithShopsDTO> getProductsWithShops() {
        List<ProductWithShopsDTO> producto = new ArrayList<>();

        // Producto 1: Agua
        List<ShopInfoDTO> aguaShops = List.of(
                new ShopInfoDTO(1, new BigDecimal("10.5")),
                new ShopInfoDTO(2, new BigDecimal("9.5")),
                new ShopInfoDTO(3, new BigDecimal("11"))

        );
        producto.add(new ProductWithShopsDTO(2, "CocaCola", aguaShops));
        producto.add(new ProductWithShopsDTO(3, "Agua con gas ", aguaShops));
        producto.add(new ProductWithShopsDTO(4, "Fanta", aguaShops));
        producto.add(new ProductWithShopsDTO(5, "Limón", aguaShops));
        producto.add(new ProductWithShopsDTO(6, "Fresa", aguaShops));
        // Producto 2: Pizza con piña
        List<ShopInfoDTO> pizzaShops = List.of(
                new ShopInfoDTO(1,new BigDecimal("10.5"))
        );
        producto.add(new ProductWithShopsDTO(10, "Pizza con piña", pizzaShops));

        return producto;
    }


    @GetMapping("/{id}")
    public List<ProductWithShopsDTO> getProductsWithid(@PathVariable("id") Integer id) {
        List<ProductWithShopsDTO> producto = new ArrayList<>();

        // Producto 1: Agua
        List<ShopInfoDTO> aguaShops = List.of(
                new ShopInfoDTO(1, new BigDecimal("8.5")),
                new ShopInfoDTO(2, new BigDecimal("10.5"))
        );
        producto.add(new ProductWithShopsDTO(2, "Agua", aguaShops));

        // Producto 2: Pizza con piña
        List<ShopInfoDTO> pizzaShops = List.of(
                new ShopInfoDTO(1, new BigDecimal("10.5")
        ));
        producto.add(new ProductWithShopsDTO(10, "Pizza con piña", pizzaShops));

        for(ProductWithShopsDTO p : producto){
            if(p.getProductId() == id) {
                return List.of(p); // Retornar solo el producto con el id solicitado
            }
        }
        return new ArrayList<>(); // Si no se encuentra, devolver todos los productos
    }

    @GetMapping("/filter")
    public List<ProductWithShopsDTO> getProductsWithFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal priceMin,
            @RequestParam(required = false) BigDecimal priceMax){

        List<ProductWithShopsDTO> product = new ArrayList<>();
        // Agua
        List<ShopInfoDTO> aguaShops = List.of(
                new ShopInfoDTO(1, new BigDecimal("9")),
                new ShopInfoDTO(2, new BigDecimal("8.5")),
                new ShopInfoDTO(3, new BigDecimal("12"))
        );
        product.add(new ProductWithShopsDTO(2, "Agua", aguaShops));
        // Pizza
        List<ShopInfoDTO> pizzaShops = List.of(
                new ShopInfoDTO(1, new BigDecimal("10.5"))
        );
        product.add(new ProductWithShopsDTO(10, "Pizza con piña", pizzaShops));

        // Filtrado por nombre
        if (name != null) {
            product.removeIf(p -> !p.getName().toLowerCase().contains(name.toLowerCase()));
        }

        // Filtrado por precio mínimo
        if (priceMin != null) {
            for (ProductWithShopsDTO p : product) {
                List<ShopInfoDTO> filteredShops = p.getShop().stream()
                        .filter(shop -> shop.getPrice().compareTo(priceMin) >= 0)
                        .collect(Collectors.toList());

                // Actualiza el producto con esas tiendas
                if (!filteredShops.isEmpty()) {
                    p.setShop(filteredShops);
                } else {
                    product.remove(p);
                }
            }
        }

        if (priceMax != null) {
            for (ProductWithShopsDTO p : product) {
                // Filtrar solo las tiendas cuyo precio sea menor o igual al precio máximo
                List<ShopInfoDTO> filteredShops = p.getShop().stream()
                        .filter(shop -> shop.getPrice().compareTo(priceMax) <= 0)
                        .collect(Collectors.toList());

                if (!filteredShops.isEmpty()) {
                    p.setShop(filteredShops);
                } else {
                    product.remove(p);
                }
            }
        }

        return product;
    }


    @PostMapping("")
    public ProductModel addProduct(@RequestBody ProductModel productModel){
        productModel.setProductId(ProductModel.getNextId());
        products.add(productModel);

        return productModel;
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
