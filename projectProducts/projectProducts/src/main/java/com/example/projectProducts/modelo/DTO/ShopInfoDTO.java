package com.example.projectProducts.modelo.DTO;

import java.math.BigDecimal;

public class ShopInfoDTO {
    private int shopId;
    private BigDecimal price;

    public ShopInfoDTO(int shopId, BigDecimal price) {
        this.shopId = shopId;
        this.price = price;
    }

    public int getShopId() {
        return shopId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
