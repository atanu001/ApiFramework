Feature: test sample api

  Scenario: test sample rest assured api
    Given user get api endpoint
    And user send http request with the following details
      |name|cpuModel|
      |Apple MacBook Pro 16|Intel Core i9|
