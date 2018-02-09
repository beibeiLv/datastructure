package designPattern.singleton;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingletonEnum s1 = SingletonEnum.Add;
		SingletonEnum s2 = SingletonEnum.Add;
		System.out.print(s1 == s2);

	}

}
