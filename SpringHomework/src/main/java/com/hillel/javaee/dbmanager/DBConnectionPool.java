package com.hillel.javaee.dbmanager;

import com.hillel.javaee.util.SpringScriptUtility;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Component("connectionPool")
public class DBConnectionPool {
    private static DataSource datasource;
    private static final Map<String, String> databaseProperties = SpringScriptUtility.
            readResourceProperties("database.properties");

    private DBConnectionPool() {

    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException, URISyntaxException {
        if (datasource == null) {
            PoolProperties p = new PoolProperties();
            p.setUrl(databaseProperties.get("url"));
            p.setUsername(databaseProperties.get("username"));
            p.setPassword(databaseProperties.get("password"));
            p.setDriverClassName("org.postgresql.Driver");
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
            if (!datasource.getConnection().getMetaData().getTables("shop", "public", "customer", null).isBeforeFirst()) {
                SpringScriptUtility.runScript(Paths.get(DBConnectionPool.class.getClassLoader().getResource("sql/sqlScript.sql").toURI()).toString(), datasource.getConnection());
            }
        }
        return datasource.getConnection();
    }
}
