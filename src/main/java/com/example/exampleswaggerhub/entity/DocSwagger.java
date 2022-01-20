package com.example.exampleswaggerhub.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DocSwagger {

    private String name;
    private String version;
    private Type type;
    private String location;
    private String description;

    public enum Type{
        SWAGGER,
        OPEN_API
    }

}
