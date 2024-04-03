package udemyLessons;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import udemyDataInputFiles.PayLoad;

import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class RequestAndResponseLoggingFilter {

	@Test
	public static void parseResponse() throws FileNotFoundException {

		PrintStream reportLogFile = new PrintStream(new FileOutputStream("Output.txt"));
		//PrintStream used for printing to the console or to write to files or other output streams.

		RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123")

				/**
				 * RequestLoggingFilter -  used to log incoming requests, This filter will only log things
				 * specified in the request specification 
				 * &&
				 * ResponseLoggingFilter - used to log outgoing responses
				 * 
				 * By adding these filters, we are ensuring that all incoming requests 
				 * and outgoing responses are logged to the same file (reportLogFile in this case).
				 **/

				.addFilter(RequestLoggingFilter.logRequestTo(reportLogFile))
				.addFilter(ResponseLoggingFilter.logResponseTo(reportLogFile))

				.setContentType(ContentType.JSON).build();

		ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectStatusLine("HTTP/1.1 200 OK").expectContentType(ContentType.JSON).build();

		// given - Inputs for the request
		RequestSpecification res = given().spec(reqSpec).body(PayLoad.locationJsonData());

		// when - action of the request
		Response validateResponse = res.when().post("/maps/api/place/add/json")

				// then - validation/assertion for the request
				.then().spec(responseSpec).extract().response();

		//String comparingValues = validateResponse.asString();
		JsonPath jsp = new JsonPath(validateResponse.asString());
		System.out.println(jsp.get("id"));

	}

}
