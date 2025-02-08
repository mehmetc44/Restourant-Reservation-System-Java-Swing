package View.Pages;

import Controller.OrderController;
import Controller.TableController;
import Model.Orders;
import Model.Product;
import Model.Table;
import View.Elements.Tables.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class TablesPage extends JPanel {

    public Table selectedTable = new Table(1, 2);
    public JPanel tablePanel;
    public JTextArea informations = new JTextArea();
    public JLabel totalCostLabel = new JLabel("Total Cost: 0.00$");
    private Table table;
    private TableController tableController = new TableController();
    private ArrayList<Table> allTables;

    public TablesPage() {

        this.setLayout(null);
        this.setBounds(0, 0, 1000, 800);
        this.setBackground(Color.white);
        tablePanel = new JPanel();
        tablePanel.setBounds(50, 50, 860, 420);
        tablePanel.setLayout(new GridLayout(3, 5, 30, 50));
        informations.setBounds(20, 20, 415, 220);
        informations.setLineWrap(true);
        informations.setWrapStyleWord(true);
        informations.setFont(new Font("Arial", Font.BOLD, 13));
        informations.setEditable(false);
        informations.setMargin(new Insets(20, 20, 20, 20));
        informations.append("Statement:   " +"Unknown" + "\n");
        informations.append("Capacity: " + "Unkown\n");
        informations.append("\nOrders: \n");
        totalCostLabel.setBounds(500, 200, 150, 40);

        TableController tableController = new TableController();
        allTables = tableController.getAllTables();

        for (Table table : allTables) {
            table.setFont(new Font("Arial", Font.BOLD, 18)); 
            if(table.statement.equals("Full")) {
                table.setBackground(Color.red);
            }
            
            table.addActionListener(e -> {
                OrderController orderController = new OrderController();
                Orders activeOrder = orderController.getOrderByTableNumber(table.getTableNumber());
                table.setOrders(activeOrder);
                informations.setText("");
                try {
                    this.informations.setText("");
                    this.informations.append("Statement:   " + table.getStatement() + "\n");
                    this.informations.append("Capacity:   " + table.getCapacity() + "\n");
                    this.informations.append("\nOrders: \n");
                    
                    HashMap<Product, Integer> products = activeOrder.getProductAndQuantity();
                    for (Product tempProduct : products.keySet()) {
                        this.informations.append("  " + tempProduct.getProductName() + " ---- x" + products.get(tempProduct) + " ---- " + "$" + products.get(tempProduct) * tempProduct.getPrice() + "\n");
                    }
                    table.getOrders().calculateTotalCost();
                    this.totalCostLabel.setText("Total Cost: "+table.getOrders().getTotalCost()+"$");
                } catch (java.lang.Exception a) {
                    this.informations.setText("");
                    this.informations.append("Statement: " + table.getStatement() + "\n");
                    this.informations.append("Capacity:   " + table.getCapacity() + "\n");
                    this.informations.append("\nOrders: \n\n");
                }
                selectedTable = table;
            });
            tablePanel.add(table);
        }

        JPanel tableInformations = new JPanel();
        tableInformations.setBounds(5, 500, 970, 300);
        TitledBorder border1 = new TitledBorder("Table Information");
        tableInformations.setBorder(border1);
        tableInformations.setLayout(null);


        
        JButton enterOrderButton = new JButton("Enter Order");
        enterOrderButton.setBounds(500, 50, 150, 40);
        JButton payButton = new JButton("Pay");
        payButton.setBounds(500, 100, 150, 40);

        JButton editTableButton = new JButton("Edit Table");
        editTableButton.setBounds(700, 100, 150, 40);
        JButton addTableButton = new JButton("Add Table");
        addTableButton.setBounds(700, 50, 150, 40);
        JButton deleteTable = new JButton("Delete Table");
        deleteTable.setBounds(700, 150, 150, 40);

        enterOrderButton.addActionListener(e -> {
            if(selectedTable.getStatement().equals("Free")){
                OrderEnter orderEnter = new OrderEnter(this);
            }else{
                JOptionPane.showMessageDialog(this, "This table already has an order. ");
            }
           
        });
        addTableButton.addActionListener(e -> {
            new AddTable(this);
        });
        editTableButton.addActionListener(e -> {
            new EditTable(this);
        });
        payButton.addActionListener(e -> {
            if(!selectedTable.getStatement().equals("Free")){
                new Payment(this);
            }else{
                JOptionPane.showMessageDialog(this, "There must be an oreder in this table before pay.");
            }
        });
        deleteTable.addActionListener(e -> {
            int answer = JOptionPane.showConfirmDialog(null,
                    "Do you want to delete table " + selectedTable.getTableNumber() + "?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (answer == JOptionPane.YES_OPTION) {

                tablePanel.remove(selectedTable);
                tablePanel.setVisible(false);
                tablePanel.setVisible(true);
                tableController.deleteTable(selectedTable.getTableNumber());
                JOptionPane.showMessageDialog(this, "Table has been deleted.");
            }
        });
        tableInformations.add(editTableButton);
        tableInformations.add(deleteTable);
        tableInformations.add(enterOrderButton);
        tableInformations.add(addTableButton);
        tableInformations.add(payButton);
        tableInformations.add(informations);
        tableInformations.add(totalCostLabel);
        this.add(tableInformations);
        this.add(tablePanel);
    }
}
