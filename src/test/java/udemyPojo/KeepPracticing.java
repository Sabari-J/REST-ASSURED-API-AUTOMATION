package udemyPojo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
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

public class KeepPracticing {

	@Test
	public void reqSpec() throws FileNotFoundException {

			PrintStream logfile= new PrintStream (new FileOutputStream("logfile.txt"));
			
			RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
			.addFilter(RequestLoggingFilter.logRequestTo(logfile))
			.addFilter(ResponseLoggingFilter.logResponseTo(logfile))
			.setContentType(ContentType.JSON).build();
			
			ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectStatusLine("")
			.expectContentType(ContentType.JSON).build();
			
		RequestSpecification requestData = given().spec(req).body(PayLoad.courseDetails());
		
		Response responseData = requestData.when().post("").then().spec(res).extract().response();
		
		
		JsonPath jsp = new JsonPath(responseData.asString());
		
		
		
	}
}
