package com.example.exampleswaggerhub.service.loader;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import io.swagger.util.Json;
import org.springframework.stereotype.Service;

@Service("SWAGGER")
public class LocalSwaggerLoaderService implements SwaggerLoaderService{

    private static final String SERVICE_PREFIX_PATH = "/docs/tribe";
    @Override
    public String load(String url) {
        Swagger swagger = new SwaggerParser().read(SERVICE_PREFIX_PATH+url);
        return Json.pretty(swagger);
    }
}
