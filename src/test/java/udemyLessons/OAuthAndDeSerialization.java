package udemyLessons;

import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import udemyPojo.GetDetails;
import udemyPojo.WebAutomation;

public class OAuthAndDeSerialization {

	@Test
	public static void oAuthAuthorizationCode() {

		String[] actualCourses = {"Selenium Webdriver Java", "Cypress"};
		
		// https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
		// Hit the above url(gives the below URL) in browser and login then copy paste
		// the blank page URL - since the below url will keep on changing

		String completeURL = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWjmQ8GLxE0oPAjIO6wnDa8ebmS6sqysVZfXsWnzTOwvZMj6Zo3_zOzN_OAqh455_A&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";

		String midURL = completeURL.split("code=")[1];
		String code = midURL.split("&scope")[0];

		String data = given().urlEncodingEnabled(false).queryParams("code", code)

				.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

				.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

				.queryParams("grant_type", "authorization_code")

				.queryParams("state", "verifyfjdss")

				.queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")

				.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")

				.when().post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath jsonResp = new JsonPath(data);
		String accessToken = jsonResp.getString("access_token");

		// return type is GetDetails, since the json response will be stored as java object (using GetDetails Class_POJO), instead of string/response
		GetDetails resp = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
				.when().get("https://rahulshettyacademy.com/getCourse.php").as(GetDetails.class);
		

		System.out.println(resp.getLinkedIn());
		System.out.println(resp.getInstructor());
		resp.setInstructor("John");
		System.out.println(resp.getInstructor());

		ArrayList<String> expectedCourses = new ArrayList<String>();
		List<WebAutomation> courseNames = resp.getCourses().getWebAutomation(); // Fetching course details for the courses under WebAutomation(List)
		// OR --->resp.getCourses().getWebAutomation().get(1).getCourseTitle();
		for (WebAutomation name : courseNames) {
			expectedCourses.add(name.getCourseTitle());

			if (name.getCourseTitle().equalsIgnoreCase("Cypress")) {
				System.out.println(name.getPrice());
				break;
			}

		}

		System.out.println("Course Name: " + expectedCourses);

		List<String> actual = Arrays.asList(actualCourses);//converts arrays to arraylist, so that we can compare both the arraylists

		Assert.assertTrue(actual.equals(expectedCourses));
	}

}
