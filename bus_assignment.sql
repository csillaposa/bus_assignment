-- creating the database 
CREATE DATABASE bus_assignment;

USE bus_assignment;

-- filling the database with the required tables
-- Bus table
CREATE TABLE Bus(
bus_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
model_nr VARCHAR(20),
capacity INT
);

-- Route table
CREATE TABLE Route(
route_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
start_stop VARCHAR(50),
end_stop VARCHAR(50),
expected_time VARCHAR(20)
);

-- Trip table
CREATE TABLE Trip(
trip_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
trip_name VARCHAR(20),
trip_date VARCHAR(20),
bus_id INT,
route_id INT,
FOREIGN KEY (bus_id) REFERENCES Bus(bus_id),
FOREIGN KEY (route_id) REFERENCES Route(route_id)
);

-- Passenger table
CREATE TABLE Passenger(
passenger_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
first_name VARCHAR(50),
last_name VARCHAR(50)
);

-- Trip_Passenger table
CREATE TABLE Trip_Passenger(
trip_id INT,
passenger_id INT,
seat_type VARCHAR(10),
PRIMARY KEY (trip_id, passenger_id),
FOREIGN KEY (trip_id) REFERENCES Trip(trip_id),
FOREIGN KEY (passenger_id) REFERENCES Passenger(passenger_id)
);


DROP TABLE IF EXISTS bus, passenger, route, trip, trip_passenger;

-- creating an admin
-- has all privileges for the whole database
CREATE USER "bus_system_admin"@"localhost" IDENTIFIED BY "admin";
GRANT ALL PRIVILEGES ON bus_assignment.* TO "bus_system_admin"@"localhost";

-- trip_admin
-- has all privileges except drop, but has drop on Passenger table
CREATE USER "trip_admin"@"localhost" IDENTIFIED BY "tripadmin";
GRANT ALL PRIVILEGES ON bus_assignment.* TO "trip_admin"@"localhost";
REVOKE DROP ON bus_assignment.* FROM "trip_admin"@"localhost";
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

CREATE USER "test_user"@"localhost" IDENTIFIED BY "testuser";
GRANT INSERT ON bus_assignment.* TO "test_user"@"localhost";

SELECT * FROM mysql.user;

SHOW GRANTS FOR 'ticket_booker'@'localhost';