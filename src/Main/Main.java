package Main;

import Controller.*;
import Model.OnlineOrders;
import Model.Orders;
import Model.Product;
import Model.Table;
import View.Elements.Report.ReportFrame;
import View.Pages.LoginPage;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Restourant Otomation System");
        frame.setBounds(0,0, 1200, 800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        
        LoginPage loginPage = new LoginPage();
        frame.add(loginPage);
        frame.setVisible(true);
        
    }
}
