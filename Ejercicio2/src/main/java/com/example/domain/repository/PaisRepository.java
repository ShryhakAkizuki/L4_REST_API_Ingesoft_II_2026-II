package com.example.domain.repository;

import com.example.exception.PaisNoDisponible;
import com.example.domain.model.Pais;


public interface PaisRepository {
    Pais buscar(String nombre) throws PaisNoDisponible;
}
