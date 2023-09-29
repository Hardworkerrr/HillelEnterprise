package com.hillel.javaee.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionHolder {
    private static Connection connection;
//    private static final String connectionURL = "jdbc:h2:mem:default;INIT=RUNSCRIPT from 'classpath:sample.sql'";
    private static final String connectionURL = "jdbc:mysql://localhost:3306/test";

    private DBConnectionHolder (){

    }

//    public static Connection getConnection() throws ClassNotFoundException, SQLException {
//        Class.forName("org.h2.Driver");
//        if (connection==null){
//            connection = DriverManager.getConnection(connectionURL);
//        }
//        return connection;
//    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        if (connection==null){
            connection = DriverManager.getConnection(connectionURL,"root","root");
        }
        return connection;
    }
}
