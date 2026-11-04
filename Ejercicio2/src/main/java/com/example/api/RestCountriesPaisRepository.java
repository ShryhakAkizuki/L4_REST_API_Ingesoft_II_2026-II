package com.example.api;

import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

import com.example.domain.repository.PaisRepository;
import com.example.domain.model.Pais;
import com.example.exception.PaisNoDisponible;

public class RestCountriesPaisRepository implements PaisRepository{

    // https://api.restcountries.com/countries/v5?q=canada
    private static final String URL = "https://api.restcountries.com/countries/v5/names.common?q=%s" 
        + "&response_fields=names.common,population,capitals,coordinates.lat,coordinates.lng";

    private final HttpClient http = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(5)).build();

    private final String apiKey = System.getenv("REST_COUNTRIES_API_KEY");

    @Override
    public Pais buscar(String nombre) throws PaisNoDisponible {
        HttpRequest req = HttpRequest.newBuilder(URI.create(String.format(URL, nombre)))
            .header("Authorization", apiKey) // cambiar API key
            .timeout(Duration.ofSeconds(5))
            .GET()
            .build();

        try {
            HttpResponse<String> response = http.send(
                req,
                HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() != 200) {
                throw new PaisNoDisponible(
                    "Open-Meteo returned HTTP " + response.statusCode()
                );
            }

            JsonObject json = JsonParser.parseString(response.body())
                .getAsJsonObject();

            JsonObject data = json.getAsJsonObject("data");
            JsonArray objects = data.getAsJsonArray("objects");
            if (objects.isEmpty()) { // porque puede retornar un objeto vacio
                throw new PaisNoDisponible(
                    "No se encontró el país: " + nombre
                );
            }
            JsonObject country = objects.get(0).getAsJsonObject();
            
            String nombre_pais = country
                .getAsJsonObject("names")
                .get("common")
                .getAsString();
            long poblacion = country
                .get("population")
                .getAsLong();

            JsonArray capitals = country.getAsJsonArray("capitals");
            JsonObject capital = capitals.get(0).getAsJsonObject();
            String nombreCapital = capital.get("name").getAsString();
            JsonObject coordinates = capital.getAsJsonObject("coordinates");
            double capital_lat = coordinates.get("lat").getAsDouble();
            double capital_lon = coordinates.get("lng").getAsDouble();
            
            return new Pais(nombre_pais, nombreCapital, poblacion, capital_lat, capital_lon);
            
        } catch (IOException e) {
            throw new PaisNoDisponible("Connection error: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new PaisNoDisponible("Request interrupted");
        }
    }
}
