package udemyLessons;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import udemyDataInputFiles.PayLoad;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ParsingJsonBodyResponseAsString {

	@Test
	public static void parseResponse() {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json")
				.body(PayLoad.locationJsonData())
				.when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200)
				// Validation of a particular value in Json response
				.body("scope", equalTo("APP")).extract().response().asString();

		System.out.println(response);

		// JsonPath class to parse the response data as String to select/check each
		// attribute in response
		JsonPath jsonStringResponse = new JsonPath(response);
		String statusLine = jsonStringResponse.getString("status");
		String placeId = jsonStringResponse.getString("place_id");

		System.out.println("This is the Status for this request " + statusLine);
		System.out.println("This is the Place ID for this request " + placeId);

	}

}
