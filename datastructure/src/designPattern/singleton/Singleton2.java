package designPattern.singleton;

public class Singleton2 {
	private Singleton2() {
	}

	private Singleton2 instance = new Singleton2();

	public Singleton2 getInstance() {
		return instance;
	}
}
