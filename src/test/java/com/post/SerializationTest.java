package com.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.CategoryPetPojo;
import com.pojo.PetPojo;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class SerializationTest {

    @Test
    public void serializationTest() throws IOException {


        PetPojo pet = new PetPojo();
        pet.setId(12);
        pet.setName("zeus");
        pet.setStatus("gone");

        CategoryPetPojo categoryPetPojo = new CategoryPetPojo();
        categoryPetPojo.setName("name");
        categoryPetPojo.setId(33);
        pet.setCategory(categoryPetPojo);

        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File("src/test/resources/jsonFiles/pet.json");
        objectMapper.writeValue(file, pet);


    }


}
