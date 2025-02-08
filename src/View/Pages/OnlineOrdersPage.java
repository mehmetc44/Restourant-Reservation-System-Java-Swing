package View.Pages;

import Controller.EmployeeController;
import Controller.OrderController;
import Model.Employee;
import Model.OnlineOrders;
import Model.Orders;

import View.Elements.Reservation.UpdateReservation;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;



public class OnlineOrdersPage extends JPanel {
    EmployeeController employeeController;
    public OnlineOrdersPage(){
        this.setLayout(null);
        this.setBounds(0, 0, 1000, 800);
        this.setBackground(Color.white);
        employeeController = new EmployeeController();
        DefaultTableModel model1 = new DefaultTableModel(new String[]{"Name-Surname","Phone Number","Adress","Order ID","Price","Description","Courier Name","Status"}, 0);
        JTable table = new JTable(model1);
        table.setRowHeight(30);
        JScrollPane onlineOrders = new JScrollPane(table);
        onlineOrders.setBounds(75, 100, 850, 400);
        
        ArrayList<OnlineOrders> allOnlineOrders = new OrderController().getAllOnlineOrders();
        for(OnlineOrders o:allOnlineOrders){
            model1.addRow(new Object[]{o.getCustomer().nameSurname, o.getCustomer().phoneNumber, o.getAdress(),o.getOrderId(),
            o.getTotalCost(),o.getDescription(), o.getCourier().nameSurname,o.getStatus()}
                    );
        }
        
        
        JLabel title = new JLabel("Online Orders");
        title.setBounds(360, 50,260,35);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        this.add(title);
        
        
        
        JPanel updateStuation = new JPanel();
        updateStuation.setBounds(75, 530, 230, 180);
        TitledBorder border1 = new TitledBorder("Table Information");
        updateStuation.setBorder(border1);
        updateStuation.setLayout(null);
        this.add(updateStuation);
        
        
        
        
        JLabel lbl1 = new JLabel("Update Status: ");
        lbl1.setFont(new Font("Arial", Font.BOLD, 14));
        lbl1.setBounds(10, 30, 175, 30);
        updateStuation.add(lbl1);
        
        JComboBox<String> selectStuation = new JComboBox<>(new String[]{"Preparing","On Way","Deliverd"});
        selectStuation.setBounds(25,70 , 175, 30);
        selectStuation.setFont(new Font("Arial", Font.BOLD, 14));
        updateStuation.add(selectStuation);
        JButton selectButton = new JButton("Select");
        selectButton.setBounds(25, 110, 175, 40);
        selectButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateStuation.add(selectButton);
        
        
        
        
        
        
                
        JPanel choose = new JPanel();
        choose.setBounds(350, 530, 230, 180);
        TitledBorder border2 = new TitledBorder("Courier Add");
        choose.setBorder(border2);
        choose.setLayout(null);
        this.add(choose);
        
        
        JLabel lbl2 = new JLabel("Choose Courier: ");
        lbl2.setFont(new Font("Arial", Font.BOLD, 14));
        lbl2.setBounds(10, 30, 175, 30);
        choose.add(lbl2);
        
        ArrayList<Employee> couriers = employeeController.getCouriers();
        String[] arr = new String[couriers.size()];
        for(int i =0;i<couriers.size();i++){
        arr[i]=couriers.get(i).getNameSurname();
        }
        JComboBox<String> selectCourier = new JComboBox<>(arr);
        selectCourier.setBounds(25,70 , 175, 30);
        selectCourier.setFont(new Font("Arial", Font.BOLD, 14));
        choose.add(selectCourier);
        
        JButton selectCourierButton = new JButton("Select Courier");
        selectCourierButton.setBounds(25, 110, 175, 40);
        selectCourierButton.setFont(new Font("Arial", Font.BOLD, 14));
        choose.add(selectCourierButton);
        
        
        
        JPanel operations = new JPanel();
        operations.setBounds(630, 530, 295, 180);
        TitledBorder border3 = new TitledBorder("Operations");
        operations.setBorder(border3);
        operations.setLayout(null);
        this.add(operations);  
        JTextArea details = new JTextArea();
        details.setBounds(10,20,275,150);
        details.setMargin(new Insets(10, 5, 10, 10));
        details.setLineWrap(true);
        details.setLineWrap(true);
        
        operations.add(details);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                details.setText("");
                int i = table.getSelectedRow();
                if(i==1){
                            details.append("Döner----- x2 ----- 20$ ");
                            details.append("\nTOTAL COST: 20$");
                }else{
                    details.append("Ayran----- x2 ----- 14$ ");
                            details.append("\nTOTAL COST: 14$");
                }
            }
        });
        
        selectButton.addActionListener(e->{
            try{
                model1.setValueAt(selectStuation.getSelectedItem().toString(),table.getSelectedRow(),7);
                table.setVisible(false);
                table.setVisible(true);
            }catch(java.lang.Exception o){
                JOptionPane.showMessageDialog(this, "Choose an Order");
            }
        });
        selectCourierButton.addActionListener(e->{
            try{
                model1.setValueAt(selectCourier.getSelectedItem().toString(),table.getSelectedRow(),6);
                table.setVisible(false);
                table.setVisible(true);
            }catch(java.lang.Exception o){
                JOptionPane.showMessageDialog(this, "Choose a Courier");
            }
        });
        
        
        this.add(onlineOrders);
        this.setVisible(true);
    }
}
