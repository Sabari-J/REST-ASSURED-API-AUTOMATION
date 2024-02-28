POJO Class has been created and maintained here in this package

Steps:
1) Create a class according to the need
2) Create variables for each "Key" in the Json, if the Json has array create a new class and Create variables for each "Key" in the array
3) In case of Array, change the return type of the variable(Key) to the subclass(newly created Pojo), instead of the datatype
 (e-x)
 	private Location location;
	private int accuracy;
	private String name;
 
4) if the array has multiple items, change the return type of the subclass(newly created Pojo) as List
	(e-x)	
		private List<WebAutomation> WebAutomation;
		private List<Api> api;
		private List<Mobile> mobile;
5) Select all the variables and click "ALT+SHIFT+S" and select "genereate getters and Setters" option in the pop-up