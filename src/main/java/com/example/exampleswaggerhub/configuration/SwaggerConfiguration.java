package com.example.exampleswaggerhub.configuration;

import com.example.exampleswaggerhub.entity.Tribe;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class SwaggerConfiguration {

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean(name = "tribes")
    public List<Tribe> getTribes() throws IOException {
        TypeReference<List<Tribe>> tribeTypeReference = new TypeReference<List<Tribe>>() {};
        InputStream inputStream =  new ClassPathResource("/docs/docs.json").getInputStream();
        List<Tribe> tribes = objectMapper.readValue(inputStream,tribeTypeReference);
        return tribes;
    }
}
