package com.transgrid.stepdef;

import com.transgrid.core.ui.WebDriverManager;
import com.transgrid.pages.LoginPage;
import com.transgrid.utils.PropertyUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class Hooks {
    public Properties property;
    protected WebDriver driver;
    protected String appUrl;

    @Before
    public void loadConfig() {
        System.out.println("before");
        property = PropertyUtils.getProperty();
        appUrl = property.getProperty("app.url");
        driver = WebDriverManager.getDriver();
        driver.navigate().to(appUrl);

    }

    @After
    public void teardown(){
        WebDriverManager.shutdownDriver();
    }
}
