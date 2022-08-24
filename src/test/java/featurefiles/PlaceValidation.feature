Feature: Login 

Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
	Given Add Place Payload with "<name>"  "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"
	Then check the "testdata.compose.soundex[0].info.date_of_birth"
	Then check the "testdata.compose.soundex[0].info.name_id"
	
Examples:
	|name 	 | language |address		   |
	|AAhouse |  English |World cross center|
	
	
Scenario Outline: Verify 2nd Place is being Succesfully added using AddPlaceAPI
	Given Add Place Payload with "<name>"  "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"
	
Examples:
	|name 	 | language |address		   |
	|BBhouse | Spanish  |Sea cross center  |
