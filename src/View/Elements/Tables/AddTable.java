package View.Elements.Tables;

import Controller.OrderController;
import Controller.TableController;
import Model.Orders;
import Model.Product;
import Model.Table;
import View.Pages.TablesPage;
import java.awt.Font;
import java.util.HashMap;
import javax.swing.*;

public class AddTable {

    TableController tableController;

    public AddTable(TablesPage tablesPage) {

        JFrame frame = new JFrame("Add Table");
        frame.setBounds(400, 300, 400, 300);
        frame.setResizable(false);
        frame.setLayout(null);

        String[] arr = {"1", "2", "4", "6", "8"};
        JLabel lbl1 = new JLabel("Capacity: ");
        lbl1.setBounds(80, 75, 100, 30);
        JComboBox<String> capacity = new JComboBox<>(arr);
        capacity.setBounds(200, 75, 100, 30);
        JLabel lbl2 = new JLabel("Table Number: ");
        lbl2.setBounds(80, 120, 100, 30);

        JTextField tf1 = new JTextField(0);
        tf1.setBounds(200, 120, 100, 30);

        JButton addTable = new JButton("Add Table");
        addTable.setBounds(110, 180, 180, 40);

        addTable.addActionListener(e -> {
            tableController = new TableController();
            if (!tableController.checkTableExist(Integer.parseInt(tf1.getText()))) {
                Table table = new Table(Integer.parseInt(tf1.getText()), Integer.parseInt((String) capacity.getSelectedItem()));
                table.addActionListener(e1 -> {
                    OrderController orderController = new OrderController();
                    Orders activeOrder = orderController.getOrderByTableNumber(table.getTableNumber());
                    table.setOrders(activeOrder);
                    tablesPage.informations.setText("");
                    try {
                        tablesPage.informations.setText("");
                        tablesPage.informations.append("Statement:   " + table.getStatement() + "\n");
                        tablesPage.informations.append("Capacity:   " + table.getCapacity() + "\n");
                        tablesPage.informations.append("\nOrders: \n");

                        HashMap<Product, Integer> products = activeOrder.getProductAndQuantity();
                        for (Product tempProduct : products.keySet()) {
                            tablesPage.informations.append("  " + tempProduct.getProductName() + " ---- x" + products.get(tempProduct) + " ---- " + "$" + products.get(tempProduct) * tempProduct.getPrice() + "\n");
                        }
                        table.getOrders().calculateTotalCost();
                        tablesPage.totalCostLabel.setText("Total Cost: " + table.getOrders().getTotalCost() + "$");
                    } catch (java.lang.Exception a) {
                        tablesPage.informations.setText("");
                        tablesPage.informations.append("Statement: " + table.getStatement() + "\n");
                        tablesPage.informations.append("Capacity:   " + table.getCapacity() + "\n");
                        tablesPage.informations.append("\nOrders: \n\n");
                    }
                    tablesPage.selectedTable = table;
                });
                tableController.addTable(table.getTableNumber(), table.getCapacity());

                table.setFont(new Font("Arial", Font.BOLD, 18));
                tablesPage.informations.setText("");
                tablesPage.informations.append("Statement:   " + "Unknown" + "\n");
                tablesPage.informations.append("Capacity: " + "Unkown\n");
                tablesPage.informations.append("\nOrders: \n");
                tablesPage.selectedTable = table;
                tablesPage.tablePanel.add(table);
                tablesPage.setVisible(false);
                tablesPage.setVisible(true);
                JOptionPane.showMessageDialog(frame, "Table has been added.");

            } else {
                JOptionPane.showMessageDialog(addTable, "This numbered table is also exist!");
            }
        });

        frame.add(addTable);
        frame.add(capacity);
        frame.add(lbl1);
        frame.add(lbl2);
        frame.add(tf1);
        frame.setVisible(true);
    }
}
