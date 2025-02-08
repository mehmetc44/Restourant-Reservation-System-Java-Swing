package Model;

import java.awt.Color;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class Table extends JButton{
    public Table(int tableNumber, int capacity){
        this.capacity=capacity;
        this.tableNumber=tableNumber;
        this.setText(Integer.toString(tableNumber));
        this.setBackground(Color.WHITE);        
    }
    public Table(int tableNumber, int capacity,Orders orders){
        this.capacity=capacity;
        this.tableNumber=tableNumber;
        this.orders=orders;
        this.setText(Integer.toString(tableNumber));
        this.setBackground(Color.WHITE);
    }
    

    private boolean isReservated=false;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }
    private boolean isFree=true;
    private int capacity;
    private int tableNumber;
    private Orders orders;
    public String statement="Free";
    
    
    public boolean isReservated() {
        
        return isReservated;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        if("Full".equals(statement)){
            this.statement=statement;
            this.isReservated=false;
            this.isFree=false;
        }else if("Reservated".equals(statement)){
            this.statement=statement;
            this.isReservated=true;
            this.isFree=false;
        }else if("Free".equals(statement)){
            this.statement=statement;
            this.isReservated=false;
            this.isFree=true;
        }
    }


    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getTableNumber() {
        return tableNumber;
    }
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
