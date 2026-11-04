package com.example;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.example.domain.repository.*;
import com.example.domain.model.*;
import com.example.exception.*;
import com.example.api.*;

public class AppTest {

    @Test
    public void buscarColombiaYObtenerClima() throws Exception {

        PaisRepository paisRepository =
            new RestCountriesPaisRepository();

        ClimaRepository climaRepository =
            new OpenMeteoClimaRepository();

        Pais pais = paisRepository.buscar("Colombia");

        assertNotNull(pais);
        assertNotNull(pais.nombre());
        assertNotNull(pais.capital());

        Clima clima = climaRepository.actual(
            pais.lat(),
            pais.lon()
        );

        assertNotNull(clima);

        System.out.println(
            pais.nombre()
            + " (capital " + pais.capital()
            + ", " + pais.poblacion()
            + " hab.): "
            + clima.temperatura()
            + " °C, viento "
            + clima.viento()
            + " km/h"
        );
    }

    @Test
    public void buscarPaisInexistente() {

        PaisRepository paisRepository =
            new RestCountriesPaisRepository();

        try {
            paisRepository.buscar("Wakanda");

            fail("Se esperaba PaisNoDisponible");

        } catch (PaisNoDisponible e) {

            System.out.println(
                e.getMessage()
            );

            assertTrue(e.getMessage() != null);

        }
    }
}