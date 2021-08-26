package config;

import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import modelsUser.LoginLogoutUserDto;

import java.util.HashMap;
import java.util.Map;

import static config.Endpoints.*;
import static io.restassured.RestAssured.given;
import static java.util.Map.entry;

public class UserApi {
    RequestSpecificationBuilder requestSpecificationBuilder = new RequestSpecificationBuilder();
    Response response;

    public Response logIn(String email, String pwd){
        response = given().spec(requestSpecificationBuilder.buildRequestSpecUser()).
                queryParam("username", email).
                queryParam("password", pwd).
                when().get(LOGIN).
                then().log().all().extract().response();
        return response;
    }

    public String retrieveMessage(){
        LoginLogoutUserDto actual = response.as(LoginLogoutUserDto.class);
        String[] responseMessage = actual.getMessage().split(":");
        String messageFirstPart = responseMessage[0];
        return messageFirstPart;
    }

    public String retrieveUserSession(){
        LoginLogoutUserDto actual = response.as(LoginLogoutUserDto.class);
        String[] responseMessage = actual.getMessage().split(":");
        String userSession = responseMessage[1];
        return userSession;
    }

    public Response logout(){
        response = given().spec(requestSpecificationBuilder.buildRequestSpecUser()).
                when().get(LOGOUT).
                then().log().all().extract().response();
        return response;
    }


    public Response createUser(Map<String, String> parameters) {

        Map<String, String> body = Map.ofEntries(
                entry("id", parameters.get("id")),
                entry("username", parameters.get("username")),
                entry("firstName", parameters.get("firstName")),
                entry("lastName", parameters.get("lastName")),
                entry("email", parameters.get("email")),
                entry("password", parameters.get("password")),
                entry("phone", parameters.get("phone")),
                entry("userStatus", parameters.get("userStatus"))
        );

        response = given().spec(requestSpecificationBuilder.buildRequestSpecUser())
                .body(body).
                        when().post().
                        then().log().all().extract().response();
        return response;
    }


    public Response retrieveUserByUsername(String username) {
        response = given().spec(requestSpecificationBuilder.buildRequestSpecUserPath()).
                pathParam(USERNAME, username).
                get().
                then().log().all().extract().response();
        return response;
    }

    public Response deleteUser(String username1) {
        response = given().spec(requestSpecificationBuilder.buildRequestSpecUserPath()).
                pathParam(USERNAME, username1). //долго вытягивает username, но если указать значение напрямую ("TesterOl1"), то ок
                delete().
                then().log().all().extract().response();
        return response;
    }

    public Response updateUser(String oldUsername, String newUsername) {
//        Map<String, String> body = Map.ofEntries(
//                entry("id", parameters.get("id")),
//                entry("username", newUsername),
//                entry("firstName", parameters.get("firstName")),
//                entry("lastName", parameters.get("lastName")),
//                entry("email", parameters.get("email")),
//                entry("password", parameters.get("password")),
//                entry("phone", parameters.get("phone")),
//                entry("userStatus", parameters.get("userStatus"))
//        );

        Map<String, String> body = Map.ofEntries(
                entry("id", "32234563"),
                entry("username", newUsername),
                entry("firstName", "userOl2"),
                entry("lastName", "Usser2"),
                entry("email", "userOl2@gmail.com"),
                entry("password", "test2"),
                entry("phone", "723786342"),
                entry("userStatus", "0")
        );

        response = given().spec(requestSpecificationBuilder.buildRequestSpecUserPath()).
                pathParam(USERNAME, oldUsername).
                body(body).
                when().put().
                then().log().all().extract().response();
        return response;
    }
}
