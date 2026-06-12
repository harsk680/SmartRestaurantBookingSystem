package gui;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

import database.DBConnection;

public class OrderFoodFrame extends JFrame {

	JTextField txtName;
	JTextField txtTable;
    JComboBox<String> itemBox;
    JTextField txtQuantity;
    JButton btnOrder;

    public OrderFoodFrame() {

        setTitle("Order Food");
        setSize(400,300);
        setLayout(null);

        JLabel lbl1 = new JLabel("Customer Name");
        lbl1.setBounds(30,30,120,25);
        add(lbl1);

        txtName = new JTextField();
        txtName.setBounds(160,30,150,25);
        add(txtName);
        JLabel lblTable = new JLabel("Table Number");
        lblTable.setBounds(30,80,120,25);
        add(lblTable);

        txtTable = new JTextField();
        txtTable.setBounds(160,80,150,25);
        add(txtTable);

        JLabel lbl2 = new JLabel("Food Item");
        lbl2.setBounds(30,120,120,25);
        add(lbl2);

        itemBox = new JComboBox<>();

        itemBox.addItem("Pizza");
        itemBox.addItem("Burger");
        itemBox.addItem("Pasta");
        itemBox.addItem("Cold Drink");

        itemBox.setBounds(160,120,150,25);;
        add(itemBox);

        JLabel lbl3 = new JLabel("Quantity");
        lbl3.setBounds(30,170,120,25);
        add(lbl3);

        txtQuantity = new JTextField();
        txtQuantity.setBounds(160,170,150,25);
        add(txtQuantity);

        btnOrder = new JButton("Place Order");
        btnOrder.setBounds(120,230,140,30);
        add(btnOrder);

        btnOrder.addActionListener(e -> placeOrder());

        setVisible(true);
    }

    private void placeOrder() {

        try {

            String item = (String)itemBox.getSelectedItem();

            int price = 0;

            if(item.equals("Pizza"))
                price = 200;
            else if(item.equals("Burger"))
                price = 150;
            else if(item.equals("Pasta"))
                price = 250;
            else if(item.equals("Cold Drink"))
                price = 50;

            int qty = Integer.parseInt(txtQuantity.getText());

            double total = price * qty;

            Connection con = DBConnection.getConnection();

            String sql =
            		"INSERT INTO orders(customer_name,table_id,item_name,quantity,total_price) VALUES(?,?,?,?,?)";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setString(1, txtName.getText());
            pst.setInt(2, Integer.parseInt(txtTable.getText()));
            pst.setString(3, item);
            pst.setInt(4, qty);
            pst.setDouble(5, total);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,
                    "Order Placed\nTotal Amount = ₹" + total);

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new OrderFoodFrame();
    }
}