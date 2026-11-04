# Laboratorio 4 - REST APIs

Entrega del Laboratorio 4 del curso **Ingeniería de software II** (2026-II).

## Integrantes

| # | Nombre |
|---|--------|
| 1 | Oscar Leonardo Riveros Perez |
| 2 | Jader Camilo Rodriguez Arboleda |
| 3 | Juan Luis Arteaga Mantovani |
| 4 | Daniel Oñate Hernandez |

## Contenido

| Carpeta | Descripción |
|---------|-------------|
| [`Ejercicio #0/`](Ejercicio%20%230/) | Colección de Postman para el Ejercicio 0 (peticiones HTTP básicas con httpbin). |
| [`Ejercicio #1/`](Ejercicio%20%231/) | Colección y entorno de Postman para el Ejercicio 1. |
| [`Ejercicio2/`](Ejercicio2/) | Proyecto Java (Maven) que consulta un país con [Rest Countries API](https://restcountries.com) y su clima con [Open-Meteo](https://open-meteo.com). |
| [`Ejercicio #3/`](Ejercicio%20%233/) | Colección de Postman para el Ejercicio 3 (peticiones GraphQL). |
| [`Ejercicio #4/`](Ejercicio%20%234/) | API de Préstamos de Equipos de Laboratorio: especificación [OpenAPI](Ejercicio%20%234/openapi_prestamos.yaml) y colección de Postman. |

## Ejercicio 2 — Ejecución

Requisitos: JDK 21 y Maven.

```bash
cd Ejercicio2
mvn compile exec:java
```
