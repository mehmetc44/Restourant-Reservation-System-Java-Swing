package Controller;

import Model.Orders;
import Model.Product;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
public class ReportController {

    public ReportController(String date) {
    this.allOrders=orcon.getOrdersByDate(date);
    this.date=date;
    }
    String date;
    ArrayList<Orders> allOrders;
    
    OrderController orcon = new OrderController();
    
    public void createReport()  {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("reports/"+date+"Report.pdf"));
            document.open();
            double totalCost=0;
            HashMap<String,Integer> productAndQuantities = new OrderController().getProductsByDate(date);
            for(Orders order:this.allOrders){
                totalCost =order.getTotalCost();
            }

            
            
            Font boldFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("DAILY SALES REPORT", boldFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            
            document.add(new Paragraph("Date: "+date));
            document.add(new Paragraph("\n\n"));

            
            document.add(new Paragraph("1. Total Sales Amount"));
            document.add(new Paragraph("- Daily Sales: "+totalCost+"$"));
            document.add(new Paragraph("- Total Order Count: "+this.allOrders.size()));
            document.add(new Paragraph("- Average Order Amount: "+totalCost/this.allOrders.size()+"$\n\n"));

            // Sold Products Table
            document.add(new Paragraph("2. Sold Products\n\n"));
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{3, 1, 1, 1});
            
            table.addCell("Product Name");
            table.addCell("Category");
            table.addCell("Quantity");
            table.addCell("Total Cost");
            
            
            for(String name:productAndQuantities.keySet()){
                Product p = new ProductController().getProductByName(name);
                table.addCell(p.getProductName());
                table.addCell(p.getCategory());
                table.addCell(productAndQuantities.get(name).toString());
                table.addCell(Double.toString(p.getPrice()*productAndQuantities.get(name))+"$");
            }
            

            document.add(table);
            document.add(new Paragraph("\n"));
            

            String maxKey = null;
            int maxValue = Integer.MIN_VALUE;

            for (Map.Entry<String, Integer> entry : productAndQuantities.entrySet()) {
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxKey = entry.getKey();
            }
        }

            document.add(new Paragraph("3. Most Popular Product of the Day"));
            document.add(new Paragraph("- Product: "+maxKey));
            document.add(new Paragraph("- Quantity Sold: "+maxValue));
            document.add(new Paragraph("- Revenue: "+maxValue*new ProductController().getProductByName(maxKey).getPrice()+"$"+"\n\n"));

            
            document.add(new Paragraph("Report Prepared by: Restourant Reservation System"));

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy, HH:mm",Locale.US);
        String formattedDateTime = now.format(formatter);
            document.add(new Paragraph(formattedDateTime));

            document.close();
            System.out.println("PDF created successfully!");
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }catch(java.lang.NullPointerException a){
            try{
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("reports/"+date+"Report.pdf"));
            document.open();

            Font boldFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("DAILY SALES REPORT", boldFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            document.add(new Paragraph("Date: "+date));
            document.add(new Paragraph("\n\n"));

       
            document.add(new Paragraph("1. Total Sales Amount"));
            document.add(new Paragraph("- Daily Sales: "+0+"$"));
            document.add(new Paragraph("- Total Order Count: "+0));
            document.add(new Paragraph("- Average Order Amount: "+0+"$\n\n"));

            // Sold Products Table
            document.add(new Paragraph("2. Sold Products\n\n"));
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{3, 1, 1, 1});
            
            table.addCell("Product Name");
            table.addCell("Category");
            table.addCell("Quantity");
            table.addCell("Total Cost");
            
            
            document.add(table);
            document.add(new Paragraph("\n"));
            

            String maxKey = null;
            int maxValue = Integer.MIN_VALUE;



            document.add(new Paragraph("3. Most Popular Product of the Day"));
            document.add(new Paragraph("- Product: "));
            document.add(new Paragraph("- Quantity Sold: "+0));
            document.add(new Paragraph("- Revenue: "+0+"$"+"\n\n"));

            
            document.add(new Paragraph("Report Prepared by: Restourant Reservation System"));

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy, HH:mm",Locale.US);
        String formattedDateTime = now.format(formatter);
            document.add(new Paragraph(formattedDateTime));

            document.close();
            System.out.println("PDF created successfully!");
        }
        catch(FileNotFoundException | DocumentException e) {
            e.printStackTrace();
            
        }
    }
}}
