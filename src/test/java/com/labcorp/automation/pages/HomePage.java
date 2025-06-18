package com.labcorp.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By careersLink = By.linkText("Careers");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCareers() {
        driver.findElement(careersLink).click();
    }
} 