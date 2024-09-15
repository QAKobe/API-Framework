package com.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PetPojo {
    private int id;
    private String name;
    private List<String> photoUrls;
    private List<TagsPojo> tags;
    private String status;
    private CategoryPetPojo category;
}
