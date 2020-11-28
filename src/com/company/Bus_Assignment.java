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
        String model_nr = "005";
        Integer capacity = 40;

        //int createBus = AddBus(bus_assignment_db, model_nr, capacity);

        //create route
        String start_stop = "Trondheim";
        String end_stop = "Molde";
        String expected_time = "15:05";

        //int createRoute = AddRoute(bus_assignment_db, start_stop, end_stop, expected_time);

        //create trip
        String trip_name = "Trondheim_Molde";
        String trip_date = "15.12.20.";
        Integer bus_id = 5;
        Integer route_id = 5;

        //int createTrip = AddTrip(bus_assignment_db, trip_name, trip_date, bus_id, route_id);

        //create passenger
        String first_name = "Adam";
        String last_name = "Rajkai";

        //int createPassenger = AddPassenger(bus_assignment_db, first_name, last_name);

        //create trip for passenger
        Integer trip_id = 3;
        Integer passenger_id = 2;
        String seat_type = "economy";

        //int createPassengerTrip = AddTripPassenger(bus_assignment_db, trip_id, passenger_id, seat_type);

        displayTripRoute(bus_assignment_db);

    }

    // method to add busses
    private static int AddBus(Connection bus_assignment_db, String model_nr, Integer capacity) throws SQLException {
        PreparedStatement addBus = bus_assignment_db.prepareStatement("INSERT INTO bus (model_nr, capacity) " +
                "VALUES (?, ?)");
        addBus.setString(1, model_nr);
        addBus.setInt(2, capacity);
        return addBus.executeUpdate();
    }

    // method to create new routes
    private static int AddRoute(Connection bus_assignment_db, String start_stop, String end_stop, String expected_time)
            throws SQLException {
        PreparedStatement addRoute = bus_assignment_db.prepareStatement("INSERT INTO route " +
                "(start_stop, end_stop, expected_time) VALUES (?, ?, ?)");
        addRoute.setString(1, start_stop);
        addRoute.setString(2, end_stop);
        addRoute.setString(3, expected_time);
        return addRoute.executeUpdate();
    }

    // method to create new trips
    private static int AddTrip(Connection bus_assignment_db, String trip_name, String trip_date, Integer bus_id, Integer route_id) throws SQLException {
        PreparedStatement addTrip = bus_assignment_db.prepareStatement("INSERT INTO trip (trip_name, trip_date, bus_id, route_id) VALUES (?, ?, ?, ?)");
        addTrip.setString(1, trip_name);
        addTrip.setString(2, trip_date);
        addTrip.setInt(3, bus_id);
        addTrip.setInt(4, route_id);
        return addTrip.executeUpdate();
    }

    // method for registering a passenger
    private static int AddPassenger(Connection bus_assignment_db, String first_name, String last_name) throws SQLException {
        PreparedStatement addPassenger = bus_assignment_db.prepareStatement("INSERT INTO passenger (first_name, last_name) VALUES (?, ?)");
        addPassenger.setString(1, first_name);
        addPassenger.setString(2, last_name);
        return addPassenger.executeUpdate();
    }

    // method for booking a trip for a passenger
    private static int AddTripPassenger(Connection bus_assignment_db, Integer trip_id, Integer passenger_id, String seat_type) throws SQLException {
        PreparedStatement addTripPassenger = bus_assignment_db.prepareStatement("INSERT INTO trip_passenger (trip_id, passenger_id, seat_type) VALUES (?, ?, ?)");
        addTripPassenger.setInt(1, trip_id);
        addTripPassenger.setInt(2, passenger_id);
        addTripPassenger.setString(3, seat_type);
        return addTripPassenger.executeUpdate();
    }

    // method for printing out each trip's route
    private static void displayTripRoute(Connection bus_assignment_db) throws SQLException {
        PreparedStatement displayStatement = bus_assignment_db.prepareStatement("SELECT * FROM trip NATURAL JOIN route");

        ResultSet tripRouteTable = displayStatement.executeQuery();

        while (tripRouteTable.next()) {
            System.out.println(tripRouteTable.getString("trip_id")
                    + " " + tripRouteTable.getString("trip_name")
                    + " " + tripRouteTable.getString("trip_date")
                    + " " + tripRouteTable.getString("bus_id")
                    + " " + tripRouteTable.getString("route_id")
                    + " " + tripRouteTable.getString("start_stop")
                    + " " + tripRouteTable.getString("end_stop")
                    + " " + tripRouteTable.getString("expected_time"));
        }
    }
}
