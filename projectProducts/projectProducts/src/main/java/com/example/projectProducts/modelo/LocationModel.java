package com.example.projectProducts.modelo;

import java.io.Serializable;

public class LocationModel implements Serializable {

    private String locationId;
    private String country;
    private String city;
    private String adress;

    public LocationModel() {
    }

    public LocationModel(String locationId, String country, String adress, String city) {
        this.locationId = locationId;
        this.country = country;
        this.adress = adress;
        this.city = city;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAdress() {
        return adress;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "LocationModel{" +
                "locationId='" + locationId + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }
}
