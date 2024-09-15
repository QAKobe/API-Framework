package com.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class Currency_Day_1_1 {

    @Test
    public void getCurrencies() {

        Response response = RestAssured.given().accept("application/json")
                .when()
                .get("https://openexchangerates.org/api/currencies.json")
                .then()
                .statusCode(200)
                .extract()
                .response();


        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        String kgs = (String) parsedResponse.get("KGS");
        Assert.assertEquals(kgs, "Kyrgystani Som", "Failed to validate currency");

    }

}
