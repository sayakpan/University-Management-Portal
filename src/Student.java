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

        h2 = new JLabel("Login to Your Account");
        h2.setFont(new Font("Consolas", Font.BOLD, 25));
        h2.setForeground(themeColor);
        h2.setBounds(520, 200, 300, 30);

        userLabel = new JLabel("Username :");
        userLabel.setFont(new Font("Consolas", Font.BOLD, 15));
        userLabel.setBounds(520, 270, 80, 30);

        userField = new JTextField("admin");
        userField.setBounds(520, 300, 300, 30);

        passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Consolas", Font.BOLD, 15));
        passLabel.setBounds(520, 340, 80, 25);

        passField = new JPasswordField("admin");
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
        loginPanel.setVisible(false);

        // Find the Data of the Current User
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
        logoutBtn(studentPanel);

        frame.setVisible(true);
    }

    void teacherFrame(ResultSet user) throws SQLException {
        loginPanel.setVisible(false);
        JLabel lb, lb1, lb2, lb3, lb4,lb5,lb6,lb7,lb8;

        JTextField tf1, tf2, tf3, tf4,tf5,tf6,tf7,tf8;

        // Find the Data of the Curent User
        ResultSet currentUser = stmt
                .executeQuery("Select * from teachers where teachers.Email_id='" + user.getString(1) + "'");
        currentUser.next();
// this is teacher pannel 
        teacherPanel = new JPanel();
        teacherPanel.setBackground(Color.white);
        teacherPanel.setBounds(80, 80, 880, 520);
        teacherPanel.setLayout(null);

        h1 = new JLabel("Welcome " + currentUser.getString(2) + ",");
        h1.setBounds(50, 50, 400, 50);
        h1.setFont(new Font("Consolas", Font.PLAIN, 30));

        frame.add(teacherPanel);
        teacherPanel.add(h1);

        lb1 = new JLabel("Teacher ID");
        lb1.setBounds(50, 110, 170, 20);
        lb1.setFont(new Font("Consolas", Font.BOLD, 20));

        tf1 = new JTextField(30);
        tf1.setBounds(180, 110, 200, 20);
        tf1.setFont(new Font("Console", Font.BOLD, 18));

        lb2 = new JLabel("Teacher Name");
        lb2.setBounds(50, 180, 170, 20);
        lb2.setFont(new Font("Consolas", Font.BOLD, 20));

        tf2 = new JTextField(15);
        tf2.setBounds(190, 180, 200, 22);
        tf2.setFont(new Font("Console", Font.BOLD, 18));


        lb3 = new JLabel("Designation");
        lb3.setBounds(50, 250, 170, 20);
        lb3.setFont(new Font("Consolas", Font.BOLD, 20));

        tf3 = new JTextField(15);
        tf3.setBounds(180, 250, 200, 22);
        tf3.setFont(new Font("Console", Font.PLAIN, 18));


        lb4 = new JLabel("Contact No");
        lb4.setBounds(50, 320, 170, 20);
        lb4.setFont(new Font("Consolas", Font.BOLD, 20));

        tf4 = new JTextField(30);
        tf4.setBounds(180, 320, 200, 20);
        tf4.setFont(new Font("Console", Font.PLAIN, 18));


        lb5 = new JLabel("Email");
        lb5.setBounds(430, 110, 170, 20);
        lb5.setFont(new Font("Consolas", Font.BOLD, 20));

        tf5 = new JTextField(30);
        tf5.setBounds(500, 110, 200, 26);//
        tf5.setFont(new Font("Console", Font.PLAIN, 18));


        lb6 = new JLabel("Address");
        lb6.setBounds(435, 180, 170, 22);
        lb6.setFont(new Font("Consolas", Font.BOLD, 20));


        tf6 = new JTextField(45);
        tf6.setBounds(520, 180, 320, 24);
        tf6.setFont(new Font("Console", Font.PLAIN, 16));


        lb7 = new JLabel("DOB");
        lb7.setBounds(435, 250, 200, 20);
        lb7.setFont(new Font("Consolas", Font.BOLD, 20));

        tf7 = new JTextField(30);
        tf7.setBounds(500, 250, 200, 20);
        tf7.setFont(new Font("Console", Font.PLAIN, 20));

        lb8 = new JLabel("Course ID");
        lb8.setBounds(430, 320, 200, 20);
        lb8.setFont(new Font("Consolas", Font.BOLD, 20));

        tf8 = new JTextField(30);
        tf8.setBounds(510, 320, 200, 20);
        tf8.setFont(new Font("Console", Font.PLAIN, 18));


        teacherPanel.setLayout(null);

 

        //Add components to the JFrame
        teacherPanel.add(lb1);

        teacherPanel.add(tf1);

        teacherPanel.add(lb2);

        teacherPanel. add(tf2);

        teacherPanel. add(lb3);

        teacherPanel.add(tf3);

        teacherPanel.add(lb4);

        teacherPanel.add(tf4);
        
        teacherPanel.add(lb5);
        
        teacherPanel.add(tf5);
        
        teacherPanel.add(lb6);
        
        teacherPanel.add(tf6);
        
        teacherPanel.add(lb7);
        teacherPanel.add(lb8);

        teacherPanel.add(tf7);

        teacherPanel.add(tf8);
       
        //Set TextField Editable False

        tf1.setEditable(false);
        tf2.setEditable(false);
        tf3.setEditable(false);
        tf4.setEditable(false);
        tf5.setEditable(false);
        tf6.setEditable(false);
        tf7.setEditable(false);
        tf8.setEditable(false);
      
            //Fetch output

            String s = currentUser.getString(1);
            String s1 = currentUser.getString(2);
            String s2 = currentUser.getString(3);
            String s3 = currentUser.getString(4);
            String s4 = currentUser.getString(5);
            String s5 = currentUser.getString(6);
            String s6 = currentUser.getString(7);
            String s7 = currentUser.getString(8);
            String s8= currentUser.getString(9);

            //Sets Records in TextFields.

            tf1.setText(s);
            tf2.setText(s1+" " +s2);
            tf3.setText(s3);
            tf4.setText(s5);
            tf5.setText(s4);
            tf6.setText(s7);
            tf7.setText(s6);
            tf8.setText(s8);


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
        student.loginPage();
    }
}
