package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PropertiesUtilities {

	public static String getValuePropertyFile(String value) throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\sabareesan.j\\eclipse\\eclipse-workspace\\RestAssuredAutomationAPI\\src\\test\\resources\\Global.properties");
		prop.load(fis);

		//System.out.println(prop.get(value));
		return (String) prop.get(value);

	}
	
	//This method can be used as an alternative for the above method- to fetch a value from the properties file
	public static ResourceBundle getPropertyData() {
		ResourceBundle value = ResourceBundle.getBundle("routes"); //Load the respective properties file (e-x) 'routes' file here
		return value;
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(getValuePropertyFile("testDataPath"));
		// This is dummy method to run this class
	}

	public static String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		String value = js.get(key).toString();

		return value;

	}
}
