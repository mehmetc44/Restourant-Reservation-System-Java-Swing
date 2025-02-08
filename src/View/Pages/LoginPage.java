package View.Pages;
import Controller.EmployeeController;
import Controller.UserController;
import Model.User;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class LoginPage extends JPanel {
    
    UserController userController = new UserController();
    public User ActiveUser=null;
    
    public LoginPage(){
        this.setLayout(null);
        this.setBounds(0, 0, 1200, 800);
        this.setBackground(new Color(21, 11, 46));
        JLabel lbl1 = new JLabel("User Name:");
        lbl1.setForeground(Color.WHITE);
        lbl1.setBounds(350, 370, 150, 15);
        lbl1.setFont(new Font("Arial", Font.PLAIN, 16));
        this.add(lbl1);
        
        JLabel lbl2 = new JLabel("Password:");
        lbl2.setBounds(350, 470, 150, 15);
        lbl2.setFont(new Font("Arial", Font.PLAIN, 16));
        lbl2.setForeground(Color.WHITE);
        this.add(lbl2);

        JLabel title = new JLabel("RESTOURANT OTOMATION");
        title.setBounds(290, 200, 1000, 80);
        title.setFont(new Font("Arial", Font.PLAIN, 50));
        title.setForeground(Color.WHITE);
        this.add(title);
        
        JTextField userName = new JTextField();
        userName.setBounds(350, 400, 500, 50);
        userName.setFont(new Font("Arial", Font.PLAIN, 18));
        userName.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); 
        userName.setBackground(new Color(14, 7, 31));
        userName.setForeground(Color.WHITE);
        userName.setCaretColor(Color.WHITE);
        this.add(userName);
        
        
        
        JPasswordField password = new JPasswordField();
        password.setBounds(350, 500, 500, 50);
        password.setFont(new Font("Arial", Font.BOLD, 30));
        password.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); 
        password.setBackground(new Color(14, 7, 31));
        password.setForeground(Color.WHITE);
        password.setCaretColor(Color.WHITE);
        this.add(password);
        
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(350, 600, 200, 60);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        loginButton.setBackground(new Color(0, 7, 140));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.add(loginButton);
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(650, 600, 200, 60);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        registerButton.setBackground(new Color(0, 7, 140));
        registerButton.setForeground(Color.WHITE);
        this.add(registerButton);
        loginButton.addActionListener(e -> {
            
            
            if(!userName.getText().isEmpty()&&password.getPassword().length!=0){
                boolean result =new EmployeeController().checkUserNameIsExist(userName.getText());
                if(result==true){
                     ActiveUser= userController.getUser(userName.getText());

                    if(ActiveUser.getPassword().equals(new String(password.getPassword()))){
                        
                        
                        
                    }else{
                        JOptionPane.showMessageDialog(this,"Incorrect Password !");
                    }
                    
                }else{
                    JOptionPane.showMessageDialog(this,"Enter username which is exist!");
                }
                
            }else{
                JOptionPane.showMessageDialog(this,"You must enter your user name and password");
            }
            
            
            MainPage mainPage = new MainPage(this);
            mainPage.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
            this.setVisible(false);
            this.getParent().add(mainPage);
        });
        registerButton.addActionListener(e->{
        RegisterPage registerPage = new RegisterPage(this);
            registerPage.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
            this.setVisible(false);
            this.getParent().add(registerPage);
         
        });
    }

}
