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

### Uso de la API Rest Countries

Para acceder a [Rest Countries](https://restcountries.com/) es necesario tener una API key. Se puede obtener una llave gratis [al crear una cuenta](https://restcountries.com/apis/countries). 

El proyecto usa la variable de entorno `REST_COUNTRIES_API_KEY` para guardar esta llave. Una vez se obtiene una llave, puede guardarse  usando uno de los siguientes comandos

#### PowerShell
```PowerShell
$env:REST_COUNTRIES_API_KEY = "your_actual_api_key"
``` 
#### Linux 
```Bash
export REST_COUNTRIES_API_KEY="your_actual_api_key"
```
#### Hardcoded (no recomendado)
Puedes guardar la llave directamente en la variable `apiKey` del archivo [`RestCountriesPaisRepository`](Ejercicio2/src/main/java/com/example/api/RestCountriesPaisRepository.java)

### Ejecución
Requisitos: JDK 21 y Maven.

Para compilar y ejecutar el archivo `Main.java`:

``` Bash
mvn clean compile exec:java
```
Para ejecutar pruebas:
``` Bash
mvn test
```
