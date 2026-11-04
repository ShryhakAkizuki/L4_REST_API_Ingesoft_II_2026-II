package com.example;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        String nombrePais = "colombia";

        PaisRepository paisRepository = new RestCountriesPaisRepository();
        ClimaRepository climaRepository = new OpenMeteoClimaRepository();

        try {
            // Buscar el país
            Pais pais = paisRepository.buscar(nombrePais);
            //System.out.println(pais);

            // Consultar el clima usando las coordenadas de su capital
            Clima clima = climaRepository.actual(
                pais.lat(),
                pais.lon()
            );
            //System.out.println(clima);

            // Imprimir resultado
            System.out.printf(
                Locale.forLanguageTag("es-CO"),
                "%s (Capital %s, %,d hab.): %.1f °C, viento %.1f km/h%n",
                pais.nombre(),
                pais.capital(),
                pais.poblacion(),
                clima.temperatura(),
                clima.viento()
            );

        } catch (PaisNoDisponible | ClimaNoDisponible e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}