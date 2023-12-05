package com.transgrid.runner;


import com.transgrid.core.ui.WebDriverManager;
import com.transgrid.pages.LoginPage;
import com.transgrid.utils.PropertyUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;

import java.util.Properties;


@CucumberOptions(
        features = {"src/test/java/com/transgrid/features"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        glue = {"com.transgrid.stepdef"},
        tags = "@ui")
public class TestRunner extends AbstractTestNGCucumberTests {


}
