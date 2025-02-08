package View.Pages;

import Controller.EmployeeController;
import Controller.UserController;
import Model.Employee;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;
import java.util.Date;

public class RegisterPage extends JPanel {

    private JTextField nameSurnameField, tcNumberField, userNameField;
    private JPasswordField passwordField, verifyPasswordField;
    private JComboBox<String> roleComboBox, countryComboBox;
    private JButton registerButton, cancelButton;
    private JSpinner birthDateSpinner;

    public RegisterPage(LoginPage loginpage) {
        setLayout(null);
        setPreferredSize(new Dimension(1200, 800));

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font textFieldFont = new Font("Arial", Font.PLAIN, 14);

        // Create text fields and password fields
        nameSurnameField = new JTextField();
        nameSurnameField.setFont(textFieldFont);
        nameSurnameField.setPreferredSize(new Dimension(300, 40));
        tcNumberField = new JTextField();
        tcNumberField.setFont(textFieldFont);
        tcNumberField.setPreferredSize(new Dimension(300, 40));
        userNameField = new JTextField();
        userNameField.setFont(textFieldFont);
        userNameField.setPreferredSize(new Dimension(300, 40));
        passwordField = new JPasswordField();
        passwordField.setFont(textFieldFont);
        passwordField.setPreferredSize(new Dimension(300, 40));
        verifyPasswordField = new JPasswordField();
        verifyPasswordField.setFont(textFieldFont);
        verifyPasswordField.setPreferredSize(new Dimension(300, 40));

        String[] roles = {"Admin","Courier","Waiter","Cashier","Chef"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setFont(textFieldFont);
        roleComboBox.setPreferredSize(new Dimension(300, 40));

        countryComboBox = new JComboBox<>(new EmployeeController().getAllCountries().toArray(new String[81]));
        countryComboBox.setFont(textFieldFont);
        countryComboBox.setPreferredSize(new Dimension(300, 40));

        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));

        Calendar cal = Calendar.getInstance();
        SpinnerDateModel dateModel = new SpinnerDateModel();
        dateModel.setCalendarField(Calendar.YEAR);
        birthDateSpinner = new JSpinner(dateModel);
        birthDateSpinner.setFont(textFieldFont);
        birthDateSpinner.setPreferredSize(new Dimension(300, 40));
        JSpinner.DateEditor editor = new JSpinner.DateEditor(birthDateSpinner, "dd/MM/yyyy");
        birthDateSpinner.setEditor(editor);
        Date selectedDate = (Date) birthDateSpinner.getValue();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(selectedDate);

        JLabel nameSurnameLabel = new JLabel("Name-Surname:");
        nameSurnameLabel.setFont(labelFont);
        nameSurnameLabel.setBounds(120, 300, 150, 30);
        add(nameSurnameLabel);
        nameSurnameField.setBounds(220, 300, 300, 40);
        add(nameSurnameField);

        JLabel tcNumberLabel = new JLabel("TC Number:");
        tcNumberLabel.setFont(labelFont);
        tcNumberLabel.setBounds(120, 370, 150, 30);
        add(tcNumberLabel);
        tcNumberField.setBounds(220, 370, 300, 40);
        add(tcNumberField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(labelFont);
        roleLabel.setBounds(120, 440, 150, 30);
        add(roleLabel);
        roleComboBox.setBounds(220, 440, 300, 40);
        add(roleComboBox);

        JLabel countryLabel = new JLabel("Country:");
        countryLabel.setFont(labelFont);
        countryLabel.setBounds(120, 510, 150, 30);
        add(countryLabel);
        countryComboBox.setBounds(220, 510, 300, 40);
        add(countryComboBox);

        JLabel userNameLabel = new JLabel("User Name:");
        userNameLabel.setFont(labelFont);
        userNameLabel.setBounds(580, 300, 150, 30);
        add(userNameLabel);
        userNameField.setBounds(730, 300, 300, 40);
        add(userNameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        passwordLabel.setBounds(580, 370, 150, 30);
        add(passwordLabel);
        passwordField.setBounds(730, 370, 300, 40);
        add(passwordField);

        JLabel verifyPasswordLabel = new JLabel("Verify Password:");
        verifyPasswordLabel.setFont(labelFont);
        verifyPasswordLabel.setBounds(580, 440, 150, 30);
        add(verifyPasswordLabel);
        verifyPasswordField.setBounds(730, 440, 300, 40);
        add(verifyPasswordField);

        JLabel birthDateLabel = new JLabel("Birth Date:");
        birthDateLabel.setFont(labelFont);
        birthDateLabel.setBounds(580, 510, 150, 30);
        add(birthDateLabel);
        birthDateSpinner.setBounds(730, 510, 300, 40);
        add(birthDateSpinner);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBounds(580, 580, 450, 60);
        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel);

        JTextField phoneNumberField = new JTextField();
        phoneNumberField.setFont(textFieldFont);
        phoneNumberField.setPreferredSize(new Dimension(300, 40));

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setFont(labelFont);
        phoneNumberLabel.setBounds(120, 230, 150, 30);  // Adjust the Y-coordinate for positioning
        add(phoneNumberLabel);
        phoneNumberField.setBounds(220, 230, 300, 40);  // Adjust the Y-coordinate for positioning
        add(phoneNumberField);

        cancelButton.addActionListener(e -> {
            this.setVisible(false);
            loginpage.setVisible(true);
        });

        registerButton.addActionListener(a -> {
            if (tcNumberField.getText().length() == 11) {
                if (!new EmployeeController().checkUserNameIsExist(userNameField.getText())) {
                    if (!nameSurnameField.getText().isEmpty() && !tcNumberField.getText().isEmpty() && !phoneNumberField.getText().isEmpty()
                            && !roleComboBox.getSelectedItem().toString().isEmpty() && !countryComboBox.getSelectedItem().toString().isEmpty()
                            && !formattedDate.isEmpty() && !userNameField.getText().isEmpty() && (passwordField.getPassword().length != 0)
                            && (verifyPasswordField.getPassword().length != 0)) {
                        if (new String(verifyPasswordField.getPassword()).equals(new String(passwordField.getPassword()))) {
                            String name = nameSurnameField.getText();
                            String tcNumber = tcNumberField.getText();
                            String phoneNumber = phoneNumberField.getText();
                            String userName = userNameField.getText();
                            String password = new String(passwordField.getPassword());
                            String verifyPassword = new String(verifyPasswordField.getPassword());  // Eğer gerekli ise doğrulama
                            String role = roleComboBox.getSelectedItem().toString();
                            String country = countryComboBox.getSelectedItem().toString();
                            String birthDate = formattedDate;  

                            Employee employee = new Employee(name, tcNumber, phoneNumber, userName, password, role, country, birthDate);
                            new UserController().registerUser(employee);
                            JOptionPane.showMessageDialog(this, "You are registered");
                        } else {
                            JOptionPane.showMessageDialog(this, "Please check your passwords");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "You must enter all informations!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "This userName is already exist!");
                }

            } else {
                JOptionPane.showMessageDialog(this, "TC number must be 11 caracter.");
            }
        });

    }
}
