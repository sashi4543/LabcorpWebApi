package com.labcorp.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

public class JobSearchResultsPage {
    private WebDriver driver;
    private By jobTitles = By.xpath("//div[@class='job-title']");

    public JobSearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectJob(String jobTitle) {
        List<WebElement> allJobTitles = driver.findElements(jobTitles);
        
        // Find the first job that contains the given title (case-insensitive)
        WebElement matchingJob = allJobTitles.stream()
            .filter(element -> element.getText().toLowerCase()
                .contains(jobTitle.toLowerCase()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No job found containing title: " + jobTitle));
        
        // Store the exact title text before clicking
        String exactTitle = matchingJob.getText();
        System.out.println("Found matching job: " + exactTitle);
        
        matchingJob.click();
    }

    public List<String> getAllJobTitles() {
        List<WebElement> elements = driver.findElements(jobTitles);
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getExactJobTitle(String partialTitle) {
        List<WebElement> allJobTitles = driver.findElements(jobTitles);
        return allJobTitles.stream()
            .map(WebElement::getText)
            .filter(text -> text.toLowerCase().contains(partialTitle.toLowerCase()))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No job found containing title: " + partialTitle));
    }
} 