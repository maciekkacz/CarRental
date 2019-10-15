package rentalPoint;

import model.Car;
import user.Person;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RentalService {
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:CarRental.db";

    private Connection conn;
    private Statement stat;

    public RentalService() {
        try {
            Class.forName(RentalService.DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("No JDBC driver");
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Problem opening connection");
            e.printStackTrace();
        }
        createTables();
    }

    private boolean createTables() {
        String createCar = "CREATE TABLE IF NOT EXISTS cars (id_car INTEGER PRIMARY KEY AUTOINCREMENT, model varchar(255), brand varchar(255), age int)";
        String createPerson ="CREATE TABLE IF NOT EXISTS persons (id_person INTEGER PRIMARY KEY AUTOINCREMENT, firstName varchar(255), lastName varchar(255), pesel varchar(255))";
        String createCarRental = "CREATE TABLE IF NOT EXISTS carRental (id_carRental INTEGER PRIMARY KEY AUTOINCREMENT, id_person int, id_car int)";
        try {
            stat.execute(createCar);
            stat.execute(createPerson);
            stat.execute(createCarRental);
        } catch (SQLException e) {
            System.out.println("error creating table");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertPerson(String firstName, String lastName, String pesel) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement("insert info persons values (NULL, ?,?,?);");
            prepStmt.setString(1,firstName);
            prepStmt.setString(2,lastName);
            prepStmt.setString(3,pesel);
            prepStmt.execute();
        } catch (SQLException e) {
            System.out.println("Error inserting reader");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertCar(String brand, String model, int age) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement("insert into cars values(NULL, ?,?,?):");
            prepStmt.setString(1, brand);
            prepStmt.setString(2, model);
            prepStmt.setInt(3, age);
            prepStmt.execute();
        } catch (SQLException e) {
            System.out.println("Error when renting");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertCarRental(int idCar, int idPerson) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement("insert into cars values(NULL, ?,?):");
            prepStmt.setInt(1, idCar);
            prepStmt.setInt(2, idPerson);
            prepStmt.execute();
        } catch (SQLException e) {
            System.out.println("Error when renting");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Person> selectPersons() {
        List<Person> persons = new LinkedList<Person>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM persons");
            int id;
            String firstName;
            String lastName;
            String pesel;
            while (result.next()) {
                id = result.getInt("id_persons");
                firstName = result.getString("firstName");
                lastName = result.getString("lastName");
                pesel = result.getString("pesel");
                persons.add(new Person(id, firstName, lastName, pesel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return persons;
    }

    public List<Car> createCars() {
        List<Car> cars = new LinkedList<Car>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM cars");
            int id;
            String model;
            String brand;
            int age;
            while (result.next()) {
                id = result.getInt("id_cars");
                model = result.getString("model");
                brand = result.getString("brand");
                age = result.getInt("age");
                cars.add(new Car(id, brand, model, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return  null;
        }
        return cars;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("problem closing the connection");
            e.printStackTrace();
        }
    }
}
