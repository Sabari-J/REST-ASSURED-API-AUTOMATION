package udemyLessons;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Test implements Serializable {

	int i = 0;
	int j = 1;
}

public class SerializationAndDeserialization {
	
	/*** => Serialization and Deserialization both helps to parse and extract the responses (Json/XML)
	  => Serialization -> converting an object to a file/network supported form (Payload)***/

	public static void serializeMethod() throws Exception {

		Test t1 = new Test();
 
		// fetch the information from the class(Object) and create/update it in the file(Json) ==>setter methods helps here...

		FileOutputStream fos = new FileOutputStream("Textsample.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		oos.writeObject(t1);

	}

	public static void deserializeMethod() throws Exception {
		// DeSerialization -> converting back a file(Json)/network supported form to a class(object) ==>getter methods helps here...

		FileInputStream fis = new FileInputStream("Textsample.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);

		Test t2 = (Test) ois.readObject();
		System.out.println(t2.i);
		System.out.println(t2.j);
	}

}
