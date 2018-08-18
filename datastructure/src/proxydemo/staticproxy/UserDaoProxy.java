package proxydemo.staticproxy;

public class UserDaoProxy {
    public IUserDao userDao;
    public UserDaoProxy (IUserDao userDao){
        this.userDao = userDao;

    }
    public String getUserName(){
        //
        System.out.println("begin transaction");

        return userDao.getUserName();
    }
}
