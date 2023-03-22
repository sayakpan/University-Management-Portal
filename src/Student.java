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
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sayakdb", "root", "sayak007");
            state = con.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    void selectAll() throws SQLException {
        result = state.executeQuery("select * from employee");
        System.out.printf("%-15s %-15s %-30s %-10s\n", "FNAME", "LNAME", "ADDRESS", "SSN");
        while (result.next()) {
            String fname = result.getString(1);
            String lname = result.getString(3);
            String address = result.getString(6);
            int ssn = result.getInt(4);
            System.out.printf("%-15s %-15s %-30s %-10d\n", fname, lname, address, ssn);
        }
    }

    public static void main(String[] args) throws SQLException {
        Student student = new Student();
        student.selectAll();
    }
}
