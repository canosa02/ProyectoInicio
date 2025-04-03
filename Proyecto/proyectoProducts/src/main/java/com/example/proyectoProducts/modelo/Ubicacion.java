package com.example.proyectoProducts.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructorpublic class Ubicacion {
    private String pais;
    private String ciudad;
    private String direccion;
}