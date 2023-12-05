package com.transgrid.core.ui;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class WebDriverManager {
	public static final Logger logger = LoggerFactory.getLogger(WebDriverManager.class);
	static WebDriver driver = null;
	public static WebDriver getDriver() {
		if(driver==null) {
			logger.info("initialising Web driver....");
			WebDriverFactory wf= new WebDriverFactory();
			driver = wf.initializeDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}

	public static void shutdownDriver(){
		logger.info("shutting down Web driver....");
		if(driver!=null) {
			driver.quit();
		}
	}
}
