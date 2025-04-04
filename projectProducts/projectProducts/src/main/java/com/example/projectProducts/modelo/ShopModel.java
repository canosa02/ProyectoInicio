package com.example.projectproducts.project_products.modelo;

import java.io.Serializable;



public class ShopModel implements Serializable {

    private int idShop;
    private String idUbicacion;

    public ShopModel() {
    }

    public ShopModel(int id_shop, String idUbicacion) {
        this.idShop = id_shop;
        this.idUbicacion = idUbicacion;
    }

    public int getIdShop() {
        return idShop;
    }

    public void setIdShop(int idShop) {
        this.idShop = idShop;
    }

    public String getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(String idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    @Override
    public String toString() {
        return "Tienda{" +
                "id_shop=" + idShop +
                ", id_ubicacion='" + idUbicacion + '\'' +
                '}';
    }
}
