package Model;

import java.util.HashMap;
import java.util.Date;

public class Orders {

    public Orders(int tableNumber) {
        this.tableNumber = tableNumber;
        isPaid=false;
        this.discount=0;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    private int tableNumber;
    private HashMap<Product,Integer> productAndQuantities=new HashMap<>();
    private double totalCost;
    private double discount;
    private double unitCost;
    private boolean isPaid;
    private String paymentMethod;
    private String date;
    public void addProductAndQuantity(Product product, int quantity){
        productAndQuantities.put(product, quantity);
    }
    public HashMap<Product,Integer> getProductAndQuantity(){
        return productAndQuantities;
    }
    
    public void calculateUnitcost(double discount){
        this.unitCost=totalCost-discount;
    }
    public double calculateTotalCost(){
        double total =0;
        for(Product product:productAndQuantities.keySet()){
            total+=product.getPrice()*productAndQuantities.get(product);
        }
        this.totalCost=total;
        this.unitCost=this.totalCost;
        return total;
    }
    

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
