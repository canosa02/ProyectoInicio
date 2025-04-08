package com.example.projectProducts.modelo;

import java.math.BigDecimal;

public class ShopLocation {
    private static int contador= 1;
    private int shopId;
    private String locationId;
    private String country;
    private String city;
    private String address;

    public ShopLocation() {
    }

    public ShopLocation(String locationId, String country, String city, String address) {
        this.shopId = contador++;
        this.locationId = locationId;
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

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
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
