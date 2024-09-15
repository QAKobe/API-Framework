package com.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class FakeStore_Day_1_2 {


    // THIS IS DONE
    @Test
    public void productPriceTest() {

        // First class
        RestAssured.baseURI = "https://fakestoreapi.com/products/1";
        Response response = RestAssured.given().accept("application/json").when()
                .get().then().statusCode(200).log().body()
                .extract().response();


        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        double price = (double) deserializedResponse.get("price");
        Double price2 = (Double) deserializedResponse.get("price");

        Assert.assertEquals(109.95, price, 0);
        Assert.assertEquals(109.95, price2, 0);

        Assert.assertEquals(109.95, deserializedResponse.get("price"));

        Map<String, Object> ratingMap = (Map<String, Object>) deserializedResponse.get("rating");

        Double rate = (Double) ratingMap.get("rate");

        Integer count = (Integer) ratingMap.get("count");

        Assert.assertTrue(rate == 3.9);
        Assert.assertTrue(count == 120);

    }

    @Test
    public void totalPriceTest() {


        RestAssured.baseURI = "https://fakestoreapi.com";
        RestAssured.basePath = "products";

        //GET call
        Response response = RestAssured.given().accept("application/json")
                .when().get()
                .then().statusCode(200).extract().response();

        //deserialization/parsing
        List<Map<String, Object>> parsedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
        });


        double totalPrice = 0;
        for (Map<String, Object> product : parsedResponse) {
            //double       (cast)       Object
            double price = Double.parseDouble(product.get("price").toString());
            totalPrice += price;
        }

        System.out.println("total price: " + totalPrice);

        Assert.assertTrue(totalPrice > 200);



    }

}
