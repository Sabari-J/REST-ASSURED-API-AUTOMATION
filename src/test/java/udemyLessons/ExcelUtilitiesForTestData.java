package udemyLessons;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import utilities.ExcelDataDriven;

public class ExcelUtilitiesForTestData {

	@Test
	public void useDataFromExcel() throws Exception {

		ExcelDataDriven data = new ExcelDataDriven();

		ArrayList<String> testData = data.handlingExcelTestData("Sample", "RestAssured");

		System.out.println(testData.get(1));
		System.out.println(testData.get(2));
		System.out.println(testData.get(3));
		System.out.println(testData.get(4));

	}

}
