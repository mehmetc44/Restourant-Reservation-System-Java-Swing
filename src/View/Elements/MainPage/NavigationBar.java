
package View.Elements.MainPage;

import Model.User;
import View.Elements.Report.ReportFrame;
import View.Pages.ProfilePage;
import View.Pages.*;
import java.awt.*;
import javax.swing.*;

public class NavigationBar extends JPanel {
    public TablesPage tablesPage = new TablesPage();
    
    public ReservationsPage reservationsPage = new ReservationsPage();
    public OnlineOrdersPage onlineOrdersPage = new OnlineOrdersPage();
    public EmployeesPage employeesPage = new EmployeesPage();
    public User ActiveUser; 
    
    public NavigationBar(MainPage m){
        this.setLayout(null);
        this.setBackground(new Color(35, 17, 79));
        this.setBounds(0, 0, 200, 800);
        ActiveUser=m.loginPage.ActiveUser;
        JButton tablesButton = new JButton("Tables");
        tablesButton.setBounds(20, 50, 160, 70);
        tablesButton.setFont(new Font("Arial", Font.BOLD, 18));
        tablesButton.setBackground(Color.WHITE);
        this.add(tablesButton);

        JButton reservationsButton = new JButton("Reservations");
        reservationsButton.setBounds(20, 150, 160, 70);
        reservationsButton.setBackground(Color.WHITE);
        reservationsButton.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(reservationsButton);

        JButton orderButton = new JButton("Online Orders");
        orderButton.setBounds(20, 250, 160, 70);
        orderButton.setBackground(Color.WHITE);
        orderButton.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(orderButton);

        JButton employeesButton = new JButton("Employees");
        employeesButton.setBounds(20, 350, 160, 70);
        employeesButton.setBackground(Color.WHITE);
        employeesButton.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(employeesButton);

        JButton profileButton = new JButton("Profile");
        profileButton.setBounds(20, 450, 160, 70);
        profileButton.setBackground(Color.WHITE);
        profileButton.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(profileButton);
        
        JButton reportButton = new JButton("Report");
        reportButton.setBounds(20, 550, 160, 70);
        reportButton.setBackground(Color.WHITE);
        reportButton.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(reportButton);
        
         
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(20, 650, 160, 70);
        quitButton.setBackground(Color.WHITE);
        quitButton.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(quitButton);
        
        reportButton.addActionListener(e -> {
            new ReportFrame();
        });
        tablesButton.addActionListener(e -> {
            m.backPanel.removeAll();
           m.backPanel.revalidate();
            m.backPanel.repaint();
            m.backPanel.add(tablesPage);
        });
        reservationsButton.addActionListener(e -> {
            m.backPanel.removeAll();
            m.backPanel.revalidate();
            m.backPanel.repaint();
            m.backPanel.add(reservationsPage);
        });
        orderButton.addActionListener(e -> {
            m.backPanel.removeAll();
            m.backPanel.revalidate();
            m.backPanel.repaint();
            m.backPanel.add(onlineOrdersPage);
        });
        
        employeesButton.addActionListener(e -> {
            m.backPanel.removeAll();
            m.backPanel.revalidate();
            m.backPanel.repaint();
            m.backPanel.add(employeesPage);
        });
        
        profileButton.addActionListener(e -> {
            m.backPanel.removeAll();
            m.backPanel.revalidate();
            m.backPanel.repaint();
            m.backPanel.add(new ProfilePage(this));
        });
        quitButton.addActionListener(e -> {int answer = JOptionPane.showConfirmDialog(null,
                    "If you quit the application you must login again. Do you want to continue?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (answer == JOptionPane.YES_OPTION) {
            m.setVisible(false);
            m.loginPage.setVisible(true);
            
            }
            
        });
        
        
    }
}
