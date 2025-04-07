package com.example.projectProducts.modelo;

import java.util.List;

public class ProductModel {
    private static int  contador = 1;
    private int productId;
    private String name;

    public ProductModel() {
    }

    public ProductModel( String name) {
        this.productId = contador++;
        this.name = name;
    }

    public static int getNextId(){
        return contador++;
    }
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                '}';
    }
}
