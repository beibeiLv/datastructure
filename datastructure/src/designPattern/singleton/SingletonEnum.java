package designPattern.singleton;

public enum SingletonEnum {
Add("1");
private String field;
SingletonEnum(String field){}
public String getField() {
	return field;
}
public void setField(String field) {
	this.field = field;
}

}
