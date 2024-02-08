package udemyPojo;

public enum KeepPracticing {

	AddPlaceAPI("/maps/api/place/add/json"),
	DeletePlaceAPI("/maps/api/place/delete/json"),
	GetPlaceAPI("/maps/api/place/get/json");

	private String resource;

	KeepPracticing(String val) {
		this.resource = val;
		
	}

	public String getResource(String aisle) {

		return resource;
	}

}

