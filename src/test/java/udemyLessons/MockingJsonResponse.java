package udemyLessons;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import junit.framework.Assert;
import udemyDataInputFiles.PayLoad;

@SuppressWarnings("deprecation")
public class MockingJsonResponse {
	
	@Test
	public static void getCourseDetails() {
		
		JsonPath jsonMockingData = new JsonPath(PayLoad.courseDetails());
		
		//1. Print No of courses returned by API
		int noOfCourses = jsonMockingData.getInt("courses.size()");
		System.out.println("Number of courses :" + noOfCourses);
		
		//2. Print Purchase Amount
		int purchaseAmount = jsonMockingData.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount :" + purchaseAmount);
		
		//3. Print Title of the first course
		String titleOfCourse = jsonMockingData.getString("courses[0].title");
		System.out.println("Title Of the Course :" + titleOfCourse);
		
		//4. Print All course titles and their respective Prices
		for (int i = 0; i < noOfCourses; i++) {
			String titleOfCourses = jsonMockingData.getString("courses[" + i + "].title");
			int PriceOfCourses = jsonMockingData.getInt("courses[" + i + "].price");
			System.out.println(" Title Of the Course " + (i + 1) + ": " + titleOfCourses);
			System.out.println(" Price Of the Course " + (i + 1) + ": " + PriceOfCourses + "$");
		}
	
		//5. Print no of copies sold by RPA Course

		for (int i = 0; i < noOfCourses; i++) {
			String course = jsonMockingData.getString("courses[" + i + "].title");
			if (course.contentEquals("RPA")) {
				int count = jsonMockingData.getInt("courses[" + i + "].copies");
				System.out.println("copies sold by RPA Course: " + count);
				break;
			}
		}
		
		// Sum of all Course prices = Total Purchase Amount
		int sum = 0;
		for (int i = 0; i < noOfCourses; i++) {
			int price = jsonMockingData.getInt("courses[" + i + "].price");
			int copies = jsonMockingData.getInt("courses[" + i + "].copies");
			int priceofCourse = (price * copies);
			sum = sum + priceofCourse;
		}
		System.out.println("Sum of all Course prices: " + sum);

		int totalPurchaseAmt = jsonMockingData.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, totalPurchaseAmt);
		System.out.println("Sum of all the Course price is same as Total Purchase Amount");

	}
	
}
