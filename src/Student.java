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
    static private JButton loginButton, logoutButton;
    Color themeColor = new Color(52, 88, 235);

    void window() {
        frame = new JFrame("Student Management System");
        frame.setSize(1080, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        loginPage();
    }

    void loginPage() {
        h1 = new JLabel("Student Management System");
        h1.setBounds(250, 50, 700, 50);
        h1.setFont(new Font("Consolas", Font.PLAIN, 30));

        loginPanel = new JPanel();
        loginPanel.setBackground(Color.white);
        loginPanel.setBounds(80, 80, 880, 520);
        loginPanel.setLayout(null);

        h2 = new JLabel("Login to Your Account");
        h2.setFont(new Font("Consolas", Font.BOLD, 25));
        h2.setForeground(themeColor);
        h2.setBounds(520, 200, 300, 30);

        userLabel = new JLabel("Username :");
        userLabel.setFont(new Font("Consolas", Font.BOLD, 15));
        userLabel.setBounds(520, 270, 80, 30);

        userField = new JTextField("nikita.verma@gmail.com");
        userField.setBounds(520, 300, 300, 30);

        passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Consolas", Font.BOLD, 15));
        passLabel.setBounds(520, 340, 80, 25);

        passField = new JPasswordField("08112001");
        passField.setBounds(520, 370, 300, 30);

        ImageIcon imageIcon = new ImageIcon("src//Assets//login.png");
        Image image = imageIcon.getImage().getScaledInstance(300, -1, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(image);
        image1 = new JLabel(scaledImageIcon);
        image1.setHorizontalAlignment(JLabel.LEFT);
        image1.setBounds(100, 50, 400, 500);

        errorMsg = new JLabel();
        errorMsg.setForeground(Color.red);
        errorMsg.setBounds(520, 400, 300, 25);

        loginButton = new JButton("Login");
        loginButton.setBounds(620, 430, 90, 25);
        loginButton.setBackground(themeColor);
        loginButton.setForeground(Color.WHITE);
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
        JLabel name, roll_no, father_name, dob, address, email, contact, course, branch;

        loginPanel.setVisible(false);

        // Find the Data of the Current User
        ResultSet currentUser = stmt
                .executeQuery("Select * from students,courses where students.Email_id='" + user.getString(1)
                        + "' and students.Course_ID=courses.Course_ID");
        currentUser.next();

        studentPanel = new JPanel();
        studentPanel.setBackground(Color.white);
        studentPanel.setBounds(80, 80, 880, 520);
        studentPanel.setLayout(null);

        h1 = new JLabel("Welcome " + currentUser.getString(2) + ",");
        h1.setBounds(50, 40, 400, 50);
        h1.setFont(new Font("Consolas", Font.PLAIN, 30));

        name = new JLabel("Name : " + currentUser.getString(2) + " " + currentUser.getString(3));
        name.setFont(new Font("Consolas", Font.PLAIN, 16));
        name.setBounds(50, 120, 400, 30);

        roll_no = new JLabel("Roll No : " + currentUser.getString(1));
        roll_no.setFont(new Font("Consolas", Font.PLAIN, 16));
        roll_no.setBounds(50, 150, 400, 30);

        father_name = new JLabel("Father's Name : " + currentUser.getString(4));
        father_name.setFont(new Font("Consolas", Font.PLAIN, 16));
        father_name.setBounds(50, 180, 400, 30);

        dob = new JLabel("Date of Birth : " + currentUser.getString(6));
        dob.setFont(new Font("Consolas", Font.PLAIN, 16));
        dob.setBounds(50, 210, 400, 30);

        address = new JLabel("Address : " + currentUser.getString(7));
        address.setFont(new Font("Consolas", Font.PLAIN, 16));
        address.setBounds(50, 240, 600, 30);

        course = new JLabel("Course : " + currentUser.getString(11));
        course.setFont(new Font("Consolas", Font.PLAIN, 16));
        course.setBounds(50, 270, 400, 30);

        branch = new JLabel("Branch : " + currentUser.getString(12));
        branch.setFont(new Font("Consolas", Font.PLAIN, 16));
        branch.setBounds(50, 300, 400, 30);

        contact = new JLabel("Mobile : " + currentUser.getString(8));
        contact.setFont(new Font("Consolas", Font.PLAIN, 16));
        contact.setBounds(550, 120, 300, 30);

        email = new JLabel("Email : " + currentUser.getString(5));
        email.setFont(new Font("Consolas", Font.PLAIN, 16));
        email.setBounds(550, 150, 400, 30);

        // Add Components
        frame.add(studentPanel);
        studentPanel.add(h1);
        studentPanel.add(name);
        studentPanel.add(roll_no);
        studentPanel.add(father_name);
        studentPanel.add(dob);
        studentPanel.add(address);
        studentPanel.add(course);
        studentPanel.add(branch);
        studentPanel.add(contact);
        studentPanel.add(email);
        logoutBtn(studentPanel);

        frame.setVisible(true);
    }

    void teacherFrame(ResultSet user) throws SQLException {
        loginPanel.setVisible(false);

        // Find the Data of the Curent User
        ResultSet currentUser = stmt
                .executeQuery("Select * from teachers where teachers.Email_id='" + user.getString(1) + "'");
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
        logoutBtn(teacherPanel);

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
        logoutBtn(adminPanel);

        frame.setVisible(true);
    }

    void logoutBtn(JPanel removePanel) {
        logoutButton = new JButton("Log Out");
        logoutButton.setBounds(760, 50, 90, 25);
        logoutButton.setBackground(themeColor);
        logoutButton.setForeground(new Color(255, 255, 255));
        removePanel.add(logoutButton);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removePanel.setVisible(false);
                loginPanel.setVisible(true);

            }
        });
    }

    public static void main(String[] args) throws SQLException {
        Student student = new Student();
        student.window();
    }
}