package udemyLessons;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import udemyDataInputFiles.PayLoad;

import static io.restassured.RestAssured.*;

public class RequestAndResponseSpecBuilder {

	@Test
	public static Response parseResponse() {

		// RestAssured.baseURI = "https://rahulshettyacademy.com"; --> replaced by "RequestSpecBuilder"
		// .log().all().assertThat().statusCode(200) --> replaced by - ResponseSpecification
		
		/**RequestSpecBuilder && ResponseBuilder **/
		RequestSpecification reqSpec = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
	
		ResponseSpecification responseSpec = new ResponseSpecBuilder()
				.expectStatusCode(200)
				.expectStatusLine("HTTP/1.1 200 OK")
				.expectContentType(ContentType.JSON).build();
		
		
		// given - Inputs for the request
		RequestSpecification res = given().spec(reqSpec).body(PayLoad.bodyData());
		
		// when - action of the request
		Response validateResponse = res.when().post("/maps/api/place/add/json")
		
		// then - validation/assertion for the request		
				.then().spec(responseSpec).extract().response();	

		String comparingValues = validateResponse.asString();
		System.out.println(comparingValues);
		
		return validateResponse;
		
	}

}
