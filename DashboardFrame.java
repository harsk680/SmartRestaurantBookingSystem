package gui;
import gui.BookTableFrame;
import gui.ViewBookingFrame;
import gui.OrderFoodFrame;
import gui.GenerateBillFrame;

import javax.swing.*;

public class DashboardFrame extends JFrame {

    JButton btnBookTable;
    JButton btnViewBooking;
    JButton btnOrderFood;
    JButton btnGenerateBill;

    public DashboardFrame() {

        setTitle("Smart Restaurant Dashboard");
        setSize(500,400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel heading =
                new JLabel("Welcome To Smart Restaurant");

        heading.setBounds(130,30,250,30);
        add(heading);

        btnBookTable = new JButton("Book Table");
        btnBookTable.setBounds(150,80,180,35);
        add(btnBookTable);

        btnViewBooking = new JButton("View Booking");
        btnViewBooking.setBounds(150,130,180,35);
        add(btnViewBooking);

        btnOrderFood = new JButton("Order Food");
        btnOrderFood.setBounds(150,180,180,35);
        add(btnOrderFood);

        btnGenerateBill = new JButton("Generate Bill");
        btnGenerateBill.setBounds(150,230,180,35);
        add(btnGenerateBill);
        
        btnBookTable.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Book Table Clicked");
        });

        btnViewBooking.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "View Booking Clicked");
        });

        btnOrderFood.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Order Food Clicked");
        });

        btnGenerateBill.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Generate Bill Clicked");
        });
        add(btnGenerateBill);

        btnBookTable.addActionListener(e -> {
            new BookTableFrame();
        });

        btnViewBooking.addActionListener(e -> {
            new ViewBookingFrame();
        });
        
        btnOrderFood.addActionListener(e -> {
            new OrderFoodFrame();
        });

        btnGenerateBill.addActionListener(e -> {
            new GenerateBillFrame();
        });

        

        setVisible(true);
    }

    public static void main(String[] args) {
        new DashboardFrame();
    }
}