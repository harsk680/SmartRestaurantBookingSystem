package gui;

import javax.swing.*;
import java.sql.*;
import database.DBConnection;
import service.QRGenerator;

public class BookTableFrame extends JFrame {

    JComboBox<Integer> tableBox;
    JTextField dateField;
    JTextField timeField;
    JTextField nameField;
    JButton bookBtn;

    public BookTableFrame() {

        setTitle("Book Table");
        setSize(400,300);
        setLayout(null);

        JLabel lbl1 = new JLabel("Customer Name");
        lbl1.setBounds(30,30,120,25);
        add(lbl1);

        nameField = new JTextField();
        nameField.setBounds(160,30,150,25);
        add(nameField);

        JLabel lbl2 = new JLabel("Table Number");
        lbl2.setBounds(30,70,120,25);
        add(lbl2);

        tableBox = new JComboBox<>();
        for(int i = 1; i <= 5; i++) {
            tableBox.addItem(i);
        }

        tableBox.setBounds(160,70,150,25);
        add(tableBox);

        JLabel lbl3 = new JLabel("Date");
        lbl3.setBounds(30,110,120,25);
        add(lbl3);

        dateField = new JTextField("2026-06-08");
        dateField.setBounds(160,110,150,25);
        add(dateField);

        JLabel lbl4 = new JLabel("Time");
        lbl4.setBounds(30,150,120,25);
        add(lbl4);

        timeField = new JTextField("7:00 PM");
        timeField.setBounds(160,150,150,25);
        add(timeField);

        bookBtn = new JButton("Book Now");
        bookBtn.setBounds(120,210,120,30);
        add(bookBtn);

        bookBtn.addActionListener(e -> saveBooking());

        setVisible(true);
    }
    
    private void saveBooking() {

        try {

            Connection con = DBConnection.getConnection();

            

            String checkSql =
            "SELECT * FROM table_bookings WHERE table_id=? AND booking_date=? AND booking_time=?";

            PreparedStatement checkStmt =
            con.prepareStatement(checkSql);

            checkStmt.setInt(1, (Integer) tableBox.getSelectedItem());
            checkStmt.setString(2, dateField.getText());
            checkStmt.setString(3, timeField.getText());

            ResultSet rs = checkStmt.executeQuery();

            if(rs.next()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Table already booked for this date and time!");

                con.close();
                return;
            }

            String sql =
            "INSERT INTO table_bookings(customer_name,table_id,booking_date,booking_time) VALUES(?,?,?,?)";

            PreparedStatement pst =
            con.prepareStatement(sql);

            pst.setString(1, nameField.getText());
            pst.setInt(2, (Integer) tableBox.getSelectedItem());
            pst.setString(3, dateField.getText());
            pst.setString(4, timeField.getText());

            pst.executeUpdate();

            String qrData =
            		"Customer: " + nameField.getText()
            		+ "\nTable: " + tableBox.getSelectedItem()
            		+ "\nDate: " + dateField.getText()
            		+ "\nTime: " + timeField.getText();

            		service.QRGenerator.generateQR(
            		        qrData,
            		        "booking_qr.png");

            		pst.executeUpdate();

            		String qrData1 =
            		        "Customer: " + nameField.getText()
            		        + "\nTable: " + tableBox.getSelectedItem()
            		        + "\nDate: " + dateField.getText()
            		        + "\nTime: " + timeField.getText();

            		QRGenerator.generateQR(
            		        qrData1,
            		        "booking_qr.png");

            		JOptionPane.showMessageDialog(
            		        null,
            		        "Table Booked Successfully\nQR Generated");

            		con.close();

        } catch(Exception ex) {

            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BookTableFrame();
    }
}