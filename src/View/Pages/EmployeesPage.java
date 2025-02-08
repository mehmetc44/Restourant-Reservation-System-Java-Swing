package View.Pages;

import Controller.EmployeeController;
import Model.Employee;
import View.Elements.Employees.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class EmployeesPage extends JPanel {

    private EmployeeController employeeController;
    public Employee selectedEmployee;
    public DefaultTableModel model1;
    public JTable table;
    public JScrollPane employees;
    public ArrayList<Employee> allEmployees;

    JTextArea details;

    public EmployeesPage() {
        this.setLayout(null);
        this.setBounds(0, 0, 1000, 800);
        this.setBackground(Color.white);
        employeeController = new EmployeeController();
        allEmployees = employeeController.getAllEmployees();

        String[] columnNames = {"Name-Surname", "User Name", "Phone Number", "Role"};

        model1 = new DefaultTableModel(columnNames, 0);
        table = new JTable(model1);
        table.setRowHeight(30);
        employees = new JScrollPane(table);
        employees.setBounds(75, 100, 550, 600);
        for (Employee employee : allEmployees) {
            model1.addRow(new String[]{employee.nameSurname, employee.getUserName(), employee.getPhoneNumber(), employee.getRole()});
        }
        this.add(employees);
        ListSelectionModel selectionModel = table.getSelectionModel();

        JLabel title = new JLabel("Employees");
        title.setBounds(360, 50, 260, 35);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        this.add(title);

        JPanel choose = new JPanel();
        choose.setBounds(650, 100, 300, 400);
        TitledBorder border2 = new TitledBorder("Employee Details");
        choose.setBorder(border2);
        choose.setLayout(null);
        this.add(choose);

        details = new JTextArea();
        details.setBounds(10, 20, 280, 370);
        details.setLineWrap(true);
        details.setLineWrap(true);
        details.setMargin(new Insets(20, 20, 20, 20));
        details.setFont(new Font("Arial", Font.BOLD, 14));
        choose.add(details);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {

                    details.setText("");
                    int selectedRow = table.getSelectedRow();
                    try {
                        selectedEmployee = allEmployees.get(selectedRow);
                        if (table.getRowCount() != 0) {
                            updateEmployeeInformation(selectedRow);
                        }

                    } catch (java.lang.IndexOutOfBoundsException e) {
                        selectedEmployee = allEmployees.get(selectedRow + 1);
                        updateEmployeeInformation(selectedRow + 1);
                    }
                }
            }
        });

        JButton addEmployee = new JButton("Add Employee");
        addEmployee.setBounds(700, 530, 175, 40);
        addEmployee.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(addEmployee);

        JButton updateEmployee = new JButton("Update Employee");
        updateEmployee.setBounds(700, 580, 175, 40);
        updateEmployee.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(updateEmployee);

        JButton deleteEmployee = new JButton("Delete Employee");
        deleteEmployee.setBounds(700, 630, 175, 40);
        deleteEmployee.setFont(new Font("Arial", Font.BOLD, 14));
        this.add(deleteEmployee);
        addEmployee.addActionListener(e -> {
            new AddEmployee(this);

        });
        updateEmployee.addActionListener(e -> {
            try {
                new UpdateEmployee(this);
            } catch (java.lang.Exception r) {
                JOptionPane.showMessageDialog(this, "You must choose a employee from table");
            }
        });

        deleteEmployee.addActionListener(e -> {

            int answer = JOptionPane.showConfirmDialog(null,
                    "Do you want to delete  " + this.selectedEmployee.getNameSurname() + "?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (answer == JOptionPane.YES_OPTION) {

                int selectedIndex = table.getSelectedRow();

                if (selectedIndex != -1) {

                    model1.removeRow(selectedIndex);
                    employeeController.deleteEmployee(this.allEmployees.get(selectedIndex).getTcNumber());
                    table.setVisible(false);
                    table.setVisible(true);
                    this.allEmployees.remove(selectedIndex);
                    JOptionPane.showMessageDialog(this, "Emplpyee " + this.allEmployees.get(selectedIndex).getNameSurname() + " has been deleted!");
                } else {
                    JOptionPane.showMessageDialog(this, "You must choose a employee from table. ");
                }
            }
        });

        this.setVisible(true);
    }

    public void updateEmployeeInformation(int index) {
        details.setText("");
        details.append("\nName-Surname: " + allEmployees.get(index).getNameSurname());
        details.append("\n\nUser Name: " + allEmployees.get(index).getUserName());
        details.append("\n\nPhone Number: " + allEmployees.get(index).getPhoneNumber());
        details.append("\n\nCountry: " + allEmployees.get(index).getCountry());
        details.append("\n\nBirth Date: " + allEmployees.get(index).getBirthDate());
        details.append("\n\nRole: " + allEmployees.get(index).getRole());
        details.setVisible(false);
        details.setVisible(true);
    }
}
