package Model;

import java.util.HashMap;


public class OnlineOrders  {
    private int orderId;

    public OnlineOrders(int orderId, Customer customer, Employee courier, String adress, double totalCost, String description, String status) {
        this.orderId = orderId;
        this.customer = customer;
        this.courier = courier;
        this.adress = adress;
        this.totalCost = totalCost;
        this.description = description;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getCourier() {
        return courier;
    }

    public void setCourier(Employee courier) {
        this.courier = courier;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    private Customer customer;
    private Employee courier;
    private String adress;
    private double totalCost;
    private String description;
    private String status;
    private HashMap<Product,Integer> productAndQuantities=new HashMap<>();
    
        public void addProductAndQuantity(Product product, int quantity){
        productAndQuantities.put(product, quantity);
    }
    
    
}
