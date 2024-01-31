import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test01_GET {

	@Test
	public static void test_01() {

		Response resp = get("https://reqres.in/api/users?page=2");
//The Response class would likely contain information about the response from the server, such as the status code, headers, and the response body.
		
		System.out.println("Status Code: " + resp.getStatusCode());
		// System.out.println("Status Code: " + resp.getHeaders());
		System.out.println("Time: " + resp.time());
		System.out.println("Body: " + resp.getBody().asPrettyString());
		System.out.println("ContentType: " + resp.getContentType());

		int statusCode = resp.getStatusCode();
		Assert.assertEquals(200, statusCode);
	

	}

	@Test
	public static void test_02() {
		given().get("https://reqres.in/api/users?page=2").then()
		.body("data.id[0]", equalTo(7))
//		.body("data.first_name", hasItem("Lindsay")) --> 'hasItem' matcher is typically used for checking if 'a collection' contains a specific item.
		.body("data.first_name", hasItems("Michael", "Lindsay"))
		.statusCode(200);

	}

}
