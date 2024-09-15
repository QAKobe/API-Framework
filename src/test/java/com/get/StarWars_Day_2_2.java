package com.get;

import com.pojo.SWPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class StarWars_Day_2_2 {

    // Pojo used here along with TypeRef
    @Test
    public void getCharactersTest(){

        Response response = RestAssured.given().accept("application/json")
                .when()
                .get("https://swapi.dev/api/people/1")
                .then().statusCode(200)
                .extract()
                .response();

        SWPojo swPojo = response.as(SWPojo.class);
        String expectedName = "Luke Skywalker";
        String actualName = swPojo.getName();
        List<String> films = swPojo.getFilms();
        System.out.println(films);
        List<String> starships = swPojo.getStarships();
        System.out.println(starships);
        Assert.assertEquals( actualName, expectedName,"failed to validate SW character name");

//        for (int i = 0; i < films.size(); i++) {
//
//            response = RestAssured.given().accept(ContentType.JSON)
//                    .when()
//                    .get(films.get(i))
//                    .then()
//                    .statusCode(200)
//                    .extract()
//                    .response();
//
//            Map<String, Object> map = response.as(new TypeRef<Map<String, Object>>() {
//            });
//            System.out.println(map);
//        }


    }

}
