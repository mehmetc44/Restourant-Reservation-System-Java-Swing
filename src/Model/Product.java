package Model;

import java.text.DecimalFormat;

public class Product {

    private String productName;
    private int productID;
    private double price;
    private String category;
    private String description="";
    
    public Product(int productID,String productName, double price, String category, String description) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.description = description;
        this.productID=productID;
    }
    public Product(String productName, double price, String category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }
    
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getPrice() {
        return price;
    }
}
