import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Test02_POST {

	@Test
	public static void postRequest() {

		JSONObject jsonObj = new JSONObject();
//		The JSONObject class is part of the JSON-java library, and it provides a way to represent and manipulate JSON objects in Java.

		jsonObj.put("name", "Sabari");
		jsonObj.put("job", "Lead");
		
		
		System.out.println("<============> \n" +jsonObj.toString() + "\n <============> \n");

		RestAssured.baseURI = "https://reqres.in";

		RestAssured.given()
		.log().all()
		.header("", "")
		.body(jsonObj.toJSONString())
		.post("/api/users")
		.then()
		.statusCode(201)
		.log().all();

	}
	
	@Test
	public static void putAndPatchRequest() {
		
		RestAssured.baseURI = "https://reqres.in";
		
		JSONObject dataInput = new JSONObject();
		dataInput.put("name", "Rashi");
		dataInput.put("job", "Racer");
				
		String resp = RestAssured.given()
		.body(dataInput.toJSONString())		
		.contentType(ContentType.JSON)
		
		/** put (or) patch request for update -->
		PUT - handles updates by replacing the entire entity,
		while PATCH - only updates the fields that you give it **/
		
		.when().patch("/api/users/2")
		//.when().put("/api/users/2")
		.then().statusCode(200)
		.extract().response().asString();
		
		System.out.println(resp);
		
	}
	
	
	@Test
	public void deleteRequest() {
		
		RestAssured.when().delete("https://reqres.in/api/users/2")
		.then().statusCode(204).log().all();
	}
	

	
}
