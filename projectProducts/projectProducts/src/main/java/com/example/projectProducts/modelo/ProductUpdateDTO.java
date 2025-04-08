package com.example.projectProducts.modelo;

public class ProductUpdateDTO {
    private String name;

    public ProductUpdateDTO(){

    }
    public ProductUpdateDTO(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
