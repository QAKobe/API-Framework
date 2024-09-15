package com.airport;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.util.Map;

import static utils.ConfigWriter.updateConfiguration;

public class GenerateAirportToken {

    @Test
    public void getToken(){

        RestAssured.baseURI = "https://airportgap.com/api";
        String credentials = ConfigReader.readProperty("credentials");
        Response response = RestAssured.given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body(credentials)
                .when()
                .post("/tokens")
                .then()
                .extract()
                .response();
        Map<String, String> map = response.as(new TypeRef<Map<String, String>>() {
        });

        updateConfiguration("token",map.get("token"));


    }

}
