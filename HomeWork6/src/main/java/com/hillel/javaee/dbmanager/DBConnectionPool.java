package com.hillel.javaee.dbmanager;

import com.hillel.javaee.utils.SpringScriptUtility;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
public class DBConnectionPool {
    private static DataSource datasource;
    private static final String connectionURL = "jdbc:postgresql://localhost:5432/shop";
    private static final String password = "2000";
    private static final String username = "postgres";

    private DBConnectionPool() {

    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException, URISyntaxException {
        if (datasource == null) {
            PoolProperties p = new PoolProperties();
            p.setUrl(connectionURL);
            p.setDriverClassName("org.postgresql.Driver");
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
            p.setRemoveAbandoned(true);
            p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
            datasource = new DataSource();
            datasource.setPoolProperties(p);
            SpringScriptUtility.runScript(Paths.get(DBConnectionPool.class.getClassLoader().getResource("sqlScript.sql").toURI()).toString(), datasource.getConnection());
        }
        return datasource.getConnection();
    }
}
