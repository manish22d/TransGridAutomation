package com.transgrid.core.ui;

import com.transgrid.listeners.WebEventListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {
	static WebDriver driver = null;

	public EventFiringWebDriver e_driver;

	protected WebDriver initializeDriver() {
//		DriverType browser = DriverType.valueOf(System.getProperty("browser").toUpperCase());
		DriverType browser = DriverType.valueOf("chrome".toUpperCase());
		switch (browser) {
			case FIREFOX -> driver = getFirefoxDriver();
			case CHROME -> driver = getChromeDriver();
			case EDGE -> driver = getInternetExplorerDriver();
		}
		WebEventListener listener = new WebEventListener();//Create instance of Listener Class
		EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator<>(listener); //Pass listener to constructor

		WebDriver decorated = decorator.decorate(driver);
		decorated.manage().window().maximize();
		decorated.manage().deleteAllCookies();
		decorated.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		decorated.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		return decorated;
	}

	private WebDriver getFirefoxDriver() {
		WebDriverManager.firefoxdriver().setup();
		return new FirefoxDriver();
	}

	private WebDriver getChromeDriver() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("useAutomationExtension", false);
		chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		chromeOptions.setExperimentalOption("prefs", prefs);
		WebDriverManager.chromedriver().setup();

		return new ChromeDriver(chromeOptions);
	}

	private WebDriver getInternetExplorerDriver() {
		WebDriverManager.iedriver().setup();
		return new InternetExplorerDriver();
	}
}
