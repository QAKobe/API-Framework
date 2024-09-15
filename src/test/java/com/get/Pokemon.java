package com.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class Pokemon {
    /*

                This class shows next level nested json objects
                The code below finds pikachu and gets its url and performs get request for that url

                when going through this code, make sure briefly explain it.

             */
    @Test
    public void getPikachu() {

        RestAssured.baseURI = "https://pokeapi.co";
        RestAssured.basePath = "api/v2/pokemon";

        Response response = RestAssured.given().accept("application/json")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });


        String pikachuURL = "";

        while (parsedResponse.get("next") != null) {
            response = RestAssured.given().accept("application/json")
                    .when()
                    .get((String) parsedResponse.get("next"))
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();
            parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
            });
            Object object = parsedResponse.get("results");
            List<Map<String, Object>> results = (List<Map<String, Object>>) object;
            for (int i = 0; i < results.size(); i++) {

                Map<String, Object> map = results.get(i);
                if (map.get("name").equals("pikachu")) {
                    pikachuURL = (String) map.get("url");
                    break;
                }

            }

        }

        response = RestAssured.given().accept("application/json")
                .when()
                .get(pikachuURL)
                .then()
                .statusCode(200)
                .extract()
                .response();


        parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        List<Map<String, Object>> abilities = (List<Map<String, Object>>) parsedResponse.get("abilities");

        for (int i = 0; i < abilities.size(); i++) {

            Map<String, Object> map = abilities.get(i);

            Map<String, Object> innerMap = (Map<String, Object>) map.get("ability");

            if (innerMap.get("name").equals("lightning-rod")) {
                System.out.println(innerMap.get("url"));
            }


        }


    }
}
