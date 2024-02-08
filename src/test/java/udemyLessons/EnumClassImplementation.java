package udemyLessons;

public class EnumClassImplementation {

	public static void getDetails(String AddPlaceAPI) {
		
		//Enum is a special data type in Java used to define a 'collection of constants/methods'
		
		EnumClass valueValidation = EnumClass.valueOf(AddPlaceAPI); // =>This is similar to creating an object for the class-EnumClass
		
	//Here 'valueOf' method returns an enum constant of type "EnumClass". When we call this method, automatically constructor get executed.
	//valueOf() in Java is used to convert any non String variable/Object (int, double, char, and others to a newly created String object). 
	//It returns the string representation of the argument passed.
		
		System.out.println(valueValidation.returnValue());

	}

	public static void main(String[] args) {
		//EnumClassImplementation imple = new EnumClassImplementation();

		//imple.getDetails("getPlaceAPI");
		getDetails("getPlaceAPI");
	}

}
