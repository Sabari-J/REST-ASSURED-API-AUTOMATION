package utilities;

import java.io.BufferedReader;

import java.io.FileReader;
import io.restassured.path.json.JsonPath;

public class JsonDataDriven extends PropertiesUtilities {

	// public String key = "testdata.compose.soundex[0].info.date_of_birth";

	public static String readTestData(String key) throws Exception {

		String filepath = getValuePropertyFile("testDataPath");
		BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/src/test/resources/TestData/" + filepath));
		String json = "";
		try {
			
			String line = reader.readLine();
			StringBuilder sb = new StringBuilder();
			
			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = reader.readLine();
			}
			json = sb.toString();

		} finally {
			reader.close();
		}

		JsonPath jsp = new JsonPath(json);
		String val = jsp.get(key).toString();

		return val;
	}
	
	

}
