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
	public void bearerToken() {
		String bearerToken = "(check the docs fetched from Udemy folder for the token)"; // Generated from Github acc, but it will be different for each applications
		
		given().headers("Authorization", "Bearer "+ bearerToken)
		.when().get("https://api.github.com/user/repos")
		.then().statusCode(200).log().all();
		
	}

}
