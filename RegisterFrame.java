package gui;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import database.DBConnection;

public class RegisterFrame extends JFrame {

    JLabel lblName, lblEmail, lblPassword;
    JTextField txtName, txtEmail;
    JPasswordField txtPassword;
    JButton btnRegister;

    public RegisterFrame() {

        setTitle("Customer Registration");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblName = new JLabel("Name:");
        lblName.setBounds(50, 50, 100, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(150, 50, 150, 25);
        add(txtName);

        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 90, 100, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 90, 150, 25);
        add(txtEmail);

        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 130, 100, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 130, 150, 25);
        add(txtPassword);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(130, 180, 120, 30);
        add(btnRegister);

        btnRegister.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {

                    Connection con = DBConnection.getConnection();

                    String sql =
                            "INSERT INTO customers(name,email,password) VALUES(?,?,?)";

                    PreparedStatement pst =
                            con.prepareStatement(sql);

                    pst.setString(1, txtName.getText());
                    pst.setString(2, txtEmail.getText());
                    pst.setString(3,
                            String.valueOf(txtPassword.getPassword()));

                    int result = pst.executeUpdate();

                    if(result > 0) {
                        JOptionPane.showMessageDialog(null,
                                "Registration Successful");
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
        new RegisterFrame();
    }
}