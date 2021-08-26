package config;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationBuilder {

    public RequestSpecification buildRequestSpecPet(){
        return new RequestSpecBuilder()
                .setBaseUri(Endpoints.BASE_URL_PET)
                .setBasePath("/pet")
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public RequestSpecification buildRequestSpecPetPath(){
        return new RequestSpecBuilder()
                .setBaseUri(Endpoints.BASE_URL_PET)
                .setBasePath("/pet/{petId}")
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public RequestSpecification buildRequestSpecUser(){
        return new RequestSpecBuilder()
                .setBaseUri(Endpoints.BASE_URL_PET)
                .setBasePath("/user")
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public RequestSpecification buildRequestSpecUserPath(){
        return new RequestSpecBuilder()
                .setBaseUri(Endpoints.BASE_URL_PET)
                .setBasePath("/user/{username}")
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addFilter(new ResponseLoggingFilter())
                .build();
    }





}
