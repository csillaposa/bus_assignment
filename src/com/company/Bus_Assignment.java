package com.company;

import java.sql.*;

public class Bus_Assignment {
    public static void main(String[] args) throws SQLException {
        String url = "JDBC:mysql://localhost:3306/bus_assignment?serverTimezone=UTC";
        String user = "bus_system_admin";
        String password = "admin";

        // connect to database
        Connection bus_assignment_db = DriverManager.getConnection(url, user, password);

        //create bus
        Integer bus_id = 1;
        String model_nr = "12345";
        Integer capacity = 40;

        int firstBus = AddBus(bus_assignment_db, bus_id, model_nr, capacity);
        displayBusses(bus_assignment_db);
    }

    // method to add busses
    private static int AddBus(Connection bus_assignment_db, Integer bus_id, String model_nr, Integer capacity) throws SQLException {
        PreparedStatement addBus = bus_assignment_db.prepareStatement("INSERT INTO bus (bus_id, model_nr, capacity) VALUES (?, ?, ?)");
        addBus.setInt(1, bus_id);
        addBus.setString(2, model_nr);
        addBus.setInt(3, capacity);
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
    // method to create new trips
}
