package udemyLessons;

public class EnumClassImplementation {

	public void getDetails(String APIvalue) {

		EnumClass valueValidation = EnumClass.valueOf(APIvalue);

		System.out.println(valueValidation.returnValue());

	}

	public static void main(String[] args) {
		EnumClassImplementation imple = new EnumClassImplementation();

		imple.getDetails("getPlaceAPI");
	}

}
