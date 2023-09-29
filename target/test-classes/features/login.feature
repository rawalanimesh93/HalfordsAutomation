@Reg
Feature: Login Functionality

  Scenario: Login With Valid Credenatials
    When user enter "standard_user" and "secret_sauce"
    And user click on login button
    Then Validate user logged in sucessfully

  Scenario: Login with invalid credenatials
    When user enter "vinod" and "secret_sauce"
    And user click on login button
    Then Validate login error message
    
  Scenario: Login with correct username and invalid password
    When user enter "standard_user" and "sauce"
    And user click on login button
    Then Validate correct username and invalid password
    
      Scenario: Login with correct username and blank password
    When user enter "standard_user" and ""
    And user click on login button
    Then Validate correct username and blank password