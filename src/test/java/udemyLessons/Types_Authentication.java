package udemyLessons;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class Types_Authentication {
	
  //The first 3	Authentication types are almost the same(using Username & Password), but only the algorithm in it makes them works differently
	@Test(priority = 0)
	public void basicAuthentication() {

		RestAssured.baseURI = "https://postman-echo.com";

		given().auth().basic("postman", "password")
		.when().get("/basic-auth")
		.then().statusCode(200).body("authenticated", equalTo(true)).log().all();

	}

	@Test(priority = 1)
	public void digestAuthentication() {

		RestAssured.baseURI = "https://postman-echo.com";

		given().auth().digest("postman", "password")
		.when().get("/basic-auth")
		.then().statusCode(200).body("authenticated", equalTo(true)).log().all();

	}

	@Test(priority = 2)
	public void preemptiveAuthentication() {

		RestAssured.baseURI = "https://postman-echo.com";

		given().auth().preemptive().basic("postman", "password")
		.when().get("/basic-auth")
		.then().statusCode(200).body("authenticated", equalTo(true)).log().all();

	}
	
	@Test(priority = 3)
	public void bearerTokenAuthentication() {
		
		/** client(User_Web) first authenticates itself with the server using credentials like a username and password. 
		Once authenticated, the server issues a bearer token to the client **/ 
		// ==>Check the Screenshot
		
		String bearerToken = "(check the docs fetched from Udemy folder for the token)"; // Generated from Github acc, but it will be different for each applications
		
		given().headers("Authorization", "Bearer "+ bearerToken)
		.when().get("https://api.github.com/user/repos")
		.then().statusCode(200).log().all();
		
	}
	
	@Test(priority = 4)
	public void oauth1Authentication() {
		
		given()
		// For the below parameters we need to contact and get it from the Devs
		 .auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")
		.when().get("URL")
		.then().statusCode(200).log().all();
	}
	
	@Test(priority = 5)
	public void oauth2Authentication() {
		/** In oauth2 Authentication, we need to hit a request and fetch some data from its response/URL,
		Then we need to use that specific data as input in another request, by which we can get the accesstoken
		Post that we can use/input the accesstoken in another request**/  
		// ==>Check "OAuthAndDeSerialization" Class
		
		given()
		 .auth().oauth2("accessToken")
		.when().get("URL")
		.then().statusCode(200).log().all();
	}

	@Test(priority = 6)
	public void apiKeyAuthentication() {
		// To generate an API key, we need to login the api_url and we can generate it.
		// ==>Check the Screenshot
		given().queryParam("appid", "fdasdge5hdrtw123414xsf")// app id is the API key
				.given().get("URL")
				.then().statusCode(200).log().all();
	}
}

