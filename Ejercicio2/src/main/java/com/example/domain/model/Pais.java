package com.example.domain.model;

public record Pais(
    String nombre, 
    String capital, 
    long poblacion,
    double lat,
    double lon)
{}
