package com.transgrid.listeners;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;


public class TestListener implements ITestListener {
	WebDriver webDriver = null;

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	public void onStart(ITestContext iTestContext) {
		System.out.println("I am in onStart method " + iTestContext.getName());
		webDriver = (WebDriver) iTestContext.getAttribute("webDriver");
		iTestContext.setAttribute("WebDriver", this.webDriver);
	}

	public void onFinish(ITestContext iTestContext) {
		System.out.println("I am in onFinish method " + iTestContext.getName());

	}

	public void onTestStart(ITestResult iTestResult) {
		System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");

	}

	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");

	}

	public void onTestFailure(ITestResult iTestResult) {
		try {
			if (this.webDriver != null) {
				// this.sendStatus(iTestResult,"FAIL");
				saveScreenshotPNG();
				System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
//				Object testClass = iTestResult.getInstance();

				String base64Screenshot = "data:image/png;base64,"
						+ ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

	public byte[] saveScreenshotPNG() {
		return ((TakesScreenshot) this.webDriver).getScreenshotAs(OutputType.BYTES);
	}

}
