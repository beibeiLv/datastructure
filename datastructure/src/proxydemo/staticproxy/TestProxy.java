package proxydemo.staticproxy;

public class TestProxy {
    public static  void main (String args []){
        UserDaoImpl userDao = new UserDaoImpl();
        UserDaoProxy proxy = new UserDaoProxy(userDao);
        //execute proxy method
        System.out.println(proxy.getUserName());
    }
}
