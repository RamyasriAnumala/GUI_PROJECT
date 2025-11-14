package Retails_Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Retail_shop extends JFrame implements ActionListener {
	

	    JLabel title, l1, l2, l3, l4, l5;
	    JTextField t1, t2, t3, t4, t5;
	    JButton addBtn, clearBtn, exitBtn, saveBtn;
	    JTextArea billArea;
	    double total = 0;

	    public Retail_shop() {

	        setTitle("Retail Shop Management System");
	        setSize(750, 600);
	        setLayout(null);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        getContentPane().setBackground(new Color(230, 230, 250));

	        // Title
	        title = new JLabel("🛍️ Retail Shop Billing System");
	        title.setFont(new Font("Arial", Font.BOLD, 22));
	        title.setBounds(200, 20, 400, 30);
	        add(title);

	        // Labels
	        l1 = new JLabel("Product Name:");
	        l1.setBounds(50, 80, 120, 25);
	        add(l1);

	        l2 = new JLabel("Product ID:");
	        l2.setBounds(50, 120, 120, 25);
	        add(l2);

	        l3 = new JLabel("Price (₹):");
	        l3.setBounds(50, 160, 120, 25);
	        add(l3);

	        l4 = new JLabel("Quantity:");
	        l4.setBounds(50, 200, 120, 25);
	        add(l4);

	        l5 = new JLabel("Total Amount:");
	        l5.setBounds(50, 240, 120, 25);
	        add(l5);

	        // Text Fields
	        t1 = new JTextField();
	        t1.setBounds(180, 80, 150, 25);
	        add(t1);

	        t2 = new JTextField();
	        t2.setBounds(180, 120, 150, 25);
	        add(t2);

	        t3 = new JTextField();
	        t3.setBounds(180, 160, 150, 25);
	        add(t3);

	        t4 = new JTextField();
	        t4.setBounds(180, 200, 150, 25);
	        add(t4);

	        t5 = new JTextField();
	        t5.setBounds(180, 240, 150, 25);
	        t5.setEditable(false);
	        add(t5);

	        // Buttons
	        addBtn = new JButton("Add to Bill");
	        addBtn.setBounds(50, 300, 120, 30);
	        addBtn.addActionListener(this);
	        add(addBtn);

	        clearBtn = new JButton("Clear");
	        clearBtn.setBounds(190, 300, 80, 30);
	        clearBtn.addActionListener(this);
	        add(clearBtn);

	        saveBtn = new JButton("Save Bill");
	        saveBtn.setBounds(50, 350, 100, 30);
	        saveBtn.addActionListener(this);
	        add(saveBtn);

	        exitBtn = new JButton("Exit");
	        exitBtn.setBounds(190, 350, 80, 30);
	        exitBtn.addActionListener(this);
	        add(exitBtn);

	        // Bill area
	        billArea = new JTextArea();
	        billArea.setBounds(380, 80, 330, 400);
	        billArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
	        billArea.setEditable(false);
	        billArea.setText("******** RETAIL SHOP BILL ********\n");
	        add(billArea);

	        setVisible(true);
	    }

	    public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == addBtn) {
	            try {
	                String name = t1.getText();
	                String id = t2.getText();
	                double price = Double.parseDouble(t3.getText());
	                int qty = Integer.parseInt(t4.getText());
	                double amt = price * qty;
	                total += amt;

	                t5.setText("₹ " + amt);

	                billArea.append("\nProduct: " + name + 
	                                "\nID: " + id + 
	                                "\nQty: " + qty + 
	                                "\nAmount: ₹" + amt + 
	                                "\n--------------------------\n");

	                // clear input fields except total
	                t1.setText("");
	                t2.setText("");
	                t3.setText("");
	                t4.setText("");

	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(this, "Please enter valid details!");
	            }

	        } else if (e.getSource() == clearBtn) {
	            t1.setText("");
	            t2.setText("");
	            t3.setText("");
	            t4.setText("");
	            t5.setText("");
	        } 
	        else if (e.getSource() == saveBtn) {
	            try {
	                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
	                String filename = "Bill_" + dtf.format(LocalDateTime.now()) + ".txt";

	                FileWriter fw = new FileWriter(filename);
	                fw.write(billArea.getText());
	                fw.write("\nGrand Total: ₹" + total);
	                fw.close();

	                JOptionPane.showMessageDialog(this, "Bill saved as " + filename);

	            } catch (IOException ex) {
	                JOptionPane.showMessageDialog(this, "Error saving bill!");
	            }
	        } 
	        else if (e.getSource() == exitBtn) {
	            JOptionPane.showMessageDialog(this, "Total Bill Amount: ₹" + total + "\nThank you for shopping!");
	            System.exit(0);
	        }
	    }

	    public static void main(String[] args) {
	        new Retail_shop();
	    }
	}



