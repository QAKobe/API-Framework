package com.post;

import com.pojo.CategoryPetPojo;
import com.pojo.PetPojo;
import com.pojo.TagsPojo;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.Payloads;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PetPost {


    @Test
    public void createPet() {

        RestAssured.baseURI = "https://petstore.swagger.io/";
         RestAssured.basePath = "v2/pet";

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(Payloads.getPetPayload(40404, "Aktosh"))
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .response();

        PetPojo parsedResponse = response.as(PetPojo.class);
        System.out.println("parsedResponse.getId() = " + parsedResponse.getId());
        System.out.println("parsedResponse.getCategory().getName() = " + parsedResponse.getCategory().getName());
    }

    @Test
    public void createPetWithSetter(){

        RestAssured.baseURI = "https://petstore.swagger.io/";
        RestAssured.basePath = "v2/pet";

        PetPojo pojo = new PetPojo();
        CategoryPetPojo categoryPetPojo = new CategoryPetPojo();
        TagsPojo tagsPojo = new TagsPojo();
        tagsPojo.setId(12345);
        tagsPojo.setName("nameofdog");
        List<TagsPojo> tags = new ArrayList<>();
        tags.add(tagsPojo);
        pojo.setTags(tags);
        categoryPetPojo.setId(999);
        categoryPetPojo.setName("Tarzan");

        pojo.setId(332211);
        pojo.setCategory(categoryPetPojo);
        pojo.setName("Laika");
        pojo.setStatus("Always barking");

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(pojo)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath parsedResponse = response.jsonPath();
        System.out.println(parsedResponse.getString("category.name"));
    }


}
