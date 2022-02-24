Feature: Articles

  @Get
  Scenario: List articles
    Given Andrea need look the articles
    When she sends the correct request for the get request
    Then she must see the articles

  @Post
  Scenario Outline: Post article
    Given Andrea needs to submit an article
    When she sends the correct fields
      | tagList     | <tagList>     |
      | title       | <title>       |
      | description | <description> |
      | body        | <body>        |
    Then  she must create the article correctly
    Examples:
      | tagList | title     | description | body              |
      | fruta   | manzana   | roja        | manzana roja      |
      | fruta   | pera      | verde       | verde pera        |
      | verdura | zanahoria | naranja     | naranja zanahoria |
  @Put
  Scenario: Put article
    Given Andrea need to update
    When she sends the correct information
    Then she must update the article correctly

  @Delete
  Scenario: Delete article created
    Given Andrea need delete the article
    When she sends the correct petition
    Then she must delete the article correctly
