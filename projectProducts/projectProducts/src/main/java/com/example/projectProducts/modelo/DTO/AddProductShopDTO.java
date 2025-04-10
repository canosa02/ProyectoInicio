package com.example.projectProducts.modelo.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

public class AddProductShopDTO implements  Serializable {

    private BigDecimal price;

    public AddProductShopDTO() {
    }

    public AddProductShopDTO(BigDecimal price, String locationId, Integer productId) {
        this.price = price;
    }



    public BigDecimal getPrice() {
        return price;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "ProductPriceModel{" +
                ", price=" + price +
                '}';
    }
}

