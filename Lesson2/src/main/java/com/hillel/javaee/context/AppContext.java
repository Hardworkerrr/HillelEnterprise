package com.hillel.javaee.context;

import com.hillel.javaee.dbmanager.DBConnectionHolder;
import com.hillel.javaee.service.impl.QueryService;

import java.sql.SQLException;

public class AppContext {
    private final QueryService service;

    AppContext() {
        try {
            this.service = new QueryService(DBConnectionHolder.getConnection());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public QueryService getService() {
        return service;
    }
}
