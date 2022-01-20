package com.example.exampleswaggerhub.service.loader;

import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import io.swagger.util.Json;
import org.springframework.stereotype.Service;

@Service("OPEN_API")
public class OpenApiSwaggerLoaderService implements SwaggerLoaderService {

    @Override
    public String load(String url) {
        Swagger swagger =  new SwaggerParser().read(url);
        return Json.pretty(swagger);
    }
}
