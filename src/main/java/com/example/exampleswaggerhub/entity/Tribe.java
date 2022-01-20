package com.example.exampleswaggerhub.entity;

import lombok.Data;

import java.util.List;

@Data
public class Tribe {
    private String tribeName;
    private String description;
    private List<DocSwagger> services;
}
