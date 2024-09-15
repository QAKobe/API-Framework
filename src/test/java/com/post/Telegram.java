package com.post;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Telegram {

    @Test
    public void sendMessageToTelegram() {


        String botToken = ConfigReader.readProperty("telegramToken");

        String chatId = ConfigReader.readProperty("chat_id");
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
    public void getUpdates1() {

        // Task for students
        String botToken = ConfigReader.readProperty("telegramToken");
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


        String botToken = ConfigReader.readProperty("telegramToken");
        given()
                .baseUri("https://api.telegram.org")
                .basePath("/bot" + botToken + "/getUpdates")
                .when()
                .get()
                .then().log().body()
                .statusCode(200).extract().response();
    }
}
