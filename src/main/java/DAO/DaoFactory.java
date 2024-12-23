package DAO;

public class DaoFactory {
    public UserDao userDao(){
        return new UserDao(simpleConnectionMaker());
    }

    public SimpleConnectionMaker simpleConnectionMaker(){
        return new DSimpleConnectionMaker();
    }
}
