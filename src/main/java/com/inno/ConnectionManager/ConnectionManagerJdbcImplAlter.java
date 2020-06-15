package com.inno.ConnectionManager;

import javax.ejb.EJB;
import java.sql.Connection;

@EJB
public class ConnectionManagerJdbcImplAlter implements ConnectionManager {

    @Override
    public Connection getConnection() {
        throw new IllegalStateException("Wrong dependencies");
    }
}
