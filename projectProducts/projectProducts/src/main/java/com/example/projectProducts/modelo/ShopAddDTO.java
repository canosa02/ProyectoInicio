package com.example.projectProducts.modelo;

public class ShopAddDTO {
    private String locationId;
    private String country;
    private String city;
    private String address;

    public ShopAddDTO() {
    }

    public ShopAddDTO(String locationId, String country, String city, String address) {
        this.locationId = locationId;
        this.country = country;
        this.city = city;
        this.address = address;
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
