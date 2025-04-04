package com.example.projectProducts.modelo;

public class ProductModel {

    private int idProducts;
    private String name;

    public ProductModel() {
    }

    public ProductModel(int idProducts, String name) {
        this.idProducts = idProducts;
        this.name = name;
    }

    public int getIdProducts() {
        return idProducts;
    }

    public void setIdProducts(int idProducts) {
        this.idProducts = idProducts;
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
                "idProducts=" + idProducts +
                ", name='" + name + '\'' +
                '}';
    }
}
