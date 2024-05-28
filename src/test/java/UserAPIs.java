import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.PropertiesUtilities;

public class UserAPIs {

	public static Response CreateUser() {
		String Post_url = PropertiesUtilities.getPropertyData().getString("post_url");

		Response resp = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body("PayLoad_Dummy")
				.when()
				.post(Post_url);

		return resp;
	}

}
