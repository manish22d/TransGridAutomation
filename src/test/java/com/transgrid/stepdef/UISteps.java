package com.transgrid.stepdef;

import com.transgrid.core.ui.WebDriverManager;
import com.transgrid.pages.LoginPage;
import com.transgrid.utils.PropertyUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class UISteps {
    WebDriver driver;

    public Properties property;
    @Given("user logs in to transgrid")
    public void userLogsInToTransgrid() {
        property = PropertyUtils.getProperty();
        driver = WebDriverManager.getDriver();
        LoginPage loginpage = new LoginPage(driver);
        loginpage.performLogin(property.getProperty("userId"), property.getProperty("pwd"));
    }

    @When("user get page title")
    public void userGetPageTitle() {
    }

    @Then("page title should be {string}")
    public void pageTitleShouldBe(String arg0) {
    }
}
