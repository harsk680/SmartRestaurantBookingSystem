package gui;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

import database.DBConnection;

public class ViewBookingFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewBookingFrame() {

        setTitle("View Bookings");
        setSize(600,400);

        model = new DefaultTableModel();

        model.addColumn("Booking ID");
        model.addColumn("Customer Name");
        model.addColumn("Table ID");
        model.addColumn("Date");
        model.addColumn("Time");

        table = new JTable(model);

        add(new JScrollPane(table));

        loadBookings();

        setVisible(true);
    }

    private void loadBookings() {

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery("SELECT * FROM table_bookings");

            while(rs.next()) {

                model.addRow(new Object[] {

                    rs.getInt("booking_id"),
                    rs.getString("customer_name"),
                    rs.getInt("table_id"),
                    rs.getDate("booking_date"),
                    rs.getString("booking_time")
                });
            }

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}