package utils;

public class Payloads {

    public static String getAirportPayload(String to, String from){

        return "{\n" +
                "    \"from\": \""+from+"\",\n" +
                "    \"to\": \""+to+"\"\n" +
                "}";

    }

    public static String getPetPayload(int id, String name){

        return "{\n" +
                "    \"id\": "+id+",\n" +
                "    \"category\": {\n" +
                "        \"id\": 0,\n" +
                "        \"name\": \"Aktosh\"\n" +
                "    },\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"photoUrls\": [\n" +
                "        \"https://s3.amazon.com\"\n" +
                "    ],\n" +
                "    \"tags\": [\n" +
                "        {\n" +
                "            \"id\": 0,\n" +
                "            \"name\": \"Aktosh Maloi\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"status\": \"All healthy\"\n" +
                "}";

    }


}
