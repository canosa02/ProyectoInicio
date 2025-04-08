package com.example.projectProducts.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductPriceModelDTO implements  Serializable {

    private String locationId;
    private BigDecimal price;

    public ProductPriceModelDTO() {
    }

    public ProductPriceModelDTO(BigDecimal price, String locationId) {
        this.price = price;
        this.locationId = locationId;
    }


    public String getLocationId() {
        return locationId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "ProductPriceModel{" +
                ", locationId='" + locationId + '\'' +
                ", price=" + price +
                '}';
    }
}

