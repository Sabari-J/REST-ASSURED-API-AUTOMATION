package udemyLessons;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import udemyDataInputFiles.PayLoad;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JsonBodyResponseAssertions {

	@Test
	public static void parseResponse() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(PayLoad.bodyData())
		.when().post("/maps/api/place/add/json")
		.then()
		.log().all()
		.assertThat()
		.statusCode(200)
				
		//Validation of a particular value in Json response/Header
		.body("scope", equalTo("APP"))
		
		//Validation of multiple values in Json response - Below mentioned "hasItems()" is just an example, not a part of this API
		// .body("scope", hasItems("APP", "APP2", "APP3"));
		
		.header("server", "Apache/2.4.41 (Ubuntu)");
		
		
		
		
		 
	}

}
