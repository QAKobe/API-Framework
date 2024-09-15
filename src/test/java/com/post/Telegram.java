package com.post;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Telegram {

    @Test
    public void sendMessageToTelegram() {


        String botToken = "7381542906:AAEbINd_Ej9PHrXSeiWqWHjYNKZrcOHueM0";
        // String chatId = "-4060811533";
        String chatId = "-4539329879";
        String message = "Wassap yo";

        given()
                .baseUri("https://api.telegram.org")
                .basePath("/bot" + botToken + "/sendMessage")
                .queryParam("chat_id", chatId)
                .queryParam("text", message)
                .when()
                .post()
                .then()
                .statusCode(200).extract().response();
    }


    @Test
    public void sendMsg2() {

        // Task for students
        String botToken = "7381542906:AAEbINd_Ej9PHrXSeiWqWHjYNKZrcOHueM0";
        Response response = given()
                .baseUri("https://api.telegram.org")
                .basePath("/bot" + botToken + "/getUpdates")
                .when()
                .get()
                .then().
                statusCode(200).extract().response();

        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        List<Map<String, Object>> result = (List<Map<String, Object>>) parsedResponse.get("result");

//        for (int i = 0; i < result.size(); i++) {
//
//            Map<String, Object> map = result.get(i);
//            if (map.containsKey("message")) {
//
//                Map<String, Object> message = (Map<String, Object>) map.get("message");
//                if (message.containsKey("new_chat_participant")) {
//
//                    Map<String, Object> innerMap = (Map<String, Object>) message.get("new_chat_participant");
//                    if (innerMap.get("first_name").equals("testBOT")) {
//                        System.out.println(innerMap.get("username"));
//                        break;
//                    }
//
//                }
//            }
//
//        }
        System.out.println(result);

    }

    @Test
    public void getUpdates() {


        String botToken = "7381542906:AAEbINd_Ej9PHrXSeiWqWHjYNKZrcOHueM0";
        given()
                .baseUri("https://api.telegram.org")
                .basePath("/bot" + botToken + "/getUpdates")
                .when()
                .get()
                .then().log().body()
                .statusCode(200).extract().response();
    }
}
