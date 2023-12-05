package com.transgrid.listeners;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.Method;


public class WebEventListener implements WebDriverListener {

	@Override
	public void beforeAnyWebDriverCall(WebDriver driver, Method method, Object[] args) {
		System.out.println("beforeAnyWebDriverCall");
	}

	@Override
	public void afterGet(WebDriver driver, String url) {
		System.out.println("after get");
	}
	@Override
	public void beforeFindElements(WebDriver driver, By locator) {
		System.out.println("trying to find element by "+locator.toString());
	}

}
