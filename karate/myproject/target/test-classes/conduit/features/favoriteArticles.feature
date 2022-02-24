Feature: Test for the favorite article

Background:precondicions
    * def timeValidator = read('classpath:conduit/helpers/time-validator.js')
    * def tokenResponse = callonce read('classpath:conduit/helpers/createToken.feature')
    * def token = tokenResponse.authToken
    * def slug = callonce read('classpath:conduit/helpers/defSlug.feature')
    * def dataSlug = slug.responseSlug
    * url apiUrl

Scenario:Favorite articles
    
     # Step 3: Make POST request to increse favorites count for the first 
    Given path 'articles/'+ dataSlug+'/favorite'
    And header Authorization = 'Token ' + token
    When method Post
    Then status 200
    And print response
    
      # Step 4: Verify response schema
      And match response ==
      """
          {
      "article": {
          "id": "#number",
          "slug": "#string",
          "title": "#string",
          "description": "#string",
          "body": "#string",
          "createdAt": "#? timeValidator(_)",
          "updatedAt": "#? timeValidator(_)",
          "authorId":"#number",
          "tagList":"#array",
  
          "author": {
              "username": "#string",
              "bio": "##string",
              "image": "#string",
              "following": "#boolean"
          },
  
          "favoritedBy": [
          {
                  "id": "#number",
                  "email": "#string",
                  "username": "#string",
                  "password": "#string",
                  "image": "#string",
                  "bio": "##string",
                  "demo": "#boolean",
          }
          ],
          "favorited": "#boolean",
          "favoritesCount": "#number"
      }
  }
      
      """
 # Step 5: Verify that favorites article incremented by 1
 Given path 'articles'
 Given params {favorited:Andrea, limit:10, offset:0}
 And header Authorization = 'Token ' + token
 When method Get
 Then status 200
    * def initialCount = 0
    * match response.articles[0].favoritesCount == initialCount + 1

    # Step 6: Get all favorite articles
    Given path 'articles'
    Given params {favorited:Andrea, limit:10, offset:0}
    And header Authorization = 'Token ' + token
    When method Get
    Then status 200

    # Step 7: Verify response schema
    And match each response.articles ==
    """
        {
			"slug": "#string",
			"title": "#string",
			"description": "#string",
			"body": "#string",
			"createdAt": "#? timeValidator(_)",
			"updatedAt": "#? timeValidator(_)",
			"tagList": "#array",
			"author": {
				"username": "#string",
				"bio": "##string",
				"image": "#string",
				"following": "#boolean"
			},
			"favoritesCount": "#number",
			"favorited": "#boolean"
		}
        """
    # Step 8: Verify that slug ID from Step 2 exist in one of the favorite article
    Given path 'articles'
    Given params {favorited:Andrea, limit:10, offset:0}
    And header Authorization = 'Token ' + token
    When method Get
    Then status 200
    And match response.articles[0].slug == dataSlug 

    # Step 9: 
    Given path 'articles/'+ dataSlug+'/favorite'
    And header Authorization = 'Token ' + token
    When method delete
    Then status 200
    And match response.article.favoritesCount == 0