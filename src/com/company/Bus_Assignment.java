package com.company;

import java.sql.*;

public class Bus_Assignment {
    public static void main(String[] args) throws SQLException {
        String url = "JDBC:mysql://localhost:3306/bus_assignment?serverTimezone=UTC";
        String user = "trip_admin";
        String password = "tripadmin";

        // connect to database
        Connection bus_assignment_db = DriverManager.getConnection(url, user, password);

        //create bus
        String model_nr = "12345";
        Integer capacity = 40;

        //int firstBus = AddBus(bus_assignment_db, model_nr, capacity);
        //displayBusses(bus_assignment_db);

        //create route
        String start_stop = "Oslo";
        String end_stop = "Trondheim";
        String expected_time = "17:35";

        int firstRoute = AddRoute(bus_assignment_db, start_stop, end_stop, expected_time);
    }

    // method to add busses
    private static int AddBus(Connection bus_assignment_db, String model_nr, Integer capacity) throws SQLException {
        PreparedStatement addBus = bus_assignment_db.prepareStatement("INSERT INTO bus (model_nr, capacity) VALUES (?, ?)");
        addBus.setString(1, model_nr);
        addBus.setInt(2, capacity);
        return addBus.executeUpdate();
    }

    // method to display busses
    private static void displayBusses(Connection bus_assignment_db) throws SQLException {
        PreparedStatement displayStatement = bus_assignment_db.prepareStatement("SELECT * FROM bus_assignment.bus");

        ResultSet busTable = displayStatement.executeQuery();

        while (busTable.next()) {
            System.out.println(busTable.getString("bus_id") + " " + busTable.getString("model_nr") + " " + busTable.getString("capacity"));
        }
    }

    // method to create new routes
    private static int AddRoute(Connection bus_assignment_db, String start_stop, String end_stop, String expected_time) throws SQLException {
        PreparedStatement addRoute = bus_assignment_db.prepareStatement("INSERT INTO route (start_stop, end_stop, expected_time) VALUES (?, ?, ?)");
        addRoute.setString(1, start_stop);
        addRoute.setString(2, end_stop);
        addRoute.setString(3, expected_time);
        return addRoute.executeUpdate();
    }

    // method to create new trips
}
