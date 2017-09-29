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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*		Cat cat = new Cat("small", 2);
		Cat cat2 = new Cat("small", 2);
		Dog dog = new Dog("small", 2);
		Dog dog2 = new Dog("small", 2);
		
		System.out.println(cat.getClass().hashCode());
		System.out.println(cat2.getClass().hashCode());
		System.out.println(cat.getClass().getName().hashCode());
		System.out.println(cat2.getClass().getName().hashCode());
		System.out.println(dog.getClass().hashCode());
		
		System.out.println(dog2.getClass().hashCode());
		System.out.println(cat.equals(cat2));
		System.out.println(dog.equals(dog2));
		System.out.println(dog == dog2);
		
		Map <Dog, String> hm = new HashMap<Dog, String> ();
		hm.put(dog, "String");
		hm.put(dog2, "String");
		
		Map <Cat, String> hm2 = new HashMap<Cat, String> ();
		hm2.put(cat, "String");
		hm2.put(cat2, "String");
		
		System.out.println(hm.size());
		System.out.println("hm2 size"+hm2.size());
		System.out.println(dog.equals(dog2));*/
		
		Map <Integer, String> map  = new LinkedHashMap<Integer, String> ();
		map.put(new Integer(1), "111");
		map.put(new Integer(1), "222");
		map.put(new Integer(1), "22");
		System.out.println(map.size());
		System.out.println(map.get(1));
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
