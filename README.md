# LabCorp QA Automation Test Project

This project contains automated tests for the LabCorp careers website and API testing using Selenium WebDriver, REST Assured, Cucumber, and Java.

## Project Overview

The automation suite includes:

### UI Tests
- Navigating to the careers page
- Searching for specific job positions
- Verifying job details
- Testing the job application flow

### API Tests
- Testing GET and POST endpoints
- Request/Response validation
- JSON payload handling
- Response status verification

## Prerequisites

- Java JDK 8 or higher
- Maven 3.6 or higher
- Chrome browser (latest version)
- ChromeDriver (automatically managed by WebDriverManager)

## Project Structure

```
src/
├── main/java/
│   └── com/labcorp/automation/
│       └── App.java
└── test/
    ├── java/
    │   └── com/labcorp/automation/
    │       ├── models/          # API request/response models
    │       ├── pages/           # Page Object Model classes
    │       ├── runners/         # Cucumber test runners
    │       └── stepdefs/        # Step definitions for UI and API tests
    └── resources/
        └── features/           # Cucumber feature files
```

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd qa-automation-coding-test
   ```

2. Install dependencies:
   ```bash
   mvn clean install -DskipTests
   ```

## Running Tests

### Command Line

To run all tests:
```bash
mvn clean test
```

To run only Web UI tests:
```bash
mvn clean test -Dcucumber.filter.tags="@web"
```

To run only API tests:
```bash
mvn clean test -Dcucumber.filter.tags="@api"
```

To run specific features:
```bash
mvn test -Dcucumber.features="src/test/resources/features/job_search.feature"
```

### Test Tags
The framework uses Cucumber tags to organize test execution:
- `@web` - For all web UI tests (browser-based tests)
- `@api` - For all API tests (non-browser tests)

### Test Reports

After test execution, you can find the reports in:
- Cucumber HTML Report: `target/cucumber-reports.html`
- Surefire Reports: `target/surefire-reports/`

## Framework Components

### UI Testing
- Page Objects for web UI interaction
- WebDriverManager for browser management
- Explicit waits for better reliability

### API Testing
- REST Assured for API testing
- JSON schema validation
- Request/Response models using Jackson
- BDD scenarios for API tests

### Features
The test scenarios are written in Gherkin syntax and located in:
- UI Tests: `src/test/resources/features/job_search.feature`
- API Tests: `src/test/resources/features/api_tests.feature`

### Configuration
- WebDriverManager for Chrome driver management
- REST Assured for API testing configuration
- Explicit waits for better reliability
- Page load strategy set to NORMAL

## Best Practices Implemented

1. Page Object Model design pattern
2. Explicit waits for better reliability
3. Dynamic element handling
4. Proper exception handling
5. Clean and maintainable code structure
6. Descriptive logging for debugging
7. Separation of UI and API test scenarios
8. Modular API request/response models
9. BDD format for better readability

## Known Issues

The job posting validation is currently commented out due to UI changes in the website:
```java
//    @Then("the Job Title, Job Location, Job ID, and another text should match the previous page")
//    public void i_should_see_job_details_match() {
//        // Implementation commented due to UI changes
//    }
```

## Test Results

Below is a screenshot of the successful test execution showing both API test scenarios passing:

![Test Results](test-results.png)

The test results show:
- 100% pass rate
- 2 scenarios executed successfully
- Total duration: 2.31 seconds
- Environment: Mac OS X with OpenJDK 64-bit Server VM 23.0.2
- Test Framework: Cucumber-JVM 7.15.0

### Test Coverage
The successful execution includes:
1. GET request verification to echo service
2. POST request verification with sample data

Both API test scenarios validated the response status codes, headers, and payload content successfully. 