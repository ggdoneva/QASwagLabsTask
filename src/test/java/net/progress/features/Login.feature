Feature: Login features
  In order to successfully enter the website
  As a client
  I want to check the validation of my credentials

  Background:
    Given a user lands on the login page

  Scenario Outline: The client successfully logs in the website
    When the user fills their "<username>" and "<password>"
    Then the user is successfully logged in to the website

    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |

  Scenario Outline: The client has locked their account
    When the user fills their "<username>" and "<password>"
    Then the user is shown the account locked message

    Examples:
      | username        | password     |
      | locked_out_user | secret_sauce |

  Scenario Outline: The client is entering incorrect credentials
    When the user fills their "<username>" and "<password>"
    Then the user is shown the username and password mismatch message

    Examples:
      | username      | password     |
      | standard_user | wrong_sauce  |
      | wrong_user    | secret_sauce |