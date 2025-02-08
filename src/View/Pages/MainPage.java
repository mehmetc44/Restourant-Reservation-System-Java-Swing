package View.Pages;

import Model.Orders;
import Model.User;
import View.Elements.MainPage.NavigationBar;
import java.awt.*;
import java.util.HashMap;
import javax.swing.*;


public class MainPage extends JPanel{
    public LoginPage loginPage;
    public JPanel backPanel;

    
    public MainPage(LoginPage loginPage){

        this.loginPage=loginPage;
 
        
        this.setLayout(null);
        this.setBounds(0, 0, 1200, 800);
        backPanel = new JPanel();
        backPanel.setBounds(200, 0, 1000, 800);
        backPanel.setLayout(null);
        
        NavigationBar naviBar = new NavigationBar(this);
        backPanel.add(naviBar.tablesPage);
        this.add(naviBar);
        this.add(backPanel);
    }
}
