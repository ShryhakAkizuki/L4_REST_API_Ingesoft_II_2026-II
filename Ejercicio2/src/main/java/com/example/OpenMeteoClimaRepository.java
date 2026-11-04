package com.example;

import java.io.IOException;
import java.net.URI;
import java.time.Duration;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OpenMeteoClimaRepository implements ClimaRepository {
    private static final String URL = "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s" + "&current=temperature_2m,wind_speed_10m";
    private final HttpClient http = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(5)).build();
    
    @Override
    public Clima actual(double lat, double lon) throws ClimaNoDisponible {
        HttpRequest req = HttpRequest.newBuilder(URI.create(String.format(URL, lat, lon)))
            .header("Accept", "application/json")
            .timeout(Duration.ofSeconds(5))
            .GET()
            .build();

        try {

            HttpResponse<String> response = http.send(
                req,
                HttpResponse.BodyHandlers.ofString()
            );

            if (response.statusCode() != 200) {
                throw new ClimaNoDisponible(
                    "RestCountries returned HTTP " + response.statusCode()
                );
            }

            JsonObject json = JsonParser.parseString(response.body())
                .getAsJsonObject();

            JsonObject current = json.getAsJsonObject("current");
            double temperatura = current.get("temperature_2m").getAsDouble();
            double viento = current.get("wind_speed_10m").getAsDouble();

            return new Clima(temperatura, viento);
            
        } catch (IOException e) {
            throw new ClimaNoDisponible("Connection error: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ClimaNoDisponible("Request interrupted");
        }
    }
}