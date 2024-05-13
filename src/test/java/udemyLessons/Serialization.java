package udemyLessons;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import udemyPojo.GetLocation;
import udemyPojo.Location;

public class Serialization {

	public Logger logger = LogManager.getLogger(this.getClass());

	@Test
	public void serializeConcept() {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		GetLocation location = new GetLocation();

		location.setAccuracy(50);
		location.setAddress("232, Electronic City, Bangalore");
		location.setLanguage("Tamil");
		location.setName("JS Home");
		location.setPhone_number("6666567");
		location.setWebsite("www.rahulshetty.com");

		List<String> types = new ArrayList<String>();
		types.add("Shoe Park");
		types.add("Park Town");

		location.setTypes(types);

		Location locationDetails = new Location();
		locationDetails.setlatitude(-38.34523222);
		locationDetails.setlongitude(-45.23464235);

		location.setLocation(locationDetails);

		// In body we have passed the object(Pojo Class) as a payload
		Response resp = given()
				// .log().all()
				.queryParam("key", "qaclick123").body(location).when().post("/maps/api/place/add/json").then()
				.assertThat().statusCode(200).extract().response();
		logger.info("*** Post Request has been done ***");

		String finalResponse = resp.asString();
		logger.info("*** Below is the Response ***");
		System.out.println("Success" + resp);
		
	}

}
