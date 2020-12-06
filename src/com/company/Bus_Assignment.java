package com.company;

import java.sql.*;

public class Bus_Assignment {
    private static final String MODEL_NR = "model_nr";
    private static final String CAPACITY = "capacity";
    private static final String START_STOP = "start_stop";
    private static final String END_STOP = "end_stop";
    private static final String EXPECTED_TIME = "expected_time";
    private static final String TRIP_NAME = "trip_name";
    private static final String TRIP_DATE = "trip_date";
    private static final String BUS_ID = "bus_id";
    private static final String ROUTE_ID = "route_id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String TRIP_ID = "trip_id";
    private static final String PASSENGER_ID = "passenger_id";
    private static final String SEAT_TYPE = "seat_type";

    public static void main(String[] args) throws SQLException {
        String url = "JDBC:mysql://localhost:3306/bus_assignment?serverTimezone=UTC";
        String user = "trip_admin";
        String password = "tripadmin";

        // connect to database
        Connection bus_assignment_db = DriverManager.getConnection(url, user, password);

        //CREATE BUS
        //int createBus = AddBus(bus_assignment_db, "003", 40);

        //DELETE BUS
        //DeleteBus(bus_assignment_db, 3);

        //CREATE ROUTE
        //int createRoute = AddRoute(bus_assignment_db, "test", "test", "16:30");

        //DELETE ROUTE
        //DeleteRoute(bus_assignment_db, 3);

        //CREATE TRIP
        //int createTrip = AddTrip(bus_assignment_db, "test2", "08.12.2020.", 2, 2);

        //DELETE TRIP
        //DeleteTrip(bus_assignment_db, 3);

        //CREATE PASSENGER
        //int createPassenger = AddPassenger(bus_assignment_db, "test", "testy");

        //DELETE PASSENGER
        //DeletePassenger(bus_assignment_db, 2);

        //UPDATE PASSENGER
        //int updatePassenger = UpdatePassenger(bus_assignment_db, 1,"John" , "Doe");

        //ADD PASSENGER TO TRIP
        //int createPassengerTrip = AddPassengerToTrip(bus_assignment_db, 1, 1, "first");

        //DELETE PASSENGER FROM TRIP
        //DeletePassengerFromTrip(bus_assignment_db, 1);

        //DISPLAY TRIP ROUTES
        //DisplayTripRoute(bus_assignment_db);

        //DISPLAY PASSENGERS BY TRIP NAME
        //DisplayPassengers(bus_assignment_db, "test1");

    }

    // method to add busses
    private static int AddBus(Connection bus_assignment_db, String MODEL_NR, Integer CAPACITY) throws SQLException {
        PreparedStatement addBus = bus_assignment_db.prepareStatement("INSERT INTO bus (model_nr, capacity) " +
                "VALUES (?, ?)");
        addBus.setString(1, MODEL_NR);
        addBus.setInt(2, CAPACITY);
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
    private static int AddRoute(Connection bus_assignment_db, String START_STOP, String END_STOP, String EXPECTED_TIME)
            throws SQLException {
        PreparedStatement addRoute = bus_assignment_db.prepareStatement("INSERT INTO route " +
                "(start_stop, end_stop, expected_time) VALUES (?, ?, ?)");
        addRoute.setString(1, START_STOP);
        addRoute.setString(2, END_STOP);
        addRoute.setString(3, EXPECTED_TIME);
        return addRoute.executeUpdate();
    }

    // method to delete routes
    private static void DeleteRoute(Connection bus_assignment_db, Integer route_id) throws SQLException {
        PreparedStatement deleteRoute = bus_assignment_db.prepareStatement("DELETE FROM route WHERE route_id = ?");
        deleteRoute.setInt(1, route_id);
        deleteRoute.executeUpdate();
    }

    // method to create new trips
    private static int AddTrip(Connection bus_assignment_db, String TRIP_NAME, String TRIP_DATE, Integer BUS_ID, Integer ROUTE_ID) throws SQLException {
        PreparedStatement addTrip = bus_assignment_db.prepareStatement("INSERT INTO trip (trip_name, trip_date, bus_id, route_id) VALUES (?, ?, ?, ?)");
        addTrip.setString(1, TRIP_NAME);
        addTrip.setString(2, TRIP_DATE);
        addTrip.setInt(3, BUS_ID);
        addTrip.setInt(4, ROUTE_ID);
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
    private static int AddPassenger(Connection bus_assignment_db, String FIRST_NAME, String LAST_NAME) throws SQLException {
        PreparedStatement addPassenger = bus_assignment_db.prepareStatement("INSERT INTO passenger (first_name, last_name) VALUES (?, ?)");
        addPassenger.setString(1, FIRST_NAME);
        addPassenger.setString(2, LAST_NAME);
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
        PreparedStatement displayPassenger = bus_assignment_db.prepareStatement("SELECT trip.trip_name, passenger.first_name, " +
                "passenger.last_name FROM ((passenger JOIN trip_passenger ON passenger.passenger_id = trip_passenger.passenger_id) " +
                "JOIN trip ON trip_passenger.trip_id = trip.trip_id) WHERE trip_name = ?");
        displayPassenger.setString(1, trip_name);

        ResultSet allPassengers = displayPassenger.executeQuery();

        while (allPassengers.next()) {
            System.out.println(allPassengers.getString("trip_name")
                    + " " + allPassengers.getString("first_name")
                    + " " + allPassengers.getString("last_name"));
        }
    }

    // method for booking a trip for a passenger
    private static int AddPassengerToTrip(Connection bus_assignment_db, Integer TRIP_ID, Integer PASSENGER_ID, String SEAT_TYPE) throws SQLException {
        PreparedStatement addTripPassenger = bus_assignment_db.prepareStatement("INSERT INTO trip_passenger (trip_id, passenger_id, seat_type) VALUES (?, ?, ?)");
        addTripPassenger.setInt(1, TRIP_ID);
        addTripPassenger.setInt(2, PASSENGER_ID);
        addTripPassenger.setString(3, SEAT_TYPE);
        return addTripPassenger.executeUpdate();
    }

    // method for deleting a passenger from a trip
    private static void DeletePassengerFromTrip(Connection bus_assignment_db, Integer passenger_id) throws SQLException {
        PreparedStatement deletePassengerFromTrip = bus_assignment_db.prepareStatement("DELETE FROM trip_passenger WHERE passenger_id = ?");
        deletePassengerFromTrip.setInt(1, passenger_id);
        deletePassengerFromTrip.executeUpdate();
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
