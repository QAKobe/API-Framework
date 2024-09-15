package com.put;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utils.Payloads;

public class UpdatePet {

    @Test
    public void testPut(){


        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";


        int originalPetId = 3434;
        String originalPetName = "Hachi";

        //1. Create pet
        RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(Payloads.getPetPayload(originalPetId, originalPetName))
                .when().post()
                .then().statusCode(200)
                .body("id", Matchers.equalTo(originalPetId))
                .body("name", Matchers.equalTo(originalPetName));


        //2. Get pet
        RestAssured.given().accept(ContentType.JSON)
                .when().get(originalPetId+"")
                .then().statusCode(200)
                .body("id", Matchers.is(originalPetId))
                .body("name", Matchers.equalTo(originalPetName));


        //3. Put
        String updatedPetName = "pluto";
        RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(Payloads.getPetPayload(originalPetId, updatedPetName))
                .when().put()
                .then().statusCode(200)
                .body("id", Matchers.is(originalPetId))
                .body("name", Matchers.is(updatedPetName));

        //4. Get pet
        RestAssured.given().accept(ContentType.JSON)
                .when().get(""+originalPetId)
                .then().statusCode(200)
                .body("id", Matchers.equalTo(originalPetId))
                .body("name", Matchers.equalTo(updatedPetName));


    }

}
