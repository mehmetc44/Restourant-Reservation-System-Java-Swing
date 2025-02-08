package View.Pages;

import Model.User;
import View.Elements.MainPage.NavigationBar;
import View.Elements.Profile.UpdateProfile;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class ProfilePage extends JPanel {

    private String passwordstar;
    private boolean statement = true;
    public User ActiveUser;
    public ProfilePage(NavigationBar navBar) {
        this.setLayout(null);
        this.setBounds(0, 0, 1000, 800);
        this.setBackground(Color.WHITE);
        ActiveUser = navBar.ActiveUser;
        JPanel backPanel = new JPanel();
        backPanel.setBounds(75, 50, 850, 625);
        TitledBorder border1 = new TitledBorder("User Informations");
        border1.setTitleFont(new Font("Arial", Font.BOLD, 18));
        backPanel.setBorder(border1);
        backPanel.setLayout(null);

        this.add(backPanel);
        //-----------------------NAME SURNAME
        JLabel lbl1 = new JLabel("Name-Surname: ");
        lbl1.setBounds(40, 295, 225, 25);
        lbl1.setFont(new Font("Arial", Font.BOLD, 16));
        backPanel.add(lbl1);
        JTextField nameTF = new JTextField();
        nameTF.setBounds(200, 290, 200, 40);
        nameTF.setText(ActiveUser.getNameSurname());

        backPanel.add(nameTF);

        //-----------------------ID NUMBER
        JLabel lbl2 = new JLabel("TC Number: ");
        lbl2.setBounds(40, 355, 225, 25);
        lbl2.setFont(new Font("Arial", Font.BOLD, 16));
        backPanel.add(lbl2);
        JTextField idTF = new JTextField();
        idTF.setBounds(200, 350, 200, 40);
        idTF.setText(ActiveUser.getTcNumber());

        backPanel.add(idTF);

        //---------------------ROLE CHOOSE
        JLabel lbl3 = new JLabel("Role: ");
        lbl3.setBounds(40, 415, 225, 25);
        lbl3.setFont(new Font("Arial", Font.BOLD, 16));
        backPanel.add(lbl3);
        JTextField roleTF = new JTextField();
        roleTF.setBounds(200, 410, 200, 40);
        roleTF.setText(ActiveUser.getRole());
        backPanel.add(roleTF);

        //------------------------Country
        JLabel lbl4 = new JLabel("Country: ");
        lbl4.setBounds(40, 475, 225, 25);
        lbl4.setFont(new Font("Arial", Font.BOLD, 16));
        backPanel.add(lbl4);
        JTextField countryTF = new JTextField();
        countryTF.setBounds(200, 470, 200, 40);
        countryTF.setText(ActiveUser.getCountry());
        backPanel.add(countryTF);

        //---------------------------BİRTH DATE
        JLabel lbl5 = new JLabel("Birth Date: ");
        lbl5.setBounds(440, 295, 225, 25);
        lbl5.setFont(new Font("Arial", Font.BOLD, 16));
        backPanel.add(lbl5);
        JTextField dateTF = new JTextField();
        dateTF.setText(ActiveUser.getBirthDate());
        dateTF.setBounds(600, 290, 200, 40);
        backPanel.add(dateTF);

        //-----------------------------------USER NAME
        JLabel lbl6 = new JLabel("User Name: ");
        lbl6.setBounds(440, 355, 225, 25);
        lbl6.setFont(new Font("Arial", Font.BOLD, 16));
        backPanel.add(lbl6);
        JTextField userNameTF = new JTextField();
        userNameTF.setText(ActiveUser.getUserName());
        userNameTF.setBounds(600, 350, 200, 40);
        backPanel.add(userNameTF);

        //-------------------------------PASSWORD
        JLabel lbl7 = new JLabel("Password: ");
        lbl7.setBounds(440, 415, 225, 25);
        lbl7.setFont(new Font("Arial", Font.BOLD, 16));
        backPanel.add(lbl7);
        JTextField passwordTF = new JTextField();
        passwordTF.setBounds(600, 410, 160, 40);
        int count = ActiveUser.getPassword().length();
        passwordstar = "";
        for (int i = 0; i < count; i++) {
            passwordstar += "*";
        }
        passwordTF.setText(passwordstar);
        backPanel.add(passwordTF);

        JButton showPassword = new JButton();
        showPassword.setBounds(760, 410, 40, 40);
        backPanel.add(showPassword);

        showPassword.addActionListener(r -> {
            if (this.statement == true) {
                passwordTF.setText(ActiveUser.getPassword());
                this.statement = false;
            } else {
                int c = ActiveUser.getPassword().length();
                passwordstar = "";
                for (int i = 0; i < c; i++) {
                    passwordstar += "*";
                }
                passwordTF.setText(passwordstar);
                this.statement = true;
            }

        });

        //-------------------------PROFILE PHOTO
        JPanel photo = new JPanel();
        photo.setBackground(Color.WHITE);
        photo.setBounds(300, 30, 250, 250);
        backPanel.add(photo);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(460, 470, 140, 40);
        backPanel.add(updateButton);

        JButton changeProfilePhoto = new JButton("Add Profile Photo");
        changeProfilePhoto.setBounds(640, 470, 140, 40);
        backPanel.add(changeProfilePhoto);

        changeProfilePhoto.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Profile Photo");

            // Dosya açma penceresi gösteriliyor
            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this, "Selected file: " + selectedFile.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(this, "No file selected.");
            }
        });
        updateButton.addActionListener(e -> {
            new UpdateProfile(this);
        });
    }

}
