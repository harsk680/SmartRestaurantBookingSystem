package gui;

import javax.swing.*;
import java.sql.*;

import database.DBConnection;

public class GenerateBillFrame extends JFrame {

    JTextArea billArea;
    JTextField txtTable;
    JButton generateBtn;

    public GenerateBillFrame() {

        setTitle("Generate Bill");
        setSize(500,450);
        setLayout(null);

        JLabel lbl = new JLabel("Table Number");
        lbl.setBounds(20,20,100,25);
        add(lbl);

        txtTable = new JTextField();
        txtTable.setBounds(130,20,100,25);
        add(txtTable);

        billArea = new JTextArea();
        billArea.setBounds(20,60,440,280);
        add(billArea);

        generateBtn = new JButton("Generate Bill");
        generateBtn.setBounds(150,360,150,30);
        add(generateBtn);

        generateBtn.addActionListener(e -> generateBill());

        setVisible(true);
    }

    private void generateBill() {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement pst =
                    con.prepareStatement(
                    "SELECT * FROM orders WHERE table_id=?");

            pst.setInt(1,
                    Integer.parseInt(txtTable.getText()));

            ResultSet rs = pst.executeQuery();

            double grandTotal = 0;

            billArea.setText("");

            billArea.append("===== RESTAURANT BILL =====\n\n");

            while(rs.next()) {

                String item =
                        rs.getString("item_name");

                int qty =
                        rs.getInt("quantity");

                double total =
                        rs.getDouble("total_price");

                grandTotal += total;

                billArea.append(
                        item + " x " + qty +
                        " = ₹" + total + "\n");
            }

            double gst = grandTotal * 0.18;
            double finalBill = grandTotal + gst;

            billArea.append("\n----------------------\n");
            billArea.append("Total : ₹" + grandTotal + "\n");
            billArea.append("GST (18%) : ₹" + gst + "\n");
            billArea.append("Final Bill : ₹" + finalBill);

            con.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}