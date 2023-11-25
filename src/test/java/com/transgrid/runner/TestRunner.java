package com.transgrid.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = {"src/test/java/com/transgrid/features"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        glue = {"com.transgrid.stepdef"},
        tags = "@weather")
public class TestRunner extends AbstractTestNGCucumberTests {
}
