package udemyLessons;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import udemyDataInputFiles.PayLoad;
import udemyPojo.GetLocation;
import udemyPojo.Location;

public class SerializationAndDeserializationByPavan {


	 // => Serialization and Deserialization both helps to parse and extract the responses (Json/XML)
	

	@Test
	public static void serializeMethod() throws Exception {
		
		// => Serialization -> converting an object (POJO) to a (Json)file/network supported form (Payload) 
		
		//Add the data to the class(POJO) and create the POJO class ==>setter methods helps here...

		GetLocation location = new GetLocation();

		location.setAccuracy(50);
		location.setAddress("232, Electronic City, Bangalore");
		location.setLanguage("Tamil");
		location.setName("JS Home");
		location.setPhone_number("6666567");
		location.setWebsite("www.rahulshetty.com");

		List<String> types = new ArrayList<String>();
		types.add("Shoe Park");
		types.add("Park Town");

		location.setTypes(types);

		Location locationDetails = new Location();
		locationDetails.setlatitide(-38.34523222);
		locationDetails.setlongitude(-45.234645);

		location.setLocation(locationDetails);
		
		
		//Converting Java Object (POJO) to Json Object 
		ObjectMapper objMapper = new ObjectMapper();
		
		String jsonData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(location);
		
		System.out.println(jsonData);
		
	}

	@Test
	public static void deserializeMethod() throws Exception {
		// DeSerialization -> converting back a file(Json)/network supported form to an object (POJO) ==>getter methods helps here...

		String jsonData = "{\r\n"
				+ "  \"location\" : {\r\n"
				+ "    \"longitude\" : -45.234645,\r\n"
				+ "    \"latitude\" : -45.234\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\" : 50,\r\n"
				+ "  \"name\" : \"JS Home\",\r\n"
				+ "  \"phone_number\" : \"6666567\",\r\n"
				+ "  \"address\" : \"232, Electronic City, Bangalore\",\r\n"
				+ "  \"types\" : [ \"Shoe Park\", \"Park Town\" ],\r\n"
				+ "  \"website\" : \"www.rahulshetty.com\",\r\n"
				+ "  \"language\" : \"Tamil\"\r\n"
				+ "}" ;
		
		//Converting Json Object to Java Object(POJO_GetLocation class) 
				ObjectMapper objMapper = new ObjectMapper();
				
				// return type is GetLocation, since the json response will be stored as java object (using GetLocation Class_POJO), instead of string/response
				GetLocation locDetailPOJO = objMapper.readValue(jsonData, GetLocation.class);
			
				System.out.println(locDetailPOJO.getName());
				System.out.println(locDetailPOJO.getLanguage());
				System.out.println(locDetailPOJO.getTypes());
				
	}

}
