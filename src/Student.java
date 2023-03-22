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
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("\n\n123456789\n\n");

            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "sayakdb", "sayak007");
            state = con.createStatement();
            System.out.println("\n\n123456789\n\n");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Driver Load Failed");
            e.printStackTrace();
        }
    }

    void selectAll() throws SQLException {
        result = state.executeQuery("select * from employee");
        System.out.println("FNAME\tLNAME\tADDRESS\tSSN");
        while (result.next()) {
            String fname = result.getString(1);
            String lname = result.getString(3);
            String address = result.getString(6);
            int ssn = result.getInt(4);
            System.out.println(fname + "\t" + lname + "\t" + address + "\t" + ssn);
        }
    }

    public static void main(String[] args) throws SQLException {
        Student student = new Student();
        student.selectAll();

    }
}
