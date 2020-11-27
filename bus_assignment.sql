-- creating the database 
CREATE DATABASE bus_assignment;

USE bus_assignment;

-- filling the database with the required tables
-- Bus table
CREATE TABLE Bus(
bus_id VARCHAR(10) PRIMARY KEY NOT NULL,
model_nr VARCHAR(20),
capacity INT
);

-- Route table
CREATE TABLE Route(
route_id VARCHAR(10) PRIMARY KEY NOT NULL,
start_stop VARCHAR(50),
end_stop VARCHAR(50),
expected_time VARCHAR(10)
);

-- Trip table
CREATE TABLE Trip(
trip_id VARCHAR(10) PRIMARY KEY NOT NULL,
trip_name VARCHAR(20),
trip_date VARCHAR(10),
bus_id VARCHAR(10),
route_id VARCHAR(10),
FOREIGN KEY (bus_id) REFERENCES Bus(bus_id),
FOREIGN KEY (route_id) REFERENCES Route(route_id)
);

-- Trip_Passenger table
CREATE TABLE Trip_Passenger(
trip_id VARCHAR(10),
passenger_id VARCHAR(10),
seat_type VARCHAR(20),
PRIMARY KEY (trip_id, passenger_id),
FOREIGN KEY (trip_id) REFERENCES Trip(trip_id),
FOREIGN KEY (passenger_id) REFERENCES Passenger(passenger_id)
);

-- Passenger table
CREATE TABLE Passenger(
passenger_id VARCHAR(10) PRIMARY KEY NOT NULL,
first_name VARCHAR(50),
last_name VARCHAR(50)
);

-- creating an admin
-- has all privileges for the whole database
CREATE USER "bus_system_admin"@"localhost" IDENTIFIED BY "admin";
GRANT ALL PRIVILEGES ON bus_assignment TO "bus_system_admin"@"localhost";

-- trip_admin
-- has all privileges except drop, but has drop on Passenger table
CREATE USER "trip_admin"@"localhost" IDENTIFIED BY "tripadmin";
GRANT ALL PRIVILEGES ON bus_assignment TO "trip_admin"@"localhost";
REVOKE DROP ON bus_assignment FROM "trip_admin"@"localhost";
GRANT DROP ON bus_assignment.passenger TO "trip_admin"@"localhost";

-- ticket_booker
-- allowed only to select, insert, update, delete from passenger and trip_passenger tables
CREATE USER "ticket_booker"@"localhost" IDENTIFIED BY "ticketbooker";
GRANT SELECT ON bus_assignment.passenger TO "ticket_booker"@"localhost";
GRANT SELECT ON bus_assignment.trip_passenger TO "ticket_booker"@"localhost";
GRANT INSERT ON bus_assignment.passenger TO "ticket_booker"@"localhost";
GRANT INSERT ON bus_assignment.trip_passenger TO "ticket_booker"@"localhost";
GRANT UPDATE ON bus_assignment.passenger TO "ticket_booker"@"localhost";
GRANT UPDATE ON bus_assignment.trip_passenger TO "ticket_booker"@"localhost";
GRANT DELETE ON bus_assignment.passenger TO "ticket_booker"@"localhost";
GRANT DELETE ON bus_assignment.trip_passenger TO "ticket_booker"@"localhost";