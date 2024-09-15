package com.get;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.List;

public class Football {


    @Test
    public void getCompetitions() {

        RestAssured.baseURI = "http://api.football-data.org";
        RestAssured.basePath = "v2/competitions";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .header("X-Auth-Token", "26ccdf687f1746b7bd9d5522aa99a2b9")
                .when()
                .get()
                .then()
                .statusCode(200) // find function works with array
                .body("competitions.find {it.id == 2006}.name", Matchers.is("WC Qualification CAF"))
                .body("competitions.findAll {it.area.name == 'Africa'}.name", Matchers.hasItems("WC Qualification CAF", "AFC Champions League", "Africa Cup"))
                .and()
                .body("competitions.min {it.id}.name", Matchers.equalTo("FIFA World Cup"))
                .and()
                .body("competitions.collect {it.id}.sum()", Matchers.greaterThanOrEqualTo(367412)).extract().response();

        List<String> africaCompetitionList = response.path("competitions.findAll {it.area.name == 'Africa'}.name");
        System.out.println(africaCompetitionList);

        String list = response.path("competitions.min {it.id}.name");
        System.out.println(list);

    }


}
