package com.example.projectProducts.modelo;

import  java.io.Serializable;
import java.math.BigDecimal;

public class ProductPriceModel implements  Serializable {

    private  int  productId;
    private String locationId;
    private BigDecimal price;

    public ProductPriceModel() {
    }

    public ProductPriceModel(BigDecimal price, String locationId, int productId) {
        this.price = price;
        this.locationId = locationId;
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
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

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductPriceModel{" +
                "productId=" + productId +
                ", locationId='" + locationId + '\'' +
                ", price=" + price +
                '}';
    }
}

