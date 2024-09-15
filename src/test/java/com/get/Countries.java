package com.get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Countries {

    @Test
    public void getMyCapital() {

        RestAssured.baseURI = "https://restcountries.com";
        RestAssured.basePath = "v3.1/all";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Map<String, Object>> parsedResponse = response.as(new TypeRef<List<Map<String, Object>>>() {
        });

        List<String> capital;
        String actualCapital = "";
        for (Map<String, Object> data : parsedResponse) {

            Map<String, Object> map = (Map<String, Object>) data.get("name");

            if (map.get("common").equals("Germany")) {

                capital = (List<String>) data.get("capital");
                actualCapital = capital.get(0);
            }


        }
        System.out.println(actualCapital);


    }

    @Test
    public void getCapitalJsonPath() {

        RestAssured.baseURI = "https://restcountries.com";
        RestAssured.basePath = "v3.1/all";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath parsedResponse = response.jsonPath();


        List<Object> list = parsedResponse.getList("name.common");
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).equals("Germany")) {
                list = parsedResponse.getList("capital");
                Object str = list.get(i);
                System.out.println(str);
                break;

            }

        }


    }

    @Test
    public void getCapitalWithGroovy() {

        RestAssured.baseURI = "https://restcountries.com";
        RestAssured.basePath = "v3.1/all";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get()
                .then().body("find {it.name.common == 'Germany'}.capital", Matchers.hasItem("Berlin"))
                .and()
                .body("find {it.name.common == 'Dominican Republic'}.currencies.DOP.name", Matchers.is("Dominican peso"))
                .body("find {it.name.common == 'Dominican Republic'}.borders", Matchers.hasItem("HTI"))
                .body("findAll {it.continents[0] == 'Asia'}.name.common", Matchers.hasItems())
                .statusCode(200)
                .extract().response();

        List<String> names = response.path("findAll {it.continents[0] == 'Antarctica'}.name.common");
        System.out.println(names);
        List<String> countries = response.path("findAll {it}.name.common");
        System.out.println(countries);
        List<String> path = response.path("find {it.name.common == 'Kyrgyzstan'}.capital");
        System.out.println(path);
        String currency = response.path("find {it.name.common == 'Dominican Republic'}.currencies.DOP.name");
        System.out.println(currency);

        List<String> borders = response.path("find {it.name.common == 'Dominican Republic'}.borders");
        System.out.println(borders);


    }


    @Test
    public void getCapitalWithJsonObject() {




    }

}
