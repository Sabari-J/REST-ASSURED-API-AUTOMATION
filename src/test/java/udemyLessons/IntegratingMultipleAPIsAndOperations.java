package udemyLessons;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import udemyDataInputFiles.PayLoad;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class IntegratingMultipleAPIsAndOperations {

	@Test
	public static void parseResponse() {
		
			RestAssured.baseURI = "https://rahulshettyacademy.com";
			
			
			// POST --> Add Place API
			String resp = given().queryParam("key", "qaclick123")
			.body(PayLoad.bodyData())
			.when().post("/maps/api/place/add/json")
			.then()
			.assertThat().statusCode(200)
			.body("status",equalTo("OK")).extract().asString();
			
			System.out.println(resp);
			
			JsonPath jsresponse = new JsonPath(resp);
			String placeId = jsresponse.get("place_id");
			System.out.println( "PlaceID : "+ placeId);
			
			
			// UPDATE --> Update Place API
			
			String UpdateResponse = given().queryParam("key", "qaclick123")
			.body("{\r\n"
					+ "\"place_id\":\""+placeId+"\",\r\n"
					+ "\"address\":\"70 sabari worksIn, USA\",\r\n"
					+ "\"key\":\"qaclick123\"\r\n"
					+ "}")
			.when().put("maps/api/place/update/json")
			.then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated")).extract().asString();
			
			JsonPath msgResponse = new JsonPath(UpdateResponse);
			String Response = msgResponse.get("msg");
			
			System.out.println( "Response : "+ Response);
			
			// GET --> Get Place API
			
			String address = given().queryParam("place_id", placeId).queryParam("key", "qaclick123")
			.body("")
			.when().get("/maps/api/place/get/json")
			.then().assertThat().statusCode(200).extract().asString();
			
			JsonPath jsAddressResponse = new JsonPath(address);
			String addressData = jsAddressResponse.getString("address");
			System.out.println(addressData);
			Assert.assertEquals(addressData, "70 sabari worksIn, USA");
						 
	}

}
