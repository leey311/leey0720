package DAO;

import java.sql.*;

public class UserDao {
    private SimpleConnectionMaker simpleConnectionMaker;
    public UserDao(SimpleConnectionMaker simpleConnectionMaker){
        this.simpleConnectionMaker = simpleConnectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = simpleConnectionMaker.getConnection();
        PreparedStatement ps = c.prepareStatement("insert into users(id, name, pass) values (?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public String get(String id) throws ClassNotFoundException, SQLException {
        Connection c = simpleConnectionMaker.getConnection();
        PreparedStatement ps = c.prepareStatement("select * from users where id=?");
        ps.setString(1,id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("pass"));

        rs.close();
        ps.close();
        c.close();

        return user.getId() + " / " + user.getName() + " / " + user.getPassword();
    }
}
