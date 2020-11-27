package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bus_Assignment {
    public static void main(String[] args) throws SQLException {
        String url = "JDBC:mysql://localhost:3306/bus_assignment?serverTimezone=UTC";
        String user = "trip_admin";
        String password = "tripadmin";

        // connect to database
        Connection bus_assignment_db = DriverManager.getConnection(url, user, password);
    }

    // create methods to add busses
}
