package com.get;

import com.pojo.BbQuotesPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class BreakingBad {

    @Test
    public void getQuotes(){

        RestAssured.baseURI = "https://api.breakingbadquotes.xyz";
        RestAssured.basePath = "v1/quotes/";

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        BbQuotesPojo[] pojo = response.as(BbQuotesPojo[].class);
        for (int i = 0; i < pojo.length; i++) {

            System.out.println(pojo[i].getQuote());
            System.out.println(pojo[i].getAuthor());

        }

    }

}
