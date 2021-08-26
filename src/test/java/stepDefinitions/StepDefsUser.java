package stepDefinitions;

import config.UserApi;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import modelsUser.LoginLogoutUserDto;
import modelsUser.ParticularUserDto;
import org.apache.http.HttpStatus;
import org.junit.Assert;

import java.util.Map;

public class StepDefsUser {

    UserApi userApi = new UserApi();
    Response response;
    Map<String, String> parameters;

    @When("user logs in with email {string}, password {string}")
    public void userLogsInWithEmailPassword(String email, String pwd) {
        response = userApi.logIn(email, pwd);
    }

    @Then("user retrieves code {int}")
    public void userRetrievesCode(int code) {
        LoginLogoutUserDto actual = response.as(LoginLogoutUserDto.class);
        Assert.assertEquals(code, actual.getCode());
    }

    @Then("user retrieves code")
    public void userRetrievesCode() {
        Assert.assertTrue(response.getStatusCode() == HttpStatus.SC_NOT_FOUND);
    }

    @Then("user retrieves message {string}")
    public void userRetrievesMessage(String message) {
        String loginMessage = userApi.retrieveMessage();
        System.out.println("User session: " + userApi.retrieveUserSession());
        Assert.assertEquals(message, loginMessage);
    }

    @When("user logs out")
    public void userLogsOut() {
        response = userApi.logout();
    }

    @When("user sends POST request with valid parameters")
    public void userSendsPOSTRequestWithValidParameters(DataTable dataTable) {
        parameters = dataTable.asMaps().get(0);
        response = userApi.createUser(parameters);
    }


    @Then("user received message {word}")
    public void receiveMessageId(String message) {
        LoginLogoutUserDto actual = response.as(LoginLogoutUserDto.class);
        Assert.assertEquals(message, actual.getMessage());
    }

    @When("user sends GET request with username {word}")
    public void userSendsGETRequestWithUsername(String username) {
        response = userApi.retrieveUserByUsername(username);
    }

    @When("the user sends GET request with username {string}")
    public void getUserByUsername(String username) {
        response = userApi.retrieveUserByUsername(username);
    }

    @Then("user retrieves id {word}")
    public void userRetrievesId(String id) {
        ParticularUserDto actual = response.as(ParticularUserDto.class);
        int expectedId = Integer.valueOf(id);
        Assert.assertEquals(expectedId, actual.getId());
    }

    @Then("user retrieves type {string}")
    public void userRetrievesType(String type) {
        LoginLogoutUserDto actual = response.as(LoginLogoutUserDto.class);
        Assert.assertEquals(type, actual.getType());
    }

    @When("user retrieves msg {string}")
    public void userRetrievesMsg(String message) {
        LoginLogoutUserDto actual = response.as(LoginLogoutUserDto.class);
        Assert.assertEquals(message, actual.getMessage());
    }

    @When("user sends DELETE request with username {word}")
    public void deleteUser(String username1) {
        response = userApi.deleteUser(username1);
    }

    @When("user retrieves message {word}")
    public void userRetrievesMessageUsername(String message) {
        LoginLogoutUserDto actual = response.as(LoginLogoutUserDto.class);
        Assert.assertEquals(message, actual.getMessage());
    }

    @When("user sends PUT request with old username {word} and new username {string}")
    public void userSendsPUTRequestWithUsername(String oldUsername, String newUsername) {
//        parameters = dataTable.asMaps().get(0);
        response = userApi.updateUser(oldUsername, newUsername);
    }

    @Then("user retrieves username {string}")
    public void userRetrievesUsername(String username) {
        ParticularUserDto actual = response.as(ParticularUserDto.class);
        Assert.assertEquals(username, actual.getUsername());
    }


}
