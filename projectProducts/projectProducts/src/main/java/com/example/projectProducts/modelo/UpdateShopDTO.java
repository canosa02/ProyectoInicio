package com.example.projectProducts.modelo;

//@JsonIgnoreProperties(ignoreUnknown = false)
public class UpdateShopDTO {
    private String country;
    private String city;
    private String address;

    public UpdateShopDTO() {
    }

    public UpdateShopDTO(String country, String city, String address) {
        this.country = country;
        this.city = city;
        this.address = address;
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
