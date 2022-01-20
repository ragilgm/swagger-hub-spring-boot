package com.example.exampleswaggerhub.controller;

import com.example.exampleswaggerhub.entity.DocSwagger;
import com.example.exampleswaggerhub.entity.Tribe;
import com.example.exampleswaggerhub.service.ApiDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class APIDocController {

    @Autowired
    private ApiDocService tribeService;


    @GetMapping("/tribes")
    private List<Tribe> getTribe() {
        return tribeService.getTribes();
    }

    @GetMapping("/tribes/{tribeName}/docs")
    private List<DocSwagger> getServices(@PathVariable("tribeName") String tribeName){
        return tribeService.getServices(tribeName);
    }

    @GetMapping("/tribes/{tribeName}/docs/{serviceName}/api")
    public String getService(@PathVariable("tribeName") String tribeName,
                             @PathVariable("serviceName") String serviceName) throws IOException {
        return tribeService.getService(tribeName,serviceName);
    }


}
