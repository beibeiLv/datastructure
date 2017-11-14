package corejava;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author belv
 * This class used to test if two objects are equals
 * 1. when Object not implement the hashcode and equals function, the two object instances has same parameter still not equals,see the cat
 * 2. When object implement the hashcode and equal functtion, the two instances will equal. see the Dog
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cat cat = new Cat("small", 2);
		Cat cat2 = new Cat("small", 2);
		Dog dog = new Dog("small", 2);
		Dog dog2 = new Dog("small", 2);
		
		System.out.println(cat.getClass().hashCode());
		System.out.println(cat2.getClass().hashCode());
		
		System.out.println(cat.getClass().getName().hashCode());
		System.out.println(cat2.getClass().getName().hashCode());
		
		System.out.println(cat.equals(cat2));
		
		System.out.println(dog.getClass().hashCode());
		System.out.println(dog2.getClass().hashCode());
		System.out.println(dog.equals(dog2));
		System.out.println(dog == dog2);

		// same hashcode , and equals. hm size is 1
		Map <Dog, String> hm = new HashMap<Dog, String> ();
		hm.put(dog, "String");
		hm.put(dog2, "String");
		System.out.println("hm size is"+ hm.size());
		
		// same hashcode , but not equals. hm size is 2
		Map <Cat, String> hm2 = new HashMap<Cat, String> ();
		hm2.put(cat, "String");
		hm2.put(cat2, "String");
		hm2.put(new Cat("big", 2), "String");
		System.out.println("hm2 size"+hm2.size());
		for(Map.Entry<Cat, String> entry: hm2.entrySet()){
			System.out.println(entry.getKey().getName() + " value is" + entry.getValue());
		}
	// Map new value covers the old value
		Map <Integer, String> map  = new HashMap<Integer, String> ();
		map.put(1, "111");
		map.put(1, "222");
		map.put(1, "22");
		System.out.println(map.size());
		System.out.println(isNumeric(""));
		
	}		
	private static boolean isNumeric(String str) {

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
}
