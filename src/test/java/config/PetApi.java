package config;
import bodies.AddPet;
import helpers.Utils;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static config.Endpoints.*;
import static io.restassured.RestAssured.given;

public class PetApi {

    RequestSpecificationBuilder requestSpecificationBuilder = new RequestSpecificationBuilder();
    Response response;
    AddPet addPet = new AddPet();
    Utils utils = new Utils();
    String petId;


    public Response createNewPet0(){
        response = given().spec(requestSpecificationBuilder.buildRequestSpecPet())
                        .body(addPet.addPet()).
                    when().post().
                    then().log().all().extract().response();

        return response;
    }

    public Response createNewPet(Map<String, String> parameters){
        petId = String.valueOf(utils.generateRandomNumber()); //here we use random generator, so our ids will always be different!
        HashMap<String, String> body = new HashMap<>();
        body.put("id", petId);
        body.put("name", parameters.get("name"));
        body.put("status", parameters.get("status"));

        response = given().spec(requestSpecificationBuilder.buildRequestSpecPet())
                    .body(body).
                    when()
                        .post().
                    then()
                        .log().all().extract().response();

        return response;
    }

    public Response retrievePetsWithParticularStatus(String status) {
        response = given().spec(requestSpecificationBuilder.buildRequestSpecPet())
                        .queryParam("status", status).
                    when()
                        .get(FIND_BY_STATUS).
                    then()
                        .log().all().extract().response();

        return response;
    }

    public Response updateExistingPetNameAndStatus(String name, String status) {
        HashMap<String, String> body = new HashMap<>();
        body.put("id", petId);
        body.put("name", name);
        body.put("status", status);

        response = given().spec(requestSpecificationBuilder.buildRequestSpecPet())
                        .body(body).
                    when().put().
                    then().log().all().extract().response();

        return response;
    }

    public Response deletePet() {
        response = given().spec(requestSpecificationBuilder.buildRequestSpecPetPath()).
                    pathParam(PET_ID, petId).
                    when().delete().
                    then().log().all().extract().response();

        return response;
    }

    public Response retrievePetById() {
        response = given().spec(requestSpecificationBuilder.buildRequestSpecPetPath()).
                pathParam(PET_ID, petId).
                when().get().
                then().log().all().extract().response();

        return response;
    }
}
