package udemyLessons;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import udemyDataInputFiles.PayLoad;

import static org.hamcrest.Matchers.*;


public class DynamicTestData {
	
	@Test (dataProvider = "BookData")
	public static void parameterizingTestData(String isbn, String aisle) {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().body(PayLoad.bookDetails(isbn, aisle))
				.when().post("/Library/Addbook.php")
				.then().assertThat().statusCode(200).body("Msg", equalTo("successfully added")).extract().asString();

		JsonPath jsResponse = new JsonPath(response);
		String id = jsResponse.get("ID");
		System.out.println("ID is :"+ id);

	}
	
	@Test(dataProvider = "BookData")
	public static void deleteTheRecentInput(String isbn, String aisle) {

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String response = given().body("{\r\n"
				+ " \r\n"
				+ "\"ID\" : \"a23h345122332\"\r\n" // ID mentioned here should be taken from the previous method (response - id)
				+ " \r\n"
				+ "} ")
				.when().post("/Library/DeleteBook.php")
				.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("Deleted successfully");
		System.out.println(response);

		JsonPath jsresp = new JsonPath(response);
		String message = jsresp.getString("msg");
		System.out.println("Response Message " + message);
		
	}
	
	@DataProvider (name = "BookData")
	public static Object[][] testDataProvider() {

		return new Object[][] { { "Aely.S", "1234" }, { "Wiel. K", "5678" }, { "Jaemy . W", "57858" } };
	}
	

}
