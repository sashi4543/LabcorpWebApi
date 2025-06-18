package com.labcorp.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ApplyPage {
    private WebDriver driver;
    private By jobTitle = By.cssSelector("h1");
    private By jobLocation = By.cssSelector(".job-location");
    private By jobId = By.cssSelector(".job-id");
    private By returnToJobSearchButton = By.cssSelector(".css-pplshs");

    public ApplyPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getJobTitle() {
        return driver.findElement(jobTitle).getText();
    }

    public String getJobLocation() {
        return driver.findElement(jobLocation).getText();
    }

    public String getJobId() {
        return driver.findElement(jobId).getText();
    }

    public By getReturnToJobSearchLocator() {
        return returnToJobSearchButton;
    }

    public WebElement getReturnToJobSearchElement() {
        return driver.findElement(returnToJobSearchButton);
    }

    public void clickReturnToJobSearch() {
        driver.findElement(returnToJobSearchButton).click();
    }

} 