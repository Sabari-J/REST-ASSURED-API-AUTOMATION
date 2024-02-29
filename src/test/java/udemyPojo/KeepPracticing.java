package udemyPojo;

import java.io.FileNotFoundException;

import org.testng.annotations.Test;

public class KeepPracticing {

	@Test
	public void reqSpec() throws FileNotFoundException {
		
		String completeURL = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWjmQ8GLxE0oPAjIO6wnDa8ebmS6sqysVZfXsWnzTOwvZMj6Zo3_zOzN_OAqh455_A&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";

		String midURL = completeURL.split("code=")[1];
		String code = midURL.split("&scope")[0];
		
		System.out.println(code);

	}
}
