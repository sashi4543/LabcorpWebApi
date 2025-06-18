package com.labcorp.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class JobDetailsPage {
    private WebDriver driver;
    private By jobTitle = By.cssSelector("h1[class='job-title']"); // Update as needed
    private By jobLocation = By.cssSelector(".au-target.job-location"); // Update as needed
    private By jobId = By.cssSelector(".au-target.jobId"); // Update as needed
    private By applyNowButton = By.cssSelector("ppc-content[key='job-us-benefits-gx2pvr-ph-job-details-v1-job-header-applyNowButtonText']"); // Update as needed
    private By requirementsList = By.cssSelector(".jd-info.au-target");
    private By jobDutiesList = By.xpath("//div[contains(text(),'Job Duties/Responsibilities:')]/following-sibling::ul/li");
    private By applyManually = By.xpath("//a[normalize-space()='Apply Manually']");

    public JobDetailsPage(WebDriver driver) {
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

    public void clickApplyNow() {
        driver.findElement(applyNowButton).click();
    }

    public void clickOnApplyManually() {
        driver.findElement(applyManually).click();
    }

    public boolean verifyRequirementText(String expectedText) {
        WebElement requirements = driver.findElement(requirementsList);
        return Arrays.stream(requirements.getText().split("\\r?\\n"))
                .anyMatch(line -> line.trim().contains(expectedText.trim()));
    }


    public boolean verifyJobDutyText(String expectedText) {
        List<WebElement> duties = driver.findElements(jobDutiesList);
        return duties.stream()
            .anyMatch(element -> element.getText().trim().equals(expectedText));
    }
} 