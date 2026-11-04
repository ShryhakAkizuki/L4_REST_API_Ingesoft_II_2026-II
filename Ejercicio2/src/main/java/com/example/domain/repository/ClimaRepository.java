package com.example.domain.repository;

import com.example.exception.ClimaNoDisponible;
import com.example.domain.model.Clima;

public interface ClimaRepository { // el dominio define el contrato
    Clima actual(double latitud, double longitud) throws ClimaNoDisponible;
}

