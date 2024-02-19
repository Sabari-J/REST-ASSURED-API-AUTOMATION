package udemyLessons;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import utilities.ExcelDataDriven;

public class ExcelUtilityFileIntegratedwithHashMap {

	@Test
	public void testDatafromExcel() throws Exception {
		
		ExcelDataDriven testdata = new ExcelDataDriven();
		ArrayList<String> data = testdata.handlingExcelTestData("Sample", "RestAssured");// ArrayList which has the value of the TestCase mentioned as "Parameter"
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", data.get(1)); //==> Frontline house
		map.put("phone_number", data.get(2)); // ==> (+91) 983 893 3937
		map.put("address", data.get(3)); //==> 29, ss layout, cohen 09, shoe park
		map.put("website", data.get(4)); //==> www.SSGroups.com
		map.put("language", data.get(5)); //==>Tamil

		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("lat", "-38.383494");
		map2.put("lng", "33.427");
		map.put("location", map2); //to deal Nested Array in Json, we can create like this

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(map)
		//.body(PayLoad.bodyData())
		//System.out.println(map);
		.when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200)

		// Validation of a particular value in Json response/Header
		.body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)");
	}

}
