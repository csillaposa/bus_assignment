package com.company;

import java.sql.*;

public class Bus_Assignment {
    public static void main(String[] args) throws SQLException {
        String url = "JDBC:mysql://localhost:3306/bus_assignment?serverTimezone=UTC";
        String user = "trip_admin";
        String password = "tripadmin";

        // connect to database
        Connection bus_assignment_db = DriverManager.getConnection(url, user, password);

        //CREATE BUS
        String model_nr = "005";
        Integer capacity = 40;
        //int createBus = AddBus(bus_assignment_db, model_nr, capacity);

        //DELETE BUS
        //DeleteBus(bus_assignment_db, 5);

        //CREATE ROUTE
        String start_stop = "Trondheim";
        String end_stop = "Oslo";
        String expected_time = "20:45";
        //int createRoute = AddRoute(bus_assignment_db, start_stop, end_stop, expected_time);

        //DELETE ROUTE
        //DeleteRoute(bus_assignment_db, 4);

        //CREATE TRIP
        String trip_name = "Trondheim_Oslo";
        String trip_date = "11.12.2020.";
        Integer bus_id = 1;
        Integer route_id = 6;
        //int createTrip = AddTrip(bus_assignment_db, trip_name, trip_date, bus_id, route_id);

        //DELETE TRIP
        //DeleteTrip(bus_assignment_db, 2);

        //CREATE PASSENGER
        String first_name = "John";
        String last_name = "Doe";
        //int createPassenger = AddPassenger(bus_assignment_db, first_name, last_name);

        //DELETE PASSENGER
        //DeletePassenger(bus_assignment_db, 9);

        //ADD PASSENGER TO TRIP
        Integer trip_id = 1;
        Integer passenger_id = 6;
        String seat_type = "economy";
        //int createPassengerTrip = AddTripPassenger(bus_assignment_db, trip_id, passenger_id, seat_type);

        //DISPLAY TRIP ROUTES
        //DisplayTripRoute(bus_assignment_db);

        //UPDATE PASSENGER
        //int updatePassenger = UpdatePassenger(bus_assignment_db, 8,"Henrik" , "Ibsen");

        //DISPLAY PASSENGERS BY TRIP NAME
        //DisplayPassengers(bus_assignment_db, "Oslo_Bergen");

    }

    // method to add busses
    private static int AddBus(Connection bus_assignment_db, String model_nr, Integer capacity) throws SQLException {
        PreparedStatement addBus = bus_assignment_db.prepareStatement("INSERT INTO bus (model_nr, capacity) " +
                "VALUES (?, ?)");
        addBus.setString(1, model_nr);
        addBus.setInt(2, capacity);
        return addBus.executeUpdate();
    }

    // method to delete bus
    private static void DeleteBus(Connection bus_assignment_db, Integer bus_id) throws SQLException {
        PreparedStatement deleteBusFromTrip = bus_assignment_db.prepareStatement("DELETE FROM trip WHERE bus_id = ?");
        deleteBusFromTrip.setInt(1, bus_id);
        deleteBusFromTrip.executeUpdate();

        PreparedStatement deleteBus = bus_assignment_db.prepareStatement("DELETE FROM bus WHERE bus_id = ?");
        deleteBus.setInt(1, bus_id);
        deleteBus.executeUpdate();
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

    // method to delete routes
    private static void DeleteRoute(Connection bus_assignment_db, Integer route_id) throws SQLException {
        PreparedStatement deleteRoute = bus_assignment_db.prepareStatement("DELETE FROM route WHERE route_id = ?");
        deleteRoute.setInt(1, route_id);
        deleteRoute.executeUpdate();
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

    // method to delete trip
    private static void DeleteTrip(Connection bus_assignment_db, Integer trip_id) throws SQLException {
        PreparedStatement deleteTripFromTP = bus_assignment_db.prepareStatement("DELETE FROM trip_passenger WHERE trip_id = ?");
        deleteTripFromTP.setInt(1, trip_id);
        deleteTripFromTP.executeUpdate();

        PreparedStatement deleteTrip = bus_assignment_db.prepareStatement("DELETE FROM trip WHERE trip_id = ?");
        deleteTrip.setInt(1, trip_id);
        deleteTrip.executeUpdate();
    }

    // method for registering a passenger
    private static int AddPassenger(Connection bus_assignment_db, String first_name, String last_name) throws SQLException {
        PreparedStatement addPassenger = bus_assignment_db.prepareStatement("INSERT INTO passenger (first_name, last_name) VALUES (?, ?)");
        addPassenger.setString(1, first_name);
        addPassenger.setString(2, last_name);
        return addPassenger.executeUpdate();
    }

    // delete passenger
    private static void DeletePassenger(Connection bus_assignment_db, Integer passenger_id) throws SQLException {
        PreparedStatement deletePassengerFromTP = bus_assignment_db.prepareStatement("DELETE FROM trip_passenger WHERE passenger_id = ?");
        deletePassengerFromTP.setInt(1, passenger_id);
        deletePassengerFromTP.executeUpdate();

        PreparedStatement deletePassengerFromPassenger = bus_assignment_db.prepareStatement("DELETE FROM passenger WHERE passenger_id = ?");
        deletePassengerFromPassenger.setInt(1, passenger_id);
        deletePassengerFromPassenger.executeUpdate();
    }

    // update passenger
    private static int UpdatePassenger(Connection bus_assignment_db, Integer passenger_id, String first_name, String last_name) throws SQLException {
        PreparedStatement updatePassenger = bus_assignment_db.prepareStatement("UPDATE passenger SET passenger_id = ?, first_name = ?, last_name = ? WHERE passenger_id = ?");
        updatePassenger.setInt(1, passenger_id);
        updatePassenger.setString(2, first_name);
        updatePassenger.setString(3, last_name);
        updatePassenger.setInt(4, passenger_id);
        return updatePassenger.executeUpdate();
    }

    // method which prints out all booked passengers from a specific trip’s name.
    private static void DisplayPassengers(Connection bus_assignment_db, String trip_name) throws SQLException {
        PreparedStatement displayPassenger = bus_assignment_db.prepareStatement("SELECT trip.trip_name, passenger.first_name, passenger.last_name FROM ((passenger JOIN trip_passenger ON passenger.passenger_id = trip_passenger.passenger_id) JOIN trip ON trip_passenger.trip_id = trip.trip_id) WHERE trip_name = ?");
        displayPassenger.setString(1, trip_name);

        ResultSet allPassengers = displayPassenger.executeQuery();

        while (allPassengers.next()) {
            System.out.println(allPassengers.getString("trip_name")
                    + " " + allPassengers.getString("first_name")
                    + " " + allPassengers.getString("last_name"));
        }
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
    private static void DisplayTripRoute(Connection bus_assignment_db) throws SQLException {
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
