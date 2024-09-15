package com.get;

import com.pojo.ContinentPojo;
import com.pojo.GoTCharacters;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoT_Day_2_1 {


    // Day 1
    @Test
    public void getCharactersTest() {
        //https://thronesapi.com/api/v2/Characters
        RestAssured.baseURI = "https://thronesapi.com/api/v2/Characters";

        RestAssured.given().header("Accept", "application/json")
                .when().get()
                .then().statusCode(200).log().body();


    }

    @Test
    public void getSpecificCharacter() {


        RestAssured.baseURI = "https://thronesapi.com/api/v2/Characters/10";

        Response response = RestAssured.given().header("Accept", "application/json")
                .when().get().then().statusCode(200).log().body().extract()
                .response();

        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        System.out.println(deserializedResponse);
        String firstName = (String) deserializedResponse.get("firstName");
        String lastName = String.valueOf(deserializedResponse.get("lastName"));
        Assert.assertEquals("Cateyln", firstName);
        Assert.assertEquals("Stark", lastName);

    }

    @Test
    public void GoTTest() {

        RestAssured.baseURI = "https://thronesapi.com";
        RestAssured.basePath = "api/v2/Characters";

//        Response response = RestAssured.given().accept("application/json").when().get().then().statusCode(200).extract().response();
//        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
//        });

        Map<String, List<Map<String, Object>>> map = new HashMap<>();

        for (int i = 1; i < 11; i++) {
            String id = "";
            id += String.valueOf(i);
            Response response = RestAssured.given().
                    accept("application/json")
                    .when()
                    .get(id)
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();
            Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
            });

            if (parsedResponse.get("family").equals("House Stark")) {
                System.out.println(parsedResponse.get("fullName"));
            }

        }


    }

    @Test
    public void getHouseStarkFamily() {
        RestAssured.baseURI = "https://thronesapi.com";
        RestAssured.basePath = "api/v2/Characters";

        Response response = RestAssured.given().accept("application/json")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Map<String, Object>> list = response.as(new TypeRef<List<Map<String, Object>>>() {
        });
        Map<String, Object> info = new HashMap<>();
//        for (int i = 0; i < list.size(); i++) {
//
//            Map<String, Object> map = list.get(i);
//            if (map.get("family").equals("House Stark")){
//
//                info.put((String) map.get("fullName"), map.get("family"));
//
//            }
//
//        }

        for (Map<String, Object> family : list) {

            if (family.get("family").equals("House Stark")) {
                info.put((String) family.get("fullName"), family.get("family"));
            }

        }

        System.out.println(info);
    }


    // +++++++++++++++++++++++++++++++++++++++++POJO Used+++++++++++++++++++++++++++++++++++++++++++++++++++++++

    // Day 2
    @Test
    public void continentPojoTest() {
        // Day 2 Introduction to Pojo
        // Pojo used for deserialization
        RestAssured.baseURI = "https://thronesapi.com";
        RestAssured.basePath = "api/v2/continents/0";

        Response response = RestAssured.given().accept("application/json")
                .when()
                .get()
                .then()
                .statusCode(200).log().all()
                .extract()
                .response();

        ContinentPojo parsedResponse = response.as(ContinentPojo.class);
        int id = parsedResponse.getId();
        String name = parsedResponse.getName();

        Assert.assertEquals(id, 0);
        Assert.assertEquals(name, "Westeros");

    }

    @Test
    public void continentsTest() {
        // pojo for list [] coming from response json body
        RestAssured.baseURI = "https://thronesapi.com";
        RestAssured.basePath = "api/v2/continents";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();


        ContinentPojo[] continentArray = response.as(ContinentPojo[].class);
        Map<String, Integer> continentMap = new HashMap<>();

        for (int i = 0; i < continentArray.length; i++) {


            String name = continentArray[i].getName();
            int id = continentArray[i].getId();
            continentMap.put(name, id);

        }
        System.out.println(continentMap);

    }



    @Test
    public void getCharactersPojo() {
        RestAssured.baseURI = "https://thronesapi.com";
        RestAssured.basePath = "api/v2/Characters";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        GoTCharacters[] characters = response.as(GoTCharacters[].class);
        for (int i = 0; i < characters.length; i++) {

            if (characters[i].getFamily().equalsIgnoreCase("House Stark")){
                System.out.println(characters[i].getFullName());
            }

        }


    }
}
