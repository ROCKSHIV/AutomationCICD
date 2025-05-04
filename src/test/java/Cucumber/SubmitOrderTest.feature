
@tag
Feature: Purchase the order from the Ecommerce Website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce page


  @tag2
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <userName> and password <passWord>
    When I add the product  <productName> to cart
    And  Checkout <productName> and submit the order
    Then verify the confirmation message  "Thankyou for the order." in this step

    Examples: 
      | userName                         | passWord           | productName     |
      | knowledge9337080935@gmail.com    |     1@Shivkumar    | ZARA COAT 3     |

