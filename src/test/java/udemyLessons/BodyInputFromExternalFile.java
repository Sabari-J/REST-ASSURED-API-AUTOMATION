package udemyLessons;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utilities.JsonDataDriven;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

public class BodyInputFromExternalFile extends JsonDataDriven {

	@Test
	public static void invokingExternalJson() throws IOException {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String resp = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")

				// Taken the data(body) from an external file ( located in Downloads folder)
				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\sabareesan.j\\Downloads\\UDEMY- API Automation\\addPlace.json"))))
				.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().asString();
		System.out.println(resp);

		JsonPath jsrep = new JsonPath(resp);
		String id = jsrep.get("id");
		System.out.println("ID for this Request: " + id);
	}

}
