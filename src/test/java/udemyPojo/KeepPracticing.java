package udemyPojo;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import utilities.PropertiesUtilities;

public class KeepPracticing extends PropertiesUtilities{

	@Test
	public void reqSpec() throws IOException {
		
		String filepath = getValuePropertyFile("testDataPath");
		
		
	}
}
