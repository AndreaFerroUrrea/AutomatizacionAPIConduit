Feature: Login to users
  i neet login to user

  @Post
  Scenario Outline: Login usuario
    Given Andrea is a client that can login
    When she enters the data "<email>" y  "<password>" correctly
    Then she must make a correct record
    Examples:
      | email                      | password     |
      | johanaandrea1000@gmail.com | 14Empanadas. |

