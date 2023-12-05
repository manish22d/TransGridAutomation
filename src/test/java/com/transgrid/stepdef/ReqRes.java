package com.transgrid.stepdef;

import com.transgrid.core.api.HttpOperation;
import com.transgrid.pojo.Request;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

public class ReqRes {
    Request request;
    Response response;
    @Given("i want to create {string} request")
    public void iWantToCreateRequest(String apiName) {
        request= Request.getRequestFromProperty(apiName);
    }

    @When("user sends create requests")
    public void userSendsCreateRequests() {
        HttpOperation httpOperation = new HttpOperation();
        response=httpOperation.execute(request);
    }

    @Then("response is {int} okay")
    public void responseIsOkay(int statuscode) {
        Assert.assertEquals(statuscode, response.getStatusCode());
    }




    @And("i want to update {string} for {string}")
    public void iWantToUpdateFor(String name , String path) {
        request.updateRequest(path, name);
    }
}
