package View.Elements.Tables;

import Controller.OrderController;
import Controller.ProductController;
import Controller.TableController;
import Model.Orders;
import Model.Product;
import Model.Table;
import View.Pages.TablesPage;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

public class OrderEnter {

    private ArrayList<Product> foods;
    private ArrayList<Product> menus;
    private ArrayList<Product> drinks;
    private ArrayList<Product> desserts;
    private ArrayList<Product> salads;
    private ArrayList<Product> extras;
    private ProductController productController = new ProductController();
    private OrderController orderController = new OrderController();
    private TableController tableController = new TableController();
    public OrderEnter(TablesPage tablesPage) {
        

        foods = productController.getAllFoods();
        menus = productController.getAllMenus();
        drinks = productController.getAllDrinks();
        desserts = productController.getAllDesserts();
        salads = productController.getAllSalads();
        extras = productController.getAllExtras();
        
        
        JFrame frame = new JFrame("Enter Order");
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLayout(null);
        JLabel tableName = new JLabel("Table-" + tablesPage.selectedTable.getText());
        tableName.setFont(new Font("Arial", Font.BOLD, 30));
        tableName.setBounds(330, 20, 300, 60);

        //--------------------SELECT ORDER TYPE
        JPanel orderType = new JPanel();
        orderType.setBounds(40, 100, 230, 280);
        TitledBorder border1 = new TitledBorder("Menu");
        orderType.setBorder(border1);
        orderType.setLayout(new GridLayout(3, 2, 10, 15));
        JButton foodsButton = new JButton("Foods");
        JButton menusButton = new JButton("Menus");
        JButton drinksButton = new JButton("Drinks");
        JButton desertsButton = new JButton("Deserts");
        JButton saladsButton = new JButton("Salads");
        JButton extrasButton = new JButton("Extras");
        orderType.add(foodsButton);
        orderType.add(menusButton);
        orderType.add(drinksButton);
        orderType.add(desertsButton);
        orderType.add(saladsButton);
        orderType.add(extrasButton);

        //-------------------------------PANEL-2  SELECTING FOOD-DRINK
        JPanel selectingPanel = new JPanel();
        selectingPanel.setBounds(300, 100, 450, 170);
        TitledBorder border2 = new TitledBorder("Select Product");
        selectingPanel.setBorder(border2);
        selectingPanel.setLayout(null);

        DefaultTableModel model1 = new DefaultTableModel(new String[]{"Product Name", "Price"}, 0);
        JTable table = new JTable(model1);
        JScrollPane selectScrollPane = new JScrollPane(table);
        selectScrollPane.setBounds(10, 20, 300, 140);
        table.getTableHeader().setBackground(Color.white);

        JLabel label1 = new JLabel("Quantity: ");
        label1.setBounds(320, 40, 130, 30);

        String[] quantities = new String[10];
        for (int i = 1; i <= 10; i++) {
            quantities[i - 1] = Integer.toString(i);
        }
        JComboBox<Integer> quantity = new JComboBox(quantities);
        quantity.setBounds(320, 70, 90, 30);
        JButton addButton = new JButton("Add");
        addButton.setBounds(320, 110, 110, 40);
        selectingPanel.add(label1);
        selectingPanel.add(quantity);
        selectingPanel.add(addButton);
        selectingPanel.add(selectScrollPane);
        //-------------------------------ORDERED PRODUCTS LİST

        JPanel ordersPanel = new JPanel();
        ordersPanel.setBounds(300, 270, 450, 250);
        TitledBorder border3 = new TitledBorder("Orders");
        ordersPanel.setBorder(border3);
        ordersPanel.setLayout(null);

        DefaultTableModel model2 = new DefaultTableModel(new String[]{"Product Name", "Price", "Quantity"}, 0);
        JTable orderedProductTable = new JTable(model2);
        JScrollPane orderScrollPane = new JScrollPane(orderedProductTable);
        orderScrollPane.setBounds(15, 20, 415, 140);

        JButton resetOrders = new JButton("Reset Orders");
        resetOrders.setBounds(20, 180, 180, 40);
        JButton deleteOrder = new JButton("Delete Order");
        deleteOrder.setBounds(240, 180, 180, 40);

        ordersPanel.add(orderScrollPane);
        ordersPanel.add(resetOrders);
        ordersPanel.add(deleteOrder);

//--------------------------------BUTTONS AND COMBOBOX
        JButton applyButton = new JButton("Apply Order");
        applyButton.setBounds(40, 420, 180, 40);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(40, 475, 180, 40);
        foodsButton.addActionListener(e -> {
            model1.setRowCount(0);
            for (Product food : foods) {
                String[] arr = new String[]{food.getProductName(), Double.toString(food.getPrice()) + " $"};
                model1.addRow(arr);
            }
        });
        menusButton.addActionListener(e -> {
            model1.setRowCount(0);
            for (Product menu : menus) {
                String[] arr = new String[]{menu.getProductName(), Double.toString(menu.getPrice()) + " $"};
                model1.addRow(arr);
            }
        });
        drinksButton.addActionListener(e -> {
            model1.setRowCount(0);
            for (Product drink : drinks) {
                String[] arr = new String[]{drink.getProductName(), Double.toString(drink.getPrice()) + " $"};
                model1.addRow(arr);
            }
        });
        desertsButton.addActionListener(e -> {
            model1.setRowCount(0);
            for (Product dessert : desserts) {
                String[] arr = new String[]{dessert.getProductName(), Double.toString(dessert.getPrice()) + " $"};
                model1.addRow(arr);
            }
        });
        saladsButton.addActionListener(e -> {
            model1.setRowCount(0);
            for (Product salad : salads) {
                String[] arr = new String[]{salad.getProductName(), Double.toString(salad.getPrice()) + " $"};
                model1.addRow(arr);
            }
        });
        extrasButton.addActionListener(e -> {
            model1.setRowCount(0);
            for (Product extra : extras) {
                String[] arr = new String[]{extra.getProductName(), Double.toString(extra.getPrice()) + " $"};
                model1.addRow(arr);
            }
        });

        addButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String[] row = new String[]{String.valueOf(model1.getValueAt(selectedRow, 0)),
                    String.valueOf(model1.getValueAt(selectedRow, 1))};
                model2.addRow(new String[]{row[0], row[1], (String) quantity.getSelectedItem()});
            } else {
                System.out.print("");
            }
        });
        resetOrders.addActionListener(e -> {
            model2.setRowCount(0);
        });
        deleteOrder.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                model2.removeRow(selectedRow);
                table.setVisible(false);
                table.setVisible(true);
            } else {
                System.out.print("");
            }
        });

        
        applyButton.addActionListener(e -> {
            if(tablesPage.selectedTable.getStatement().equals("Free")){
            Orders orders = new Orders(tablesPage.selectedTable.getTableNumber());
            Product product;
            for (int k = 0; k < model2.getRowCount(); k++) {
                String price = String.valueOf(orderedProductTable.getValueAt(k, 1));
                int productQuantity = Integer.parseInt((String) orderedProductTable.getValueAt(k, 2));
                product = productController.getProductByName(String.valueOf(orderedProductTable.getValueAt(k, 0)));
                orders.addProductAndQuantity(product, productQuantity);
                orders.calculateTotalCost();
            }
            tablesPage.selectedTable.setOrders(orders);
            tablesPage.selectedTable.setStatement("Full");
            tableController.updateTableStatement(tablesPage.selectedTable.getTableNumber(), "Full");
            tablesPage.informations.setVisible(false);
            tablesPage.informations.setVisible(true);
            tablesPage.selectedTable.setBackground(Color.red);
            orderController.enterOrder(orders);
            try {
                tablesPage.informations.setText("");
                tablesPage.informations.append("Statement:   " + tablesPage.selectedTable.getStatement() + "\n");
                tablesPage.informations.append("\nOrders: \n");
                
                HashMap<Product, Integer> products = tablesPage.selectedTable.getOrders().getProductAndQuantity();
                for (Product tempProduct : products.keySet()) {
                    tablesPage.informations.append("  " +tempProduct.getProductName() + " ---- x" + products.get(tempProduct) + " ---- " + "$" + products.get(tempProduct)*tempProduct.getPrice() + "\n");
                }
            } catch (java.lang.Exception a) {
                tablesPage.informations.setText("");
                tablesPage.informations.append("Statement: " + tablesPage.
                        selectedTable.getStatement() + "\n");
                tablesPage.informations.append("\nOrders: \n\n");
            }
            model1.setRowCount(0);
            JOptionPane.showMessageDialog(ordersPanel, "Order Applied!");
            model2.setRowCount(0);
            }else JOptionPane.showMessageDialog(ordersPanel, "This table already has an order. ");
        });
        frame.add(selectingPanel);
        frame.add(applyButton);
        frame.add(cancelButton);
        frame.add(ordersPanel);
        frame.add(tableName);
        frame.add(orderType);

        frame.setVisible(true);
    }
}
