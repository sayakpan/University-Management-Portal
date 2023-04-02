import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
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

    // Theme Customisations

    Color themeColor = new Color(10, 133, 204);
    Color hoverColor = new Color(3, 116, 168);
    Color greenColor = new Color(24, 168, 40);

    class MyButtonBlue extends JButton implements MouseListener {
        MyButtonBlue(String text) {
            super(text);
            setBackground(themeColor);
            setForeground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(themeColor));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addMouseListener(this);
        }

        // Implement MouseListener methods
        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(hoverColor); // Set background color when mouse enters
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(themeColor); // Set background color back to default when mouse exits
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    }

    class MyButtonWhite extends MyButtonBlue {
        MyButtonWhite(String text) {
            super(text);
            setBackground(Color.WHITE);
            setForeground(Color.GRAY);
            setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addMouseListener(this);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(Color.GRAY);
            setForeground(Color.WHITE);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(Color.WHITE);
            setForeground(Color.GRAY);
        }
    }

    class MyButtonGreen extends MyButtonBlue {

        MyButtonGreen(String text) {
            super(text);
            setBackground(greenColor);
            setForeground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(greenColor));

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(new Color(8, 120, 21));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(greenColor);
        }

    }

    class JMenuItemTheme extends JMenuItem implements MouseListener {
        JMenuItemTheme(String text) {
            super(text);
            setOpaque(true);
            setBorder(BorderFactory.createEmptyBorder(5, 16, 5, 10));
            setBackground(themeColor);
            setForeground(Color.WHITE);
            setHorizontalAlignment(CENTER);
            setFont(new Font("Calibri", Font.BOLD, 16));
            addMouseListener(this);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(hoverColor);
            setFont(new Font("Calibri", Font.BOLD, 18));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(themeColor);
            setFont(new Font("Calibri", Font.BOLD, 16));
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            setBackground(hoverColor);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    }

    // class JMenuItemTheme extends JMenuItem implements MouseListener {
    // JMenuItemTheme(String text) {
    // super(text);
    // setForeground(themeColor);
    // setBackground(Color.white);
    // addMouseListener(this);
    // }

    // @Override
    // public void mouseEntered(MouseEvent e) {
    // setBackground(themeColor);
    // setForeground(Color.WHITE);
    // setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    // }

    // @Override
    // public void mouseExited(MouseEvent e) {
    // setBackground(Color.WHITE);
    // setForeground(themeColor);
    // }

    // @Override
    // public void mouseClicked(MouseEvent e) {
    // setBackground(hoverColor);
    // }

    // @Override
    // public void mousePressed(MouseEvent e) {
    // }

    // @Override
    // public void mouseReleased(MouseEvent e) {
    // }
    // }

    // Global Variables

    static private JFrame frame;
    static private JLabel h1, h2;
    static private JPanel loginPanel, studentPanel, teacherPanel, adminPanel, adminSubPanel;
    MyButtonBlue modifyButton, modifyButton2;

    // Main Window Starting

    void window() {
        frame = new JFrame("Student Management System");
        frame.setSize(1080, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        loginPage();
    }

    // Login Page Method

    void loginPage() {
        JLabel userLabel, passLabel, image1, errorMsg;
        JTextField userField;
        JPasswordField passField;
        MyButtonBlue loginButton;

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

        loginButton = new MyButtonBlue("Login");
        loginButton.setBounds(620, 430, 90, 25);
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

    // Method to Validate the Entered Credential

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

    // Executed when the Entered User is a STUDENT

    void studentFrame(ResultSet user) throws SQLException {
        loginPanel.setVisible(false);

        // Find the Data of the Current User
        ResultSet currentUser = stmt
                .executeQuery("Select * from students,courses where students.Email_id='" + user.getString(1)
                        + "' and students.Course_ID=courses.Course_ID");

        currentUser.next();
        String user_email = currentUser.getString(5);

        studentPanel = new JPanel();
        studentPanel.setBackground(Color.white);
        studentPanel.setBounds(80, 80, 880, 520);
        studentPanel.setLayout(null);

        JLabel h1 = new JLabel("Welcome " + currentUser.getString(2) + ",");
        h1.setBounds(50, 40, 400, 50);
        h1.setFont(new Font("Consolas", Font.PLAIN, 30));

        JLabel name = new JLabel("Name : " + currentUser.getString(2) + " " + currentUser.getString(3));
        name.setFont(new Font("Consolas", Font.PLAIN, 16));
        name.setBounds(50, 120, 400, 30);

        JLabel roll_no = new JLabel("Roll No : " + currentUser.getString(1));
        roll_no.setFont(new Font("Consolas", Font.PLAIN, 16));
        roll_no.setBounds(50, 150, 400, 30);

        JLabel father_name = new JLabel("Father's Name : " + currentUser.getString(4));
        father_name.setFont(new Font("Consolas", Font.PLAIN, 16));
        father_name.setBounds(50, 180, 400, 30);

        JLabel dob = new JLabel("Date of Birth : " + currentUser.getString(6));
        dob.setFont(new Font("Consolas", Font.PLAIN, 16));
        dob.setBounds(50, 210, 400, 30);

        JLabel address = new JLabel("Address : " + currentUser.getString(7));
        address.setFont(new Font("Consolas", Font.PLAIN, 16));
        address.setBounds(50, 240, 600, 30);

        JLabel course = new JLabel("Course : " + currentUser.getString(11));
        course.setFont(new Font("Consolas", Font.PLAIN, 16));
        course.setBounds(50, 270, 400, 30);

        JLabel branch = new JLabel("Branch : " + currentUser.getString(12));
        branch.setFont(new Font("Consolas", Font.PLAIN, 16));
        branch.setBounds(50, 300, 400, 30);

        JLabel contact = new JLabel("Mobile : " + currentUser.getString(8));
        contact.setFont(new Font("Consolas", Font.PLAIN, 16));
        contact.setBounds(550, 120, 300, 30);

        JLabel email = new JLabel("Email : " + user_email);
        email.setFont(new Font("Consolas", Font.PLAIN, 16));
        email.setBounds(550, 150, 400, 30);

        modifyButton = new MyButtonBlue("Modify");
        modifyButton.setBounds(760, 460, 90, 25);
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyButton.setVisible(false);
                try {
                    modifyStudent(user_email, father_name, address, contact);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

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
        studentPanel.add(modifyButton);
        logoutBtn(studentPanel);

        frame.setVisible(true);
    }

    private void modifyStudent(String email, JLabel father_name, JLabel address, JLabel contact) throws SQLException {
        final String fatherNameOld = father_name.getText();
        final String addressOld = address.getText();
        final String contactOld = contact.getText();

        JTextField fatherNameField = new JTextField(father_name.getText().replaceAll("Father's Name : ", ""));
        father_name.setText("Father's Name : ");
        fatherNameField.setBounds(190, 180, 200, 25);
        JTextField addressField = new JTextField(address.getText().replaceAll("Address : ", ""));
        address.setText("Address : ");
        addressField.setBounds(150, 240, 350, 25);
        JTextField contactField = new JTextField(contact.getText().replaceAll("Mobile : ", ""));
        contact.setText("Mobile : ");
        contactField.setBounds(630, 120, 150, 25);

        studentPanel.add(fatherNameField);
        studentPanel.add(addressField);
        studentPanel.add(contactField);

        MyButtonBlue saveButton = new MyButtonBlue("Save");
        saveButton.setBounds(760, 460, 90, 25);

        MyButtonWhite cancelButton = new MyButtonWhite("Cancel");
        cancelButton.setBounds(760, 430, 90, 25);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String updateQuery = "UPDATE students SET Fathers_name='" + fatherNameField.getText() +
                            "',Address='" + addressField.getText() + "',Contact_no='" + contactField.getText()
                            + "' where Email_id='" + email + "';";
                    stmt.executeUpdate(updateQuery);

                    // Replace the text fields with new labels showing the modified fields

                    father_name.setBounds(50, 180, 400, 30);
                    father_name.setText("Father's Name : " + fatherNameField.getText());
                    studentPanel.remove(fatherNameField);
                    studentPanel.add(father_name);

                    address.setBounds(50, 240, 600, 30);
                    address.setText("Address : " + addressField.getText());
                    studentPanel.remove(addressField);
                    studentPanel.add(address);

                    contact.setBounds(550, 120, 300, 30);
                    contact.setText("Mobile : " + contactField.getText());
                    studentPanel.remove(contactField);
                    studentPanel.add(contact);

                    fatherNameField.setVisible(false);
                    addressField.setVisible(false);
                    contactField.setVisible(false);

                    studentPanel.add(modifyButton);
                    modifyButton.setVisible(true);

                    studentPanel.remove(saveButton);
                    studentPanel.remove(cancelButton);

                    // Refresh the panel to update its layout
                    studentPanel.revalidate();
                    studentPanel.repaint();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                father_name.setBounds(50, 180, 400, 30);
                father_name.setText(fatherNameOld);
                studentPanel.remove(fatherNameField);
                studentPanel.add(father_name);

                address.setBounds(50, 240, 600, 30);
                address.setText(addressOld);
                studentPanel.remove(addressField);
                studentPanel.add(address);

                contact.setBounds(550, 120, 300, 30);
                contact.setText(contactOld);
                studentPanel.remove(contactField);
                studentPanel.add(contact);

                fatherNameField.setVisible(false);
                addressField.setVisible(false);
                contactField.setVisible(false);

                studentPanel.add(modifyButton);
                modifyButton.setVisible(true);

                studentPanel.remove(saveButton);
                studentPanel.remove(cancelButton);

                // Refresh the panel to update its layout
                studentPanel.revalidate();
                studentPanel.repaint();
            }
        });

        studentPanel.revalidate();
        studentPanel.repaint();

        // Add the "Save" button to the panel
        studentPanel.add(saveButton);
        studentPanel.add(cancelButton);
    }

    // Executed when the Entered User is a TEACHER

    void teacherFrame(ResultSet user) throws SQLException {
        JLabel name, designation, id, dob, address, contact, email, course, branch;

        loginPanel.setVisible(false);

        // Find the Data of the Curent User
        ResultSet currentUser = stmt
                .executeQuery("Select * from teachers,courses where teachers.Email_id='" + user.getString(1)
                        + "' and teachers.Course_ID=courses.Course_ID");

        currentUser.next();
        String user_email = currentUser.getString(5);

        teacherPanel = new JPanel();
        teacherPanel.setBackground(Color.white);
        teacherPanel.setBounds(80, 80, 880, 520);
        teacherPanel.setLayout(null);

        h1 = new JLabel("Welcome " + currentUser.getString(2) + ",");
        h1.setBounds(50, 50, 400, 50);
        h1.setFont(new Font("Consolas", Font.PLAIN, 30));

        name = new JLabel("Name : " + currentUser.getString(2) + " " + currentUser.getString(3));
        name.setFont(new Font("Consolas", Font.PLAIN, 16));
        name.setBounds(50, 120, 400, 30);

        id = new JLabel("Teacher ID : " + currentUser.getString(1));
        id.setFont(new Font("Consolas", Font.PLAIN, 16));
        id.setBounds(50, 150, 400, 30);

        designation = new JLabel("Designation : " + currentUser.getString(4));
        designation.setFont(new Font("Consolas", Font.PLAIN, 16));
        designation.setBounds(50, 180, 400, 30);

        dob = new JLabel("Date of Birth : " + currentUser.getString(6));
        dob.setFont(new Font("Consolas", Font.PLAIN, 16));
        dob.setBounds(50, 210, 400, 30);

        address = new JLabel("Address : " + currentUser.getString(7));
        address.setFont(new Font("Consolas", Font.PLAIN, 16));
        address.setBounds(50, 240, 600, 30);

        course = new JLabel("Assigned Course : " + currentUser.getString(11));
        course.setFont(new Font("Consolas", Font.PLAIN, 16));
        course.setBounds(50, 270, 400, 30);

        branch = new JLabel("Branch : " + currentUser.getString(12));
        branch.setFont(new Font("Consolas", Font.PLAIN, 16));
        branch.setBounds(50, 300, 400, 30);

        contact = new JLabel("Mobile : " + currentUser.getString(8));
        contact.setFont(new Font("Consolas", Font.PLAIN, 16));
        contact.setBounds(550, 120, 300, 30);

        email = new JLabel("Email : " + user_email);
        email.setFont(new Font("Consolas", Font.PLAIN, 16));
        email.setBounds(550, 150, 400, 30);

        modifyButton2 = new MyButtonBlue("Modify");
        modifyButton2.setBounds(760, 460, 90, 25);
        modifyButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyButton2.setVisible(false);
                try {
                    modifyTeacher(user_email, designation, address, contact);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Add Components
        frame.add(teacherPanel);
        teacherPanel.add(h1);
        teacherPanel.add(name);
        teacherPanel.add(id);
        teacherPanel.add(designation);
        teacherPanel.add(dob);
        teacherPanel.add(address);
        teacherPanel.add(course);
        teacherPanel.add(branch);
        teacherPanel.add(contact);
        teacherPanel.add(email);
        teacherPanel.add(modifyButton2);
        logoutBtn(teacherPanel);

        frame.setVisible(true);
    }

    // Modify Button Code For TeacherFrame

    private void modifyTeacher(String email, JLabel designation, JLabel address, JLabel contact) throws SQLException {
        final String designationOld = designation.getText();
        final String addressOld = address.getText();
        final String contactOld = contact.getText();

        JTextField designationField = new JTextField(designation.getText().replaceAll("Designation : ", ""));
        designation.setText("Designation : ");
        designationField.setBounds(190, 180, 200, 25);
        JTextField addressField = new JTextField(address.getText().replaceAll("Address : ", ""));
        address.setText("Address : ");
        addressField.setBounds(150, 240, 350, 25);
        JTextField contactField = new JTextField(contact.getText().replaceAll("Mobile : ", ""));
        contact.setText("Mobile : ");
        contactField.setBounds(630, 120, 150, 25);

        teacherPanel.add(designationField);
        teacherPanel.add(addressField);
        teacherPanel.add(contactField);

        MyButtonBlue saveButton = new MyButtonBlue("Save");
        saveButton.setBounds(760, 460, 90, 25);

        MyButtonWhite cancelButton = new MyButtonWhite("Cancel");
        cancelButton.setBounds(760, 430, 90, 25);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String updateQuery = "UPDATE teachers SET Designation='" + designationField.getText() +
                            "',Address='" + addressField.getText() + "',Contact_no='" + contactField.getText()
                            + "' where Email_id='" + email + "';";
                    stmt.executeUpdate(updateQuery);

                    // Replace the text fields with new labels showing the modified fields

                    designation.setBounds(50, 180, 400, 30);
                    designation.setText("Designation : " + designationField.getText());
                    teacherPanel.remove(designationField);
                    teacherPanel.add(designation);

                    address.setBounds(50, 240, 600, 30);
                    address.setText("Address : " + addressField.getText());
                    teacherPanel.remove(addressField);
                    teacherPanel.add(address);

                    contact.setBounds(550, 120, 300, 30);
                    contact.setText("Mobile : " + contactField.getText());
                    teacherPanel.remove(contactField);
                    teacherPanel.add(contact);

                    designationField.setVisible(false);
                    addressField.setVisible(false);
                    contactField.setVisible(false);

                    teacherPanel.add(modifyButton2);
                    modifyButton2.setVisible(true);

                    teacherPanel.remove(saveButton);
                    teacherPanel.remove(cancelButton);

                    // Refresh the panel to update its layout
                    teacherPanel.revalidate();
                    teacherPanel.repaint();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                designation.setBounds(50, 180, 400, 30);
                designation.setText(designationOld);
                teacherPanel.remove(designationField);
                teacherPanel.add(designation);

                address.setBounds(50, 240, 600, 30);
                address.setText(addressOld);
                teacherPanel.remove(addressField);
                teacherPanel.add(address);

                contact.setBounds(550, 120, 300, 30);
                contact.setText(contactOld);
                teacherPanel.remove(contactField);
                teacherPanel.add(contact);

                designationField.setVisible(false);
                addressField.setVisible(false);
                contactField.setVisible(false);

                teacherPanel.add(modifyButton2);
                modifyButton2.setVisible(true);

                teacherPanel.remove(saveButton);
                teacherPanel.remove(cancelButton);

                // Refresh the panel to update its layout
                teacherPanel.revalidate();
                teacherPanel.repaint();
            }
        });

        teacherPanel.revalidate();
        teacherPanel.repaint();

        // Add the "Save" button to the panel
        teacherPanel.add(saveButton);
        teacherPanel.add(cancelButton);
    }

    // Executed when the Entered User is a ADMIN

    void adminFrame() throws SQLException {
        loginPanel.setVisible(false);

        adminPanel = new JPanel();
        adminPanel.setBackground(Color.white);
        adminPanel.setBounds(80, 80, 880, 520);
        adminPanel.setLayout(null);

        h1 = new JLabel("Administrator");
        h1.setBounds(20, 40, 400, 50);
        h1.setFont(new Font("Consolas", Font.PLAIN, 30));

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(20, 110, 130, 390);
        menuBar.setBackground(themeColor);
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));

        // Menu Options

        JMenuItemTheme dashboardMenu = new JMenuItemTheme("Dashboard");
        JMenuItemTheme addMenu = new JMenuItemTheme("Add Profile");
        JMenuItemTheme searchMenu = new JMenuItemTheme("Search");
        JMenuItemTheme updateMenu = new JMenuItemTheme("Update");
        JMenuItemTheme removeMenu = new JMenuItemTheme("Remove");
        JMenuItemTheme feeMenu = new JMenuItemTheme("Fee Details");
        JMenuItemTheme examMenu = new JMenuItemTheme("Examination");

        menuBar.add(dashboardMenu);
        menuBar.add(addMenu);
        menuBar.add(searchMenu);
        menuBar.add(updateMenu);
        menuBar.add(removeMenu);
        menuBar.add(feeMenu);
        menuBar.add(examMenu);

        // Action Listeners

        addMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewProfile();
            }
        });

        adminPanel.add(menuBar);
        frame.add(adminPanel);
        adminPanel.add(h1);
        logoutBtn(adminPanel);

        frame.setVisible(true);
    }

    void AddNewProfile() {
        adminSubPanel = new JPanel();
        adminSubPanel.setBackground(new Color(232, 246, 255));
        adminSubPanel.setBounds(160, 110, 700, 390);
        adminSubPanel.setLayout(null);

        JRadioButton addStudent = new JRadioButton("Add Student Profile");
        addStudent.setBounds(20, 15, 150, 30);
        addStudent.setBackground(adminSubPanel.getBackground());

        JRadioButton addTeacher = new JRadioButton("Add Teacher Profile");
        addTeacher.setBounds(175, 15, 150, 30);
        addTeacher.setBackground(adminSubPanel.getBackground());

        ButtonGroup RadioButtonGroup = new ButtonGroup();
        RadioButtonGroup.add(addStudent);
        RadioButtonGroup.add(addTeacher);

        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Student Add Triggered");

                JLabel roll_no = new JLabel("Roll No : Automatic");
                roll_no.setFont(new Font("Consolas", Font.PLAIN, 16));
                roll_no.setBounds(20, 70, 200, 30);

                JLabel name = new JLabel("Name :");
                name.setFont(new Font("Consolas", Font.PLAIN, 16));
                name.setBounds(20, 100, 200, 30);

                JLabel father_name = new JLabel("Father's Name :");
                father_name.setFont(new Font("Consolas", Font.PLAIN, 16));
                father_name.setBounds(20, 130, 200, 30);

                JLabel dob = new JLabel("Date of Birth :");
                dob.setFont(new Font("Consolas", Font.PLAIN, 16));
                dob.setBounds(20, 160, 200, 30);

                JLabel address = new JLabel("Address :");
                address.setFont(new Font("Consolas", Font.PLAIN, 16));
                address.setBounds(20, 190, 100, 30);

                JLabel contact = new JLabel("Mobile :");
                contact.setFont(new Font("Consolas", Font.PLAIN, 16));
                contact.setBounds(380, 100, 200, 30);

                JLabel email = new JLabel("Email : ");
                email.setFont(new Font("Consolas", Font.PLAIN, 16));
                email.setBounds(380, 130, 200, 30);

                JLabel course = new JLabel("Course :");
                course.setFont(new Font("Consolas", Font.PLAIN, 16));
                course.setBounds(380, 160, 200, 30);

                JLabel branch = new JLabel("Branch :");
                branch.setFont(new Font("Consolas", Font.PLAIN, 16));
                branch.setBounds(380, 190, 200, 30);

                JTextField nameField = new JTextField();
                nameField.setBounds(160, 100, 200, 25);

                JTextField fatherNameField = new JTextField();
                fatherNameField.setBounds(160, 130, 200, 25);

                JTextField dobField = new JTextField();
                dobField.setBounds(160, 160, 200, 25);

                JTextField addressField = new JTextField();
                addressField.setBounds(160, 190, 200, 25);

                JTextField contactField = new JTextField();
                contactField.setBounds(460, 100, 200, 25);

                JTextField emailField = new JTextField();
                emailField.setBounds(460, 130, 200, 25);

                JTextField courseField = new JTextField();
                courseField.setBounds(460, 160, 200, 25);

                JTextField branchField = new JTextField();
                branchField.setBounds(460, 190, 200, 25);

                MyButtonGreen submitButton = new MyButtonGreen("Submit");
                submitButton.setBounds(250, 250, 100, 25);

                MyButtonWhite clearButton = new MyButtonWhite("Clear");
                clearButton.setBounds(360, 250, 100, 25);
                clearButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Component[] components = adminSubPanel.getComponents();
                        for (Component comp : components) {
                            if (comp instanceof JTextField) {
                                JTextField textField = (JTextField) comp;
                                textField.setText("");
                            }
                        }
                        JOptionPane.showMessageDialog(adminSubPanel, "Cleared all Inputs !", "Cleared",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                });

                adminSubPanel.add(submitButton);
                adminSubPanel.add(clearButton);

                adminSubPanel.add(name);
                adminSubPanel.add(roll_no);
                adminSubPanel.add(father_name);
                adminSubPanel.add(dob);
                adminSubPanel.add(address);
                adminSubPanel.add(course);
                adminSubPanel.add(branch);
                adminSubPanel.add(contact);
                adminSubPanel.add(email);

                adminSubPanel.add(nameField);
                adminSubPanel.add(fatherNameField);
                adminSubPanel.add(dobField);
                adminSubPanel.add(addressField);
                adminSubPanel.add(courseField);
                adminSubPanel.add(branchField);
                adminSubPanel.add(contactField);
                adminSubPanel.add(emailField);

                adminPanel.revalidate();
                adminPanel.repaint();
            }
        });

        addTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Teacher");
            }
        });

        adminSubPanel.add(addStudent);
        adminSubPanel.add(addTeacher);

        adminPanel.add(adminSubPanel);
        adminPanel.revalidate();
        adminPanel.repaint();
    }

    // LOGOUT Button Method

    void logoutBtn(JPanel removePanel) {
        MyButtonBlue logoutButton = new MyButtonBlue("Log Out");
        logoutButton.setBounds(760, 50, 90, 25);
        removePanel.add(logoutButton);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logoutButton.setEnabled(false);
                JFrame popup = new JFrame("Confirm Logout");
                popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                popup.setLayout(null);
                popup.setSize(350, 200);
                popup.setLocationRelativeTo(removePanel);
                popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                popup.setVisible(true);
                popup.setResizable(false);
                popup.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        logoutButton.setEnabled(true);
                    }
                });

                JPanel popPanel = new JPanel();
                popPanel.setBackground(Color.WHITE);
                popPanel.setBounds(0, 0, 350, 200);
                popPanel.setLayout(null);

                JLabel logoutMSG = new JLabel("Do you want to Log Out?");
                logoutMSG.setFont(new Font("Consolas", Font.BOLD, 17));
                logoutMSG.setBounds(50, 25, 250, 30);

                MyButtonBlue yesBtn = new MyButtonBlue("Yes");
                yesBtn.setBounds(40, 80, 250, 30);
                yesBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        removePanel.setVisible(false);
                        loginPanel.setVisible(true);
                        popup.dispose();
                    }
                });

                MyButtonWhite noBtn = new MyButtonWhite("No");
                noBtn.setBounds(40, 115, 250, 30);
                noBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        popup.dispose();
                        logoutButton.setEnabled(true);
                    }
                });

                popup.add(popPanel);
                popPanel.add(logoutMSG);
                popPanel.add(yesBtn);
                popPanel.add(noBtn);
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        Student student = new Student();
        student.window();
    }
}