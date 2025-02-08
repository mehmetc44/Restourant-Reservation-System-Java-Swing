package View.Elements.Tables;

import Controller.OrderController;
import Controller.TableController;
import Model.*;
import View.Pages.TablesPage;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class Payment {
    private double totalOrderCost=0;
    private double discount=0;
    private boolean statement=false;
    private String paymentMethod="";
    private TableController tableController = new TableController();
    public Payment(TablesPage tablesPage){
        Orders order = tablesPage.selectedTable.getOrders();
        HashMap<Product,Integer> allProducts = tablesPage.selectedTable.getOrders().getProductAndQuantity();
        
        JFrame frame = new JFrame("Payment");
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLayout(null);
        
        
        //------------------ORDERS 
        DefaultTableModel model1 = new DefaultTableModel(new String[]{"Product Name", "Price","Quantity"}, 0);
        JTable table = new JTable(model1);
        JScrollPane ordersScrollPane = new JScrollPane(table);
        ordersScrollPane.setBounds(30, 20, 500, 300);
        
        if(tablesPage.selectedTable.statement.equals("Full")){    
        for(Product currentProduct: allProducts.keySet()){
            model1.addRow(new String[]{currentProduct.getProductName(),currentProduct.getPrice()+" $",allProducts.get(currentProduct)+""});
            order.calculateTotalCost();
            totalOrderCost=order.getTotalCost();
        }
        
        TitledBorder border1 = new TitledBorder("Orders");
        ordersScrollPane.setBorder(border1);
        
       
        
        //-----------------------------------GIVING DISCOUNT
        JPanel discountPanel = new JPanel();
        discountPanel.setBounds(570, 20, 180, 230);
        TitledBorder border3 = new TitledBorder("Discount");
        discountPanel.setBorder(border3);
        discountPanel.setLayout(null);
        JCheckBox discountCB = new JCheckBox("Give A Discount");
        discountCB.setBounds(10, 30, 150, 30);
        JLabel lbl1 = new JLabel("Dicount Amount: ");
        lbl1.setBounds(15, 90, 100, 30);
        JTextField discountAmount = new JTextField();
        discountAmount.setBounds(15, 120, 100, 30);
        discountAmount.setEnabled(false);
        JButton applyDiscount = new JButton("Apply Discount");
        applyDiscount.setBounds(15, 170, 150, 30);
        discountPanel.add(applyDiscount);
        discountPanel.add(discountAmount);
        discountPanel.add(discountCB);
        discountPanel.add(lbl1);
        
        discountCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(statement){
                discountAmount.setEnabled(false);
                statement=false;
                }else{
                    discountAmount.setEnabled(true);
                    statement=true;
                }
            }
        });
        

        //------------------------------PAYMENT DETAILS
        JPanel paymentDetailsPanel = new JPanel();
        paymentDetailsPanel.setBounds(30, 320, 500, 230);

        paymentDetailsPanel.setLayout(null);
        paymentDetailsPanel.setBackground(Color.WHITE);
        
        Object[][] data = {
            {"Total Cost", totalOrderCost, "$"},
            {"Tax (%8)", totalOrderCost*0.08, "$"},
            {"Discount", discount, "$"},
            {"Net Cost", totalOrderCost-discount, "$"}
        };
        Object[] columns = {"", "", ""};
        
   
        DefaultTableModel model2 = new DefaultTableModel(data, columns);
        JTable paymentDetails = new JTable(model2);
        
        
        paymentDetails.setShowGrid(false);  
        paymentDetails.setBorder(null);     
        paymentDetails.setGridColor(new Color(0, 0, 0, 0)); 
        paymentDetails.setIntercellSpacing(new Dimension(0, 0));  
        paymentDetails.setBackground(Color.WHITE);  
        paymentDetails.setBounds(50, 10, 500, 190);
        paymentDetails.setFont(new Font("Arial", Font.BOLD, 14));
        paymentDetails.setRowHeight(50);
        paymentDetailsPanel.add(paymentDetails );
        
        
        //---------------------------PAYING
        
        JCheckBox cashPayment = new JCheckBox("Cash");
        cashPayment.setBounds(550, 340, 100, 30);
        JCheckBox debitPayment = new JCheckBox("Debit Card");
        debitPayment.setBounds(550, 370, 100, 30);
        JCheckBox ticketPayment = new JCheckBox("Ticket");
        ticketPayment.setBounds(550, 400, 100, 30);
        
        ButtonGroup group = new ButtonGroup(); 
        group.add(cashPayment); 
        group.add(debitPayment);
        group.add(ticketPayment);
        
        
        JButton applyButton = new JButton("Apply");
        applyButton.setBounds(600, 450, 140, 30);
        JButton backButton = new JButton("Back");
        backButton.setBounds(600, 490, 140, 30);
        
        
        
        
        applyDiscount.addActionListener(e->{
            if(!discountAmount.getText().isEmpty()){
            discount = Double.parseDouble((String)discountAmount.getText());
            model2.setValueAt(discount, 2, 1);
            model2.setValueAt(totalOrderCost-discount, 3, 1);
            paymentDetails.setVisible(false);
            paymentDetails.setVisible(true);
            order.setDiscount(discount);
            order.calculateTotalCost();
            order.calculateUnitcost(discount);
            }
            
        });
        applyButton.addActionListener(e->{
            
            if(cashPayment.isSelected()) paymentMethod="Cash";
            if(debitPayment.isSelected()) paymentMethod="Debit Card";
            if(ticketPayment.isSelected()) paymentMethod="Ticket";
            
            System.out.println(model1.getRowCount());
            if(!tablesPage.selectedTable.getStatement().equals("Free")){
            if(!paymentMethod.equals("")){
            OrderController orderController = new OrderController();
            tableController.updateTableStatement(tablesPage.selectedTable.getTableNumber(), "Free");
            tablesPage.selectedTable.setOrders(null);
            tablesPage.selectedTable.setStatement("Free");
            tablesPage.selectedTable.setBackground(Color.WHITE);
            order.setPaymentMethod(paymentMethod);
            order.setDiscount(discount);
            order.calculateTotalCost();
            order.calculateUnitcost(discount);
            orderController.payOrder(order);
            tablesPage.informations.setText("");
            tablesPage.informations.append("Statement:   " + tablesPage.selectedTable.getStatement() + "\n");
            tablesPage.informations.append("Capacity:   " + tablesPage.selectedTable.getCapacity() + "\n");
            tablesPage.informations.append("\nOrders: \n");
            tablesPage.totalCostLabel.setText("Total Cost: 0.00$");
            }else JOptionPane.showMessageDialog(null, "You must choose a payment Method");
            }else JOptionPane.showMessageDialog(null, "This table has no order");
        });
        
        
        
        
        
        
        
        
        
        frame.add(backButton);
        
        frame.add(applyButton);
        frame.add(cashPayment);
        frame.add(debitPayment);
        frame.add(ticketPayment);
        frame.add(discountPanel);
        frame.add(ordersScrollPane);
        frame.add(paymentDetailsPanel);                
        frame.setVisible(true);
    }
}}
