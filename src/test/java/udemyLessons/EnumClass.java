package udemyLessons;

public enum EnumClass {

	// Enum is a special data type in Java used to define a 'collection of constants/methods'

	// Below 3 are the Enum methods with parameter
	AddPlaceAPI("/maps/api/place/add/json"), 
	DeletePlaceAPI("/maps/api/place/delete/json"),
	GetPlaceAPI("/maps/api/place/get/json");

	private String resource; 

	// Constructor
	EnumClass(String string1) {
		this.resource = string1; // assigning the variable(addPlaceAPI/getplaceAPI/DeletePlaceAPI) to the current class "resource"
	}

	public String returnValue() {
		return resource; //constructors do not have a return type, not even void (cannot return anything). so we need this seperate method for tat.
	}

}