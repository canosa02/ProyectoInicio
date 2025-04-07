package com.example.projectProducts.modelo;

public class ShopInfoDTO {
    private int shopId;
    private String price;

    public ShopInfoDTO(int shopId, String price) {
        this.shopId = shopId;
        this.price = price;
    }

    public int getShopId() {
        return shopId;
    }

    public String getPrice() {
        return price;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
