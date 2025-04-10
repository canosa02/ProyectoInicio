package com.example.projectProducts.modelo.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class ShopLocationDTO {

    private static int contador= 1;
    private int shopId;
    private String country;
    private String city;
    private String address;

    public ShopLocationDTO() {
    }

    public ShopLocationDTO(String country, String city, String address) {
        this.shopId = contador++;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public ShopLocationDTO(int shopId, String country, String city, String address) {
        this.shopId = shopId;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public static int getNextId(){
        return contador++;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
