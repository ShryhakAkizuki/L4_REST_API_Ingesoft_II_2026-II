package com.example;

public interface ClimaRepository { // el dominio define el contrato
    Clima actual(double latitud, double longitud) throws ClimaNoDisponible;
}

