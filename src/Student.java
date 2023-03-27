import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

public class Student {
    Connection con;
    Statement stmt;
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
            stmt = con.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    static private JFrame frame;
    static private JLabel h1, h2, userLabel, passLabel, image1, errorMsg;
    static private JTextField userField;
    static private JPasswordField passField;
    static private JPanel loginPanel, studentPanel, teacherPanel, adminPanel;
    static private JButton loginButton;

    void selectAll() throws SQLException {
        result = stmt.executeQuery("select * from students");
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
        result = stmt.executeQuery("select * from students where students.Student_ID = " + key);
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

    void window() {
        frame = new JFrame("Student Management System");
        frame.setSize(1080, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
    }

    void loginPage() {
        window();

        h1 = new JLabel("Student Management System");
        h1.setBounds(250, 50, 700, 50);
        h1.setFont(new Font("Consolas", Font.PLAIN, 30));

        loginPanel = new JPanel();
        loginPanel.setBackground(Color.white);
        loginPanel.setBounds(80, 80, 880, 520);
        loginPanel.setLayout(null);

        h2 = new JLabel("Login");
        h2.setFont(new Font("Consolas", Font.BOLD, 20));
        h2.setBounds(650, 230, 300, 30);

        userLabel = new JLabel("Username :");
        userLabel.setFont(new Font("Consolas", Font.BOLD, 15));
        userLabel.setBounds(500, 300, 80, 25);

        userField = new JTextField();
        userField.setBounds(590, 300, 230, 25);

        passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Consolas", Font.BOLD, 15));
        passLabel.setBounds(500, 340, 80, 25);

        passField = new JPasswordField();
        passField.setBounds(590, 340, 230, 25);

        ImageIcon imageIcon = new ImageIcon("src//Assets//login.png");
        Image image = imageIcon.getImage().getScaledInstance(300, -1, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(image);
        image1 = new JLabel(scaledImageIcon);
        image1.setHorizontalAlignment(JLabel.LEFT);
        image1.setBounds(100, 100, 300, 400);

        errorMsg = new JLabel();
        errorMsg.setForeground(Color.red);
        errorMsg.setBounds(590, 370, 230, 25);

        loginButton = new JButton("Login");
        loginButton.setBounds(650, 400, 70, 25);
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String entered_User = userField.getText();
                String entered_pass = new String(passField.getPassword());
                try {
                    ResultSet user_result = validateCredentials(entered_User, entered_pass);

                    if (user_result == null) {
                        errorMsg.setText("Incorrect Username or Password !");
                    } else if (user_result.getString(3).equals("student")) {
                        errorMsg.setText("");
                        System.out.println("Login Approved as Student");
                        studentFrame(user_result);
                    } else if (user_result.getString(3).equals("teacher")) {
                        errorMsg.setText("");
                        System.out.println("Login Approved as Teacher");
                        teacherFrame(user_result);
                    } else if (user_result.getString(3).equals("admin")) {
                        errorMsg.setText("");
                        System.out.println("Login Approved as Admin");
                        adminFrame();
                    } else {
                        errorMsg.setText("Incorrect Username or Password !");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });

        frame.add(loginPanel);
        loginPanel.add(h1);
        loginPanel.add(h2);
        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passLabel);
        loginPanel.add(passField);
        loginPanel.add(image1);
        loginPanel.add(errorMsg);
        loginPanel.add(loginButton);

        frame.setVisible(true);
    }

    public ResultSet validateCredentials(String username, String password) throws SQLException {
        result = stmt
                .executeQuery("select * from users where user_id ='" + username + "' and password= '" + password + "'");
        if (result.next()) {
            if (result.getString(1).equals(username) && result.getString(2).equals(password)) {
                return result;
            }
        }
        return null;
    }

    void studentFrame(ResultSet user) throws SQLException {
        loginPanel.setVisible(false);

        // Find the Student Data of the Curent User
        ResultSet currentUser = stmt
                .executeQuery("Select * from students where students.Email_id='" + user.getString(1) + "'");
        currentUser.next();

        studentPanel = new JPanel();
        studentPanel.setBackground(Color.white);
        studentPanel.setBounds(80, 80, 880, 520);
        studentPanel.setLayout(null);

        h1 = new JLabel("Welcome " + currentUser.getString(2) + ",");
        h1.setBounds(50, 50, 400, 50);
        h1.setFont(new Font("Consolas", Font.PLAIN, 30));

        frame.add(studentPanel);
        studentPanel.add(h1);

        frame.setVisible(true);
    }

    void teacherFrame(ResultSet user) throws SQLException {
        loginPanel.setVisible(false);

        // Find the Teacher Data of the Curent User
        ResultSet currentUser = stmt
                .executeQuery("Select * from students where teachers.Email_id='" + user.getString(1) + "'");
        currentUser.next();

        teacherPanel = new JPanel();
        teacherPanel.setBackground(Color.white);
        teacherPanel.setBounds(80, 80, 880, 520);
        teacherPanel.setLayout(null);

        h1 = new JLabel("Welcome " + currentUser.getString(2) + ",");
        h1.setBounds(50, 50, 400, 50);
        h1.setFont(new Font("Consolas", Font.PLAIN, 30));

        frame.add(teacherPanel);
        teacherPanel.add(h1);

        frame.setVisible(true);
    }

    void adminFrame() throws SQLException {
        loginPanel.setVisible(false);

        adminPanel = new JPanel();
        adminPanel.setBackground(Color.white);
        adminPanel.setBounds(80, 80, 880, 520);
        adminPanel.setLayout(null);

        h1 = new JLabel("Administrator");
        h1.setBounds(50, 50, 400, 50);
        h1.setFont(new Font("Consolas", Font.PLAIN, 30));

        frame.add(adminPanel);
        adminPanel.add(h1);

        frame.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        Student student = new Student();
        student.loginPage();
    }
}
