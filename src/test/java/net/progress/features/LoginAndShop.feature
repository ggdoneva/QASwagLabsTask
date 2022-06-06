Feature: Shop features
  In order to operate with the shop
  As a client
  I want the option to add and remove items to the card

  Background:
    Given a user lands on the SwagLabs login page
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the user lands on the Products page

  Scenario Outline: Login and shop - test 1
    Given the user adds items to the cart:
      | Item 1  | Item 2  | Item 3  |
      | <Item1> | <Item2> | <Item3> |
    When the user opens the cart
    Then the cart is updated with the respective products

    Examples:
      | Item1               | Item2                 | Item3                   |
      | Sauce Labs Backpack |                       |                         |
      | Sauce Labs Backpack | Sauce Labs Bike Light |                         |
      | Sauce Labs Backpack | Sauce Labs Bike Light | Sauce Labs Bolt T-Shirt |

  Scenario Outline: Login and shop - test 2
    Given the user adds items to the cart, one by one:
      | Item 1  | Item 2  | Item 3  |
      | <Item1> | <Item2> | <Item3> |
    When the user opens the cart
    Then the cart is updated with the respective products

    Examples:
      | Item1               | Item2                 | Item3                   |
      | Sauce Labs Backpack | Sauce Labs Bike Light | Sauce Labs Bolt T-Shirt |

  Scenario Outline: Login and sort to shop
    When the users selects the type of "<sorting>" sorting
    Then the "<first>" first product will be as follows

    Examples:
      | sorting             | first                             |
      | Name (A to Z)       | Sauce Labs Backpack               |
      | Name (Z to A)       | Test.allTheThings() T-Shirt (Red) |
      | Price (low to high) | Sauce Labs Onesie                 |
      | Price (high to low) | Sauce Labs Fleece Jacket          |

  Scenario: Login, shop and checkout
    Given the user adds "Sauce Labs Backpack" item to the cart
    When the user navigates to Checkout page
    And the user fills the following details:
      | FirstName | LastName | ZipCode |
      | Gergana   | Doneva   | 1715    |
    And proceeds with checkout
    Then the user successfully places the order

