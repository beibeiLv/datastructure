package corejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author belv
 * test how to serialize object and how to deserialize Object
 */
public class TestSerialized{

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File("person.out");  
		 
        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));  
        Book book = new Book(1, "nancy");  
        oout.writeObject(book);  
        oout.close();  
 
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));  
        Object newBook = oin.readObject(); 
        oin.close();  
        System.out.println(newBook);  
	}
}
