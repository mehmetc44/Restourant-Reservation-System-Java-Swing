package View.Elements.Employees;


import Controller.EmployeeController;
import Model.Employee;
import View.Pages.EmployeesPage;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.TitledBorder;

    
public class AddEmployee {
    String formattedDate;
    private EmployeeController employeeController;
    public AddEmployee(EmployeesPage employeesPage) {
        employeeController=new EmployeeController();
        JFrame frame = new JFrame("Add Employee");
        frame.setBounds(200,300,700, 350);
        frame.setResizable(false);
        frame.setLayout(null);
        
        //-----------------------NAME SURNAME
        JLabel lbl1 = new JLabel("Name-Surname: ");
        lbl1.setBounds(40, 30, 100, 20);
        frame.add(lbl1);
        JTextField nameTF = new JTextField();
        nameTF.setBounds(160, 30, 140, 30);
        frame.add(nameTF);
        
        //-----------------------ID NUMBER
        JLabel lbl2 = new JLabel("TC Number: ");
        lbl2.setBounds(40, 80, 100, 20);
        frame.add(lbl2);
        JTextField idTF = new JTextField();
        idTF.setBounds(160, 80, 140, 30);
        frame.add(idTF);
        
        
        //---------------------ROLE CHOOSE
        JLabel lbl3 = new JLabel("Role: ");
        lbl3.setBounds(370, 80, 100, 20);
        frame.add(lbl3);
        JComboBox<String> roleCB = new JComboBox<>(new String[]{"Admin","Courier","Waiter","Cashier","Chef"});
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
        formattedDate =  dateFormat.format(selectedDate);
        frame.add(dateSpinner);
        
        //-----------------------------------USER NAME
        JLabel lbl6 = new JLabel("User Name: ");
        lbl6.setBounds(370, 30, 100, 20);
        frame.add(lbl6);
        JTextField userNameTF = new JTextField();
        userNameTF.setBounds(490, 30, 140, 30);
        frame.add(userNameTF);
        
        //---------------------------------PHONE NUMBER
        JLabel lbl9 = new JLabel("Phone Number: ");
        lbl9.setBounds(40, 130, 100, 20);
        frame.add(lbl9);
        JTextField phoneTF = new JTextField();
        phoneTF.setBounds(160, 130, 140, 30);
        frame.add(phoneTF);
        
        
        //-------------------------------PASSWORD-1
        JLabel lbl7 = new JLabel("Enter Password: ");
        lbl7.setBounds(370, 130, 100, 20);
        frame.add(lbl7);
        JPasswordField passwordTF1 = new JPasswordField();
        passwordTF1.setBounds(490, 130, 140, 30);
        frame.add(passwordTF1);
        
        //-------------------------------PASSWORD-2
        JLabel lbl8 = new JLabel("Verify Password: ");
        lbl8.setBounds(370, 180, 100, 20);
        frame.add(lbl8);
        JPasswordField passwordTF2 = new JPasswordField();
        passwordTF2.setBounds(490, 180, 140, 30);
        frame.add(passwordTF2);
        
        
        
        JButton applyButton= new JButton("Apply");
        applyButton.setBounds(380, 230, 110, 40);
        frame.add(applyButton);
        JButton backButton= new JButton("Back");
        backButton.setBounds(500, 230, 110, 40);
        frame.add(backButton);
        
        backButton.addActionListener(c->{
            System.out.println(formattedDate);
        });
        
        
        applyButton.addActionListener(e->{
            if(idTF.getText().length()==11){
            if(!employeeController.checkUserNameIsExist(userNameTF.getText())){
            if(!nameTF.getText().isEmpty()&&!idTF.getText().isEmpty()&&!phoneTF.getText().isEmpty()&&
                    !roleCB.getSelectedItem().toString().isEmpty()&&!countryCB.getSelectedItem().toString().isEmpty()&&
                    !formattedDate.isEmpty()&&!userNameTF.getText().isEmpty()&&(passwordTF1.getPassword().length!=0)&&
                    (passwordTF2.getPassword().length!=0)){
            if(new String(passwordTF2.getPassword()).equals(new String(passwordTF1.getPassword()))){
                Employee employee = new Employee(nameTF.getText(), idTF.getText(), phoneTF.getText(), userNameTF.getText(),new String(passwordTF1.getPassword()),roleCB.getSelectedItem().toString(),countryCB.getSelectedItem().toString(),formattedDate);

                String[] row=new String[]{employee.getNameSurname(),employee.getUserName(),employee.getPhoneNumber(),employee.getRole()};
                employeesPage.allEmployees.add(employee);
                employeesPage.model1.addRow(row);
                employeeController.addEmployee(employee);
                employeesPage.table.setVisible(false);
                employeesPage.table.setVisible(true);
                JOptionPane.showMessageDialog(phoneTF, "Employee has added.");
            }else{
                JOptionPane.showMessageDialog(lbl7, "Please check your passwords");
            }
            }else{
                JOptionPane.showMessageDialog(lbl7, "You must enter all informations!");
            }
            }else{JOptionPane.showMessageDialog(lbl7, "This userName is already exist!");}
  
            }else{
                JOptionPane.showMessageDialog(lbl7, "TC number must be 11 caracter.");
            }
        });
        
        
        
        frame.setVisible(true);
    }
    
}
