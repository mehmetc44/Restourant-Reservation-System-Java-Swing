package View.Elements.Tables;
import Controller.TableController;
import View.Pages.TablesPage;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EditTable {
    TableController tableController;
    public EditTable(TablesPage tablesPage){
        tableController = new TableController();
        JFrame frame = new JFrame("Edit Table");
        frame.setBounds(400, 300,400,300);
        frame.setResizable(false);
        frame.setLayout(null);
        String[] arr = {"1","2","4","6","8"};
        JLabel lbl1 = new JLabel("Capacity: ");
        lbl1.setBounds(80,75,100,30);
        JComboBox<String> capacity = new JComboBox<>(arr);
        capacity.setBounds(200,75,100,30);
        JLabel lbl2 = new JLabel("Table Number: ");
        lbl2.setBounds(80,120,100,30);
        JTextField tf1 = new JTextField();
        tf1.setBounds(200,120,100,30);
        tf1.setEditable(false);
        tf1.setText(Integer.toString(tablesPage.selectedTable.getTableNumber()));
        
        JButton editTable = new JButton("Edit Table");
        editTable.setBounds(110, 180, 180, 40);
        editTable.addActionListener(e -> {
            if(tablesPage.selectedTable.getStatement().equals("Free")){
            tablesPage.selectedTable.setCapacity(Integer.parseInt((String) capacity.getSelectedItem()));
            tableController.updateTableCapacity(tablesPage.selectedTable.getTableNumber(), tablesPage.selectedTable.getCapacity());
            tablesPage.informations.setText("");
            tablesPage.informations.append("Statement:   " + tablesPage.selectedTable.getStatement() + "\n");
            tablesPage.informations.append("Capacity: "+tablesPage.selectedTable.getCapacity());
            tablesPage.informations.append("\nOrders: \n");
            JOptionPane.showMessageDialog(frame, "Table has been edited.");
            }else JOptionPane.showMessageDialog(frame, "Choosen table must be free for edit!");
        });
        
        frame.add(editTable);
        frame.add(capacity);
        frame.add(lbl1);
        frame.add(lbl2);
        frame.add(tf1);
        frame.setVisible(true);
    }
}
