package DAO;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao userDao = new DaoFactory().userDao();

        User user = new User();

        user.setId("bee");
        user.setName("yung");
        user.setPassword("123");

        userDao.add(user);

        System.out.println(userDao.get("bee"));
    }
}
