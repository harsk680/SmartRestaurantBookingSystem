package gui;
import gui.DashboardFrame;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

import database.DBConnection;

public class LoginFrame extends JFrame {

    JLabel lblEmail, lblPassword;
    JTextField txtEmail;
    JPasswordField txtPassword;
    JButton btnLogin;

    public LoginFrame() {

        setTitle("Customer Login");
        setSize(400,250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50,50,100,25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150,50,150,25);
        add(txtEmail);

        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50,100,100,25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150,100,150,25);
        add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(130,150,100,30);
        add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {

                    Connection con =
                            DBConnection.getConnection();

                    String sql =
                            "SELECT * FROM customers WHERE email=? AND password=?";

                    PreparedStatement pst =
                            con.prepareStatement(sql);

                    pst.setString(1, txtEmail.getText());
                    pst.setString(2,
                            String.valueOf(txtPassword.getPassword()));

                    ResultSet rs = pst.executeQuery();

                    if(rs.next()) {

                        JOptionPane.showMessageDialog(null,
                                "Login Successful");

                        dispose();

                        new DashboardFrame();

                    }

                     else {

                        JOptionPane.showMessageDialog(null,
                                "Invalid Email or Password");
                    }

                    con.close();

                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {

        new LoginFrame();
    }
}