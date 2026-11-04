package com.example;

import java.util.Locale;

import com.example.domain.repository.*;
import com.example.domain.model.*;
import com.example.api.*;
import com.example.exception.*;

public class Main {

    public static void main(String[] args) {

        String nombrePais = "colombia";

        PaisRepository paisRepository = new RestCountriesPaisRepository();
        ClimaRepository climaRepository = new OpenMeteoClimaRepository();

        Pais pais;
        Clima clima;

        try {
            // Buscar el país
            pais = paisRepository.buscar(nombrePais);

            // Consultar el clima usando las coordenadas de su capital
            clima = climaRepository.actual(
                pais.lat(),
                pais.lon()
            );

        } catch (PaisNoDisponible | ClimaNoDisponible e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        // Imprimir resultado
        System.out.printf(
            Locale.forLanguageTag("es-CO"),
            "%s (Capital: %s, %,d hab.): %.1f °C, viento %.1f km/h%n",
            pais.nombre(),
            pais.capital(),
            pais.poblacion(),
            clima.temperatura(),
            clima.viento()
        );
    }
}