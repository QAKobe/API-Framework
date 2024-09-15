package com.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class CatFacts_Day_1_1 {

    @Test
    public void countCatFacts() {


        RestAssured.baseURI = "https://catfact.ninja";
        RestAssured.basePath = "facts";

        Response response = RestAssured.given().accept("application/json")
                .queryParam("limit", 100)
                .when().get()
                .then().statusCode(200).log().body().extract().response();


        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        List<Map<String, Object>> data = (List<Map<String, Object>>) parsedResponse.get("data");

        Integer expectedFactNumber = 100;

        Integer actualFactNumber = data.size();

        Assert.assertEquals(expectedFactNumber, actualFactNumber);


    }

}
