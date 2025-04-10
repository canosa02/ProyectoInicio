package com.example.projectProducts.modelo.DTO;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductPriceModelDTO implements Serializable {

    private  int  productId;
    private BigDecimal price;

    public ProductPriceModelDTO() {
    }

    public ProductPriceModelDTO(int productId, BigDecimal price) {
        this.productId = productId;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }


    public BigDecimal getPrice() {
        return price;
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
                ", price=" + price +
                '}';
    }
}
