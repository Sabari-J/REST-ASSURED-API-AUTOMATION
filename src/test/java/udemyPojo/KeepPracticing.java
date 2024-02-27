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
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import udemyDataInputFiles.PayLoad;

public class KeepPracticing {

	@Test
	public void reqSpec() throws FileNotFoundException {

		PrintStream LogFile = new PrintStream(new FileOutputStream("LogFile.txt"));
		
		 RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
		.addFilter(RequestLoggingFilter.logRequestTo(LogFile))
		.addFilter(ResponseLoggingFilter.logResponseTo(LogFile))
		.build();
		 
		 
		 ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).expectStatusLine("HTTP/1.1 200 OK")
				 .expectContentType(ContentType.JSON).build();
		 
		 
		  RequestSpecification request = given().spec(req).body(PayLoad.courseDetails());
		  
		  Response response = request.when().post("").then().spec(res).extract().response();
		 
		
	}
}
