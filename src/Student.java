import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Student {
    Connection con;
    Statement state;
    ResultSet result;

    // Establish Connection to the Production Database
    Student() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // AWS RDS Database
            con = DriverManager.getConnection(
                    "jdbc:mysql://sayakdb-aws.c8l2rvtowbt0.eu-north-1.rds.amazonaws.com:3306/studentdb",
                    "admin",
                    "sayak007");
            state = con.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    private JFrame frame;
    private JLabel h1, h2, userLabel, passLabel, image1, errorMsg;
    private JTextField userField;
    private JPasswordField passField;
    private JPanel bgPanel;

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

    void welcomePage() {
        frame = new JFrame("Student Management System");
        frame.setSize(1080, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        h1 = new JLabel("Student Management System");
        h1.setBounds(250, 50, 700, 50);
        h1.setFont(new Font("Consolas", Font.PLAIN, 30));

        h2 = new JLabel("Login");
        h2.setFont(new Font("Verdana", Font.BOLD, 20));
        h2.setBounds(670, 230, 300, 30);

        userLabel = new JLabel("Username:");
        userLabel.setBounds(500, 300, 80, 25);

        userField = new JTextField();
        userField.setBounds(590, 300, 230, 25);

        passLabel = new JLabel("Password:");
        passLabel.setBounds(500, 340, 80, 25);

        passField = new JPasswordField();
        passField.setBounds(590, 340, 230, 25);

        ImageIcon imageIcon = new ImageIcon("src//Assets//login.png");
        Image image = imageIcon.getImage().getScaledInstance(300, -1, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(image);
        image1 = new JLabel(scaledImageIcon);
        image1.setHorizontalAlignment(JLabel.LEFT);
        image1.setBounds(100, 100, 300, 400);

        bgPanel = new JPanel();
        bgPanel.setBackground(Color.white);
        bgPanel.setBounds(80, 80, 880, 520);
        bgPanel.setLayout(null);

        errorMsg = new JLabel("Incorrect Username or Password !");
        errorMsg.setForeground(Color.red);
        errorMsg.setBounds(590, 370, 230, 25);

        frame.add(bgPanel);
        bgPanel.add(h1);
        bgPanel.add(h2);
        bgPanel.add(userLabel);
        bgPanel.add(userField);
        bgPanel.add(passLabel);
        bgPanel.add(passField);
        bgPanel.add(errorMsg);
        bgPanel.add(image1);

        frame.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        Student student = new Student();
        student.welcomePage();
    }
}
