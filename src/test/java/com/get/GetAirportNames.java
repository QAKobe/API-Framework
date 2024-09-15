package com.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetAirportNames {

    @Test
    public void getNameOfAirport() throws InterruptedException {

        RestAssured.baseURI = "https://airportgap.dev-tester.com";
        RestAssured.basePath = "api/airports/";

        Response response = given().accept(ContentType.JSON).when().get().then().statusCode(200).extract().response();

        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });


        System.out.println(parsedResponse);
    }

}
