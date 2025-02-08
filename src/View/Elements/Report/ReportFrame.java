package View.Elements.Report;

import Controller.ReportController;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

public class ReportFrame {

    public ReportFrame() {
        
        JFrame frame = new JFrame("Daily Report");
        frame.setBounds(233, 110, 500, 400);
        frame.setLayout(null);
        frame.setResizable(false);
        JPanel panel = new JPanel();
        panel.setBackground(Color.red);
        
        
        
        //-------------------------DATE PICK
        
        JLabel lbl = new JLabel("Choose Report Date: ");
        lbl.setBounds(125, 140, 150, 20);
        frame.add(lbl);
        
        Date today = new Date();
        SpinnerDateModel model = new SpinnerDateModel(today, null, null, Calendar.DAY_OF_MONTH);
        JSpinner dateSpinner = new JSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(editor);
        dateSpinner.setBounds(125, 170, 250, 30);
        frame.add(dateSpinner);
        
        JButton getReport= new JButton("Get Report");
        getReport.setBounds(175, 250, 150, 30);
        
        
        
        getReport.addActionListener(w->{
            Date selectedDate = (Date) dateSpinner.getValue(); 
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
            String formattedDate = dateFormat.format(selectedDate);
            System.out.println(formattedDate);
            ReportController rc = new ReportController(formattedDate);
            rc.createReport();
            JOptionPane.showMessageDialog(getReport, "Daily report has been created!");
        });
        
        
        frame.add(getReport);
        frame.setVisible(true);
        
    }
    
}
