Feature: get tags
  i neet tags
  @Get
  Scenario: Andrea get tags
    Given Andrea is a client that can get tags
    When she send petition
    Then she must see the tags articles
