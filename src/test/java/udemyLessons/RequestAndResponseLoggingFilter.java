package udemyLessons;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
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

		RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123")

				/**
				 * RequestLoggingFilter - Will log the request, This filter will only log things
				 * specified in the request specification
				 * 
				 * &&
				 * 
				 * ResponseLoggingFilter - A filter that'll print the response body
				 **/

				.addFilter(RequestLoggingFilter.logRequestTo(reportLogFile))
				.addFilter(ResponseLoggingFilter.logResponseTo(reportLogFile))

				.setContentType(ContentType.JSON).build();

		ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectStatusLine("HTTP/1.1 200 OK").expectContentType(ContentType.JSON).build();

		// given - Inputs for the request
		RequestSpecification res = given().spec(reqSpec).body(PayLoad.bodyData());

		// when - action of the request
		Response validateResponse = res.when().post("/maps/api/place/add/json")

				// then - validation/assertion for the request
				.then().spec(responseSpec).extract().response();

		String comparingValues = validateResponse.asString();
		System.out.println(comparingValues);

	}

}
