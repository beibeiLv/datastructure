package proxydemo.jdkproxy;

public class UserDaoImpl implements IUserDao {
    @Override
    public String getUserName() {
        return "nancy";
    }
}
