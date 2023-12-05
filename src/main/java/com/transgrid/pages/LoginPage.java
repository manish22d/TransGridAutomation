package com.transgrid.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//label[contains(.,'Username')]/..//input")
    WebElement username;
    @FindBy(id = "endng")
    WebElement password;
    @FindBy(xpath = "//button[@label='Continue']")
    WebElement logOnButton;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }
    public void  performLogin(String userId, String pwd){
        wait.until(elementToBeClickable(username));
        username.sendKeys(userId);
        password.sendKeys(pwd);
//        logOnButton.click();
    }
}
