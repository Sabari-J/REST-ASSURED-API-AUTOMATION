package udemyPojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import udemyDataInputFiles.PayLoad;
import utilities.JsonDataDriven;

public class KeepPracticing extends JsonDataDriven {

	@Test(dataProvider = "bookdata")
	public void postBookDetails(String isbn, String aisle) {

		RestAssured.baseURI = "http://216.10.245.166";

		String resp = given().body(PayLoad.bookDetails(isbn, aisle)).header("contentType", "application/json")
				.when().post("Library/Addbook.php")
				.then().assertThat().statusCode(200)
				.body("Msg", equalTo("successfully added")).extract().asPrettyString();

		JsonPath jsp = new JsonPath(resp);
		String id = jsp.get("ID");
		System.out.println("id is " + id);
		//System.out.println(resp);

	}

	@DataProvider(name = "bookdata")
	public static Object[][] dataprovider() {
		return new Object[][] { { "sabari-10", "123" }, { "sabari-11", "456" }, { "sabari-12", "789" } }; //

	}

}
