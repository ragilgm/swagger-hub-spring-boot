package com.example.exampleswaggerhub.service;

import com.example.exampleswaggerhub.entity.DocSwagger;
import com.example.exampleswaggerhub.entity.Tribe;
import com.example.exampleswaggerhub.service.loader.SwaggerLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ApiDocService {

    @Autowired
    @Qualifier("tribes")
    private List<Tribe> tribeStream;

    @Autowired
    private ApplicationContext applicationContext;



    public List<Tribe> getTribes() {
        return tribeStream;
    }

    public List<DocSwagger> getServices(String tribeName) {
        Optional<Tribe> tribe =  tribeStream.stream()
                .filter(data-> data.getTribeName().equals(tribeName)).findFirst();
        if(tribe.isPresent()){
            return tribe.get().getServices();
        }
        return new ArrayList<>();
    }

    public String getService(String tribeName, String serviceName) throws IOException {
        Optional<DocSwagger> docSwaggerOptional = getServices(tribeName).stream()
                .filter(service-> service.getName().equals(serviceName)).findFirst();

        if(docSwaggerOptional.isEmpty()){
            throw new NoSuchElementException("service not found");
        }
        try{
            DocSwagger docSwagger = docSwaggerOptional.get();
            SwaggerLoaderService swaggerLoaderService = (SwaggerLoaderService) applicationContext.getBean(docSwagger.getType().toString());
            return swaggerLoaderService.load(docSwagger.getLocation());
        }catch (Exception e){
            throw new NoSuchFileException("error while read swagger");
        }
    }
}
