Feature: Comment articles
Background:precondicions
    * def newComent = read('classpath:conduit/data/newComentArticle.json')
    * def timeValidator = read('classpath:conduit/helpers/time-validator.js')
    * def tokenResponse = callonce read('classpath:conduit/helpers/createToken.feature')
    * def token = tokenResponse.authToken
    * def slug = callonce read('classpath:conduit/helpers/defSlug.feature')
    * def dataSlug = slug.responseSlug
    * url apiUrl
    

Scenario: Comment articles
    # Step 2: Get the slug ID for the first arice, save it to variable
    # Step 3: Make a GET call to 'comments' end-point to get all comments
    Given path 'articles/'+ dataSlug+'/comments'
    And header Authorization = 'Token ' + token
    When method Get
    Then status 200
    * print response
    # Step 4: Verify response schema
    And match response ==
    """
    {
    "comments": [
        {
            "id": "#number",
            "createdAt": "#? timeValidator(_)",
            "updatedAt": "#? timeValidator(_)",
            "body": "#string",
            "author": {
                "username": "#string",
                "bio": "##string",
                "image": "#string",
                "following": "#boolean"
            }
        }
    ]
}
"""
    # Step 5: Get the count of the comments array lentgh and save to variable
    * def commentsCount = karate.sizeOf(response.comments)
    * print commentsCount
    # Step 6: Make a POST request to publish a new comment
    Given path 'articles/'+ dataSlug+'/comments'
    And header Authorization = 'Token ' + token
    And request newComent
    When method Post
    Then status 200
    # Step 7: Verify response schema that should contain posted comment text
    And match response ==
    """
        {
	"comment": {
		"id":"#number",
		"createdAt": "#? timeValidator(_)",
		"updatedAt": "#? timeValidator(_)",
		"body": "#string",
		"author": {
			"username": "#string",
			"bio": "##string",
			"image": "#string",
			"following":"#boolean" 
		}
	}
}
    """
    # Step 8: Get the list of all comments for this article one more time
    Given path 'articles/'+ dataSlug+'/comments'
    And header Authorization = 'Token ' + token
    When method Get
    Then status 200
    * def idComment = response.comments[0].id
    * print response

    
# Step 9: Verify number of comments increased by 1 (similar like we did with favorite counts)
Given path 'articles/'+ dataSlug+ '/comments'
And header Authorization = 'Token ' + token
* def initialCount = commentsCount
* def newCuent = karate.sizeOf(response.comments)
* match  newCuent == initialCount + 1
* print newCuent

    # Step 10: Make a DELETE request to delete comment
 Given path idComment
 And header Authorization = 'Token ' + token
 When method Delete
 Then status 204
 * print response


 # Step 11: Get all comments again and verify number of comments decreased by 1

 Given path 'articles/'+ dataSlug+'/comments'
 And header Authorization = 'Token ' + token
 When method Get
 Then status 200
 
 * def finalCuent = karate.sizeOf(response.comments)
 * match  finalCuent == newCuent -1
 * print finalCuent