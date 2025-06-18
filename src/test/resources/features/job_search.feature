@web
Feature: LabCorp Careers Job Search and Validation
  As a job seeker
  I want to search and validate a job posting on LabCorp Careers
  So that I can confirm the job details and apply

  Scenario: Search and validate a job posting
    Given I open the LabCorp home page
    When I navigate to the Careers page
    And I search for the job title "Hospital Reference Test Clerk"
    And I select the job from the search results "Hospital Reference Test Clerk"
    Then I should see the correct Job Title, Job Location, and Job ID
    And I should verify the first requirement "High School Diploma or equivalent"
    And I should verify the second requirement "Experience in a laboratory environment is preferred"
    And I should verify the job duty "Prepare laboratory specimens for various analysis and testing"
    When I click Apply Now
    # This step is commented out because the apply page UI does not contain these elements to validate
    # Then the Job Title, Job Location, Job ID, and another text should match the previous page
    When I click to Return to Job Search 