package com.hillel.javaee.dbmanager;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionHolder {
    private static DataSource datasource;
    private static final String connectionURL = "jdbc:mysql://localhost:3306/test";
    private static final String password = "root";
    private static final String username = "root";

    private DBConnectionHolder() {

    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (datasource == null) {
            PoolProperties p = new PoolProperties();
            p.setUrl(connectionURL);
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUsername(username);
            p.setPassword(password);
            p.setJmxEnabled(true);
            p.setTestWhileIdle(false);
            p.setTestOnBorrow(true);
            p.setTestOnReturn(false);
            p.setValidationInterval(30000);
            p.setTimeBetweenEvictionRunsMillis(30000);
            p.setMaxActive(100);
            p.setInitialSize(5);
            p.setMaxWait(10000);
            p.setRemoveAbandonedTimeout(30);
            p.setMinEvictableIdleTimeMillis(30000);
            p.setMinIdle(10);
            p.setLogAbandoned(true);
            p.setRemoveAbandoned(false);
            p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
            datasource = new DataSource();
            datasource.setPoolProperties(p);
        }
        return datasource.getConnection();
    }
}
