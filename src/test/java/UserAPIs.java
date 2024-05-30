import static io.restassured.RestAssured.given;

import org.testng.annotations.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.PropertiesUtilities;

public class UserAPIs {

	@Test()
	public static void CreateUser() {
		
		String Post_url = PropertiesUtilities.getPropertyData().getString("post_url");

//		Response resp = given()
//				.contentType(ContentType.JSON)
//				.accept(ContentType.JSON)
//				.body("PayLoad_Dummy")
//				.when()
//				.post(Post_url);
		System.out.println(Post_url);

//		return resp;
	}

}
