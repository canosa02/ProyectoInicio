package com.example.projectProducts.modelo.DTO;

public class ProductModelDTO {
    private static int  contador = 1;
    private int productId;
    private String name;

    public ProductModelDTO() {
    }

    public ProductModelDTO( String name) {
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
