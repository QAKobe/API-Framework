package com.get;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Movies {

    @Test
    public void getMovie(){

        RestAssured.baseURI = "http://www.omdbapi.com";
        String apiKey = "d95e991e";
        String year = "1997";
        String movie = "Titanic";

        given().accept(ContentType.JSON)
                .queryParam("apikey", apiKey)
                .queryParam("t", movie)
                .queryParam("y", year)
                .when()
                .get()
                .then().statusCode(200)
                .log().body()
                .extract().response();

    }

}
