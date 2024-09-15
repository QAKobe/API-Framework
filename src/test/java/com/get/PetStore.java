package com.get;

import com.pojo.PetPojo;
import com.pojo.TagsPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PetStore {


    @Test
    public void getPetTest() {

        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet/40404";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response();

        PetPojo parsedResponse = response.as(PetPojo.class);

        String actualPetName = parsedResponse.getName();
        String expectedPetName = "Aktosh";
        List<TagsPojo> list = parsedResponse.getTags();
        for (int i = 0; i < list.size(); i++) {

            System.out.println(list.get(i).getId());
            System.out.println(list.get(i).getName());


        }
        Assert.assertEquals(actualPetName, expectedPetName, "Failed to validate pet name");


    }


}
