package com.ejemplo.demo.api.dto;

import jakarta.validation.constraints.NotBlank;

public class ProductoRequest {

    @NotBlank
    private String nombre;

    private Double precio;

    private Long categoriaId;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
}
