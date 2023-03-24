import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Student {
    Connection con;
    Statement state;
    ResultSet result;

    Student() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studentdb",
            // "root", "sayak007");
            con = DriverManager.getConnection(
                    "jdbc:mysql://sayakdb-aws.c8l2rvtowbt0.eu-north-1.rds.amazonaws.com:3306/studentdb", "admin",
                    "sayak007");
            state = con.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    void selectAll() throws SQLException {
        result = state.executeQuery("select * from students");
        System.out.printf("%-10s %-10s %-10s %-25s %-15s %-15s %-10s %-25s\n", "Student_ID", "First_name", "Last_name",
                "Email_address", "Phone_number", "Date_of_birth", "Gender", "Address");
        while (result.next()) {
            String studentId = result.getString(1);
            String firstName = result.getString(2);
            String lastName = result.getString(3);
            String emailAddress = result.getString(4);
            String phoneNumber = result.getString(5);
            String dateOfBirth = result.getString(6);
            String gender = result.getString(7);
            String address = result.getString(8);
            System.out.printf("%-10s %-10s %-10s %-25s %-15s %-15s %-10s %-25s\n", studentId, firstName, lastName,
                    emailAddress, phoneNumber, dateOfBirth, gender, address);
        }
    }

    void search(int key) throws SQLException {
        result = state.executeQuery("select * from students where students.Student_ID = " + key);
        if (result.next() == true) {
            System.out.println("\n\nMatch Found !!");
            System.out.printf("%-10s %-10s %-10s %-25s %-15s %-15s %-10s %-25s\n", "Student_ID", "First_name",
                    "Last_name",
                    "Email_address", "Phone_number", "Date_of_birth", "Gender", "Address");
            do {
                String studentId = result.getString(1);
                String firstName = result.getString(2);
                String lastName = result.getString(3);
                String emailAddress = result.getString(4);
                String phoneNumber = result.getString(5);
                String dateOfBirth = result.getString(6);
                String gender = result.getString(7);
                String address = result.getString(8);
                System.out.printf("%-10s %-10s %-10s %-25s %-15s %-15s %-10s %-25s\n", studentId, firstName, lastName,
                        emailAddress, phoneNumber, dateOfBirth, gender, address);
            } while (result.next());
        } else {
            System.out.println("\nStudent ID " + key + " Not Found  !!");
        }
    }

    void loginpage() {
    }

    public static void main(String[] args) throws SQLException {
        Student student = new Student();
        student.selectAll();
        System.out.println();
        System.out.println();
        student.search(103);
    }
}
