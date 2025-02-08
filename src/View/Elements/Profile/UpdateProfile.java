package View.Elements.Profile;

import Controller.EmployeeController;
import Model.Employee;
import Model.User;
import View.Pages.ProfilePage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

public class UpdateProfile {
    
    private EmployeeController employeeController;
    String formattedDate;

    public UpdateProfile(ProfilePage profilePage) {
        
        User ActiveUser = profilePage.ActiveUser;
        employeeController = new EmployeeController();
        JFrame frame = new JFrame("Add Employee");
        frame.setBounds(200, 300, 700, 350);
        frame.setResizable(false);
        frame.setLayout(null);

        //-----------------------NAME SURNAME
        JLabel lbl1 = new JLabel("Name-Surname: ");
        lbl1.setBounds(40, 30, 100, 20);
        frame.add(lbl1);
        JTextField nameTF = new JTextField();
        nameTF.setText(ActiveUser.getNameSurname());
        nameTF.setEditable(false);
        nameTF.setBounds(160, 30, 140, 30);
        frame.add(nameTF);

        //-----------------------ID NUMBER
        JLabel lbl2 = new JLabel("TC Number: ");
        lbl2.setBounds(40, 80, 100, 20);
        frame.add(lbl2);
        JTextField idTF = new JTextField();
        idTF.setBounds(160, 80, 140, 30);
        idTF.setText(ActiveUser.getTcNumber());
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
        userNameTF.setText(ActiveUser.getUserName());
        frame.add(userNameTF);

        //---------------------------------PHONE NUMBER
        JLabel lbl9 = new JLabel("Phone Number: ");
        lbl9.setBounds(40, 130, 100, 20);
        frame.add(lbl9);
        JTextField phoneTF = new JTextField();
        phoneTF.setBounds(160, 130, 140, 30);
        phoneTF.setText(ActiveUser.getPhoneNumber());
        frame.add(phoneTF);

        //-----------------------------PASSWORD
        JLabel lbl7 = new JLabel("Password: ");
        lbl7.setBounds(370, 130, 100, 20);
        frame.add(lbl7);
        JPasswordField passwordTF = new JPasswordField();
        passwordTF.setBounds(490, 130, 140, 30);
        frame.add(passwordTF);

//-----------------------------CONFIRM PASSWORD
        JLabel lbl8 = new JLabel("Confirm Password: ");
        lbl8.setBounds(370, 180, 120, 20);
        frame.add(lbl8);
        JPasswordField confirmPasswordTF = new JPasswordField();
        confirmPasswordTF.setBounds(490, 180, 140, 30);
        frame.add(confirmPasswordTF);
        
        JButton applyButton = new JButton("Apply");
        applyButton.setBounds(380, 230, 110, 40);
        frame.add(applyButton);
        JButton backButton = new JButton("Back");
        backButton.setBounds(500, 230, 110, 40);
        frame.add(backButton);
        
        applyButton.addActionListener(E -> {
            String password = ActiveUser.getPassword();
            if (!idTF.getText().isEmpty() && !phoneTF.getText().isEmpty()
                    && !roleCB.getSelectedItem().toString().isEmpty() && !countryCB.getSelectedItem().toString().isEmpty()
                    && !formattedDate.isEmpty() && !userNameTF.getText().isEmpty()) {
                if (new String(passwordTF.getPassword()).equals(new String(confirmPasswordTF.getPassword()))) {
                    if (!new String(passwordTF.getPassword()).isEmpty() && !new String(confirmPasswordTF.getPassword()).isEmpty()) {
                        if (new String(passwordTF.getPassword()).equals(new String(confirmPasswordTF.getPassword()))) {
                            password = new String(passwordTF.getPassword());
                        } else {
                            JOptionPane.showMessageDialog(passwordTF, "Your passwors must be same! ");
                        }
                    } else {
                        password = ActiveUser.getPassword();
                    }
                    
                    Employee employee = new Employee(nameTF.getText(), idTF.getText(), phoneTF.getText(), userNameTF.getText(), password, roleCB.getSelectedItem().toString(), countryCB.getSelectedItem().toString(), formattedDate);
                    employeeController.updateEmployee(employee);
                    JOptionPane.showMessageDialog(phoneTF, "Employee has been updated.");
                    profilePage.setVisible(false);
                    profilePage.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(lbl4, "You must enter all informations!");
            }
        });
        frame.setVisible(true);
    }
}
