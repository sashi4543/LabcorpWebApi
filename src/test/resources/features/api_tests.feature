Feature: API Testing for Echo Free Service

  @api
  Scenario: Verify GET request to echo service
    Given I have the base URI "https://echo.free.beeceptor.com"
    When I send a GET request to "/sample-request" with parameter "author=beeceptor"
    Then the response status code should be 200
    And I should validate the following in response:
      | path    | /sample-request?author=beeceptor |
      | ip      | should not be empty              |
    And the response headers should be present

  @api
  Scenario: Verify POST request with sample data
    Given I have the base URI "https://echo.free.beeceptor.com"
    When I send a POST request to "/api/sample" with the following data:
      """
      {
        "customer": {
          "name": "Test User",
          "email": "test@test.com",
          "address": {
            "city": "TestCity",
            "state": "TS",
            "zipcode": "12345"
          }
        },
        "items": [
          {
            "product_id": "12345",
            "quantity": 2,
            "price": 99.99
          }
        ],
        "payment": {
          "card_number": "1234-5678-9012-3456",
          "expiry": "12/25"
        }
      }
      """
    Then the response status code should be 200
    And the response should contain valid confirmation 