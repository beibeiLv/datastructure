package proxydemo.cglibproxy;

public class TestCglibProxy {
    public  static void main (String args  []){
        UserDao  userDao = new UserDao();
        CglibProxyFactory  proxyFactory = new CglibProxyFactory(userDao);
       UserDao userDaoProxy = (UserDao) proxyFactory.getProxyInstance();
        String username = userDaoProxy.getUserName();
        System.out.println(username);
    }
}
