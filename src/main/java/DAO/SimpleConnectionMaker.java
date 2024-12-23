package DAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface SimpleConnectionMaker{
    public Connection getConnection() throws ClassNotFoundException, SQLException;
}