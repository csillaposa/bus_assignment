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

