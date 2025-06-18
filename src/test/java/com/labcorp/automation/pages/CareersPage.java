package com.labcorp.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CareersPage {
    private WebDriver driver;
    private By searchBox = By.xpath("//input[@id='typehead']"); // Update this selector as needed
    private By searchButton = By.xpath("//span[@class='submit-icon']"); // Update as needed

    public CareersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchJob(String jobTitle) {
        driver.findElement(searchBox).sendKeys(jobTitle);
        driver.findElement(searchButton).click();
    }
} 