import java.io.Serializable;



public class Test  implements Serializable{
public int v1 =1;
public Test(){
	System.out.print("Test is loaded by" + this.getClass().getClassLoader());
	new Dog();
}
}
