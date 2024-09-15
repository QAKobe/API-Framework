package com.delete;

import com.pojo.PetPojo;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class DeletePet {

    @Test
    public void deletePetTest() {
        //1. Create a pet
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        int petId = 12321;
        String petName = "kitty";
        String petStatus = "playing";

        PetPojo petPayload = new PetPojo();
        petPayload.setId(petId);
        petPayload.setName(petName);
        petPayload.setStatus(petStatus);

        Response response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(petPayload)
                .when().post()
                .then().statusCode(200).extract().response();

        //deserialization with POJO
        PetPojo parsedResponse = response.as(PetPojo.class);
        Assert.assertEquals(petName, parsedResponse.getName());
        Assert.assertEquals(petId, parsedResponse.getId());
        Assert.assertEquals(petStatus, parsedResponse.getStatus());

        //2. Delete a pet
        response = RestAssured.given().accept(ContentType.JSON)
                .when().delete(String.valueOf(petId))
                .then().statusCode(200).extract().response();


        Map<String, Object> deserializedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        String actualMessage = (String) deserializedResponse.get("message");
        int actualCode = (int) deserializedResponse.get("code");

        Assert.assertEquals(200, actualCode);
        Assert.assertEquals(String.valueOf(petId), actualMessage);

        //3. Delete a pet - 404
        RestAssured.given().accept(ContentType.JSON)
                .when().delete(String.valueOf(petId))
                .then().statusCode(404);


    }
}
