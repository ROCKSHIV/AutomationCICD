


@tag
Feature: Error Validation
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce page

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given Logged in with username <userName> and password <passWord>
    Then "Incorrect email or password." message is displayed

     Examples: 
      | userName                         | passWord           |
      | knowledge9337080935@gmail.com    |     1@Shivkuma     |
