package com.example.projectProducts.modelo;

import java.io.Serializable;



public class ShopModel implements Serializable {

    private int shopId;
    private String locationId;

    public ShopModel() {
    }

    public ShopModel(int shopId, String locationId) {
        this.shopId = shopId;
        this.locationId = locationId;
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

    @Override
    public String toString() {
        return "Tienda{" +
                "id_shop=" + shopId +
                ", id_ubicacion='" + locationId + '\'' +
                '}';
    }
}
