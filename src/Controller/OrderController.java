package Controller;

import Model.*;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Controller.*;
import java.util.HashMap;
public class OrderController {

    public OrderController() {
        dbContext = new DBContext();
    }
    private DBContext dbContext;

    public void enterOrder(Orders o) {
        int id = 0;
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "insert into orders(TableNumber,TotalCost,Discount,UnitCost,PaymentMethod,isPaid) values(" + o.getTableNumber() + ", " + o.getTotalCost() + ", " + o.getDiscount() + ", " + o.getUnitCost() + ", " + o.getPaymentMethod() + ", false);\n";
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            ResultSet rs = stmt.executeQuery("SELECT id FROM orders\nWHere isPaid=false\nORDER BY id DESC\nLIMIT 1;");
            while (rs.next()) {
                id = rs.getInt("id");
            }
            for (Product product : o.getProductAndQuantity().keySet()) {
                stmt.execute("insert into orderdetails(OrderID,ProductID,Quantity)values(" + id + "," + product.getProductID() + "," + o.getProductAndQuantity().get(product) + ");");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Orders getOrderByTableNumber(int tableNumber) {
        ProductController productController = new ProductController();
        Product product;
        Orders orders=null;
        
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "SELECT * FROM orders o, orderDetails od,Products p"+ " "+
               "where o.ID=od.OrderID and p.Id=od.ProductID and o.TableNumber="+tableNumber+ " and o.isPaid=0;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            orders = new Orders(tableNumber);
            while (rs.next()) {
                 product = productController.getProductByID(rs.getInt("ProductID"));
                 orders.addProductAndQuantity(product, rs.getInt("quantity"));
            }
            orders.calculateTotalCost();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public void payOrder(Orders o){
        int id = 0;
        try {
            Connection conn = dbContext.ConnectToDatabase();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM orders\nWHere isPaid=false\nORDER BY id DESC\nLIMIT 1;");
            while (rs.next()) {
                id = rs.getInt("id");
            }
            String query = "update orders set TotalCost="+o.getTotalCost()+",Discount="+o.getDiscount()+",UnitCost="+o.getUnitCost()+",PaymentMethod='"+o.getPaymentMethod()+"', isPaid = 1 where id="+id+";\n";
            stmt.execute(query);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Orders> getOrdersByDate(String date) {
        ProductController productController = new ProductController();
        Product product;
        Orders orders=null;
        ArrayList<Orders> allOrders = new ArrayList<>();
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "SELECT * FROM orders o, orderDetails od,Products p"+ " "+
               "where o.ID=od.OrderID and p.Id=od.ProductID and o.date='"+date+ "' and o.isPaid=1;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            orders = new Orders(0);
            while (rs.next()) {
                 product = productController.getProductByID(rs.getInt("ProductID"));
                 orders.addProductAndQuantity(product, rs.getInt("quantity"));
                 orders.calculateTotalCost();
                 allOrders.add(orders);
            }
            
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allOrders;
    }
    
    public HashMap<String,Integer> getProductsByDate(String date){
        ProductController productController = new ProductController();
        Product product;
        Orders orders=null;
        HashMap<String,ArrayList<Integer>> productAndQuantities = new HashMap<>();
        HashMap<String,Integer> productAndQuantity = new HashMap<>();
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "SELECT * FROM orders o, orderDetails od,Products p"+ " "+
               "where o.ID=od.OrderID and p.Id=od.ProductID and o.date='"+date+ "' and o.isPaid=1;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            orders = new Orders(0);
            while (rs.next()) {
                 product = productController.getProductByID(rs.getInt("ProductID"));
                 productAndQuantities.putIfAbsent(product.getProductName(), new ArrayList<>());
                 if(productAndQuantities.containsKey(product.getProductName())){
                     productAndQuantities.get(product.getProductName()).add(rs.getInt("quantity"));
                 }
            }
            
            for(String Name:productAndQuantities.keySet()){
                int temp=0;
                for(int quantity : productAndQuantities.get(Name)){
                    temp+=quantity;
                }
                productAndQuantity.putIfAbsent(Name, temp);
            }
            
            
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productAndQuantity;
    }
    public ArrayList<OnlineOrders> getAllOnlineOrders(){
        ArrayList<OnlineOrders> allOnlineOrders=new ArrayList<>();
        OnlineOrders onlineOrder = null;
        Customer customer;
        Employee courier;
        Product product;
        try {
            Connection conn = dbContext.ConnectToDatabase();
            String query = "SELECT * FROM onlineOrders oo, OrderDetails od, customer c, products p \n" +
"where oo.id= od.orderID and c.ID = oo.customerID and p.id=od.productID;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                product = new ProductController().getProductByID(rs.getInt("od.ProductID"));
                courier = new EmployeeController().getCourierByID(rs.getInt("oo.CourierID"));
                customer = new Customer(rs.getString("c.Namesurname"),rs.getString("c.phoneNumber"));
                onlineOrder = new OnlineOrders(rs.getInt("oo.id"),customer,courier,rs.getString("oo.adress"),
                rs.getDouble("oo.totalCost"),rs.getString("oo.description"),rs.getString("oo.status"))
                ;
                allOnlineOrders.add(onlineOrder);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allOnlineOrders;
    }
    

}
