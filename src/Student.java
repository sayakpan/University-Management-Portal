import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

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
    Color yellowColor = new Color(255, 193, 7);
    Color redColor = new Color(220, 53, 69);

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

    class MyButtonYellow extends MyButtonBlue {
        MyButtonYellow(String text) {
            super(text);
            setBackground(yellowColor);
            setForeground(Color.BLACK);
            setBorder(BorderFactory.createLineBorder(yellowColor));

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(new Color(224, 168, 0));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(yellowColor);
        }

    }

    class MyButtonRed extends MyButtonBlue {
        MyButtonRed(String text) {
            super(text);
            setBackground(redColor);
            setForeground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(redColor));

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(new Color(200, 35, 51));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(redColor);
        }

    }

    class MyButtonHoverBlue extends MyButtonBlue {
        MyButtonHoverBlue(String text) {
            super(text);
            setBackground(Color.WHITE);
            setForeground(themeColor);
            setBorder(BorderFactory.createLineBorder(themeColor));

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(themeColor);
            setForeground(Color.WHITE);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(Color.WHITE);
            setForeground(themeColor);
        }

    }

    class DashboardCountButton extends MyButtonBlue {
        DashboardCountButton(int count, String label, Color buttonColor) {
            super("<html><div style='text-align: right;'><font size='6'><b>" + count + "</b></font><br><font size='3'>"
                    + label + "</font></div></html>");
            setOpaque(true);
            setBackground(buttonColor);
            setForeground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(buttonColor));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(getBackground().darker());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(getBackground().brighter());
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

    // Global Variables

    static private JFrame frame;
    static private JLabel h1, h2;
    static private JPanel loginPanel, studentPanel, teacherPanel, adminPanel, adminSubPanel;
    MyButtonBlue modifyButton, modifyButton2;

    // Main Window Starting

    void window() {
        frame = new JFrame("University Management Portal");
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

        h1 = new JLabel("University Management Portal");
        h1.setBounds(210, 50, 700, 50);
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
        loginButton.setBounds(610, 430, 110, 25);
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

        userField.addActionListener(new ActionListener() { // Press Enter to Login
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
            }
        });
        passField.addActionListener(new ActionListener() { // Press Enter to Login
            @Override
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick();
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

    // Modify Button Code For TeacherFrame

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

        adminSubPanel = new JPanel();
        adminSubPanel.setBackground(new Color(232, 246, 255));
        adminSubPanel.setBounds(160, 110, 700, 390);
        adminSubPanel.setLayout(null);

        // Action Listeners
        dashboardMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminSubPanel.removeAll();
                dashboard();
            }
        });

        addMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminSubPanel.removeAll();
                AddNewProfile();
            }
        });

        updateMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminSubPanel.removeAll();
                UpdateExistingProfile("default", "default");
            }
        });

        searchMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminSubPanel.removeAll();
                SearchExistingProfile();
            }
        });

        removeMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminSubPanel.removeAll();
                RemoveExistingProfile("default", "default");
            }
        });

        feeMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminSubPanel.removeAll();
                feeStructure();
            }
        });

        examMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminSubPanel.removeAll();
                examinationMenu();
            }
        });

        adminPanel.add(menuBar);
        frame.add(adminPanel);
        adminPanel.add(h1);
        logoutBtn(adminPanel);
        dashboardMenu.doClick();

        frame.setVisible(true);
    }

    // Method for Dashboard of Admin
    void dashboard() {
        String studentQuery = "SELECT count(*) from students";
        String teachersQuery = "SELECT count(*) from teachers";
        String courseQuery = "SELECT count(*) from courses";
        try {
            ResultSet studentCount = stmt.executeQuery(studentQuery);
            studentCount.next();
            DashboardCountButton studentBoxButton = new DashboardCountButton(studentCount.getInt(1), "STUDENT",
                    new Color(8, 100, 252));
            studentBoxButton.setBounds(20, 20, 150, 70);
            adminSubPanel.add(studentBoxButton);

            ResultSet teacherCount = stmt.executeQuery(teachersQuery);
            teacherCount.next();
            DashboardCountButton teacherBoxButton = new DashboardCountButton(teacherCount.getInt(1), "TEACHERS",
                    new Color(44, 196, 128));
            teacherBoxButton.setBounds(190, 20, 150, 70);
            adminSubPanel.add(teacherBoxButton);

            ResultSet courserCount = stmt.executeQuery(courseQuery);
            courserCount.next();
            DashboardCountButton courseBoxButton = new DashboardCountButton(courserCount.getInt(1), "COURSES",
                    new Color(244, 153, 14));
            courseBoxButton.setBounds(360, 20, 150, 70);
            adminSubPanel.add(courseBoxButton);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DashboardCountButton examBoxButton = new DashboardCountButton(2, "EXAMS", new Color(250, 100, 105));
        examBoxButton.setBounds(530, 20, 150, 70);

        adminSubPanel.add(examBoxButton);
        adminPanel.add(adminSubPanel);
        adminPanel.revalidate();
        adminPanel.repaint();
    }

    // Method to Add Profiles from Admin

    void AddNewProfile() {
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

                adminSubPanel.removeAll();
                adminSubPanel.add(addStudent);
                adminSubPanel.add(addTeacher);

                JLabel roll_no = new JLabel("Roll No : Automatic");
                roll_no.setFont(new Font("Consolas", Font.PLAIN, 16));
                roll_no.setBounds(20, 70, 200, 30);

                JLabel fname = new JLabel("First Name :");
                fname.setFont(new Font("Consolas", Font.PLAIN, 16));
                fname.setBounds(20, 100, 200, 30);

                JLabel lname = new JLabel("Last Name :");
                lname.setFont(new Font("Consolas", Font.PLAIN, 16));
                lname.setBounds(380, 100, 200, 30);

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
                contact.setBounds(380, 130, 200, 30);

                JLabel email = new JLabel("Email : ");
                email.setFont(new Font("Consolas", Font.PLAIN, 16));
                email.setBounds(380, 160, 200, 30);

                JLabel course = new JLabel("Course :");
                course.setFont(new Font("Consolas", Font.PLAIN, 16));
                course.setBounds(380, 190, 200, 30);

                JTextField fnameField = new JTextField();
                fnameField.setBounds(160, 100, 200, 25);

                JTextField lnameField = new JTextField();
                lnameField.setBounds(480, 100, 200, 25);

                JTextField fatherNameField = new JTextField();
                fatherNameField.setBounds(160, 130, 200, 25);

                JTextField dobField = new JTextField("YYYY-MM-DD");
                dobField.setBounds(160, 160, 200, 25);

                JTextField addressField = new JTextField();
                addressField.setBounds(160, 190, 200, 55);

                JTextField contactField = new JTextField("+91 ");
                contactField.setBounds(480, 130, 200, 25);

                JTextField emailField = new JTextField();
                emailField.setBounds(480, 160, 200, 25);

                JLabel branch = new JLabel("Branch :");
                branch.setFont(new Font("Consolas", Font.PLAIN, 16));
                branch.setBounds(380, 220, 200, 30);

                String[] branchList = { "-- Select Branch --", "Mechanical Engineering",
                        "Electronics and Communication Engineering", "Electrical Engineering",
                        "Computer Science Engineering", "Civil Engineering" };
                JComboBox<String> branchComboBox = new JComboBox<String>(branchList);
                branchComboBox.setBounds(480, 220, 200, 25);

                String[] courseList = { "-- Select Course --", "BCA", "MCA", "BTECH", "BBA", "MBA" };
                JComboBox<String> courseComboBox = new JComboBox<String>(courseList);
                courseComboBox.setBounds(480, 190, 200, 25);
                courseComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (courseComboBox.getSelectedItem().equals("BTECH")) {
                            adminSubPanel.add(branch);
                            adminSubPanel.add(branchComboBox);
                        } else {
                            adminSubPanel.remove(branch);
                            adminSubPanel.remove(branchComboBox);
                        }
                        adminPanel.revalidate();
                        adminPanel.repaint();
                    }
                });

                MyButtonGreen submitButton = new MyButtonGreen("Submit");
                submitButton.setBounds(250, 280, 100, 25);
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Check if all fields are filled and ComboBox are Selected
                        Component[] components = adminSubPanel.getComponents();
                        for (Component component : components) {
                            if (component instanceof JTextField) {
                                JTextField textField = (JTextField) component;
                                if (textField.getText().trim().isEmpty()) {
                                    JOptionPane.showMessageDialog(adminSubPanel, "Please fill in all fields.",
                                            "Empty Field",
                                            JOptionPane.WARNING_MESSAGE);
                                    return;
                                }
                            }
                            if (component instanceof JComboBox) {
                                JComboBox<?> comboBox = (JComboBox<?>) component;
                                if (comboBox.getSelectedIndex() == 0) {
                                    JOptionPane.showMessageDialog(adminSubPanel,
                                            "Please select an item from the drop-down list.", "Empty Drop-Down",
                                            JOptionPane.WARNING_MESSAGE);
                                    return;
                                }
                            }
                        }
                        // Run Ouery to Fetch Course ID
                        String query;
                        if (courseComboBox.getSelectedItem().equals("BTECH")) {
                            query = "select Course_ID from courses where Course_Name='BTECH'and Branch='"
                                    + branchComboBox.getSelectedItem() + "';";
                        } else {
                            query = "select Course_ID from courses where Course_Name='"
                                    + courseComboBox.getSelectedItem() + "';";
                        }
                        System.out.println(query);
                        try {
                            result = stmt.executeQuery(query);
                            result.next();
                            // Prepared Statement is used to prevent SQL injection attacks
                            String InsertQuery = "INSERT INTO students (First_name, Last_name, Fathers_name, Email_id, Date_of_birth, Address, Contact_no, Course_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                            PreparedStatement preparedStmt = con.prepareStatement(InsertQuery);
                            preparedStmt.setString(1, fnameField.getText());
                            preparedStmt.setString(2, lnameField.getText());
                            preparedStmt.setString(3, fatherNameField.getText());
                            preparedStmt.setString(4, emailField.getText());
                            preparedStmt.setString(5, dobField.getText());
                            preparedStmt.setString(6, addressField.getText());
                            preparedStmt.setString(7, contactField.getText());
                            preparedStmt.setInt(8, result.getInt(1));
                            // preparedStmt.executeUpdate();
                            System.out.println(preparedStmt);
                            ImageIcon doneIcon = new ImageIcon("src//Assets//done.png");
                            Image doneImage = doneIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                            JOptionPane.showMessageDialog(adminSubPanel,
                                    "Insertion Done !\nUser Name : " + emailField.getText()
                                            + "\nPassword : DOB in DDMMYYYY format.",
                                    "Insertion Done",
                                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(doneImage));
                        } catch (SQLException e1) {
                            JOptionPane.showMessageDialog(adminSubPanel,
                                    "ERROR : Profile Creation Failed\nCheck console for details", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            e1.printStackTrace();
                        }
                    }
                });

                MyButtonWhite clearButton = new MyButtonWhite("Clear");
                clearButton.setBounds(360, 280, 100, 25);
                clearButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Component[] components = adminSubPanel.getComponents();
                        for (Component comp : components) {
                            if (comp instanceof JTextField) {
                                JTextField textField = (JTextField) comp;
                                textField.setText("");
                            }
                            if (comp instanceof JComboBox) {
                                JComboBox<?> comboBox = (JComboBox<?>) comp;
                                comboBox.setSelectedIndex(0);
                            }
                        }
                        JOptionPane.showMessageDialog(adminSubPanel, "Cleared all Inputs !", "Cleared",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                });

                adminSubPanel.add(submitButton);
                adminSubPanel.add(clearButton);

                adminSubPanel.add(fname);
                adminSubPanel.add(lname);
                adminSubPanel.add(roll_no);
                adminSubPanel.add(father_name);
                adminSubPanel.add(dob);
                adminSubPanel.add(address);
                adminSubPanel.add(course);
                adminSubPanel.add(contact);
                adminSubPanel.add(email);

                adminSubPanel.add(fnameField);
                adminSubPanel.add(lnameField);
                adminSubPanel.add(fatherNameField);
                adminSubPanel.add(dobField);
                adminSubPanel.add(addressField);
                adminSubPanel.add(courseComboBox);
                adminSubPanel.add(contactField);
                adminSubPanel.add(emailField);

                adminPanel.revalidate();
                adminPanel.repaint();
            }
        });

        addTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Teacher Add Triggered");

                adminSubPanel.removeAll();
                adminSubPanel.add(addStudent);
                adminSubPanel.add(addTeacher);

                JLabel id = new JLabel("Id : Automatic");
                id.setFont(new Font("Consolas", Font.PLAIN, 16));
                id.setBounds(20, 70, 200, 30);

                JLabel fname = new JLabel("First Name :");
                fname.setFont(new Font("Consolas", Font.PLAIN, 16));
                fname.setBounds(20, 100, 200, 30);

                JLabel lname = new JLabel("Last Name :");
                lname.setFont(new Font("Consolas", Font.PLAIN, 16));
                lname.setBounds(380, 100, 200, 30);

                JLabel designation = new JLabel("Designation :");
                designation.setFont(new Font("Consolas", Font.PLAIN, 16));
                designation.setBounds(20, 130, 200, 30);

                JLabel dob = new JLabel("Date of Birth :");
                dob.setFont(new Font("Consolas", Font.PLAIN, 16));
                dob.setBounds(20, 160, 200, 30);

                JLabel address = new JLabel("Address :");
                address.setFont(new Font("Consolas", Font.PLAIN, 16));
                address.setBounds(20, 190, 100, 30);

                JLabel contact = new JLabel("Mobile :");
                contact.setFont(new Font("Consolas", Font.PLAIN, 16));
                contact.setBounds(380, 130, 200, 30);

                JLabel email = new JLabel("Email : ");
                email.setFont(new Font("Consolas", Font.PLAIN, 16));
                email.setBounds(380, 160, 200, 30);

                JLabel course = new JLabel("Department :");
                course.setFont(new Font("Consolas", Font.PLAIN, 16));
                course.setBounds(380, 190, 200, 30);

                JTextField fnameField = new JTextField();
                fnameField.setBounds(160, 100, 200, 25);

                JTextField lnameField = new JTextField();
                lnameField.setBounds(480, 100, 200, 25);

                JTextField designationField = new JTextField();
                designationField.setBounds(160, 130, 200, 25);

                JTextField dobField = new JTextField("YYYY-MM-DD");
                dobField.setBounds(160, 160, 200, 25);

                JTextField addressField = new JTextField();
                addressField.setBounds(160, 190, 200, 55);

                JTextField contactField = new JTextField("+91 ");
                contactField.setBounds(480, 130, 200, 25);

                JTextField emailField = new JTextField();
                emailField.setBounds(480, 160, 200, 25);

                JLabel branch = new JLabel("Branch :");
                branch.setFont(new Font("Consolas", Font.PLAIN, 16));
                branch.setBounds(380, 220, 200, 30);

                String[] branchList = { "-- Select Branch --", "Mechanical Engineering",
                        "Electronics and Communication Engineering", "Electrical Engineering",
                        "Computer Science Engineering", "Civil Engineering" };
                JComboBox<String> branchComboBox = new JComboBox<String>(branchList);
                branchComboBox.setBounds(500, 220, 180, 25);

                String[] courseList = { "-- Select Course --", "BCA", "MCA", "BTECH", "BBA", "MBA" };
                JComboBox<String> courseComboBox = new JComboBox<String>(courseList);
                courseComboBox.setBounds(500, 190, 180, 25);
                courseComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (courseComboBox.getSelectedItem().equals("BTECH")) {
                            adminSubPanel.add(branch);
                            adminSubPanel.add(branchComboBox);
                        } else {
                            adminSubPanel.remove(branch);
                            adminSubPanel.remove(branchComboBox);
                        }
                        adminSubPanel.revalidate();
                        adminSubPanel.repaint();
                    }
                });

                MyButtonGreen submitButton = new MyButtonGreen("Submit");
                submitButton.setBounds(250, 280, 100, 25);
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Check if all fields are filled and ComboBox are Selected
                        Component[] components = adminSubPanel.getComponents();
                        for (Component component : components) {
                            if (component instanceof JTextField) {
                                JTextField textField = (JTextField) component;
                                if (textField.getText().trim().isEmpty()) {
                                    JOptionPane.showMessageDialog(adminSubPanel, "Please fill in all fields.",
                                            "Empty Field",
                                            JOptionPane.WARNING_MESSAGE);
                                    return;
                                }
                            }
                            if (component instanceof JComboBox) {
                                JComboBox<?> comboBox = (JComboBox<?>) component;
                                if (comboBox.getSelectedIndex() == 0) {
                                    JOptionPane.showMessageDialog(adminSubPanel,
                                            "Please select an item from the drop-down list.", "Empty Drop-Down",
                                            JOptionPane.WARNING_MESSAGE);
                                    return;
                                }
                            }
                        }
                        // Run Ouery to Fetch Course ID
                        String query;
                        if (courseComboBox.getSelectedItem().equals("BTECH")) {
                            query = "select Course_ID from courses where Course_Name='BTECH'and Branch='"
                                    + branchComboBox.getSelectedItem() + "';";
                        } else {
                            query = "select Course_ID from courses where Course_Name='"
                                    + courseComboBox.getSelectedItem() + "';";
                        }
                        System.out.println(query);
                        try {
                            result = stmt.executeQuery(query);
                            result.next();
                            // Prepared Statement is used to prevent SQL injection attacks
                            String InsertQuery = "INSERT INTO teachers (First_name, Last_name, Designation, Email_id, Date_of_birth, Address, Contact_no, Course_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                            PreparedStatement preparedStmt = con.prepareStatement(InsertQuery);
                            preparedStmt.setString(1, fnameField.getText());
                            preparedStmt.setString(2, lnameField.getText());
                            preparedStmt.setString(3, designationField.getText());
                            preparedStmt.setString(4, emailField.getText());
                            preparedStmt.setString(5, dobField.getText());
                            preparedStmt.setString(6, addressField.getText());
                            preparedStmt.setString(7, contactField.getText());
                            preparedStmt.setInt(8, result.getInt(1));
                            // preparedStmt.executeUpdate();
                            System.out.println(preparedStmt);
                            ImageIcon doneIcon = new ImageIcon("src//Assets//done.png");
                            Image doneImage = doneIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                            JOptionPane.showMessageDialog(adminSubPanel,
                                    "Insertion Done !\nUser Name : " + emailField.getText()
                                            + "\nPassword : DOB in DDMMYYYY format.",
                                    "Insertion Done",
                                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(doneImage));
                        } catch (SQLException e1) {
                            JOptionPane.showMessageDialog(adminSubPanel,
                                    "ERROR : Profile Creation Failed\nCheck console for details", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            e1.printStackTrace();
                        }
                    }
                });

                MyButtonWhite clearButton = new MyButtonWhite("Clear");
                clearButton.setBounds(360, 280, 100, 25);
                clearButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Component[] components = adminSubPanel.getComponents();
                        for (Component comp : components) {
                            if (comp instanceof JTextField) {
                                JTextField textField = (JTextField) comp;
                                textField.setText("");
                            }
                            if (comp instanceof JComboBox) {
                                JComboBox<?> comboBox = (JComboBox<?>) comp;
                                comboBox.setSelectedIndex(0);
                            }
                        }
                        JOptionPane.showMessageDialog(adminSubPanel, "Cleared all Inputs !", "Cleared",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                });

                adminSubPanel.add(submitButton);
                adminSubPanel.add(clearButton);

                adminSubPanel.add(designation);
                adminSubPanel.add(lname);
                adminSubPanel.add(id);
                adminSubPanel.add(fname);
                adminSubPanel.add(dob);
                adminSubPanel.add(address);
                adminSubPanel.add(course);
                adminSubPanel.add(contact);
                adminSubPanel.add(email);

                adminSubPanel.add(fnameField);
                adminSubPanel.add(lnameField);
                adminSubPanel.add(designationField);
                adminSubPanel.add(dobField);
                adminSubPanel.add(addressField);
                adminSubPanel.add(courseComboBox);
                adminSubPanel.add(contactField);
                adminSubPanel.add(emailField);
                adminSubPanel.revalidate();
                adminSubPanel.repaint();
            }
        });

        adminSubPanel.add(addStudent);
        adminSubPanel.add(addTeacher);

        adminPanel.add(adminSubPanel);
        adminPanel.revalidate();
        adminPanel.repaint();
    }

    // Method to Update any Existing Profile from Admin

    void UpdateExistingProfile(String key, String type) {
        JRadioButton updateStudent = new JRadioButton("Update Student Details");
        updateStudent.setBounds(20, 15, 170, 30);
        updateStudent.setBackground(adminSubPanel.getBackground());

        JRadioButton updateTeacher = new JRadioButton("Update Teacher Details");
        updateTeacher.setBounds(200, 15, 170, 30);
        updateTeacher.setBackground(adminSubPanel.getBackground());

        ButtonGroup RadioButtonGroup = new ButtonGroup();
        RadioButtonGroup.add(updateStudent);
        RadioButtonGroup.add(updateTeacher);

        // Update Student

        updateStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Student Update Triggered");

                adminSubPanel.removeAll();
                adminSubPanel.add(updateStudent);
                adminSubPanel.add(updateTeacher);

                JLabel rollnumberLabel = new JLabel("Select Roll Number ");
                rollnumberLabel.setBounds(20, 70, 170, 16);
                rollnumberLabel.setFont(new Font("Consolas", Font.BOLD, 16));

                Choice rollnoChoice = new Choice();
                rollnoChoice.setBounds(200, 65, 120, 16);
                rollnoChoice.add("-- Select Roll No --");
                adminSubPanel.add(rollnoChoice);

                try {
                    ResultSet rs = stmt.executeQuery("SELECT Roll_No FROM students ORDER BY Roll_No ASC;");
                    while (rs.next()) {
                        rollnoChoice.add(rs.getString("Roll_No"));
                    }
                } catch (Exception p) {
                    p.printStackTrace();
                }

                JLabel name = new JLabel("Name :");
                name.setFont(new Font("Consolas", Font.PLAIN, 16));
                name.setBounds(20, 120, 200, 30);

                JLabel nameLabel = new JLabel();
                nameLabel.setBounds(160, 120, 200, 30);
                nameLabel.setFont(new Font("Consolas", Font.PLAIN, 16));

                JLabel father_name = new JLabel("Father's Name :");
                father_name.setFont(new Font("Consolas", Font.PLAIN, 16));
                father_name.setBounds(20, 150, 200, 30);

                JTextField new_father_name = new JTextField();
                new_father_name.setBounds(160, 150, 200, 25);

                JLabel dob = new JLabel("Date of Birth :");
                dob.setFont(new Font("Consolas", Font.PLAIN, 16));
                dob.setBounds(20, 180, 200, 30);

                JLabel dobLabel = new JLabel();
                dobLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                dobLabel.setBounds(160, 180, 200, 30);

                JLabel address = new JLabel("Address :");
                address.setFont(new Font("Consolas", Font.PLAIN, 16));
                address.setBounds(20, 210, 180, 25);

                JTextField new_address = new JTextField();
                new_address.setBounds(110, 210, 250, 25);

                JLabel contact = new JLabel("Mobile :");
                contact.setFont(new Font("Consolas", Font.PLAIN, 16));
                contact.setBounds(380, 120, 200, 30);

                JTextField new_contact = new JTextField();
                new_contact.setBounds(460, 120, 220, 25);

                JLabel email = new JLabel("Email : ");
                email.setFont(new Font("Consolas", Font.PLAIN, 16));
                email.setBounds(380, 150, 200, 30);

                JLabel emailLabel = new JLabel();
                emailLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                emailLabel.setBounds(460, 150, 220, 30);

                JLabel course = new JLabel("Course :");
                course.setFont(new Font("Consolas", Font.PLAIN, 16));
                course.setBounds(380, 180, 200, 30);

                JLabel courseLabel = new JLabel();
                courseLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                courseLabel.setBounds(460, 180, 220, 30);

                JLabel branch = new JLabel("Branch :");
                branch.setFont(new Font("Consolas", Font.PLAIN, 16));
                branch.setBounds(380, 210, 200, 30);

                JTextArea branchTextArea = new JTextArea();
                branchTextArea.setFont(new Font("Consolas", Font.PLAIN, 16));
                branchTextArea.setLineWrap(true);
                branchTextArea.setWrapStyleWord(true);
                branchTextArea.setOpaque(false);
                branchTextArea.setBounds(460, 215, 220, 60);

                MyButtonGreen submitButton = new MyButtonGreen("Submit");
                submitButton.setBounds(250, 300, 100, 25);
                adminSubPanel.add(submitButton);
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String updateQuery = "UPDATE students SET Fathers_name=?, Address=?, Contact_no=? WHERE Email_id=?;";
                            PreparedStatement pstmt = con.prepareStatement(updateQuery);

                            pstmt.setString(1, new_father_name.getText());
                            pstmt.setString(2, new_address.getText());
                            pstmt.setString(3, new_contact.getText());
                            pstmt.setString(4, emailLabel.getText());

                            pstmt.executeUpdate();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(adminSubPanel,
                                    "ERROR : Update Failed\nCheck console for details", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                // All Components are not visible by default

                name.setVisible(false);
                nameLabel.setVisible(false);
                father_name.setVisible(false);
                new_father_name.setVisible(false);
                dobLabel.setVisible(false);
                dob.setVisible(false);
                address.setVisible(false);
                new_address.setVisible(false);
                contact.setVisible(false);
                new_contact.setVisible(false);
                email.setVisible(false);
                emailLabel.setVisible(false);
                course.setVisible(false);
                courseLabel.setVisible(false);
                branch.setVisible(false);
                branchTextArea.setVisible(false);
                submitButton.setVisible(false);

                rollnoChoice.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent ie) {
                        if ((rollnoChoice.getSelectedIndex() == 0) || (ie.getStateChange() == 0)) {
                            // Components will be visible if Roll No is Selected
                            name.setVisible(false);
                            nameLabel.setVisible(false);
                            father_name.setVisible(false);
                            new_father_name.setVisible(false);
                            dobLabel.setVisible(false);
                            dob.setVisible(false);
                            address.setVisible(false);
                            new_address.setVisible(false);
                            contact.setVisible(false);
                            new_contact.setVisible(false);
                            email.setVisible(false);
                            emailLabel.setVisible(false);
                            course.setVisible(false);
                            courseLabel.setVisible(false);
                            branch.setVisible(false);
                            branchTextArea.setVisible(false);
                            submitButton.setVisible(false);
                        } else {
                            try {
                                name.setVisible(true);
                                nameLabel.setVisible(true);
                                father_name.setVisible(true);
                                new_father_name.setVisible(true);
                                dobLabel.setVisible(true);
                                dob.setVisible(true);
                                address.setVisible(true);
                                new_address.setVisible(true);
                                contact.setVisible(true);
                                new_contact.setVisible(true);
                                email.setVisible(true);
                                emailLabel.setVisible(true);
                                course.setVisible(true);
                                courseLabel.setVisible(true);
                                branch.setVisible(true);
                                branchTextArea.setVisible(true);
                                submitButton.setVisible(true);

                                String query = "select * from students,courses where Roll_No='"
                                        + rollnoChoice.getSelectedItem()
                                        + "' and students.Course_ID=courses.Course_ID";
                                ResultSet rs = stmt.executeQuery(query);
                                rs.next();
                                nameLabel.setText(rs.getString(2) + " " + rs.getString(3));
                                new_father_name.setText(rs.getString(4));
                                emailLabel.setText(rs.getString(5));
                                dobLabel.setText(rs.getString(6));
                                new_address.setText(rs.getString(7));
                                new_contact.setText(rs.getString(8));
                                courseLabel.setText(rs.getString(11));
                                branchTextArea.setText(rs.getString(12));
                            } catch (Exception c) {
                                c.printStackTrace();
                            }
                        }
                    }
                });

                rollnoChoice.select(key);
                for (ItemListener listener : rollnoChoice.getItemListeners()) {
                    listener.itemStateChanged(new ItemEvent(rollnoChoice, ItemEvent.ITEM_STATE_CHANGED, rollnoChoice,
                            ItemEvent.SELECTED));
                }

                adminSubPanel.add(rollnumberLabel);
                adminSubPanel.add(name);
                adminSubPanel.add(nameLabel);
                adminSubPanel.add(father_name);
                adminSubPanel.add(new_father_name);
                adminSubPanel.add(dobLabel);
                adminSubPanel.add(dob);
                adminSubPanel.add(address);
                adminSubPanel.add(new_address);
                adminSubPanel.add(contact);
                adminSubPanel.add(new_contact);
                adminSubPanel.add(email);
                adminSubPanel.add(emailLabel);
                adminSubPanel.add(course);
                adminSubPanel.add(courseLabel);
                adminSubPanel.add(branch);
                adminSubPanel.add(branchTextArea);
                adminPanel.revalidate();
                adminPanel.repaint();
            }
        });

        // Update Teacher

        updateTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Teacher Update Triggered");

                adminSubPanel.removeAll();
                adminSubPanel.add(updateStudent);
                adminSubPanel.add(updateTeacher);

                JLabel idLabel = new JLabel("Select Id ");
                idLabel.setBounds(20, 70, 100, 16);
                idLabel.setFont(new Font("Consolas", Font.BOLD, 16));

                Choice idChoice = new Choice();
                idChoice.setBounds(130, 65, 120, 16);
                idChoice.add("-- Select ID --");

                try {
                    ResultSet rs = stmt.executeQuery("SELECT Teacher_ID FROM teachers ORDER BY Teacher_ID ASC;");
                    while (rs.next()) {
                        idChoice.add(rs.getString("Teacher_ID"));
                    }
                } catch (Exception p) {
                    p.printStackTrace();
                }

                JLabel name = new JLabel("Name :");
                name.setFont(new Font("Consolas", Font.PLAIN, 16));
                name.setBounds(20, 120, 200, 30);

                JLabel nameLabel = new JLabel();
                nameLabel.setBounds(160, 120, 200, 30);
                nameLabel.setFont(new Font("Consolas", Font.PLAIN, 16));

                JLabel designation = new JLabel("Designation :");
                designation.setFont(new Font("Consolas", Font.PLAIN, 16));
                designation.setBounds(20, 150, 200, 30);

                JTextField new_designation = new JTextField();
                new_designation.setBounds(160, 150, 200, 25);

                JLabel dob = new JLabel("Date of Birth :");
                dob.setFont(new Font("Consolas", Font.PLAIN, 16));
                dob.setBounds(20, 180, 200, 30);

                JLabel dobLabel = new JLabel();
                dobLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                dobLabel.setBounds(160, 180, 200, 30);

                JLabel address = new JLabel("Address :");
                address.setFont(new Font("Consolas", Font.PLAIN, 16));
                address.setBounds(20, 210, 180, 25);

                JTextField new_address = new JTextField();
                new_address.setBounds(110, 210, 250, 25);

                JLabel contact = new JLabel("Mobile :");
                contact.setFont(new Font("Consolas", Font.PLAIN, 16));
                contact.setBounds(380, 120, 200, 30);

                JTextField new_contact = new JTextField();
                new_contact.setBounds(460, 120, 220, 25);

                JLabel email = new JLabel("Email : ");
                email.setFont(new Font("Consolas", Font.PLAIN, 16));
                email.setBounds(380, 150, 200, 30);

                JLabel emailLabel = new JLabel();
                emailLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                emailLabel.setBounds(460, 150, 220, 30);

                JLabel course = new JLabel("Department :");
                course.setFont(new Font("Consolas", Font.PLAIN, 16));
                course.setBounds(380, 180, 200, 30);

                JLabel courseLabel = new JLabel();
                courseLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                courseLabel.setBounds(490, 180, 200, 30);

                JLabel branch = new JLabel("Branch :");
                branch.setFont(new Font("Consolas", Font.PLAIN, 16));
                branch.setBounds(380, 210, 200, 30);

                JTextArea branchTextArea = new JTextArea();
                branchTextArea.setFont(new Font("Consolas", Font.PLAIN, 16));
                branchTextArea.setLineWrap(true);
                branchTextArea.setWrapStyleWord(true);
                branchTextArea.setOpaque(false);
                branchTextArea.setBounds(460, 215, 220, 60);

                MyButtonGreen submitButton = new MyButtonGreen("Submit");
                submitButton.setBounds(250, 300, 100, 25);
                adminSubPanel.add(submitButton);
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String updateQuery = "UPDATE teachers SET Designation=?, Address=?, Contact_no=? WHERE Email_id=?;";
                            PreparedStatement pstmt = con.prepareStatement(updateQuery);

                            pstmt.setString(1, new_designation.getText());
                            pstmt.setString(2, new_address.getText());
                            pstmt.setString(3, new_contact.getText());
                            pstmt.setString(4, emailLabel.getText());

                            pstmt.executeUpdate();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(adminSubPanel,
                                    "ERROR : Update Failed\nCheck console for details", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                // All Components are not visible by default

                name.setVisible(false);
                nameLabel.setVisible(false);
                designation.setVisible(false);
                new_designation.setVisible(false);
                dobLabel.setVisible(false);
                dob.setVisible(false);
                address.setVisible(false);
                new_address.setVisible(false);
                contact.setVisible(false);
                new_contact.setVisible(false);
                email.setVisible(false);
                emailLabel.setVisible(false);
                course.setVisible(false);
                courseLabel.setVisible(false);
                branch.setVisible(false);
                branchTextArea.setVisible(false);
                submitButton.setVisible(false);

                idChoice.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent ie) {
                        if ((idChoice.getSelectedIndex() == 0) || (ie.getStateChange() == 0)) {
                            // Components will be visible if ID is Selected
                            name.setVisible(false);
                            nameLabel.setVisible(false);
                            designation.setVisible(false);
                            new_designation.setVisible(false);
                            dobLabel.setVisible(false);
                            dob.setVisible(false);
                            address.setVisible(false);
                            new_address.setVisible(false);
                            contact.setVisible(false);
                            new_contact.setVisible(false);
                            email.setVisible(false);
                            emailLabel.setVisible(false);
                            course.setVisible(false);
                            courseLabel.setVisible(false);
                            branch.setVisible(false);
                            branchTextArea.setVisible(false);
                            submitButton.setVisible(false);
                        } else {
                            try {
                                name.setVisible(true);
                                nameLabel.setVisible(true);
                                designation.setVisible(true);
                                new_designation.setVisible(true);
                                dobLabel.setVisible(true);
                                dob.setVisible(true);
                                address.setVisible(true);
                                new_address.setVisible(true);
                                contact.setVisible(true);
                                new_contact.setVisible(true);
                                email.setVisible(true);
                                emailLabel.setVisible(true);
                                course.setVisible(true);
                                courseLabel.setVisible(true);
                                branch.setVisible(true);
                                branchTextArea.setVisible(true);
                                submitButton.setVisible(true);

                                String query = "select * from teachers,courses where Teacher_ID='"
                                        + idChoice.getSelectedItem() + "' and teachers.Course_ID=courses.Course_ID";
                                ResultSet rs = stmt.executeQuery(query);
                                rs.next();
                                nameLabel.setText(rs.getString(2) + " " + rs.getString(3));
                                new_designation.setText(rs.getString(4));
                                emailLabel.setText(rs.getString(5));
                                dobLabel.setText(rs.getString(6));
                                new_address.setText(rs.getString(7));
                                new_contact.setText(rs.getString(8));
                                courseLabel.setText(rs.getString(11));
                                branchTextArea.setText(rs.getString(12));
                            } catch (Exception c) {
                                c.printStackTrace();
                            }
                        }
                    }
                });

                idChoice.select(key);
                for (ItemListener listener : idChoice.getItemListeners()) {
                    listener.itemStateChanged(new ItemEvent(idChoice, ItemEvent.ITEM_STATE_CHANGED, idChoice,
                            ItemEvent.SELECTED));
                }

                adminSubPanel.add(idLabel);
                adminSubPanel.add(idChoice);
                adminSubPanel.add(name);
                adminSubPanel.add(nameLabel);
                adminSubPanel.add(designation);
                adminSubPanel.add(new_designation);
                adminSubPanel.add(dobLabel);
                adminSubPanel.add(dob);
                adminSubPanel.add(address);
                adminSubPanel.add(new_address);
                adminSubPanel.add(contact);
                adminSubPanel.add(new_contact);
                adminSubPanel.add(email);
                adminSubPanel.add(emailLabel);
                adminSubPanel.add(course);
                adminSubPanel.add(courseLabel);
                adminSubPanel.add(branch);
                adminSubPanel.add(branchTextArea);
                adminSubPanel.revalidate();
                adminSubPanel.repaint();
            }
        });

        adminSubPanel.add(updateStudent);
        adminSubPanel.add(updateTeacher);

        if (type.equals("student")) {
            updateStudent.doClick();
        } else if (type.equals("teacher")) {
            updateTeacher.doClick();
        }

        adminPanel.add(adminSubPanel);
        adminSubPanel.setVisible(true);
        adminPanel.revalidate();
        adminPanel.repaint();
    }

    // Method to Search any Existing Profile from Admin

    void SearchExistingProfile() {
        JRadioButton searchStudent = new JRadioButton("Search Student Profile");
        searchStudent.setBounds(20, 15, 170, 30);
        searchStudent.setBackground(adminSubPanel.getBackground());

        JRadioButton searchTeacher = new JRadioButton("Search Teacher Profile");
        searchTeacher.setBounds(195, 15, 170, 30);
        searchTeacher.setBackground(adminSubPanel.getBackground());

        ButtonGroup RadioButtonGroup = new ButtonGroup();
        RadioButtonGroup.add(searchStudent);
        RadioButtonGroup.add(searchTeacher);

        searchStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminSubPanel.removeAll();
                adminSubPanel.add(searchStudent);
                adminSubPanel.add(searchTeacher);

                MyButtonGreen searchButton = new MyButtonGreen("Search");
                searchButton.setBounds(330, 50, 70, 25);

                MyButtonYellow updateSelectedButton = new MyButtonYellow("Update");
                updateSelectedButton.setBounds(520, 50, 70, 25);
                adminSubPanel.add(updateSelectedButton);
                updateSelectedButton.setVisible(false);

                MyButtonRed deleteSelectedButon = new MyButtonRed("Delete");
                deleteSelectedButon.setBounds(600, 50, 70, 25);
                adminSubPanel.add(deleteSelectedButon);
                deleteSelectedButon.setVisible(false);

                JTextField searchBar = new JTextField();
                searchBar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                searchBar.setBounds(20, 50, 300, 25);
                searchBar.addActionListener(new ActionListener() { // Performs Search on Clicking "Enter"
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        searchButton.doClick();
                    }
                });

                JLabel countResult = new JLabel();
                countResult.setBounds(410, 60, 200, 25);
                countResult.setFont(new Font("Arial", Font.PLAIN, 12));

                DefaultTableModel model = new DefaultTableModel() {
                    public boolean isCellEditable(int row, int column) { // To Restrict Cell Editability
                        return false;
                    };
                };

                // Table Columns
                model.addColumn("Roll No");
                model.addColumn("Name");
                model.addColumn("Email");
                model.addColumn("DOB");
                model.addColumn("Course");
                model.addColumn("Branch");

                // Query to Fetch Table Data

                try {
                    String searchQuery = "Select * from students,courses where students.Course_ID=courses.Course_ID LIMIT 0,20;";
                    ResultSet searchResult = stmt.executeQuery(searchQuery);
                    while (searchResult.next()) {
                        model.addRow(new String[] {
                                searchResult.getString("Roll_No"),
                                searchResult.getString("First_name") + " " + searchResult.getString("Last_name"),
                                searchResult.getString("Email_id"),
                                searchResult.getString("Date_of_birth"),
                                searchResult.getString("Course_Name"),
                                searchResult.getString("Branch")
                        });
                    }
                } catch (Exception e1) {
                    e1.getStackTrace();
                }

                JTable resultTable = new JTable(model);
                resultTable.setRowHeight(22);

                TableColumnModel columnModel = resultTable.getColumnModel();
                columnModel.getColumn(0).setPreferredWidth(50);
                columnModel.getColumn(1).setPreferredWidth(100);
                columnModel.getColumn(2).setPreferredWidth(170);
                columnModel.getColumn(3).setPreferredWidth(80);
                columnModel.getColumn(4).setPreferredWidth(60);
                columnModel.getColumn(5).setPreferredWidth(200);

                JScrollPane tableScrollPane = new JScrollPane(resultTable);
                tableScrollPane.getViewport().add(resultTable);
                tableScrollPane.setBounds(20, 90, 660, 290);

                // Search Button Action Listener
                searchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String searchKey = searchBar.getText();
                        model.setRowCount(0);

                        try {
                            String searchQuery = "SELECT * FROM students JOIN courses ON students.Course_ID=courses.Course_ID WHERE CONCAT(First_name, ' ', Last_name) LIKE ? ;";
                            PreparedStatement pstmt = con.prepareStatement(searchQuery);
                            pstmt.setString(1, searchKey + "%");
                            ResultSet searchResult = pstmt.executeQuery();

                            while (searchResult.next()) {
                                model.addRow(new String[] {
                                        searchResult.getString("Roll_No"),
                                        searchResult.getString("First_name") + " "
                                                + searchResult.getString("Last_name"),
                                        searchResult.getString("Email_id"),
                                        searchResult.getString("Date_of_birth"),
                                        searchResult.getString("Course_Name"),
                                        searchResult.getString("Branch")
                                });
                            }
                            countResult.setText(model.getRowCount() + " Results");
                        } catch (Exception e1) {
                            e1.getStackTrace();
                        }

                    }
                });

                // Get Roll No from Selected Row

                resultTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent event) {
                        if (event.getValueIsAdjusting()) {
                            return;
                        }
                        int selectedRow = resultTable.getSelectedRow();
                        if (selectedRow != -1) {
                            String rollNo = resultTable.getValueAt(selectedRow, 0).toString();
                            System.out.println(rollNo);
                            updateSelectedButton.setVisible(true);
                            deleteSelectedButon.setVisible(true);

                            updateSelectedButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    UpdateExistingProfile(rollNo, "student");
                                };
                            });
                            deleteSelectedButon.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    RemoveExistingProfile(rollNo, "student");
                                };
                            });
                        }
                    }
                });

                adminSubPanel.add(searchBar);
                adminSubPanel.add(searchButton);
                adminSubPanel.add(countResult);
                adminSubPanel.add(tableScrollPane);

                adminSubPanel.revalidate();
                adminSubPanel.repaint();
            }
        });

        searchTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminSubPanel.removeAll();
                adminSubPanel.add(searchStudent);
                adminSubPanel.add(searchTeacher);

                MyButtonGreen searchButton = new MyButtonGreen("Search");
                searchButton.setBounds(330, 50, 70, 25);

                MyButtonYellow updateSelectedButton = new MyButtonYellow("Update");
                updateSelectedButton.setBounds(520, 50, 70, 25);
                adminSubPanel.add(updateSelectedButton);
                updateSelectedButton.setVisible(false);

                MyButtonRed deleteSelectedButon = new MyButtonRed("Delete");
                deleteSelectedButon.setBounds(600, 50, 70, 25);
                adminSubPanel.add(deleteSelectedButon);
                deleteSelectedButon.setVisible(false);

                JTextField searchBar = new JTextField();
                searchBar.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                searchBar.setBounds(20, 50, 300, 25);
                searchBar.addActionListener(new ActionListener() { // Performs Search on Clicking "Enter"
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        searchButton.doClick();
                    }
                });

                JLabel countResult = new JLabel();
                countResult.setBounds(410, 60, 200, 25);
                countResult.setFont(new Font("Arial", Font.PLAIN, 12));

                DefaultTableModel model = new DefaultTableModel() {
                    public boolean isCellEditable(int row, int column) { // To Restrict Cell Editability
                        return false;
                    };
                };

                // Table Columns
                model.addColumn("ID");
                model.addColumn("Name");
                model.addColumn("Email");
                model.addColumn("DOB");
                model.addColumn("Designation");
                model.addColumn("Department");

                // Query to Fetch Table Data

                try {
                    String searchQuery = "Select * from teachers,courses where teachers.Course_ID=courses.Course_ID LIMIT 0,20;";
                    ResultSet searchResult = stmt.executeQuery(searchQuery);
                    while (searchResult.next()) {
                        model.addRow(new String[] {
                                searchResult.getString("Teacher_ID"),
                                searchResult.getString("First_name") + " " + searchResult.getString("Last_name"),
                                searchResult.getString("Email_id"),
                                searchResult.getString("Date_of_birth"),
                                searchResult.getString("Designation"),
                                searchResult.getString("Course_Name")
                        });
                    }
                } catch (Exception e1) {
                    e1.getStackTrace();
                }

                JTable resultTable = new JTable(model);
                resultTable.setRowHeight(22);

                TableColumnModel columnModel = resultTable.getColumnModel();
                columnModel.getColumn(0).setPreferredWidth(60);
                columnModel.getColumn(1).setPreferredWidth(100);
                columnModel.getColumn(2).setPreferredWidth(170);
                columnModel.getColumn(3).setPreferredWidth(80);
                columnModel.getColumn(4).setPreferredWidth(120);
                columnModel.getColumn(5).setPreferredWidth(130);

                JScrollPane tableScrollPane = new JScrollPane(resultTable);
                tableScrollPane.getViewport().add(resultTable);
                tableScrollPane.setBounds(20, 90, 660, 290);

                // Search Button Action Listener
                searchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String searchKey = searchBar.getText();
                        model.setRowCount(0);

                        try {
                            String searchQuery = "SELECT * FROM teachers JOIN courses ON students.Course_ID=courses.Course_ID WHERE CONCAT(First_name, ' ', Last_name) LIKE ? ;";
                            PreparedStatement pstmt = con.prepareStatement(searchQuery);
                            pstmt.setString(1, searchKey + "%");
                            ResultSet searchResult = pstmt.executeQuery();

                            while (searchResult.next()) {
                                model.addRow(new String[] {
                                        searchResult.getString("Teacher_ID"),
                                        searchResult.getString("First_name") + " "
                                                + searchResult.getString("Last_name"),
                                        searchResult.getString("Email_id"),
                                        searchResult.getString("Date_of_birth"),
                                        searchResult.getString("Designation"),
                                        searchResult.getString("Course_Name")
                                });
                            }
                            countResult.setText(model.getRowCount() + " Results");
                        } catch (Exception e1) {
                            e1.getStackTrace();
                        }

                    }
                });

                // Get Roll No from Selected Row

                resultTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent event) {
                        if (event.getValueIsAdjusting()) {
                            return;
                        }
                        int selectedRow = resultTable.getSelectedRow();
                        if (selectedRow != -1) {
                            String id = resultTable.getValueAt(selectedRow, 0).toString();
                            System.out.println(id);
                            updateSelectedButton.setVisible(true);
                            deleteSelectedButon.setVisible(true);

                            updateSelectedButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    UpdateExistingProfile(id, "teacher");
                                };
                            });
                            deleteSelectedButon.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    RemoveExistingProfile(id, "teacher");
                                };
                            });
                        }
                    }
                });

                adminSubPanel.add(searchBar);
                adminSubPanel.add(searchButton);
                adminSubPanel.add(countResult);
                adminSubPanel.add(tableScrollPane);

                adminSubPanel.revalidate();
                adminSubPanel.repaint();
            }
        });

        adminSubPanel.add(searchStudent);
        adminSubPanel.add(searchTeacher);

        adminPanel.add(adminSubPanel);
        adminPanel.revalidate();
        adminPanel.repaint();

    }

    // Method to Update any Existing Profile from Admin

    void RemoveExistingProfile(String key, String type) {
        JRadioButton removeStudent = new JRadioButton("Remove Student Profile");
        removeStudent.setBounds(20, 15, 170, 30);
        removeStudent.setBackground(adminSubPanel.getBackground());

        JRadioButton removeTeacher = new JRadioButton("Remove Teacher Profile");
        removeTeacher.setBounds(200, 15, 170, 30);
        removeTeacher.setBackground(adminSubPanel.getBackground());

        ButtonGroup RadioButtonGroup = new ButtonGroup();
        RadioButtonGroup.add(removeStudent);
        RadioButtonGroup.add(removeTeacher);

        adminSubPanel.add(removeStudent);
        adminSubPanel.add(removeTeacher);

        removeStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Student Delete Triggered");

                adminSubPanel.removeAll();
                adminSubPanel.add(removeStudent);
                adminSubPanel.add(removeTeacher);

                JLabel rollnumberLabel = new JLabel("Select Roll Number ");
                rollnumberLabel.setBounds(20, 70, 170, 16);
                rollnumberLabel.setFont(new Font("Consolas", Font.BOLD, 16));

                Choice rollnoChoice = new Choice();
                rollnoChoice.setBounds(200, 65, 120, 16);
                rollnoChoice.add("-- Select Roll No --");
                adminSubPanel.add(rollnoChoice);

                try {
                    ResultSet rs = stmt.executeQuery("SELECT Roll_No FROM students ORDER BY Roll_No ASC;");
                    while (rs.next()) {
                        rollnoChoice.add(rs.getString("Roll_No"));
                    }
                } catch (Exception p) {
                    p.printStackTrace();
                }

                JLabel name = new JLabel("Name :");
                name.setFont(new Font("Consolas", Font.PLAIN, 16));
                name.setBounds(20, 120, 60, 30);

                JLabel nameLabel = new JLabel();
                nameLabel.setBounds(80, 120, 200, 30);
                nameLabel.setFont(new Font("Consolas", Font.PLAIN, 16));

                JLabel father_name = new JLabel("Father's Name :");
                father_name.setFont(new Font("Consolas", Font.PLAIN, 16));
                father_name.setBounds(20, 150, 200, 30);

                JLabel father_name_Label = new JLabel();
                father_name_Label.setFont(new Font("Consolas", Font.PLAIN, 16));
                father_name_Label.setBounds(160, 150, 200, 30);

                JLabel dob = new JLabel("Date of Birth :");
                dob.setFont(new Font("Consolas", Font.PLAIN, 16));
                dob.setBounds(20, 180, 200, 30);

                JLabel dobLabel = new JLabel();
                dobLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                dobLabel.setBounds(160, 180, 200, 30);

                JLabel address = new JLabel("Address :");
                address.setFont(new Font("Consolas", Font.PLAIN, 16));
                address.setBounds(20, 210, 180, 25);

                JTextArea addressArea = new JTextArea();
                addressArea.setFont(new Font("Consolas", Font.PLAIN, 16));
                addressArea.setLineWrap(true);
                addressArea.setWrapStyleWord(true);
                addressArea.setOpaque(false);
                addressArea.setBounds(110, 215, 250, 60);

                JLabel contact = new JLabel("Mobile :");
                contact.setFont(new Font("Consolas", Font.PLAIN, 16));
                contact.setBounds(380, 120, 200, 30);

                JLabel contactLabel = new JLabel();
                contactLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                contactLabel.setBounds(460, 120, 220, 30);

                JLabel email = new JLabel("Email : ");
                email.setFont(new Font("Consolas", Font.PLAIN, 16));
                email.setBounds(380, 150, 200, 30);

                JLabel emailLabel = new JLabel();
                emailLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                emailLabel.setBounds(460, 150, 220, 30);

                JLabel course = new JLabel("Course :");
                course.setFont(new Font("Consolas", Font.PLAIN, 16));
                course.setBounds(380, 180, 200, 30);

                JLabel courseLabel = new JLabel();
                courseLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                courseLabel.setBounds(460, 180, 220, 30);

                JLabel branch = new JLabel("Branch :");
                branch.setFont(new Font("Consolas", Font.PLAIN, 16));
                branch.setBounds(380, 210, 200, 30);

                JTextArea branchTextArea = new JTextArea();
                branchTextArea.setFont(new Font("Consolas", Font.PLAIN, 16));
                branchTextArea.setLineWrap(true);
                branchTextArea.setWrapStyleWord(true);
                branchTextArea.setOpaque(false);
                branchTextArea.setBounds(460, 215, 220, 60);

                MyButtonRed deleteButton = new MyButtonRed("Delete");
                deleteButton.setBounds(250, 300, 100, 25);
                adminSubPanel.add(deleteButton);
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            int deleteConfirm = JOptionPane.showConfirmDialog(adminSubPanel, "Confirm Delete ?",
                                    "Delete", JOptionPane.YES_NO_OPTION);
                            if (deleteConfirm == JOptionPane.YES_OPTION) {
                                String removeQuery = "DELETE FROM students WHERE Email_id = ?";
                                PreparedStatement pstmt = con.prepareStatement(removeQuery);
                                pstmt.setString(1, emailLabel.getText());
                                pstmt.executeUpdate();
                                String currentRoll = rollnoChoice.getSelectedItem();
                                System.out.println(currentRoll);
                                rollnoChoice.select(0);
                                rollnoChoice.remove(currentRoll);

                                name.setVisible(false);
                                nameLabel.setVisible(false);
                                father_name.setVisible(false);
                                father_name_Label.setVisible(false);
                                dobLabel.setVisible(false);
                                dob.setVisible(false);
                                address.setVisible(false);
                                addressArea.setVisible(false);
                                contact.setVisible(false);
                                contactLabel.setVisible(false);
                                email.setVisible(false);
                                emailLabel.setVisible(false);
                                course.setVisible(false);
                                courseLabel.setVisible(false);
                                branch.setVisible(false);
                                branchTextArea.setVisible(false);
                                deleteButton.setVisible(false);

                                adminSubPanel.repaint();
                                adminSubPanel.revalidate();
                                JOptionPane.showMessageDialog(adminSubPanel, "Profile deleted successfully!", "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                return;
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(adminSubPanel,
                                    "ERROR : Delete Failed\nCheck console for details", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                // All Components are not visible by default

                name.setVisible(false);
                nameLabel.setVisible(false);
                father_name.setVisible(false);
                father_name_Label.setVisible(false);
                dobLabel.setVisible(false);
                dob.setVisible(false);
                address.setVisible(false);
                addressArea.setVisible(false);
                contact.setVisible(false);
                contactLabel.setVisible(false);
                email.setVisible(false);
                emailLabel.setVisible(false);
                course.setVisible(false);
                courseLabel.setVisible(false);
                branch.setVisible(false);
                branchTextArea.setVisible(false);
                deleteButton.setVisible(false);

                rollnoChoice.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent ie) {
                        if ((rollnoChoice.getSelectedIndex() == 0) || (ie.getStateChange() == 0)) {
                            // Components will be visible if Roll No is Selected
                            name.setVisible(false);
                            nameLabel.setVisible(false);
                            father_name.setVisible(false);
                            father_name_Label.setVisible(false);
                            dobLabel.setVisible(false);
                            dob.setVisible(false);
                            address.setVisible(false);
                            addressArea.setVisible(false);
                            contact.setVisible(false);
                            contactLabel.setVisible(false);
                            email.setVisible(false);
                            emailLabel.setVisible(false);
                            course.setVisible(false);
                            courseLabel.setVisible(false);
                            branch.setVisible(false);
                            branchTextArea.setVisible(false);
                            deleteButton.setVisible(false);
                        } else {
                            try {
                                name.setVisible(true);
                                nameLabel.setVisible(true);
                                father_name.setVisible(true);
                                father_name_Label.setVisible(true);
                                dobLabel.setVisible(true);
                                dob.setVisible(true);
                                address.setVisible(true);
                                addressArea.setVisible(true);
                                contact.setVisible(true);
                                contactLabel.setVisible(true);
                                email.setVisible(true);
                                emailLabel.setVisible(true);
                                course.setVisible(true);
                                courseLabel.setVisible(true);
                                branch.setVisible(true);
                                branchTextArea.setVisible(true);
                                deleteButton.setVisible(true);

                                String query = "select * from students,courses where Roll_No='"
                                        + rollnoChoice.getSelectedItem()
                                        + "' and students.Course_ID=courses.Course_ID";
                                ResultSet rs = stmt.executeQuery(query);
                                rs.next();
                                nameLabel.setText(rs.getString(2) + " " + rs.getString(3));
                                father_name_Label.setText(rs.getString(4));
                                emailLabel.setText(rs.getString(5));
                                dobLabel.setText(rs.getString(6));
                                addressArea.setText(rs.getString(7));
                                contactLabel.setText(rs.getString(8));
                                courseLabel.setText(rs.getString(11));
                                branchTextArea.setText(rs.getString(12));
                            } catch (Exception c) {
                                c.printStackTrace();
                            }
                        }
                    }
                });

                rollnoChoice.select(key);
                for (ItemListener listener : rollnoChoice.getItemListeners()) {
                    listener.itemStateChanged(new ItemEvent(rollnoChoice, ItemEvent.ITEM_STATE_CHANGED, rollnoChoice,
                            ItemEvent.SELECTED));
                }

                adminSubPanel.add(rollnumberLabel);
                adminSubPanel.add(name);
                adminSubPanel.add(nameLabel);
                adminSubPanel.add(father_name);
                adminSubPanel.add(father_name_Label);
                adminSubPanel.add(dobLabel);
                adminSubPanel.add(dob);
                adminSubPanel.add(address);
                adminSubPanel.add(addressArea);
                adminSubPanel.add(contact);
                adminSubPanel.add(contactLabel);
                adminSubPanel.add(email);
                adminSubPanel.add(emailLabel);
                adminSubPanel.add(course);
                adminSubPanel.add(courseLabel);
                adminSubPanel.add(branch);
                adminSubPanel.add(branchTextArea);
                adminPanel.revalidate();
                adminPanel.repaint();
            }
        });

        removeTeacher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Teacher Delete Triggered");

                adminSubPanel.removeAll();
                adminSubPanel.add(removeStudent);
                adminSubPanel.add(removeTeacher);

                JLabel idLabel = new JLabel("Select Id ");
                idLabel.setBounds(20, 70, 100, 16);
                idLabel.setFont(new Font("Consolas", Font.BOLD, 16));

                Choice idChoice = new Choice();
                idChoice.setBounds(130, 65, 120, 16);
                idChoice.add("-- Select ID --");

                try {
                    ResultSet rs = stmt.executeQuery("SELECT Teacher_ID FROM teachers ORDER BY Teacher_ID ASC;");
                    while (rs.next()) {
                        idChoice.add(rs.getString("Teacher_ID"));
                    }
                } catch (Exception p) {
                    p.printStackTrace();
                }

                JLabel name = new JLabel("Name :");
                name.setFont(new Font("Consolas", Font.PLAIN, 16));
                name.setBounds(20, 120, 200, 30);

                JLabel nameLabel = new JLabel();
                nameLabel.setBounds(160, 120, 200, 30);
                nameLabel.setFont(new Font("Consolas", Font.PLAIN, 16));

                JLabel designation = new JLabel("Designation :");
                designation.setFont(new Font("Consolas", Font.PLAIN, 16));
                designation.setBounds(20, 150, 200, 30);

                JLabel designationLabel = new JLabel();
                designationLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                designationLabel.setBounds(160, 150, 200, 30);

                JLabel dob = new JLabel("Date of Birth :");
                dob.setFont(new Font("Consolas", Font.PLAIN, 16));
                dob.setBounds(20, 180, 200, 30);

                JLabel dobLabel = new JLabel();
                dobLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                dobLabel.setBounds(160, 180, 200, 30);

                JLabel address = new JLabel("Address :");
                address.setFont(new Font("Consolas", Font.PLAIN, 16));
                address.setBounds(20, 210, 180, 25);

                JTextArea addressArea = new JTextArea();
                addressArea.setFont(new Font("Consolas", Font.PLAIN, 16));
                addressArea.setLineWrap(true);
                addressArea.setWrapStyleWord(true);
                addressArea.setOpaque(false);
                addressArea.setBounds(110, 215, 250, 50);

                JLabel contact = new JLabel("Mobile :");
                contact.setFont(new Font("Consolas", Font.PLAIN, 16));
                contact.setBounds(380, 120, 200, 30);

                JLabel contactLabel = new JLabel();
                contactLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                contactLabel.setBounds(460, 120, 220, 30);

                JLabel email = new JLabel("Email : ");
                email.setFont(new Font("Consolas", Font.PLAIN, 16));
                email.setBounds(380, 150, 200, 30);

                JLabel emailLabel = new JLabel();
                emailLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                emailLabel.setBounds(460, 150, 220, 30);

                JLabel course = new JLabel("Department :");
                course.setFont(new Font("Consolas", Font.PLAIN, 16));
                course.setBounds(380, 180, 200, 30);

                JLabel courseLabel = new JLabel();
                courseLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                courseLabel.setBounds(490, 180, 200, 30);

                JLabel branch = new JLabel("Branch :");
                branch.setFont(new Font("Consolas", Font.PLAIN, 16));
                branch.setBounds(380, 210, 200, 30);

                JTextArea branchTextArea = new JTextArea();
                branchTextArea.setFont(new Font("Consolas", Font.PLAIN, 16));
                branchTextArea.setLineWrap(true);
                branchTextArea.setWrapStyleWord(true);
                branchTextArea.setOpaque(false);
                branchTextArea.setBounds(460, 215, 220, 60);

                MyButtonRed deleteButton = new MyButtonRed("Delete");
                deleteButton.setBounds(250, 300, 100, 25);
                adminSubPanel.add(deleteButton);
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            int deleteConfirm = JOptionPane.showConfirmDialog(adminSubPanel, "Confirm Delete ?",
                                    "Delete", JOptionPane.YES_NO_OPTION);
                            if (deleteConfirm == JOptionPane.YES_OPTION) {
                                String removeQuery = "DELETE FROM teachers WHERE Email_id = ?";
                                PreparedStatement pstmt = con.prepareStatement(removeQuery);
                                pstmt.setString(1, emailLabel.getText());
                                pstmt.executeUpdate();
                                String currentRoll = idChoice.getSelectedItem();
                                System.out.println(currentRoll);
                                idChoice.select(0);
                                idChoice.remove(currentRoll);

                                name.setVisible(false);
                                nameLabel.setVisible(false);
                                designation.setVisible(false);
                                designationLabel.setVisible(false);
                                dobLabel.setVisible(false);
                                dob.setVisible(false);
                                address.setVisible(false);
                                addressArea.setVisible(false);
                                contact.setVisible(false);
                                contactLabel.setVisible(false);
                                email.setVisible(false);
                                emailLabel.setVisible(false);
                                course.setVisible(false);
                                courseLabel.setVisible(false);
                                branch.setVisible(false);
                                branchTextArea.setVisible(false);
                                deleteButton.setVisible(false);

                                adminSubPanel.repaint();
                                adminSubPanel.revalidate();
                                JOptionPane.showMessageDialog(adminSubPanel, "Profile deleted successfully!", "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                return;
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(adminSubPanel,
                                    "ERROR : Delete Failed\nCheck console for details", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });

                // All Components are not visible by default

                name.setVisible(false);
                nameLabel.setVisible(false);
                designation.setVisible(false);
                designationLabel.setVisible(false);
                dobLabel.setVisible(false);
                dob.setVisible(false);
                address.setVisible(false);
                addressArea.setVisible(false);
                contact.setVisible(false);
                contactLabel.setVisible(false);
                email.setVisible(false);
                emailLabel.setVisible(false);
                course.setVisible(false);
                courseLabel.setVisible(false);
                branch.setVisible(false);
                branchTextArea.setVisible(false);
                deleteButton.setVisible(false);

                idChoice.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent ie) {
                        if ((idChoice.getSelectedIndex() == 0) || (ie.getStateChange() == 0)) {
                            // Components will be visible if ID is Selected
                            name.setVisible(false);
                            nameLabel.setVisible(false);
                            designation.setVisible(false);
                            designationLabel.setVisible(false);
                            dobLabel.setVisible(false);
                            dob.setVisible(false);
                            address.setVisible(false);
                            addressArea.setVisible(false);
                            contact.setVisible(false);
                            contactLabel.setVisible(false);
                            email.setVisible(false);
                            emailLabel.setVisible(false);
                            course.setVisible(false);
                            courseLabel.setVisible(false);
                            branch.setVisible(false);
                            branchTextArea.setVisible(false);
                            deleteButton.setVisible(false);
                        } else {
                            try {
                                name.setVisible(true);
                                nameLabel.setVisible(true);
                                designation.setVisible(true);
                                designationLabel.setVisible(true);
                                dobLabel.setVisible(true);
                                dob.setVisible(true);
                                address.setVisible(true);
                                addressArea.setVisible(true);
                                contact.setVisible(true);
                                contactLabel.setVisible(true);
                                email.setVisible(true);
                                emailLabel.setVisible(true);
                                course.setVisible(true);
                                courseLabel.setVisible(true);
                                branch.setVisible(true);
                                branchTextArea.setVisible(true);
                                deleteButton.setVisible(true);

                                String query = "select * from teachers,courses where Teacher_ID='"
                                        + idChoice.getSelectedItem() + "' and teachers.Course_ID=courses.Course_ID";
                                ResultSet rs = stmt.executeQuery(query);
                                rs.next();
                                nameLabel.setText(rs.getString(2) + " " + rs.getString(3));
                                designationLabel.setText(rs.getString(4));
                                emailLabel.setText(rs.getString(5));
                                dobLabel.setText(rs.getString(6));
                                addressArea.setText(rs.getString(7));
                                contactLabel.setText(rs.getString(8));
                                courseLabel.setText(rs.getString(11));
                                branchTextArea.setText(rs.getString(12));
                            } catch (Exception c) {
                                c.printStackTrace();
                            }
                        }
                    }
                });

                idChoice.select(key);
                for (ItemListener listener : idChoice.getItemListeners()) {
                    listener.itemStateChanged(new ItemEvent(idChoice, ItemEvent.ITEM_STATE_CHANGED, idChoice,
                            ItemEvent.SELECTED));
                }

                adminSubPanel.add(idLabel);
                adminSubPanel.add(idChoice);
                adminSubPanel.add(name);
                adminSubPanel.add(nameLabel);
                adminSubPanel.add(designation);
                adminSubPanel.add(designationLabel);
                adminSubPanel.add(dobLabel);
                adminSubPanel.add(dob);
                adminSubPanel.add(address);
                adminSubPanel.add(addressArea);
                adminSubPanel.add(contact);
                adminSubPanel.add(contactLabel);
                adminSubPanel.add(email);
                adminSubPanel.add(emailLabel);
                adminSubPanel.add(course);
                adminSubPanel.add(courseLabel);
                adminSubPanel.add(branch);
                adminSubPanel.add(branchTextArea);
                adminSubPanel.revalidate();
                adminSubPanel.repaint();
            }
        });

        if (type.equals("student")) {
            removeStudent.doClick();
        } else if (type.equals("teacher")) {
            removeTeacher.doClick();
        }

        adminPanel.add(adminSubPanel);
        adminSubPanel.setVisible(true);
        adminPanel.revalidate();
        adminPanel.repaint();
    }

    // Method to Examination Menu from Admin

    void examinationMenu() {
        JRadioButton displayResults = new JRadioButton("Display Student Result");
        displayResults.setBounds(20, 15, 175, 30);
        displayResults.setBackground(adminSubPanel.getBackground());

        JRadioButton studentMarks = new JRadioButton("Student Marks System");
        studentMarks.setBounds(200, 15, 175, 30);
        studentMarks.setBackground(adminSubPanel.getBackground());

        ButtonGroup RadioButtonGroup = new ButtonGroup();
        RadioButtonGroup.add(studentMarks);
        RadioButtonGroup.add(displayResults);

        displayResults.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Student Add Marks Triggered");

                adminSubPanel.removeAll();
                adminSubPanel.add(studentMarks);
                adminSubPanel.add(displayResults);
                adminSubPanel.repaint();
                adminSubPanel.revalidate();

                JLabel enterRolLabel = new JLabel("Enter Roll Number to see Results");
                enterRolLabel.setBounds(215, 130, 350, 25);
                enterRolLabel.setFont(new Font("Arial", Font.PLAIN, 18));

                MyButtonGreen searchButton = new MyButtonGreen("Search");
                searchButton.setBounds(520, 180, 90, 33);
                searchButton.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 4, new Color(222, 223, 224)));

                JTextField searchBar = new JTextField();
                searchBar.setFont(new Font("Arial", Font.PLAIN, 15));
                searchBar.setForeground(themeColor);
                searchBar.setCaretColor(Color.ORANGE);
                searchBar.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 4, 4, new Color(222, 223, 224)),
                        BorderFactory.createLineBorder(Color.BLACK)));
                searchBar.setBounds(100, 180, 400, 35);
                searchBar.addActionListener(new ActionListener() { // Performs Search on Clicking "Enter"
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        searchButton.doClick();
                    }
                });

                MyButtonYellow backButton = new MyButtonYellow("Back");
                backButton.setBounds(20, 80, 70, 25);
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        displayResults.doClick();
                    }
                });
                backButton.setVisible(false);

                JLabel nameLabel = new JLabel("Student Name :");
                nameLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                nameLabel.setBounds(20, 140, 300, 20);

                JLabel rollLabel = new JLabel("Roll Number :");
                rollLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                rollLabel.setBounds(20, 160, 300, 20);

                JLabel courseLabel = new JLabel("Course :");
                courseLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                courseLabel.setBounds(20, 180, 300, 20);

                JLabel branchLabel = new JLabel("Branch :");
                branchLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                branchLabel.setBounds(20, 190, 80, 40);

                JTextArea branchTextArea = new JTextArea();
                branchTextArea.setFont(new Font("Consolas", Font.PLAIN, 16));
                branchTextArea.setOpaque(false);
                branchTextArea.setLineWrap(true);
                branchTextArea.setWrapStyleWord(true);
                branchTextArea.setBounds(100, 200, 200, 50);

                JLabel semesterLabel = new JLabel("Select Semester :");
                semesterLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
                semesterLabel.setBounds(20, 270, 150, 20);

                Choice semesterChoice = new Choice();
                semesterChoice.setBounds(180, 270, 130, 16);
                semesterChoice.add("-- Select Semester --");

                MyButtonHoverBlue displayResultButton = new MyButtonHoverBlue("Display Result");
                displayResultButton.setBounds(180, 310, 130, 25);

                DefaultTableModel resultModel = new DefaultTableModel() {
                    public boolean isCellEditable(int row, int column) { // To Restrict Cell Editability
                        return false;
                    };
                };

                resultModel.addColumn("Subject Name");
                resultModel.addColumn("Score");
                resultModel.addColumn("Grade");

                JTable resultTable = new JTable(resultModel);
                resultTable.setRowHeight(32);
                resultTable.setBackground(new Color(232, 246, 255));
                resultTable.setForeground(Color.BLACK);
                resultTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                TableColumnModel columnModel = resultTable.getColumnModel();
                columnModel.getColumn(0).setPreferredWidth(200);
                columnModel.getColumn(1).setPreferredWidth(70);
                columnModel.getColumn(2).setPreferredWidth(70);

                JScrollPane resultScrollPane = new JScrollPane(resultTable);
                resultScrollPane.getViewport().add(resultTable);
                resultScrollPane.setBounds(335, 140, 350, 152);

                JTextArea totalLabel = new JTextArea();
                totalLabel.setFont(new Font("ARIAL", Font.BOLD, 14));
                totalLabel.setBounds(375, 310, 220, 60);
                totalLabel.setOpaque(false);
                totalLabel.setLineWrap(true);
                totalLabel.setWrapStyleWord(true);

                nameLabel.setVisible(false);
                rollLabel.setVisible(false);
                courseLabel.setVisible(false);
                branchLabel.setVisible(false);
                branchTextArea.setVisible(false);
                semesterLabel.setVisible(false);
                semesterChoice.setVisible(false);
                displayResultButton.setVisible(false);
                resultScrollPane.setVisible(false);
                totalLabel.setVisible(false);

                searchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String searchKey = searchBar.getText();
                            String searchQuery = "Select * from students,courses where students.Course_ID=courses.Course_ID and Roll_No = ?";
                            PreparedStatement pstmt = con.prepareStatement(searchQuery);
                            pstmt.setString(1, searchKey);
                            ResultSet searchResult = pstmt.executeQuery();
                            if (!searchResult.isBeforeFirst()) {
                                JOptionPane.showMessageDialog(adminSubPanel,
                                        "No results found. Check the Roll Number and try again", "No Results",
                                        JOptionPane.ERROR_MESSAGE);
                            } else {
                                searchResult.next();

                                searchBar.setVisible(false);
                                searchButton.setVisible(false);
                                enterRolLabel.setVisible(false);
                                backButton.setVisible(true);

                                nameLabel.setVisible(true);
                                rollLabel.setVisible(true);
                                courseLabel.setVisible(true);
                                branchLabel.setVisible(true);
                                branchTextArea.setVisible(true);
                                semesterLabel.setVisible(true);
                                semesterChoice.setVisible(true);
                                resultScrollPane.setVisible(false);
                                totalLabel.setVisible(false);

                                // Search the entered Roll Number
                                int RollNo = searchResult.getInt("Roll_No");
                                nameLabel.setText("Student Name : " + searchResult.getString("First_name") + " "
                                        + searchResult.getString("Last_name"));
                                rollLabel.setText("Roll Number : " + RollNo);
                                courseLabel.setText("Course : " + searchResult.getString("Course_Name"));
                                branchTextArea.setText(searchResult.getString("Branch"));

                                // Fetch Number of Sem in Checkbox
                                semesterChoice.removeAll();
                                semesterChoice.add("-- Select Semester --");
                                for (int i = 1; i <= searchResult.getInt("No_of_Semesters"); i++) {
                                    semesterChoice.add("Semester " + i);
                                }

                                semesterChoice.addItemListener(new ItemListener() {
                                    public void itemStateChanged(ItemEvent e) {
                                        if ((semesterChoice.getSelectedIndex() == 0) || (e.getStateChange() == 0)) {
                                            displayResultButton.setVisible(false);
                                            adminSubPanel.repaint();
                                            adminSubPanel.revalidate();
                                        } else {
                                            displayResultButton.setVisible(true);
                                        }
                                    }
                                });

                                // Display new Action Listener
                                displayResultButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        System.out.println("Selected semester: " + semesterChoice.getSelectedIndex());
                                        resultModel.setRowCount(0);
                                        // Fetch Marks
                                        try {
                                            String marksQuery = "SELECT * FROM marks JOIN subjects ON marks.subject_id = subjects.id  WHERE marks.Roll_No = ? AND subjects.semester = ?;";
                                            PreparedStatement pstmt2 = con.prepareStatement(marksQuery);
                                            ;
                                            pstmt2.setInt(1, RollNo);
                                            pstmt2.setInt(2, semesterChoice.getSelectedIndex());
                                            ResultSet result = pstmt2.executeQuery();

                                            while (result.next()) {
                                                String[] row = { result.getString("subjectName"),
                                                        result.getString("marks"), result.getString("letter_grade") };
                                                resultModel.addRow(row);
                                            }

                                            // Find Total of each Semester
                                            String totalQuery = "	SELECT * FROM studentdb.results_total where Roll_No = ? And semester=?;";
                                            PreparedStatement pstmt3 = con.prepareStatement(totalQuery);
                                            pstmt3.setInt(1, RollNo);
                                            pstmt3.setInt(2, semesterChoice.getSelectedIndex());
                                            ResultSet totalResult = pstmt3.executeQuery();
                                            totalResult.next();
                                            totalLabel
                                                    .setText("Total Marks Obtained : " + totalResult.getString("total")
                                                            + "\nGrade : " + totalResult.getString("letter_grade")
                                                            + "\nSGPA : " + totalResult.getString("sgpa"));
                                        } catch (SQLException e1) {
                                            e1.printStackTrace();
                                        }
                                        resultScrollPane.setVisible(true);
                                        totalLabel.setVisible(true);

                                    }
                                });
                            }
                        } catch (SQLException e1) {
                            e1.getStackTrace();
                        }
                    }
                });

                adminSubPanel.add(nameLabel);
                adminSubPanel.add(rollLabel);
                adminSubPanel.add(courseLabel);
                adminSubPanel.add(branchLabel);
                adminSubPanel.add(branchTextArea);
                adminSubPanel.add(semesterLabel);
                adminSubPanel.add(semesterChoice);
                adminSubPanel.add(displayResultButton);
                adminSubPanel.add(resultScrollPane);
                adminSubPanel.add(totalLabel);

                adminSubPanel.add(enterRolLabel);
                adminSubPanel.add(searchBar);
                adminSubPanel.add(searchButton);
                adminSubPanel.add(backButton);

                adminSubPanel.revalidate();
                adminSubPanel.repaint();
            }
        });

        studentMarks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Student Display Marks Triggered");

                adminSubPanel.removeAll();
                adminSubPanel.add(studentMarks);
                adminSubPanel.add(displayResults);
                adminSubPanel.repaint();
                adminSubPanel.revalidate();

            }
        });

        adminSubPanel.add(studentMarks);
        adminSubPanel.add(displayResults);

        adminPanel.add(adminSubPanel);
        adminPanel.revalidate();
        adminPanel.repaint();
    }

    // Method for fees structure

    void feeStructure() {
        adminSubPanel.repaint();
        adminSubPanel.revalidate();

        JLabel course = new JLabel("Course :");
        course.setFont(new Font("Consolas", Font.PLAIN, 16));
        course.setBounds(20, 25, 70, 30);
        adminSubPanel.add(course);

        String[] courseList = { "-- Select Course --", "BCA", "MCA", "BTECH", "BBA", "MBA" };
        JComboBox<String> courseComboBox = new JComboBox<String>(courseList);
        courseComboBox.setBounds(100, 25, 140, 25);
        adminSubPanel.add(courseComboBox);

        JLabel branch = new JLabel("Branch :");
        branch.setFont(new Font("Consolas", Font.PLAIN, 16));
        branch.setBounds(20, 60, 70, 30);

        String[] branchList = { "-- Select Branch --", "Mechanical Engineering",
                "Electronics and Communication Engineering", "Electrical Engineering",
                "Computer Science Engineering", "Civil Engineering" };
        JComboBox<String> branchComboBox = new JComboBox<String>(branchList);
        branchComboBox.setBounds(100, 60, 240, 25);

        DefaultTableModel feesTableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        feesTableModel.addColumn("Semester");
        feesTableModel.addColumn("Fees (in Rs.)");

        JTable feesTable = new JTable(feesTableModel);
        feesTable.setRowHeight(30);
        feesTable.setFont(new Font("Arial", Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(feesTable);
        scrollPane.setBounds(20, 110, 350, 265);
        adminSubPanel.add(scrollPane);
        scrollPane.setVisible(false);

        courseComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (courseComboBox.getSelectedItem().equals("BTECH")) {
                    adminSubPanel.add(branch);
                    adminSubPanel.add(branchComboBox);
                } else {
                    adminSubPanel.remove(branch);
                    adminSubPanel.remove(branchComboBox);
                }
                adminPanel.revalidate();
                adminPanel.repaint();
            }
        });

        // add a button to query
        MyButtonGreen searchButton = new MyButtonGreen("Search");
        searchButton.setBounds(580, 25, 90, 25);
        adminSubPanel.add(searchButton);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get the selected course and branch
                String selectedCourse = (String) courseComboBox.getSelectedItem();
                String selectedBranch = (String) branchComboBox.getSelectedItem();
                String query = "SELECT * FROM fees ,courses WHERE Course_Name='" + selectedCourse
                        + "'and courses.Course_ID=fees.Course_ID";
                if (selectedCourse.equals("BTECH")) {
                    query += " AND Branch='" + selectedBranch + "'";
                }
                try {
                    scrollPane.setVisible(true);
                    feesTableModel.setRowCount(0);
                    ResultSet result = stmt.executeQuery(query);

                    // populate the table model with the results
                    while (result.next()) {
                        int NumOfSemester = result.getInt("No_of_Semesters");

                        for (int i = 1; i <= NumOfSemester; i++) {
                            String[] row = { "Semester " + i, result.getString(i + 1) };
                            feesTableModel.addRow(row);
                        }
                    }
                    adminSubPanel.revalidate();
                    adminSubPanel.repaint();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        adminPanel.add(adminSubPanel);
        adminSubPanel.setVisible(true);
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