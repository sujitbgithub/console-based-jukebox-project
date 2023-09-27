package org.example.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectiontoDB {
    Connection getConnection() throws ClassNotFoundException,SQLException;

}
