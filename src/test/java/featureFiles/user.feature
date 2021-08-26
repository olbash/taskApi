Feature: User feature

  Scenario: log in as a valid user //no possibility to create a reversed test - as an invalid user (any email/pwd and even their absence treated as correct behavior)
    When user logs in with email "ol@gmail.com", password "test"
    Then user retrieves code 200
    And user retrieves message "logged in user session"

  Scenario: User is able to log out
    Given user logs in with email "ol@gmail.com", password "test"
    When user logs out
    Then user retrieves code 200

  Scenario Outline: Create a user
    When user sends POST request with valid parameters
      | id   | username   | firstName   | lastName   | email   | password   | phone   | userStatus   |
      | <id> | <username> | <firstName> | <lastName> | <email> | <password> | <phone> | <userStatus> |
    Then user retrieves code 200
    And user received message <id>
    Examples:
      | id        | username | firstName | lastName | email        | password | phone      | userStatus |
      | 387845885 | OlTester | Ol        | Tester   | ol@gmail.com | test     | 7234672378 | 1          |


  Scenario Outline: Get user by username - positive scenario
    Given user sends POST request with valid parameters
      | id   | username   | firstName   | lastName   | email   | password   | phone   | userStatus   |
      | <id> | <username> | <firstName> | <lastName> | <email> | <password> | <phone> | <userStatus> |
    When user sends GET request with username <username>
    Then user retrieves id <id>
    Examples:
      | id        | username   | firstName | lastName | email          | password | phone      | userStatus |
      | 387845887 | OlolTester | Olol      | Tester1  | olol@gmail.com | tester   | 7234672334 | 2          |


    Scenario Outline: Get user by username - negative scenario
      Given user sends POST request with valid parameters
        | id   | username   | firstName   | lastName   | email   | password   | phone   | userStatus   |
        | <id> | <username> | <firstName> | <lastName> | <email> | <password> | <phone> | <userStatus> |
      When the user sends GET request with username "OlolTester1"
      Then user retrieves code
      And user retrieves type "error"
      And user retrieves msg "User not found"
      Examples:
        | id        | username   | firstName | lastName | email          | password | phone      | userStatus |
        | 387845887 | OlolTester | Olol      | Tester1  | olol@gmail.com | tester   | 7234672334 | 2          |


      Scenario Outline: Delete a user - positive scenario
        Given user sends POST request with valid parameters
          | id   | username   | firstName   | lastName   | email   | password   | phone   | userStatus   |
          | <id> | <username> | <firstName> | <lastName> | <email> | <password> | <phone> | <userStatus> |
        When user sends DELETE request with username <username>
        Then user retrieves code 200
        And user retrieves message <username>
        Examples:
          | id        | username  | firstName | lastName | email         | password | phone   | userStatus |
          | 387842563 | TesterOl1 | tOl       | Tester2  | tol@gmail.com | tester   | 7235634 | 1          |


  Scenario Outline: Delete a user - negative scenario
    Given user sends POST request with valid parameters
      | id   | username   | firstName   | lastName   | email   | password   | phone   | userStatus   |
      | <id> | <username> | <firstName> | <lastName> | <email> | <password> | <phone> | <userStatus> |
    When user sends DELETE request with username "OlolTester1"
    Then user retrieves code
    Examples:
      | id       | username | firstName | lastName | email            | password | phone    | userStatus |
      | 32234563 | userOl   | userOl    | Usser    | userOl@gmail.com | test     | 72378634 | 0          |


  Scenario Outline: Update a user
    Given user sends POST request with valid parameters
      | id   | username   | firstName   | lastName   | email   | password   | phone   | userStatus   |
      | <id> | <username> | <firstName> | <lastName> | <email> | <password> | <phone> | <userStatus> |
    When user sends PUT request with old username <username> and new username "userOl23updated"
    And the user sends GET request with username "userOl23updated"
    Then user retrieves username "userOl23updated"
    Examples:
      | id       | username | firstName | lastName | email             | password | phone     | userStatus |
      | 32234563 | userOl2  | userOl2   | Usser2   | userOl2@gmail.com | test2    | 723786342 | 0          |