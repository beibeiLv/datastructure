package designPattern.singleton;

public class Singleton1 {
	private Singleton1() {
	}

	private Singleton1 instance;

	public Singleton1 getInstance() {
		if(instance == null){
			instance = new Singleton1();
		}
		return instance;
	}
}
