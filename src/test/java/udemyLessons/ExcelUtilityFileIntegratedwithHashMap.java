package udemyLessons;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import udemyDataInputFiles.PayLoad;
import utilities.ExcelDataDriven;

public class ExcelUtilityFileIntegratedwithHashMap {

	ExcelDataDriven testdata = new ExcelDataDriven();

	@Test
	public void testDatafromExcel() throws Exception {

		System.out.println(testdata.handlingExcelTestData("Sample", "RestAssured"));

		HashMap<String, Object> map = new HashMap<String, Object>();

		testdata.handlingExcelTestData("Sample", "RestAssured");
		map.put("name", map.get(1));
		map.put("phone_number", "(+91) 983 893 3937");
		map.put("address", "29, ss layout, cohen 09, shoe park");
		map.put("website", "www.SSGroups.com");
		map.put("language", "Tamil");

		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("lat", "-38.383494");
		map2.put("lng", "33.427");
		map2.put("location", map2); //

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(PayLoad.bodyData())
				.when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200)

				// Validation of a particular value in Json response/Header
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.41 (Ubuntu)");
	}

}
