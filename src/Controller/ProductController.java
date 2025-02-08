package Controller;

import Model.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductController {
    public ProductController() {
        dbContext = new DBContext();
    }
    
    private DBContext dbContext;
    public ArrayList<Product> getAllFoods(){
        ArrayList<Product> allFoods=new ArrayList<>();
        Product food = null;
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "SELECT * FROM products where category= 'Food';";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                food = new Product(rs.getInt("Id"),rs.getString("Name"),rs.getDouble("Price"),rs.getString("Category"),rs.getString("Description"));
                allFoods.add(food);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allFoods;
    }
    public ArrayList<Product> getAllMenus(){
        ArrayList<Product> allMenus=new ArrayList<>();
        Product menu = null;
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "SELECT * FROM products where category= 'Menu';";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                menu = new Product(rs.getInt("Id"),rs.getString("Name"),rs.getDouble("Price"),rs.getString("Category"),rs.getString("Description"));
                allMenus.add(menu);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allMenus;
    }
    public ArrayList<Product> getAllDrinks(){
        ArrayList<Product> allDrinks=new ArrayList<>();
        Product drink = null;
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "SELECT * FROM products where category= 'Drink';";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                drink = new Product(rs.getInt("Id"),rs.getString("Name"),rs.getDouble("Price"),rs.getString("Category"),rs.getString("Description"));
                allDrinks.add(drink);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allDrinks;
    }
    public ArrayList<Product> getAllDesserts(){
        ArrayList<Product> allDesserts=new ArrayList<>();
        Product dessert = null;
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "SELECT * FROM products where category= 'Dessert';";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                dessert = new Product(rs.getInt("Id"),rs.getString("Name"),rs.getDouble("Price"),rs.getString("Category"),rs.getString("Description"));
                allDesserts.add(dessert);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allDesserts;
    }
    public ArrayList<Product> getAllSalads(){
        ArrayList<Product> allSalads=new ArrayList<>();
        Product salad = null;
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "SELECT * FROM products where category= 'Salad';";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                salad = new Product(rs.getInt("Id"),rs.getString("Name"),rs.getDouble("Price"),rs.getString("Category"),rs.getString("Description"));
                allSalads.add(salad);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allSalads;
    }
    public ArrayList<Product> getAllExtras(){
        ArrayList<Product> allExtras=new ArrayList<>();
        Product extra = null;
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "SELECT * FROM products where category= 'Extra';";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                extra = new Product(rs.getInt("Id"),rs.getString("Name"),rs.getDouble("Price"),rs.getString("Category"),rs.getString("Description"));
                allExtras.add(extra);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allExtras;
    }
    
    public Product getProductByName(String productName){
        Product product = null;
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "SELECT * FROM products where name ='"+productName+"';";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                product = new Product(rs.getInt("Id"),rs.getString("Name"),rs.getDouble("Price"),rs.getString("Category"),rs.getString("Description"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
    public Product getProductByID(int id){
        Product product = null;
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "SELECT * FROM products where id ='"+id+"';";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                product = new Product(rs.getInt("Id"),rs.getString("Name"),rs.getDouble("Price"),rs.getString("Category"),rs.getString("Description"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
