package udemyLessons;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class JsonSchemaValidation {

	@Test
	public void jsonSchemaValidatation() throws IOException {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

				given().contentType(ContentType.JSON).queryParam("key", "qaclick123")
				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\sabareesan.j\\Downloads\\UDEMY- APIs Automation\\addPlace.json"))))
				
				.when().post("/maps/api/place/add/json")
				//.then().log().all();

				.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema.json"));
				System.out.println("Successfully validated the Json schema");

	}

}
