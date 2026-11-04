package com.example;

public interface PaisRepository {
    Pais buscar(String nombre) throws PaisNoDisponible;
}
