package com.labcorp.automation.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.labcorp.automation.stepdefs", // ✅ FIXED: points to package
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true,
        tags = "@api"

)
public class CucumberTestRunner {
}
