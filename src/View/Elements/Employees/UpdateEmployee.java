package View.Elements.Employees;

import Controller.EmployeeController;
import Model.Employee;
import View.Pages.EmployeesPage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

public class UpdateEmployee {

    Employee selectedEmployee;
    String formattedDate;
    private EmployeeController employeeController;

    public UpdateEmployee(EmployeesPage employeesPage) {
        selectedEmployee = employeesPage.selectedEmployee;
        employeeController=new EmployeeController();
        JFrame frame = new JFrame("Add Employee");
        frame.setBounds(200, 300, 700, 350);
        frame.setResizable(false);
        frame.setLayout(null);

        //-----------------------NAME SURNAME
        JLabel lbl1 = new JLabel("Name-Surname: ");
        lbl1.setBounds(40, 30, 100, 20);
        frame.add(lbl1);
        JTextField nameTF = new JTextField();
        nameTF.setText(selectedEmployee.getNameSurname());
        nameTF.setEditable(false);
        nameTF.setBounds(160, 30, 140, 30);
        frame.add(nameTF);

        //-----------------------ID NUMBER
        JLabel lbl2 = new JLabel("TC Number: ");
        lbl2.setBounds(40, 80, 100, 20);
        frame.add(lbl2);
        JTextField idTF = new JTextField();
        idTF.setBounds(160, 80, 140, 30);
        idTF.setText(selectedEmployee.getTcNumber());
        idTF.setEditable(false);
        frame.add(idTF);

        //---------------------ROLE CHOOSE
        JLabel lbl3 = new JLabel("Role: ");
        lbl3.setBounds(370, 80, 100, 20);
        frame.add(lbl3);
        JComboBox<String> roleCB = new JComboBox<>(new String[]{"Admin", "Courier", "Waiter", "Cashier", "Chef"});
        roleCB.setBounds(490, 80, 140, 30);
        frame.add(roleCB);

        //------------------------Country
        JLabel lbl4 = new JLabel("Country: ");
        lbl4.setBounds(40, 180, 100, 20);
        frame.add(lbl4);
        JComboBox<String> countryCB = new JComboBox<>(this.employeeController.getAllCountries().toArray(new String[81]));
        countryCB.setBounds(160, 180, 140, 30);
        frame.add(countryCB);

        //---------------------------BİRTH DATE
        JLabel lbl5 = new JLabel("Birth Date: ");
        lbl5.setBounds(40, 230, 100, 20);
        frame.add(lbl5);
        Date today = new Date();
        SpinnerDateModel model = new SpinnerDateModel(today, null, null, Calendar.DAY_OF_MONTH);
        JSpinner dateSpinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(editor);
        dateSpinner.setBounds(160, 230, 140, 30);
        Date selectedDate = (Date) dateSpinner.getValue();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        formattedDate = dateFormat.format(selectedDate);
        frame.add(dateSpinner);
        frame.add(dateSpinner);

        //-----------------------------------USER NAME
        JLabel lbl6 = new JLabel("User Name: ");
        lbl6.setBounds(370, 30, 100, 20);
        frame.add(lbl6);
        JTextField userNameTF = new JTextField();
        userNameTF.setBounds(490, 30, 140, 30);
        userNameTF.setText(selectedEmployee.getUserName());
        frame.add(userNameTF);

        //---------------------------------PHONE NUMBER
        JLabel lbl9 = new JLabel("Phone Number: ");
        lbl9.setBounds(40, 130, 100, 20);
        frame.add(lbl9);
        JTextField phoneTF = new JTextField();
        phoneTF.setBounds(160, 130, 140, 30);
        phoneTF.setText(selectedEmployee.getPhoneNumber());
        frame.add(phoneTF);



        JButton applyButton = new JButton("Apply");
        applyButton.setBounds(380, 230, 110, 40);
        frame.add(applyButton);
        JButton backButton = new JButton("Back");
        backButton.setBounds(500, 230, 110, 40);
        frame.add(backButton);

        applyButton.addActionListener(E -> {
            if (!nameTF.getText().isEmpty() && !idTF.getText().isEmpty() && !phoneTF.getText().isEmpty()
                    && !roleCB.getSelectedItem().toString().isEmpty() && !countryCB.getSelectedItem().toString().isEmpty()
                    && !formattedDate.isEmpty() && !userNameTF.getText().isEmpty()) {
                int selectedRow = employeesPage.table.getSelectedRow();
                Employee employee = new Employee(nameTF.getText(), idTF.getText(), phoneTF.getText(), userNameTF.getText(), selectedEmployee.getPassword(), roleCB.getSelectedItem().toString(), countryCB.getSelectedItem().toString(), formattedDate);
                String[] row = new String[]{employee.getNameSurname(), employee.getUserName(), employee.getPhoneNumber(), employee.getRole()};
                employeesPage.allEmployees.set(selectedRow, employee);
                
                selectedEmployee = employee;
                System.out.println(selectedRow);
                System.out.println(employeesPage.allEmployees.get(selectedRow).getNameSurname());
                employeesPage.model1.setValueAt(selectedEmployee.getNameSurname(), selectedRow, 0);
                employeesPage.model1.setValueAt(selectedEmployee.getUserName(), selectedRow, 1);
                employeesPage.model1.setValueAt(selectedEmployee.getPhoneNumber(), selectedRow, 2);
                employeesPage.model1.setValueAt(selectedEmployee.getRole(), selectedRow, 3);
                employeesPage.updateEmployeeInformation(selectedRow);
                employeesPage.table.setVisible(false);
                employeesPage.table.setVisible(true);
                employeeController.updateEmployee(selectedEmployee);
                JOptionPane.showMessageDialog(phoneTF, "Employee has been updated.");

            } else {
                JOptionPane.showMessageDialog(lbl4, "You must enter all informations!");
            }
        });

        frame.setVisible(true);
    }

}
