package Sample;

import java.awt.Color;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class sampleproject {

    Connection conn;
    Statement state;
    ResultSet res;

    sampleproject() {
        showLoginPanel();
        try {

            // conn =
            // DriverManager.getConnection("jdbc:mysql://localhost/Student1","root","");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student1", "root", "sayak007");
            state = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static JFrame index = new JFrame();

    static JPanel login = new JPanel();
    static JPanel welcom = new JPanel();
    static JPanel addStudent = new JPanel();
    static JPanel showStudentList = new JPanel();
    static JPanel addResult = new JPanel();
    static JPanel showResult = new JPanel();
    static JPanel stdResult = new JPanel();

    static JMenuBar menuBar = new JMenuBar();

    static JMenu home = new JMenu("Home");
    static JMenu student = new JMenu("Student");
    static JMenu result = new JMenu("Result");

    static JMenuItem loginmenu = new JMenuItem("Login");
    static JMenuItem exit = new JMenuItem("Exit");
    static JMenuItem sstd = new JMenuItem("Student List");
    static JMenuItem astd = new JMenuItem("Add Student");
    static JMenuItem sres = new JMenuItem("Result List");
    static JMenuItem ares = new JMenuItem("Add Result");
    static JLabel header = new JLabel("Welcome to Student Result Management System\n Please Sign in to Continue");
    static JLabel username = new JLabel("User Name");
    static JLabel password = new JLabel("Password ");
    static JButton submit = new JButton("Log In");
    static JTextField uname = new JTextField();
    static JPasswordField pass = new JPasswordField();
    static JLabel icon = new JLabel();
    static JLabel welcome = new JLabel("Well Come to Student Result Management");
    static JLabel caction = new JLabel("Please Choose your action from the ActionBar");

    public void setIcon() {
        icon.setIcon(new ImageIcon(getClass().getResource("images/logo.png")));
    }

    // Login Panel for Admin
    public void showLoginPanel() {
        setIcon();
        index.setTitle("Login");
        login.setLayout(null);
        icon.setBounds(570, 150, 100, 100);
        header.setBounds(400, 250, 600, 30);
        username.setBounds(500, 300, 100, 30);
        uname.setBounds(600, 300, 150, 30);
        password.setBounds(500, 350, 100, 30);
        pass.setBounds(600, 350, 150, 30);
        submit.setBounds(550, 400, 100, 30);
        login.add(icon);
        login.add(header);
        login.add(username);
        login.add(password);
        login.add(submit);
        login.add(uname);
        login.add(pass);
        login.setBackground(Color.gray);
        login.setVisible(true);
        index.add(login);
        // Add Action Listener to Button
        submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                String aname = uname.getText().toString();
                String apass = new String(pass.getPassword());
                try {
                    adminLogin(aname, apass);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    // Login Method
    private void adminLogin(String aname, String apass) throws SQLException {
        String name;
        String pass;

        try {
            // creating connection with Database

            res = state.executeQuery("select * from admin");
            while (res.next()) {
                name = res.getString(1);
                pass = res.getString(2);
                // Checking if Given Information is correct or not
                if (aname.equals("admin") && apass.equals("admin")) {
                    // JOptionPane.showMessageDialog(rootPane, "User Loged in successfully!");

                    index.setJMenuBar(menuBar);
                    login.setVisible(false);
                    welcome(name);
                } else {
                    JOptionPane.showMessageDialog(index, "Invalid Credentials. Please provide Valid information.");

                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(index, e);
            e.printStackTrace();
        }
    }

    private void welcome(String name) {
        JLabel admin = new JLabel(name);
        index.add(welcom);
        welcome.setBounds(300, 300, 500, 100);
        caction.setBounds(300, 400, 500, 30);
        welcome.setForeground(Color.green);
        admin.setBounds(400, 100, 100, 100);
        welcom.add(admin);
        welcom.add(welcome);
        welcom.add(caction);
        welcom.setLayout(null);
        welcom.setVisible(true);
        loginmenu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                showLoginPanel();
                welcom.setVisible(false);
                showResult.setVisible(false);
                showStudentList.setVisible(false);
                addStudent.setVisible(false);
                addResult.setVisible(false);
                index.setJMenuBar(null);
            }

        });

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                index.dispose();
            }

        });

        sstd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                try {
                    showStudent();
                    welcom.setVisible(false);
                    showResult.setVisible(false);
                    addResult.setVisible(false);
                    addStudent.setVisible(false);
                } catch (SQLException e) {

                    e.printStackTrace();
                }
            }
        });

        astd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                addstd();
                welcom.setVisible(false);
                showResult.setVisible(false);
                addResult.setVisible(false);
                showStudentList.setVisible(false);
            }
        });
        sres.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                try {
                    showres();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                welcom.setVisible(false);
                addStudent.setVisible(false);
                addResult.setVisible(false);
                showStudentList.setVisible(false);
            }
        });

        ares.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                addres();
                welcom.setVisible(false);
                showResult.setVisible(false);
                addStudent.setVisible(false);
                showStudentList.setVisible(false);
            }

        });
    }

    // UI assets for Add Result Panel
    static JLabel lbl = new JLabel("Please Add Student Iformation:");
    static JLabel std_name = new JLabel("Student Name:");
    static JLabel stdf_name = new JLabel("Father Name:");
    static JLabel reg_no = new JLabel("Registration No:");
    static JLabel Department = new JLabel("Department:");
    static JLabel semester = new JLabel("Semester:");

    JTextField name = new JTextField();
    JTextField fname = new JTextField();
    JTextField reg = new JTextField();
    JTextField dep = new JTextField();
    JTextField sem = new JTextField();

    static JButton add = new JButton("Add Student");

    // Method to add result in Database
    protected void addres() {
        JLabel lbl1 = new JLabel("Please Add Result Details:");
        JLabel reg = new JLabel("Student regno:");
        JLabel oop = new JLabel("OOP:");
        JLabel dld = new JLabel("DLD:");
        JLabel isl = new JLabel("Islamiyat:");
        JLabel eng = new JLabel("English:");
        JLabel fa = new JLabel("Financial Accounting:");

        JTextField regno = new JTextField();
        JTextField moop = new JTextField();
        JTextField mdld = new JTextField();
        JTextField misl = new JTextField();
        JTextField meng = new JTextField();
        JTextField mfa = new JTextField();

        JButton add = new JButton("Add Result");
        addResult.setLayout(null);

        lbl1.setBounds(300, 50, 300, 30);
        add.setBounds(300, 500, 300, 30);
        reg.setBounds(100, 100, 200, 30);
        oop.setBounds(100, 150, 200, 30);
        dld.setBounds(100, 200, 200, 30);
        isl.setBounds(100, 250, 200, 30);
        eng.setBounds(100, 300, 200, 30);
        fa.setBounds(100, 350, 200, 30);

        regno.setBounds(400, 100, 200, 30);
        moop.setBounds(400, 150, 200, 30);
        mdld.setBounds(400, 200, 200, 30);
        misl.setBounds(400, 250, 200, 30);
        meng.setBounds(400, 300, 200, 30);
        mfa.setBounds(400, 350, 200, 30);

        addResult.add(lbl1);
        addResult.add(reg);
        addResult.add(oop);
        addResult.add(dld);
        addResult.add(isl);
        addResult.add(eng);
        addResult.add(fa);
        addResult.add(regno);
        addResult.add(moop);
        addResult.add(mdld);
        addResult.add(misl);
        addResult.add(meng);
        addResult.add(mfa);
        addResult.add(add);
        addResult.setBackground(Color.GRAY);
        addResult.setVisible(true);
        index.setTitle("Add Result");
        index.add(addResult);

        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String rn = regno.getText().toString();
                String oops = moop.getText().toString();
                String dlds = mdld.getText().toString();
                String isls = misl.getText().toString();
                String engs = meng.getText().toString();
                String fas = mfa.getText().toString();
                try {
                    res = state.executeQuery("select * from student where regno ='" + rn + "'");
                    while (res.next()) {
                        int sid = res.getInt(1);
                        String qry = "INSERT INTO `result` (`stdid`, `oop`, `dld`, `isl`, `eng`, `fa`) VALUES ('" + sid
                                + "', '" + oops + "', '" + dlds + "', '" + isls + "', '" + engs + "', '" + fas + "')";
                        state.executeUpdate(qry);
                        JOptionPane.showMessageDialog(index, "Result Added Successfully");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(index, "Something Went Wrong", "Error", 3);

                }
            }

        });
    }

    JTable tbl = new JTable();
    String columnName[] = { "Reg No", "Student Name", "Father Name", "Department", "Semester", "OOP", "DLD",
            "Islamiyat", "English", "Financial Accouting" };
    DefaultTableModel modelres = new DefaultTableModel();
    JScrollPane scroll = new JScrollPane(tbl);
    // Method for Show Result

    protected void showres() throws SQLException {
        modelres.setColumnIdentifiers(columnName);
        tbl.setModel(modelres);
        // tbl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl.setFillsViewportHeight(true);

        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // String column[]={"Reg No","NAME","F Name","Department","Semester"};

        res = state.executeQuery(
                "select student.regno as reg,student.name as name, student.fname as fname, student.department as dep,student.semester as sem, result.oop as soop, result.dld as sdld, result.isl as sisl, result.eng as seng, result.fa as sfa from student join result on student.id=result.stdid");
        String sname = "";
        String sfname = "";
        String sreg = "";
        String sdep = "";
        String sses = "";
        String oop = "";
        String dld = "";
        String isl = "";
        String eng = "";
        String fa = "";

        while (res.next()) {
            sreg = res.getString("reg");
            sname = res.getString("name");
            sfname = res.getString("fname");
            sdep = res.getString("dep");
            sses = res.getString("sem");
            oop = res.getString("soop");
            dld = res.getString("sdld");
            isl = res.getString("sisl");
            eng = res.getString("seng");
            fa = res.getString("sfa");

            modelres.addRow(new Object[] { sreg, sname, sfname, sdep, sses, oop, dld, isl, eng, fa });
        }
        showResult.setLayout(null);
        scroll.setBounds(0, 0, 1300, 750);
        showResult.add(scroll);
        showResult.setVisible(true);
        index.setTitle("Result List");
        index.add(showResult);
    }

    // Method for Adding Student in Database

    public void addstd() {
        addStudent.setLayout(null);

        lbl.setBounds(300, 50, 300, 30);
        add.setBounds(300, 500, 300, 30);

        std_name.setBounds(100, 100, 200, 30);
        stdf_name.setBounds(100, 150, 200, 30);
        reg_no.setBounds(100, 200, 200, 30);
        Department.setBounds(100, 250, 200, 30);
        semester.setBounds(100, 300, 200, 30);
        name.setBounds(400, 100, 200, 30);
        fname.setBounds(400, 150, 200, 30);
        reg.setBounds(400, 200, 200, 30);
        dep.setBounds(400, 250, 200, 30);
        sem.setBounds(400, 300, 200, 30);

        addStudent.add(sem);
        addStudent.add(dep);
        addStudent.add(reg);
        addStudent.add(fname);
        addStudent.add(name);
        addStudent.add(semester);
        addStudent.add(Department);
        addStudent.add(reg_no);
        addStudent.add(stdf_name);
        addStudent.add(std_name);
        addStudent.add(add);
        addStudent.setBackground(Color.GRAY);
        addStudent.setVisible(true);
        index.setTitle("Add Result");
        index.add(addStudent);

        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String regno = reg.getText().toString();
                String n = name.getText().toString();
                String fn = fname.getText().toString();
                String d = dep.getText().toString();
                String s = sem.getText().toString();
                try {
                    String querry = "INSERT INTO `student` (`id`, `regno`, `name`, `fname`, `department`, `semester`) VALUES (NULL, '"
                            + regno + "', '" + n + "', '" + fn + "', '" + d + "', '" + s + "')";
                    state.executeUpdate(querry);
                    JOptionPane.showMessageDialog(index, "Student Added Successfully");
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(index, "Something Went Wrong", "Error", 3);
                }
            }

        });
    }

    // Method for Showing All Student Lists
    JTable std = new JTable();
    String columnNames[] = { "Reg No", "Name", "Father Name", "Department", "Semester" };
    DefaultTableModel model = new DefaultTableModel();
    JScrollPane jp = new JScrollPane(std);

    private void showStudent() throws SQLException {

        model.setColumnIdentifiers(columnNames);
        std.setModel(model);
        std.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        std.setFillsViewportHeight(true);

        jp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        res = state.executeQuery("select * from student");
        String sname = "";
        String sfname = "";
        String sreg = "";
        String sdep = "";
        String sses = "";

        while (res.next()) {
            sreg = res.getString("regno");
            sname = res.getString("name");
            sfname = res.getString("fname");
            sdep = res.getString("department");
            sses = res.getString("semester");
            model.addRow(new Object[] { sreg, sname, sfname, sdep, sses });
        }
        showStudentList.setLayout(null);
        jp.setBounds(0, 0, 1250, 750);
        showStudentList.add(jp);
        showStudentList.setVisible(true);
        index.setTitle("Student List");
        index.add(showStudentList);
    }

    // main method

    public static void main(String[] args) {
        sampleproject std = new sampleproject();
        home.add(loginmenu);
        home.add(exit);
        student.add(sstd);
        student.add(astd);
        result.add(sres);
        result.add(ares);

        menuBar.add(home);
        menuBar.add(student);
        menuBar.add(result);

        index.setVisible(true);
        index.setSize(1300, 750);
        index.setTitle("Student Result");
        index.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
