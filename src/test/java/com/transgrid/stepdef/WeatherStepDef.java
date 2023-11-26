package com.transgrid.stepdef;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.transgrid.api.core.HttpOperation;
import com.transgrid.api.pojo.Request;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;

public class WeatherStepDef {
    Request request;
    Response response;
    @Given("i want to get weather")
    public void iWantToGetWeather() {
        request = Request.getRequestFromProperty("weatherapi");
    }

    @When("i send get request")
    public void iSendGetRequest() {
        HttpOperation httpOperation = new HttpOperation();
        response = httpOperation.execute(request);
    }

    @Then("response is received successfully")
    public void responseIsReceivedSuccessfully() {
        Assert.assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, "this is a step");
    }
}
