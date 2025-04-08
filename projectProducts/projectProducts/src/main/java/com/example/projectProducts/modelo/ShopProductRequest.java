package com.example.projectProducts.modelo;

import java.math.BigDecimal;

public class ShopProductRequest {
    private String locationId;
    private BigDecimal price;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
