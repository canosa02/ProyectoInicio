package com.example.projectProducts.modelo;

public class ShopLocation {
    private int shopId;
    private String locationId;
    private String country;
    private String city;
    private String address;
    private int numProducts;

    public ShopLocation() {
    }

    public ShopLocation(int shopId, String locationId, String country, String city, String address, int numProducts) {
        this.shopId = shopId;
        this.locationId = locationId;
        this.country = country;
        this.city = city;
        this.address = address;
        this.numProducts = numProducts;
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

    public int getNumProducts() {
        return numProducts;
    }

    public void setNumProducts(int numProducts) {
        this.numProducts = numProducts;
    }
}
