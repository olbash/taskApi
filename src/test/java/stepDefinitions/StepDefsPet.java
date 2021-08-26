package stepDefinitions;
import config.PetApi;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import modelsPet.ParticularPetDto;
import modelsPet.RetrievePetByIdDto;
import org.junit.Assert;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StepDefsPet {
    PetApi petApi = new PetApi();
    Response response;
    Map<String, String> parameters;

    @When("user creates a new pet with name, status") //(.+)
    public void userCreatesANewPet(DataTable dataTable) {
        parameters = dataTable.asMaps().get(0);
        response = petApi.createNewPet(parameters);
    }

    @Then("api call is successful with status code {int}")
    public void apiCallIsSuccessfulWithStatusCode(int code) throws AssertionError {
            Assert.assertTrue(response.getStatusCode() == code);
    }


    @Then("DTO contains specified name, status")
    public void dtoContainsSpecifiedNameStatus() {
        ParticularPetDto actualDto = response.as(ParticularPetDto.class);
        Assert.assertEquals(actualDto.getName(), parameters.get("name"));
    }

    @When("user looks for pets with a particular {string}:")
    public void userLooksForPetsWithAParticularStatus(String status) {
        response = petApi.retrievePetsWithParticularStatus(status);
    }

    @Then("only particular {string} is present in response:")
    public void onlyParticularStatusIsPresentInResponse(String status){
        List<ParticularPetDto> actualDto = Arrays.asList(response.as(ParticularPetDto[].class));
        actualDto.forEach(dto -> Assert.assertEquals(status, dto.getStatus()));
    }


    @When("user updates existing pet's name to {string} and status to {string}")
    public void userUpdatesExistingPetNameAndStatus(String name, String status) {
        response = petApi.updateExistingPetNameAndStatus(name, status);
    }


    @Then("pet's name equals to {string}, status equals to {string}")
    public void petNameEqualsToStatusEqualsTo(String name, String status) {
        ParticularPetDto actualDto = response.as(ParticularPetDto.class);
        Assert.assertEquals(actualDto.getName(), name);
        Assert.assertEquals(actualDto.getStatus(), status);
    }

    @When("user deletes the newly created pet")
    public void userDeletesTheNewlyCreatedPet() {
        response = petApi.deletePet();
    }

    @When("user retrieves pet by id")
    public void userRetrievesPetById() {
        response = petApi.retrievePetById();
    }

    @Then("GET pet by id returns type : {string} and message: {string}")
    public void getPetByIdReturnsAnd(String typeValue, String messageValue) {
        RetrievePetByIdDto actualDto = response.as(RetrievePetByIdDto.class);
        Assert.assertEquals(typeValue, actualDto.getType());
        Assert.assertEquals(messageValue, actualDto.getMessage());
    }

}
