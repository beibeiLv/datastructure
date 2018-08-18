package proxydemo.jdkproxy;

public class TestJDKProxy {
    public static  void main(String args []){
        IUserDao userdao = new UserDaoImpl();
        ProxyFactory proxyFactory = new ProxyFactory(userdao);
        IUserDao userdaoproxy =(IUserDao) proxyFactory.getProxyInstance();
        String  username = userdaoproxy.getUserName();
        System.out.println(username);


    }
}
