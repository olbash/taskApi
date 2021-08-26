Feature: Pet feature

  Scenario Outline: Add a new pet to the store and add a photo
    When user creates a new pet with name, status
      | name   | status   |
      | <name> | <status> |
    Then api call is successful with status code 200
    And DTO contains specified name, status

    Examples:
      | name  | status    |
      | Alice | available |
      | Bob   | sold      |

  Scenario Outline: Find all pets with a particular status
    When user looks for pets with a particular '<status>':
    Then api call is successful with status code 200
    And only particular '<status>' is present in response:

    Examples:
      | status    |
#      | available |
      | sold      |

  Scenario Outline: Create a pet and update its name to "RickMorty"
    When user creates a new pet with name, status
      | name   | status   |
      | <name> | <status> |
    And user updates existing pet's name to "RickMorty" and status to "pending"
    Then api call is successful with status code 200
    And pet's name equals to "RickMorty", status equals to "pending"

    Examples:
      | name  | status    |
      | Alice | available |

  Scenario Outline: Create a pet and then delete it
    When user creates a new pet with name, status
      | name   | status   |
      | <name> | <status> |
    And user deletes the newly created pet
    Then api call is successful with status code 200
    When user retrieves pet by id
    And GET pet by id returns type : "error" and message: "Pet not found"

    Examples:
      | name  | status |
      | Alice | sold   |

