package com.labcorp.automation.stepdefs;

import com.labcorp.automation.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import static org.assertj.core.api.Assertions.*;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class JobSearchStepDefs {
    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private CareersPage careersPage;
    private JobSearchResultsPage resultsPage;
    private JobDetailsPage detailsPage;
    private ApplyPage applyPage;
    private String selectedJobTitle;
    private String jobTitle;
    private String jobLocation;
    private String jobId;

    @Before("@web")
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I open the LabCorp home page")
    public void i_open_the_labcorp_home_page() {
        driver.get("https://www.labcorp.com/");
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
            .executeScript("return document.readyState").equals("complete"));
        wait.until(webDriver -> (Boolean)((JavascriptExecutor) webDriver)
            .executeScript("return window.jQuery == null || window.jQuery.active == 0"));
        homePage = new HomePage(driver);
    }

    @When("I navigate to the Careers page")
    public void i_navigate_to_the_careers_page() {
        homePage.clickCareers();
        careersPage = new CareersPage(driver);
    }

    @When("I search for the job title {string}")
    public void i_search_for_the_job_title(String title) {
        careersPage.searchJob(title);
        resultsPage = new JobSearchResultsPage(driver);
        selectedJobTitle = title;
    }

    @When("I select the job from the search results {string}")
    public void i_select_the_job_from_the_search_results(String jobTitle) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='job-title']")));
        selectedJobTitle = resultsPage.getExactJobTitle(jobTitle);
        System.out.println("Selected job title: " + selectedJobTitle);
        resultsPage.selectJob(selectedJobTitle);
        detailsPage = new JobDetailsPage(driver);
    }

    @Then("I should see the correct Job Title, Job Location, and Job ID")
    public void i_should_see_the_correct_job_title_location_id() {
        jobTitle = detailsPage.getJobTitle();
        jobLocation = detailsPage.getJobLocation().split("\n")[1];
        jobId = detailsPage.getJobId().split(":")[1];
        assertThat(jobTitle).isEqualTo(selectedJobTitle);
        assertThat(jobLocation).isNotNull();
        assertThat(jobId).isNotNull();
    }

    @Then("I should verify the first requirement {string}")
    public void i_should_verify_the_first_requirement(String requirementText) {
        assertThat(detailsPage.verifyRequirementText(requirementText))
            .as("Requirement text should be present: " + requirementText)
            .isTrue();
    }

    @Then("I should verify the second requirement {string}")
    public void i_should_verify_the_second_requirement(String requirementText) {
        assertThat(detailsPage.verifyRequirementText(requirementText))
            .as("Requirement text should be present: " + requirementText)
            .isTrue();
    }

    @Then("I should verify the job duty {string}")
    public void i_should_verify_the_job_duty(String dutyText) {
        assertThat(detailsPage.verifyRequirementText(dutyText))
            .as("Job duty text should be present: " + dutyText)
            .isTrue();
    }

    @When("I click Apply Now")
    public void i_click_apply_now() throws InterruptedException {
        Set<String> existingWindows = driver.getWindowHandles();

        // Click the Apply Now button (assumed to open a new tab)
        detailsPage.clickApplyNow();
        TimeUnit.SECONDS.sleep(10);

        // Wait up to 10s for a new window handle to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        String newTabHandle = wait.until(driver -> {
            Set<String> handles = driver.getWindowHandles();
            handles.removeAll(existingWindows);
            return !handles.isEmpty() ? handles.iterator().next() : null;
        });

        // Switch to the new tab
        driver.switchTo().window(newTabHandle);
        detailsPage.clickOnApplyManually();
        applyPage = new ApplyPage(driver);
    }


  /*
  The below code is commented because in the website UI there is no option showing to validate the job pposting
   */

//    @Then("the Job Title, Job Location, Job ID, and another text should match the previous page")
//    public void i_should_see_job_details_match() {
//        assertThat(applyPage.getJobTitle()).isEqualTo(jobTitle);
//        assertThat(applyPage.getJobLocation()).isEqualTo(jobLocation);
//        assertThat(applyPage.getJobId()).isEqualTo(jobId);
//    }

    @When("I click to Return to Job Search")
    public void i_click_to_return_to_job_search() {
        System.out.println("Waiting for Return to Job Search button to be visible and clickable...");
        
        // First wait for visibility
        wait.until(ExpectedConditions.visibilityOfElementLocated(applyPage.getReturnToJobSearchLocator()));
        System.out.println("Return to Job Search button is visible");
        
        // Then wait for element to be clickable
        WebElement returnButton = wait.until(ExpectedConditions.elementToBeClickable(applyPage.getReturnToJobSearchLocator()));
        System.out.println("Return to Job Search button is clickable");
        
        // Finally click the button
        returnButton.click();
        System.out.println("Clicked Return to Job Search button");
    }
} 