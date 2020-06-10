/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package welcomebookshop;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart; 
import org.jfree.data.category.DefaultCategoryDataset;        
/**
 *
 * @author Sandu
 */
public class DBOperations {
    private static DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd hh:mm");
    String url="jdbc:mysql://localhost:3306/welcomebookshop.";
    String username="root";
    String password="";
    Connection con =null;
    PreparedStatement pst,pst1,pst2,pst3,pst4,pst5=null;
    ResultSet rs,rs1,rs2,rs3,rs5=null;
    java.sql.Date date=new java.sql.Date(0000-00-00);
        
        public String setdate(){
        Date date=new Date();
        DateFormat formatd=new SimpleDateFormat("yyyy-MM-dd");
        String d=dateformat.format(date);
        return d;
        }
    int checklogin(String userName,String Password){
        try{
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT userName,password,position,name FROM logindetails";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            
            while(rs.next()){
                
                if(userName.equals(rs.getString(1))&& Password.equals(rs.getString(2))&& (rs.getString(3).equals("Admin")))
                    return 0;
                    else if(userName.equals(rs.getString(1))&& Password.equals(rs.getString(2))&& (rs.getString(3).equals("Cashier")))
                        return 2;
                }
        }
        catch(Exception e){
            System.out.print(e);
          
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
        return 3;
    }
    boolean addSupplier(SupplierDetails supplier){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="INSERT INTO suppliers VALUES (?,?,?,?,?,?,?)";
        pst=(PreparedStatement)con.prepareStatement(query);
        
        pst.setString(1, supplier.getSupId());
        pst.setString(2, supplier.getName());
        pst.setString(3, supplier.getCatagory());
        pst.setString(4, supplier.getAddress()); 
        pst.setString(5, supplier.getEmail());
        pst.setString(6, supplier.getSalesRep());
        pst.setInt(7, supplier.getTele());
        
        pst.executeUpdate();
        return true;
        
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
    
    boolean UpdateSupplier(SupplierDetails supplier){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="update suppliers set salesRep='"+supplier.getSalesRep()+"',tele='"+supplier.getTele()+"' where supId='"+supplier.getSupId()+"'";
        pst=(PreparedStatement)con.prepareStatement(query);
   
        
        pst.executeUpdate();
        return true;
        
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
    
    boolean RemoveSupplier(SupplierDetails supplier){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="DELETE FROM suppliers where supId='"+supplier.getSupId()+"'";
        pst=(PreparedStatement)con.prepareStatement(query);
   
        
        pst.executeUpdate();
        return true;
        
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
    
   public String setname(String userName){
        String s="";
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT userName,name FROM logindetails";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            while(rs.next()){
                if(userName.equals(rs.getString(1)))
                    s= rs.getString(2);
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public String setStock(){
     String s="";
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT * FROM stock";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            while(rs.next()){
                if("Books".equals(rs.getString(3))){
                if(rs.getInt(5)<25){
                  s=s+"Low Stock "+rs.getString(2)+"\r\n";
                }
            }
                if("Stationaries".equals(rs.getString(3))){
                if(rs.getInt(5)<50){
                    s=s+"Low Stock "+rs.getString(2)+"\r\n";
                }
            }
               if("Reloads".equals(rs.getString(3))){
                if(rs.getInt(5)<10){
                    s=s+"Low Stock "+rs.getString(2)+"\r\n";
                }
            }
               if("Toys".equals(rs.getString(3))){
                if(rs.getInt(5)<10){
                    s=s+"Low Stock "+rs.getString(2)+"\r\n";
                }
            }
               if("Seasonal".equals(rs.getString(3))){
                if(rs.getInt(5)<10){
                    s=s+"Low Stock "+rs.getString(2)+"\r\n";
                }
            }
               if("Fancy Items".equals(rs.getString(3))){
                if(rs.getInt(5)<10){
                    s=s+"Low Stock "+rs.getString(2)+"\r\n";
                }
            }
                }
            
        }
        
            catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
}
    
    public String setOrderNot(){
        String s="";
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT * FROM orderdetails";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            while(rs.next()){
               if("Complete".equals(rs.getString(6)))
                    s=s+""+rs.getString(1)+"\t "+rs.getDate(5).toString()+"\t to be paid Rs."+rs.getString(7)+"/="+"\r\n";
               else if("Incomplete".equals(rs.getString(6)))
                  s=s+""+rs.getString(1)+"\t "+rs.getDate(5)+"\t"+rs.getString(6)+"\r\n";
                else if("In progress".equals(rs.getString(6)))
                    s=s+""+rs.getString(1)+"\t "+rs.getDate(5)+"\t"+rs.getString(6)+"\r\n";
                } 
        }
            catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
}
    
    public int setOrderNo(){
        int num=0;
        int count=0;
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT orderNo FROM orderdetails";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
           while(rs.next()){
                count++;
            }
            num=1000+count;
           } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return num;
    }
    public int setItemNo(orderItems item){
        int num=1;
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT itemNo FROM orderitems where orderNo='"+item.getOrderNo()+"'";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            int count=0;
            while(rs.next()){
                count++;
            }
            num+=count;
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return num;
    }
        boolean addordItem(orderItems item){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="INSERT INTO orderitems VALUES (?,?,?,?)";
        pst=(PreparedStatement)con.prepareStatement(query);
        
        pst.setInt(1, item.getOrderNo());
        pst.setString(2, item.getItemNo());
        pst.setString(3, item.getItemCode());
        pst.setString(4, Integer.toString(item.getQuanty())); 
        
        pst.executeUpdate();
        return true;
        
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
         boolean RemoveOrderItem(stockDetails item,String p){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="SELECT itemcode,quantity,unitPrice from stock where name='"+item.getName()+"'";
        pst=(PreparedStatement)con.prepareStatement(query);
        rs=pst.executeQuery();
        while(rs.next()){
        
                int quanta=rs.getInt(2)+(item.getQuantity());   
               double price=rs.getDouble(3)*item.getQuantity();
                String query2="update stock set quantity='"+(quanta)+"' where itemcode='"+rs.getString(1)+"'";
                pst2=(PreparedStatement)con.prepareStatement(query2);
                pst2.executeUpdate();
                
                String query3="SELECT orderNo from orderitems where itemNo='"+p+"'";
                pst3=(PreparedStatement)con.prepareStatement(query3);
                rs1=pst3.executeQuery();
                
                while(rs1.next()){
                String query4="SELECT subTotal from orderdetails where orderNo='"+rs1.getString(1)+"'";
                pst4=(PreparedStatement)con.prepareStatement(query4);
                rs2=pst4.executeQuery();
                while(rs2.next()){
                String query5="update orderdetails set subTotal='"+(rs2.getDouble(1)-price)+"' where orderNo='"+rs1.getString(1)+"'";
                pst5=(PreparedStatement)con.prepareStatement(query5);
                pst5.executeUpdate();
                }
                String query1="DELETE FROM orderitems where itemNo='"+p+"'";
                 pst1=(PreparedStatement)con.prepareStatement(query1); 
                    pst1.executeUpdate();
                }
            
        return true;
        }
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
        return false;
    }
        public float setTotal(orderItems item){
      float total=item.getTotal();
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT unitPrice FROM stock where itemcode='"+item.getItemCode()+"'";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            while(rs.next()){
           total=total+(Float.parseFloat(rs.getString(1))*item.getQuanty());
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }  
            return total;
    }
         public float setsubTotal(orderItems item){
      float subtotal=0;
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT unitPrice FROM stock where itemcode='"+item.getItemCode()+"'";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            while(rs.next())
           subtotal=(Float.parseFloat(rs.getString(1))*item.getQuanty());
          
           
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return subtotal; 
    }
         public int getquant(orderItems item){
      float subtotal=0;
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT quantity FROM stock where itemcode='"+item.getItemCode()+"'";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            while(rs.next())
           return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
         boolean addOrder(orderDetails order){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="INSERT INTO orderdetails VALUES (?,?,?,?,?,?,?)";
        pst=con.prepareStatement(query);
        
        pst.setString(1, order.getOrderNo());
        pst.setObject(2,(order.getDate()));
        pst.setString(3, order.getCusName());
        pst.setString(4, order.getTele()); 
        pst.setObject(5,(order.getSubDate()));
        pst.setString(6, order.getStatus());
        pst.setString(7, order.getTotal());
        
        pst.executeUpdate();
        return true;
        
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
   
         boolean updateOrder(orderDetails order){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="update orderdetails set customer='"+order.getCusName()+"',tele='"+order.getTele()+"',status='"+order.getStatus()+"',subTotal='"+order.getTotal()+"' where orderNo='"+order.getOrderNo()+"'";
        pst=(PreparedStatement)con.prepareStatement(query);
        pst.executeUpdate();
        return true; 
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
       public boolean removeStock(stockDetails stock){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query1="SELECT quantity FROM stock where itemcode='"+stock.getItemcode()+"'";
        pst =(PreparedStatement)con.prepareStatement(query1);
       rs= pst.executeQuery();
            while(rs.next()){
                int quanta=Integer.parseInt(rs.getString(1))-(stock.getQuantity());   
                String query="update stock set quantity='"+(quanta)+"' where itemcode='"+stock.getItemcode()+"'";
                pst=(PreparedStatement)con.prepareStatement(query);
                pst.executeUpdate();
            }  
        return true;
        
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
          boolean addStock(stockDetails stock){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query1="SELECT quantity FROM stock where itemcode='"+stock.getItemcode()+"'";
        pst =(PreparedStatement)con.prepareStatement(query1);
       rs= pst.executeQuery();
            while(rs.next()){
                int quanta=Integer.parseInt(rs.getString(1))+(stock.getQuantity());   
                String query="update stock set quantity='"+(quanta)+"' where itemcode='"+stock.getItemcode()+"'";
                pst=(PreparedStatement)con.prepareStatement(query);
                pst.executeUpdate();
            }  
        return true;
        
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
         public String[] addcmbitems(String cmb){ 
             String s[] = null;
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT name FROM suppliers where catagory='"+cmb+"'";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            int i=0,j=0;
            while(rs.next()){
                j++;
                }
            s=new String [j];
            rs= pst.executeQuery();
            while(rs.next()){
                s[i]=rs.getString(1);
                i++;
                }
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
  return s;
         }
         
          public String[] addcmbitems(){ 
             String s[] = null;
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT name FROM categories";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            int i=1,j=0;
            while(rs.next()){
                j++;
                }
            s=new String [j+1];
            s[0]="Select Category";
            rs= pst.executeQuery();
            while(rs.next()){
                s[i]=rs.getString(1);
                i++;
                }
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
  return s;
         }
         
          public void addcategory(String s){
             try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="INSERT INTO categories VALUES (?,?)";
        pst=(PreparedStatement)con.prepareStatement(query);
        
        pst.setString(1, null);
        pst.setString(2, s);
        
        pst.executeUpdate();
        
        
        }
        catch(Exception e){
            System.out.print(e);
           } 
          }
          
          
String getSupId(SupplierDetails supplier){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="SELECT SUPiD FROM suppliers where name='"+supplier.getName()+"'";
        pst=(PreparedStatement)con.prepareStatement(query);
        rs=pst.executeQuery();
        while(rs.next())    
        return rs.getString(1);
        
        }
        catch(Exception e){
            System.out.print(e);
            return null;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
        return null;
    }    
  
 boolean addNewStock(stockDetails stock){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="INSERT INTO stock VALUES (?,?,?,?,?,?,?)";
        pst=(PreparedStatement)con.prepareStatement(query);
        
        pst.setString(1, stock.getItemcode());
        pst.setString(2, stock.getName());
        pst.setString(3, stock.getCategory());
        pst.setString(4, stock.getSupId()); 
        pst.setString(5, Integer.toString(stock.getQuantity()));
        pst.setString(6, stock.getBuyingPrice());
        pst.setString(7, stock.getUnitPrice());
        
        pst.executeUpdate();
        return true;
        
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
 
 boolean updateStock(stockDetails stock){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="update stock set name='"+stock.getName()+"',quantity='"+stock.getQuantity()+"',unitPrice='"+stock.getUnitPrice()+"' where itemcode='"+stock.getItemcode()+"'";
        pst=(PreparedStatement)con.prepareStatement(query);
        pst.executeUpdate();
        return true; 
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
 
 boolean deleteStock(stockDetails stock){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="DELETE FROM stock where itemcode='"+stock.getItemcode()+"'";
        pst=(PreparedStatement)con.prepareStatement(query);
   
        
        pst.executeUpdate();
        return true;
        
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
 
public UserDetails viewProfile(String Name){
     UserDetails us=new UserDetails();
     us.setName(Name);
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT * FROM logindetails";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            while(rs.next()){
                if(Name.equals(rs.getString(1))){
                    us.setUserName(rs.getString(2));
                    us.setPosition(rs.getString(3));
                    us.setTele(Integer.parseInt(rs.getString(4)));
                    us.setEmail(rs.getString(5));
                    us.setPassword(rs.getString(6));
                    us.setImage(rs.getBytes(7));
                }
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return us;
    }
 
 public boolean updateuser(UserDetails user){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="update logindetails set userName='"+user.getUserName()+"',tele='"+user.getTele()+"',email='"+user.getEmail()+"' where name='"+user.getName()+"'";
        pst=(PreparedStatement)con.prepareStatement(query);
        pst.executeUpdate();
        return true; 
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
 
 int changepassword(UserDetails user){
        try{
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT name,password FROM logindetails";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            
            while(rs.next()){
                if(user.getName().equals(rs.getString(1))&& user.getPassword().equals(rs.getString(2)))
                    return 1;
                
                else if(user.getName().equals(rs.getString(1))&& user.getPassword()!=(rs.getString(2)))
                    return 2;
                
            else if(user.getName()!=(rs.getString(1))&& user.getPassword()==(rs.getString(2)))
                    return 4;
            }   
        }
        catch(Exception e){
            System.out.print(e);
            return 3;
        }
        return 1;
 }
 
 boolean updatepass(UserDetails user){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="update logindetails set password='"+user.getPassword()+"' where name='"+user.getName()+"'";
        pst=(PreparedStatement)con.prepareStatement(query);
        pst.executeUpdate();
        return true; 
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
 
 boolean addUser(UserDetails user){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="INSERT INTO logindetails VALUES (?,?,?,?,?,?,?)";
        pst=(PreparedStatement)con.prepareStatement(query);
        
        pst.setString(1, user.getName());
        pst.setString(2, user.getUserName());
        pst.setString(3, user.getPosition());
        pst.setString(4, Integer.toString(user.getTele())); 
        pst.setString(5, user.getEmail());
        pst.setString(6, user.getPassword());
        pst.setBytes(7, user.getImage());
        
        pst.executeUpdate();
        return true;
        
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }

  boolean deleteUser(UserDetails user){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="DELETE FROM logindetails where name='"+user.getName()+"'";
        pst=(PreparedStatement)con.prepareStatement(query);
   
        
        pst.executeUpdate();
        return true;
        
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
  public int setPurchNo(){
        int count=1;
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT Num FROM purchdetails";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
           while(rs.next()){
                count++;
            }
           } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
    }
    public int setPurchItemNo(orderItems item){
        int num=1;
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT itemNo FROM purchitems where purchNum='"+item.getOrderNo()+"'";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            int count=0;
            while(rs.next()){
                count++;
            }
            num+=count;
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return num;
    }
    
    boolean addpurchItem(orderItems item){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="INSERT INTO purchitems VALUES (?,?,?,?)";
        pst=(PreparedStatement)con.prepareStatement(query);
        
        pst.setInt(1, item.getOrderNo());
        pst.setString(2, item.getItemNo());
        pst.setString(3, item.getItemCode());
        pst.setString(4, Integer.toString(item.getQuanty())); 
        
        pst.executeUpdate();
        return true;
        
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
    
     boolean addPurchase(PurchDetails purch){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="INSERT INTO purchdetails VALUES (?,?,?,?,?,?)";
        pst=(PreparedStatement)con.prepareStatement(query);
        pst.setInt(1, purch.getNum());
        pst.setObject(2, purch.getDate());
        pst.setString(3, purch.getCategory());
        pst.setString(4, purch.getSupplier());
        pst.setString(5, purch.getUser()); 
        pst.setDouble(6, purch.getTotal());
        
        pst.executeUpdate();
        return true;
        
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
            public float setPTotal(orderItems item){
      float total=item.getTotal();
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT BuyingPrice FROM stock where itemcode='"+item.getItemCode()+"'";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            while(rs.next()){
           total=total+(Float.parseFloat(rs.getString(1))*item.getQuanty());
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return total; 
    } 
    public int setSaleNo(){
        int count=1;
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT saleNo FROM salesdetails";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
           while(rs.next()){
                count++;
            }
           } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
    }
    public int setSaleItemNo(orderItems item){
        int num=1;
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT itemNo FROM sales where saleNo='"+item.getOrderNo()+"'";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            int count=0;
            while(rs.next()){
                count++;
            }
            num+=count;
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return num;
    }
    public int setSaleItemNo(int item){
        int num=1;
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT itemNo FROM sales where saleNo='"+item+"'";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            int count=0;
            while(rs.next()){
                count++;
            }
            num+=count;
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return num;
    }
    
   public boolean addSaleItem(orderItems item){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="INSERT INTO sales VALUES (?,?,?,?)";
        pst=(PreparedStatement)con.prepareStatement(query);
        
        pst.setInt(1, item.getOrderNo());
        pst.setString(2, item.getItemNo());
        pst.setString(3, item.getItemCode());
        pst.setString(4, Integer.toString(item.getQuanty())); 
        
        pst.executeUpdate();
        return true;
        
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
    }
   
    
     public boolean addSale(orderDetails purch){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="INSERT INTO salesdetails VALUES (?,?,?,?,?)";
        pst=(PreparedStatement)con.prepareStatement(query);
        pst.setString(1, purch.getOrderNo());
        pst.setObject(2, purch.getDate());
        pst.setString(3, purch.getStatus());
        pst.setString(4, purch.getCusName());
        pst.setString(5, purch.getTotal());
        
        pst.executeUpdate();
        return true;
        
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
    }
     public boolean orderSale(orderDetails order,LocalDate d){
         int saleno=this.setSaleNo();
         int i=0;
         int y=setSaleItemNo(saleno);
         try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT itemCode,quantity FROM orderitems where orderNo='"+order.getOrderNo()+"'";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            orderItems saleit=new orderItems();
            stockDetails sk=new stockDetails();
            while(rs.next()){
                i++;
                String itemcode=rs.getString(1);
                int quantity=rs.getInt(2);
                saleit.setOrderNo(saleno);
                saleit.setItemNo(saleno+"-"+y);
                saleit.setItemCode(itemcode);
                saleit.setQuanty(quantity);
                addSaleItem(saleit);
                y++;
            }
            
            while(i>0){
            String query2="SELECT itemCode,quantity FROM orderitems where itemNo='"+(order.getOrderNo()+"-"+i)+"'";
            pst1 =(PreparedStatement)con.prepareStatement(query2);
            rs5= pst1.executeQuery();
            while(rs5.next()){
                String itemcode=rs5.getString(1);
                int quantity=rs5.getInt(2);
               sk.setItemcode(itemcode);
               sk.setQuantity(quantity);
              i= SaleYearly(sk,d,i);
               i--;
            }
            
            }
            orderDetails sales=new orderDetails();
               sales.setOrderNo(Integer.toString(saleno));
             try {
                    DateFormat formatd=new SimpleDateFormat("yyyy-MM-dd");
                    Date datef=formatd.parse(setdate());
                    sales.setDate(datef);
                        } catch (ParseException ex) {
                        Logger.getLogger(AddPurchase.class.getName()).log(Level.SEVERE, null, ex);
                            }
               sales.setCusName(order.getCusName());
               sales.setTotal(order.getTotal());
               sales.setStatus("Order");
               boolean t=addSale(sales);
               
               if(t){
                  return true;
               }     
        } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
         return false;
     }
     
     public double daypurch(Date date,String user){
        double total=0;
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT total,date FROM purchdetails";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            int dy=date.getYear();
            int dm=date.getMonth();
            int dd=date.getDate();
                          while(rs.next()){
                              Date sdate=rs.getDate(2);
                                    if(dy==sdate.getYear()){
                                        if(dm==sdate.getMonth()){
                                           if(dd==sdate.getDate()){
                                                    total=total+rs.getDouble(1);
                                           }
                                        }
                                    }
                                }

           } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return total;
    }
     public double daysale(Date date,String user,String type){
        double total=0;
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1;
            if(type=="cashier")
            query1="SELECT total,date FROM salesdetails where user='"+user+"'";
            else
             query1="SELECT total,date FROM salesdetails";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            int dy=date.getYear();
            int dm=date.getMonth();
            int dd=date.getDate();
                          while(rs.next()){
                              Date sdate=rs.getDate(2);
                                    if(dy==sdate.getYear()){
                                        if(dm==sdate.getMonth()){
                                           if(dd==sdate.getDate()){
                                                    total=total+rs.getDouble(1);
                                           }
                                        }
                                    }
                                }
           } catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return total;
    }
          public boolean addSalary(salaryDetails sal){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="INSERT INTO salaryDetails VALUES (?,?,?,?,?)";
        pst=(PreparedStatement)con.prepareStatement(query);
        pst.setString(1, null);
        pst.setObject(2, (sal.getDate()));
        pst.setString(3, sal.getName());
        pst.setString(4, sal.getOnth());
        pst.setString(5, Double.toString(sal.getSalary()));
        
        pst.executeUpdate();
        return true;
        
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
          public boolean addUtility(utilityDetails util){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="INSERT INTO utilitydetails VALUES (?,?,?,?)";
        pst=(PreparedStatement)con.prepareStatement(query);
        pst.setString(1, null);
        pst.setObject(2, util.getDate());
        pst.setString(3, util.getType());
        pst.setString(4, Double.toString(util.getAmount()));
        pst.executeUpdate();
        return true;
        }
        catch(Exception e){
            System.out.print(e);
            return false;
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
          
    public boolean SaleYearly(stockDetails sk,LocalDate d){
            int dy=d.getYear();
            int dm=d.getMonthValue();
            int dd=d.getDayOfMonth();
        int x=0;
        double total=0;
      String item=sk.getItemcode();
      int quantity=sk.getQuantity();
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT category FROM stock where itemcode='"+item+"'";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
          while(rs.next()){
          // total=total+(rs.getDouble(1)*quantity);
           String cat=rs.getString(1);
           String query2="SELECT Year,Category,total,item FROM yearly";
            pst =(PreparedStatement)con.prepareStatement(query2);
            rs1= pst.executeQuery();
            while(rs1.next()){
                if((dy==rs1.getInt(1)) && (cat.equals(rs1.getString(2))) && item.equals(rs1.getString(4))){
                        x=0;
                        double ctotal=quantity+rs1.getDouble(3);
                        String query3="update yearly set total='"+ctotal+"' where Year='"+dy+"' && item='"+item+"'";
                        pst=(PreparedStatement)con.prepareStatement(query3);
                        pst.executeUpdate();
                        break;
                        }
                        else{
                                x=1;
                                }
            }
            if(x==1){
            String query3="INSERT INTO yearly VALUES (?,?,?,?,?)";
                            pst=(PreparedStatement)con.prepareStatement(query3);
                            pst.setString(1,null);
                            pst.setInt(2, dy);
                            pst.setString(3,cat);
                            pst.setString(4, item);
                            pst.setDouble(5, quantity);
                            pst.executeUpdate();
                }
            String query3="SELECT year,month,category,total,item FROM monthlysale";
            pst =(PreparedStatement)con.prepareStatement(query3);
            rs2= pst.executeQuery();
            while(rs2.next()){
                if((dy==rs2.getInt(1)) && (dm==rs2.getInt(2)) && (cat.equals(rs2.getString(3))) && item.equals(rs2.getString(5))){
                        x=0;
                        double ctotal=quantity+rs2.getDouble(4);
                        String query4="update monthlysale set total='"+ctotal+"' where year='"+dy+"' && category='"+cat+"' && item='"+item+"' && month='"+dm+"'";
                        pst=(PreparedStatement)con.prepareStatement(query4);
                        pst.executeUpdate();
                        break;
                }
                else{
                       x=1;
                    }
            }
                if(x==1){
            String query4="INSERT INTO monthlysale VALUES (?,?,?,?,?,?)";
                            pst=(PreparedStatement)con.prepareStatement(query4);
                            pst.setString(1,null);
                            pst.setInt(2, dy);
                            pst.setInt(3, dm);
                            pst.setString(4,cat);
                            pst.setString(5, item);
                            pst.setDouble(6, quantity);
                            pst.executeUpdate();
            }
                String query5="SELECT year,month,day,category,total,item FROM dailysale";
            pst =(PreparedStatement)con.prepareStatement(query5);
            rs2= pst.executeQuery();
            while(rs2.next()){
                if((dy==rs2.getInt(1)) && (dm==rs2.getInt(2)) && (dd==rs2.getInt(3)) && (cat.equals(rs2.getString(4))) && item.equals(rs2.getString(6))){
                        x=0;
                        double ctotal=quantity+rs2.getDouble(5);
                        String query6="update dailysale set total='"+ctotal+"' where year='"+dy+"' && category='"+cat+"' && item='"+item+"' && day='"+dd+"' && month='"+dm+"'";
                        pst=(PreparedStatement)con.prepareStatement(query6);
                        pst.executeUpdate();
                        break;
                }
                else{
                                x=1;
                                }
            }
                if(x==1){
            String query6="INSERT INTO dailysale VALUES (?,?,?,?,?,?,?)";
                            pst=(PreparedStatement)con.prepareStatement(query6);
                            pst.setString(1,null);
                            pst.setInt(2, dy);
                            pst.setInt(3, dm);
                            pst.setInt(4,dd);
                            pst.setString(5, cat);
                            pst.setString(6, item);
                            pst.setDouble(7, quantity);
                            pst.executeUpdate();
            }      
            }//while
        }//try
           catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return true; 
    }
    public int SaleYearly(stockDetails sk,LocalDate d,int i){
            int dy=d.getYear();
            int dm=d.getMonthValue();
            int dd=d.getDayOfMonth();
        int x=0;
        double total=0;
      String item=sk.getItemcode();
      int quantity=sk.getQuantity();
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT category FROM stock where itemcode='"+item+"'";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
          while(rs.next()){
           String cat=rs.getString(1);
           String query2="SELECT Year,Category,total,item FROM yearly";
            pst =(PreparedStatement)con.prepareStatement(query2);
            rs1= pst.executeQuery();
            while(rs1.next()){
                if((dy==rs1.getInt(1)) && (cat.equals(rs1.getString(2))) && item.equals(rs1.getString(4))){
                        x=0;
                        double ctotal=quantity+rs1.getDouble(3);
                        String query3="update yearly set total='"+ctotal+"' where Year='"+dy+"' && item='"+item+"'";
                        pst=(PreparedStatement)con.prepareStatement(query3);
                        pst.executeUpdate();
                        break;
                        }
                        else{
                                x=1;
                                }
            }
            if(x==1){
            String query3="INSERT INTO yearly VALUES (?,?,?,?,?)";
                            pst=(PreparedStatement)con.prepareStatement(query3);
                            pst.setString(1,null);
                            pst.setInt(2, dy);
                            pst.setString(3,cat);
                            pst.setString(4, item);
                            pst.setDouble(5, quantity);
                            pst.executeUpdate();
                }
            String query3="SELECT year,month,category,total,item FROM monthlysale";
            pst =(PreparedStatement)con.prepareStatement(query3);
            rs2= pst.executeQuery();
            while(rs2.next()){
                if((dy==rs2.getInt(1)) && (dm==rs2.getInt(2)) && (cat.equals(rs2.getString(3))) && item.equals(rs2.getString(5))){
                        x=0;
                        double ctotal=quantity+rs2.getDouble(4);
                        String query4="update monthlysale set total='"+ctotal+"' where year='"+dy+"' && category='"+cat+"' && item='"+item+"' && month='"+dm+"'";
                        pst=(PreparedStatement)con.prepareStatement(query4);
                        pst.executeUpdate();
                        break;
                }
                else{
                       x=1;
                    }
            }
                if(x==1){
            String query4="INSERT INTO monthlysale VALUES (?,?,?,?,?,?)";
                            pst=(PreparedStatement)con.prepareStatement(query4);
                            pst.setString(1,null);
                            pst.setInt(2, dy);
                            pst.setInt(3, dm);
                            pst.setString(4,cat);
                            pst.setString(5, item);
                            pst.setDouble(6, quantity);
                            pst.executeUpdate();
            }
                String query5="SELECT year,month,day,category,total,item FROM dailysale";
            pst =(PreparedStatement)con.prepareStatement(query5);
            rs2= pst.executeQuery();
            while(rs2.next()){
                if((dy==rs2.getInt(1)) && (dm==rs2.getInt(2)) && (dd==rs2.getInt(3)) && (cat.equals(rs2.getString(4))) && item.equals(rs2.getString(6))){
                        x=0;
                        double ctotal=quantity+rs2.getDouble(5);
                        String query6="update dailysale set total='"+ctotal+"' where year='"+dy+"' && category='"+cat+"' && item='"+item+"' && day='"+dd+"' && month='"+dm+"'";
                        pst=(PreparedStatement)con.prepareStatement(query6);
                        pst.executeUpdate();
                        break;
                }
                else{
                                x=1;
                                }
            }
                if(x==1){
            String query6="INSERT INTO dailysale VALUES (?,?,?,?,?,?,?)";
                            pst=(PreparedStatement)con.prepareStatement(query6);
                            pst.setString(1,null);
                            pst.setInt(2, dy);
                            pst.setInt(3, dm);
                            pst.setInt(4,dd);
                            pst.setString(5, cat);
                            pst.setString(6, item);
                            pst.setDouble(7, quantity);
                            pst.executeUpdate();
            }      
            }//while
        }//try
           catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
 return i--;
    }
    public boolean purchaseYearly(stockDetails sk,LocalDate d){
            int dy=d.getYear();
            int dm=d.getMonthValue();
            int dd=d.getDayOfMonth();
        int x=0;
        double total=0;
        String item=sk.getItemcode();
        int quantity=sk.getQuantity();
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1="SELECT BuyingPrice,category FROM stock where itemcode='"+item+"'";
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();
            while(rs.next()){
            //total=(rs.getDouble(1)*quantity);
            String cat=rs.getString(2);
            String query2="SELECT Year,Category,Total,item FROM purcyearly";
            pst =(PreparedStatement)con.prepareStatement(query2);
            rs1= pst.executeQuery();
            while(rs1.next()){
                if((dy==rs1.getInt(1)) && (cat.equals(rs1.getString(2))) && item.equals(rs1.getString(4))){
                        x=0;
                        double ctotal=quantity+rs1.getDouble(3);
                        String query3="update purcyearly set total='"+ctotal+"' where Year='"+dy+"' && item='"+item+"'";
                        pst=(PreparedStatement)con.prepareStatement(query3);
                        pst.executeUpdate();
                        break;
                        }
                        else{
                                x=1;
                                }
            }
            if(x==1){
            String query3="INSERT INTO purcyearly VALUES (?,?,?,?,?)";
                            pst=(PreparedStatement)con.prepareStatement(query3);
                            pst.setString(1,null);
                            pst.setInt(2, dy);
                            pst.setString(3,cat);
                            pst.setString(4, item);
                            pst.setDouble(5, quantity);
                            pst.executeUpdate();
                }
            String query3="SELECT Year,Month,Category,total,item FROM purchmonthly";
            pst =(PreparedStatement)con.prepareStatement(query3);
            rs2= pst.executeQuery();
            while(rs2.next()){
                if((dy==rs2.getInt(1)) && (dm==rs2.getInt(2)) && (cat.equals(rs2.getString(3))) && item.equals(rs2.getString(5))){
                        x=0;
                        double ctotal=quantity+rs2.getDouble(4);
                        String query4="update purchmonthly set total='"+ctotal+"' where Year='"+dy+"' && Category='"+cat+"' && item='"+item+"' && Month='"+dm+"'";
                        pst=(PreparedStatement)con.prepareStatement(query4);
                        pst.executeUpdate();
                        break;
                }
                else{
                       x=1;
                    }
            }
                if(x==1){
            String query4="INSERT INTO purchmonthly VALUES (?,?,?,?,?,?)";
                            pst=(PreparedStatement)con.prepareStatement(query4);
                            pst.setString(1,null);
                            pst.setInt(2, dy);
                            pst.setInt(3, dm);
                            pst.setString(4,cat);
                            pst.setString(5, item);
                            pst.setDouble(6, quantity);
                            pst.executeUpdate();
            }
                String query5="SELECT Year,Month,Date,Category,total,item FROM purchdaily";
                pst =(PreparedStatement)con.prepareStatement(query5);
                rs2= pst.executeQuery();
                while(rs2.next()){
                    if((dy==rs2.getInt(1)) && (dm==rs2.getInt(2)) && (dd==rs2.getInt(3)) && (cat.equals(rs2.getString(4))) && item.equals(rs2.getString(6))){
                        x=0;
                        double ctotal=quantity+rs2.getDouble(5);
                        String query6="update purchdaily set total='"+ctotal+"' where Year='"+dy+"' && Category='"+cat+"' && item='"+item+"' && Date='"+dd+"' && Month='"+dm+"'";
                        pst=(PreparedStatement)con.prepareStatement(query6);
                        pst.executeUpdate();
                        break;
                }
                else{
                                x=1;
                                }
            }
                if(x==1){
            String query6="INSERT INTO purchdaily VALUES (?,?,?,?,?,?,?)";
                            pst=(PreparedStatement)con.prepareStatement(query6);
                            pst.setString(1,null);
                            pst.setInt(2, dy);
                            pst.setInt(3, dm);
                            pst.setInt(4,dd);
                            pst.setString(5, cat);
                            pst.setString(6, item);
                            pst.setDouble(7, quantity);
                            pst.executeUpdate();
            }      
            }//while
        }//try
           catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       return true; 
    }
    
    public void report(String type,String cat,String prof,String period,String day,String month){
        Date date=new Date();
        LocalDate ldate=date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
        try {
            con=(Connection)DriverManager.getConnection(url, username, password);
            String query1;
            if(type=="Category"){
                
                if(prof=="Sales"){
 //**************************************************************************************************************************
 //**************************************************************************************************************************
                    if(period=="Yearly"){
                        query1="SELECT * FROM yearly";
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                        pst =(PreparedStatement)con.prepareStatement(query1);
                        int i=2018;
                        while(i<=ldate.getYear()){
                        rs= pst.executeQuery();

            
                        double tB = 0,tS=0,ts=0,tT=0,tF=0,tR=0;
                        while(rs.next()){
                            if(i==rs.getInt(2)){
                                if("Books".equals(rs.getString(3)))
                                   tB=tB+rs.getDouble(5);
                                else if("Stationaries".equals(rs.getString(3)) && rs.getInt(2)==i)
                                    tS=tS+rs.getDouble(5);
                                else if("Reloads".equals(rs.getString(3)) && rs.getInt(2)==i)
                                    tR=tR+rs.getDouble(5);
                                else if("Fancy".equals(rs.getString(3)) && rs.getInt(2)==i)
                                    tF=tF+rs.getDouble(5);
                                else if("Seasonal".equals(rs.getString(3)) && rs.getInt(2)==i)
                                    ts=ts+rs.getDouble(5);
                                else if("Toys".equals(rs.getString(3)) && rs.getInt(2)==i)
                                    tT=tT+rs.getDouble(5);
                            }
                        } //while
                        dataset.setValue(tB, "Books", Integer.toString(i));
                        dataset.setValue(tS, "Stationaries", Integer.toString(i));
                        dataset.setValue(tR, "Reloads", Integer.toString(i));
                        dataset.setValue(tF, "Fancy", Integer.toString(i));
                        dataset.setValue(ts, "Seasonal", Integer.toString(i));
                        dataset.setValue(tT, "Toys", Integer.toString(i));
                        i=i+1;
                        }
                JFreeChart chart = ChartFactory.createBarChart("Yearly Sales Chart", "Year", "Sold Quantity", dataset);
                ChartFrame frame =new ChartFrame("Category Chart",chart);
                frame.setVisible(true);
                frame.setSize(1281, 798);
                frame.setLocation(320,115);
                        } //if
                    
    //********************************************************************************************************************                
                        else if(period=="Monthly"){
                        query1="SELECT * FROM monthlysale where year='"+day+"'";
                        pst =(PreparedStatement)con.prepareStatement(query1);
                        int i=1;
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                        while(i<=12){
                        rs= pst.executeQuery();               
                        double tB = 0,tS=0,ts=0,tT=0,tF=0,tR=0;
                         
                        while(rs.next()){
                           if(i==rs.getInt(3)){
                        if("Books".equals(rs.getString(4)))
                            tB=tB+rs.getDouble(6);
                        
                        else if("Stationaries".equals(rs.getString(4)))
                        tS=tS+rs.getDouble(6);
                        
                        else if("Reloads".equals(rs.getString(4)))
                        tR=tR+rs.getDouble(6);
                        
                        else if("Fancy".equals(rs.getString(4)))
                        tF=tF+rs.getDouble(6);
                        
                        else if("Seasonal".equals(rs.getString(4)))
                        ts=ts+rs.getDouble(6);

                        else if("Toys".equals(rs.getString(4)))
                        tT=tT+rs.getDouble(6);
                           }
                       }
                        dataset.setValue(tB, "Books", Integer.toString(i));
                        dataset.setValue(tS, "Stationaries", Integer.toString(i));
                        dataset.setValue(tR, "Reloads", Integer.toString(i));
                        dataset.setValue(tF, "Fancy", Integer.toString(i));
                        dataset.setValue(ts, "Seasonal", Integer.toString(i));
                        dataset.setValue(tT, "Toys", Integer.toString(i));
                        i=i+1;
                        }
                JFreeChart chart = ChartFactory.createBarChart("Monthly Sales Chart", "Month", "Sold Quantity", dataset);
                ChartFrame frame =new ChartFrame("Category Chart",chart);
                frame.setVisible(true);
                frame.setSize(1281, 798);
                frame.setLocation(320,115); 
                    } 
   //**************************************************************************************************************************************                     
                    else if(period=="Daily"){
                       query1="SELECT * FROM dailysale where year='"+month+"' && month='"+day+"'";
                        pst =(PreparedStatement)con.prepareStatement(query1);
                         int i=1;
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                        while(i<=31){
                        rs= pst.executeQuery();
                        
                        
                         double tB = 0,tS=0,ts=0,tT=0,tF=0,tR=0;
                         
                       while(rs.next()){
                           if(i==rs.getInt(4)){
                        if("Books".equals(rs.getString(5)))
                            tB=tB+rs.getDouble(7);
                        
                        else if("Stationaries".equals(rs.getString(5)))
                        tS=tS+rs.getDouble(7);
                        
                        else if("Reloads".equals(rs.getString(5)))
                        tR=tR+rs.getDouble(7);
                        
                        else if("Fancy".equals(rs.getString(5)))
                        tF=tF+rs.getDouble(7);
                        
                        else if("Seasonal".equals(rs.getString(5)))
                        ts=ts+rs.getDouble(7);

                        else if("Toys".equals(rs.getString(5)))
                        tT=tT+rs.getDouble(7);
                           }
                       }
                        dataset.setValue(tB, "Books", Integer.toString(i));
                        dataset.setValue(tS, "Stationaries", Integer.toString(i));
                        dataset.setValue(tR, "Reloads", Integer.toString(i));
                        dataset.setValue(tF, "Fancy", Integer.toString(i));
                        dataset.setValue(ts, "Seasonal", Integer.toString(i));
                        dataset.setValue(tT, "Toys", Integer.toString(i));
                        i=i+1;
                        }
                JFreeChart chart = ChartFactory.createBarChart("Daily Sales Chart", "Date", "Sold Quantity", dataset);
                ChartFrame frame =new ChartFrame("Category Chart",chart);
                frame.setVisible(true);
                frame.setSize(1281, 798);
                frame.setLocation(320,115); 
                    }
                }
 //*************************************************************************************************************************
 //**************************************************************************************************************************
                if(prof=="Profit"){
 //**************************************************************************************************************************                   
                    if(period=="Yearly"){
                        String query2="SELECT * FROM yearly";
                        String query3="SELECT itemcode,BuyingPrice,unitPrice FROM stock";
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                        pst1 =(PreparedStatement)con.prepareStatement(query2);
                        pst2 =(PreparedStatement)con.prepareStatement(query3);
                        int i=2018;
                        while(i<=ldate.getYear()){
                        rs1=pst1.executeQuery();
                        
                        double tB = 0,tS=0,ts=0,tT=0,tF=0,tR=0;
                        while(rs1.next()){
                            if(i==rs1.getInt(2)){
                                if("Books".equals(rs1.getString(3))){
                                   rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(4).equals(rs2.getString(1)))
                                       tB=tB+(rs1.getDouble(5)*rs2.getDouble(3))-(rs1.getDouble(5)*rs2.getDouble(2));
                                   }
                                }
                                   
                                else if("Stationaries".equals(rs1.getString(3))){
                                    rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(4).equals(rs2.getString(1)))
                                       tS=tS+(rs1.getDouble(5)*rs2.getDouble(3))-(rs1.getDouble(5)*rs2.getDouble(2));
                                   }
                                }
                                else if("Reloads".equals(rs1.getString(3)) && rs1.getInt(2)==i){
                                    rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(4).equals(rs2.getString(1)))
                                       tR=tR+(rs1.getDouble(5)*rs2.getDouble(3))-(rs1.getDouble(5)*rs2.getDouble(2));
                                   }
                                }
                                else if("Fancy".equals(rs1.getString(3)) && rs1.getInt(2)==i){
                                    rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(4).equals(rs2.getString(1)))
                                       tF=tF+(rs1.getDouble(5)*rs2.getDouble(3))-(rs1.getDouble(5)*rs2.getDouble(2));
                                   }
                                }
                                else if("Seasonal".equals(rs1.getString(3)) && rs1.getInt(2)==i){
                                    rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(4).equals(rs2.getString(1)))
                                       ts=ts+(rs1.getDouble(5)*rs2.getDouble(3))-(rs1.getDouble(5)*rs2.getDouble(2));
                                   }
                                }
                                else if("Toys".equals(rs1.getString(3)) && rs1.getInt(2)==i){
                                    rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(4).equals(rs2.getString(1)))
                                       tT=tT+(rs1.getDouble(5)*rs2.getDouble(3))-(rs1.getDouble(5)*rs2.getDouble(2));
                                   }
                                }
                            }
                        } //while
                        
                        dataset.setValue(tB, "Books", Integer.toString(i));
                        dataset.setValue(tS, "Stationaries", Integer.toString(i));
                        dataset.setValue(tR, "Reloads", Integer.toString(i));
                        dataset.setValue(tF, "Fancy", Integer.toString(i));
                        dataset.setValue(ts, "Seasonal", Integer.toString(i));
                        dataset.setValue(tT, "Toys", Integer.toString(i));
                        i=i+1;
                        }
                JFreeChart chart = ChartFactory.createBarChart("Yearly Profit Chart", "Year", "Profit", dataset);
                ChartFrame frame =new ChartFrame("Category Chart",chart);
                frame.setVisible(true);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("book.png")));
                frame.setSize(1281, 798);
                frame.setLocation(320,115);
                        } //if
 //**********************************************************************************************************************                   
                        else if(period=="Monthly"){
                       String query2="SELECT * FROM monthlysale where year='"+day+"'";
                       String query3="SELECT itemcode,BuyingPrice,unitPrice FROM stock";
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                        pst1 =(PreparedStatement)con.prepareStatement(query2);
                        pst2 =(PreparedStatement)con.prepareStatement(query3);
                        int i=1;
                        
                        while(i<=12){
                       
                        rs1=pst1.executeQuery();
                        double tB = 0,tS=0,ts=0,tT=0,tF=0,tR=0;
                        while(rs1.next()){
                            if(i==rs1.getInt(3)){
                                if("Books".equals(rs1.getString(4))){
                                   rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(5).equals(rs2.getString(1)))
                                       tB=tB+(rs1.getDouble(6)*rs2.getDouble(3))-(rs1.getDouble(6)*rs2.getDouble(2));
                                   }
                                }
                                   
                                else if("Stationaries".equals(rs1.getString(4))){
                                    rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(5).equals(rs2.getString(1)))
                                       tS=tS+(rs1.getDouble(6)*rs2.getDouble(3))-(rs1.getDouble(6)*rs2.getDouble(2));
                                   }
                                }
                                else if("Reloads".equals(rs1.getString(4))){
                                    rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(5).equals(rs2.getString(1)))
                                       tR=tR+(rs1.getDouble(6)*rs2.getDouble(3))-(rs1.getDouble(6)*rs2.getDouble(2));
                                   }
                                }
                                else if("Fancy".equals(rs1.getString(4))){
                                    rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(5).equals(rs2.getString(1)))
                                       tF=tF+(rs1.getDouble(6)*rs2.getDouble(3))-(rs1.getDouble(6)*rs2.getDouble(2));
                                   }
                                }
                                else if("Seasonal".equals(rs1.getString(4))){
                                    rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(5).equals(rs2.getString(1)))
                                       ts=ts+(rs1.getDouble(6)*rs2.getDouble(3))-(rs1.getDouble(6)*rs2.getDouble(2));
                                   }
                                }
                                else if("Toys".equals(rs1.getString(4))){
                                    rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(5).equals(rs2.getString(1)))
                                       tT=tT+(rs1.getDouble(6)*rs2.getDouble(3))-(rs1.getDouble(6)*rs2.getDouble(2));
                                   }
                                }
                            }
                        } //while
                        dataset.setValue(tB, "Books", Integer.toString(i));
                        dataset.setValue(tS, "Stationaries", Integer.toString(i));
                        dataset.setValue(tR, "Reloads", Integer.toString(i));
                        dataset.setValue(tF, "Fancy", Integer.toString(i));
                        dataset.setValue(ts, "Seasonal", Integer.toString(i));
                        dataset.setValue(tT, "Toys", Integer.toString(i));
                        i=i+1;
                        }
                JFreeChart chart = ChartFactory.createBarChart("Monthly Profit Chart", "Month", "Profit", dataset);
                ChartFrame frame =new ChartFrame("Category Chart",chart);
                frame.setVisible(true);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("book.png")));
                frame.setSize(1281, 798);
                frame.setLocation(320,115);
                    } 
 //**********************************************************************************************************************                       
                    else if(period=="Daily"){
                       String query2="SELECT * FROM dailysale where year='"+month+"' && month='"+day+"'";
                       String query3="SELECT itemcode,BuyingPrice,unitPrice FROM stock";
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                        pst1 =(PreparedStatement)con.prepareStatement(query2);
                        pst2 =(PreparedStatement)con.prepareStatement(query3);
                         int i=1;
                        
                        while(i<=31){
                        rs1=pst1.executeQuery();
                        
                      double tB = 0,tS=0,ts=0,tT=0,tF=0,tR=0;
                         
                       while(rs1.next()){
                           if(i==rs1.getInt(4)){
                        if("Books".equals(rs1.getString(5))){
                                   rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(6).equals(rs2.getString(1)))
                                       tB=tB+(rs1.getDouble(7)*rs2.getDouble(3))-(rs1.getDouble(7)*rs2.getDouble(2));
                                   }
                                }
                        else if("Stationaries".equals(rs1.getString(5))){
                                   rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(6).equals(rs2.getString(1)))
                                       tS=tS+(rs1.getDouble(7)*rs2.getDouble(3))-(rs1.getDouble(7)*rs2.getDouble(2));
                                   }
                                }
                        else if("Reloads".equals(rs1.getString(5))){
                                   rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(6).equals(rs2.getString(1)))
                                       tR=tR+(rs1.getDouble(7)*rs2.getDouble(3))-(rs1.getDouble(7)*rs2.getDouble(2));
                                   }
                                }
                        else if("Fancy".equals(rs1.getString(5))){
                                   rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(6).equals(rs2.getString(1)))
                                       tF=tF+(rs1.getDouble(7)*rs2.getDouble(3))-(rs1.getDouble(7)*rs2.getDouble(2));
                                   }
                                }
                        else if("Seasonal".equals(rs1.getString(5))){
                                   rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(6).equals(rs2.getString(1)))
                                       tF=tF+(rs1.getDouble(7)*rs2.getDouble(3))-(rs1.getDouble(7)*rs2.getDouble(2));
                                   }
                                }
                        else if("Toys".equals(rs1.getString(5))){
                                   rs2=pst2.executeQuery();
                                   while(rs2.next()){
                                   if(rs1.getString(6).equals(rs2.getString(1)))
                                       tT=tT+(rs1.getDouble(7)*rs2.getDouble(3))-(rs1.getDouble(7)*rs2.getDouble(2));
                                   }
                                }
                           }
                       }
                        dataset.setValue(tB, "Books", Integer.toString(i));
                        dataset.setValue(tS, "Stationaries", Integer.toString(i));
                        dataset.setValue(tR, "Reloads", Integer.toString(i));
                        dataset.setValue(tF, "Fancy", Integer.toString(i));
                        dataset.setValue(ts, "Seasonal", Integer.toString(i));
                        dataset.setValue(tT, "Toys", Integer.toString(i));
                        i=i+1;
                        }
                JFreeChart chart = ChartFactory.createBarChart("Daily Profit Chart", "Date", "Profit", dataset);
                ChartFrame frame =new ChartFrame("Category Chart",chart);
                frame.setVisible(true);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("book.png")));
                frame.setSize(1281, 798);
                frame.setLocation(320,115); 
                    }
                }
 //************************************************************************************************************************************
 //*************************************************************************************************************************************
              if(prof=="Purchases"){
 //*************************************************************************************************************************************                 
                    if(period=="Yearly"){
                        query1="SELECT * FROM purcyearly";
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                        pst =(PreparedStatement)con.prepareStatement(query1);
                        int i=2018;
                        while(i<=ldate.getYear()){
                        rs= pst.executeQuery();

            
                        double tB = 0,tS=0,ts=0,tT=0,tF=0,tR=0;
                        while(rs.next()){
                            if(i==rs.getInt(2)){
                                if("Books".equals(rs.getString(3)))
                                   tB=tB+rs.getDouble(5);
                                else if("Stationaries".equals(rs.getString(3)) && rs.getInt(2)==i)
                                    tS=tS+rs.getDouble(5);
                                else if("Reloads".equals(rs.getString(3)) && rs.getInt(2)==i)
                                    tR=tR+rs.getDouble(5);
                                else if("Fancy".equals(rs.getString(3)) && rs.getInt(2)==i)
                                    tF=tF+rs.getDouble(5);
                                else if("Seasonal".equals(rs.getString(3)) && rs.getInt(2)==i)
                                    ts=ts+rs.getDouble(5);
                                else if("Toys".equals(rs.getString(3)) && rs.getInt(2)==i)
                                    tT=tT+rs.getDouble(5);
                            }
                        } //while
                        dataset.setValue(tB, "Books", Integer.toString(i));
                        dataset.setValue(tS, "Stationaries", Integer.toString(i));
                        dataset.setValue(tR, "Reloads", Integer.toString(i));
                        dataset.setValue(tF, "Fancy", Integer.toString(i));
                        dataset.setValue(ts, "Seasonal", Integer.toString(i));
                        dataset.setValue(tT, "Toys", Integer.toString(i));
                        i=i+1;
                        }
                JFreeChart chart = ChartFactory.createBarChart("Yearly Purchases Chart", "Year", "Purchased Quantity", dataset);
                ChartFrame frame =new ChartFrame("Category Chart",chart);
                frame.setVisible(true);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("book.png")));
                frame.setSize(1281, 798);
                frame.setLocation(320,115);
                        } //if
  //*******************************************************************************************************************************                  
                        else if(period=="Monthly"){
                        query1="SELECT * FROM purchmonthly where year='"+day+"'";
                        pst =(PreparedStatement)con.prepareStatement(query1);
                        int i=1;
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                        while(i<=12){
                        rs= pst.executeQuery();               
                        double tB = 0,tS=0,ts=0,tT=0,tF=0,tR=0;
                         
                        while(rs.next()){
                           if(i==rs.getInt(3)){
                        if("Books".equals(rs.getString(4)))
                            tB=tB+rs.getDouble(6);
                        
                        else if("Stationaries".equals(rs.getString(4)))
                        tS=tS+rs.getDouble(6);
                        
                        else if("Reloads".equals(rs.getString(4)))
                        tR=tR+rs.getDouble(6);
                        
                        else if("Fancy".equals(rs.getString(4)))
                        tF=tF+rs.getDouble(6);
                        
                        else if("Seasonal".equals(rs.getString(4)))
                        ts=ts+rs.getDouble(6);

                        else if("Toys".equals(rs.getString(4)))
                        tT=tT+rs.getDouble(6);
                           }
                       }
                        dataset.setValue(tB, "Books", Integer.toString(i));
                        dataset.setValue(tS, "Stationaries", Integer.toString(i));
                        dataset.setValue(tR, "Reloads", Integer.toString(i));
                        dataset.setValue(tF, "Fancy", Integer.toString(i));
                        dataset.setValue(ts, "Seasonal", Integer.toString(i));
                        dataset.setValue(tT, "Toys", Integer.toString(i));
                        i=i+1;
                        }
                JFreeChart chart = ChartFactory.createBarChart("Monthly Purchased Chart", "Month", "Purchased Quantity", dataset);
                ChartFrame frame =new ChartFrame("Category Chart",chart);
                frame.setVisible(true);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("book.png")));
                frame.setSize(1281, 798);
                frame.setLocation(320,115);
                    } 
  //**************************************************************************************************************************************                      
                    else if(period=="Daily"){
                       query1="SELECT * FROM purchdaily where year='"+month+"' && month='"+day+"'";
                        pst =(PreparedStatement)con.prepareStatement(query1);
                         int i=1;
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                        while(i<=31){
                        rs= pst.executeQuery();
                        
                        
                         double tB = 0,tS=0,ts=0,tT=0,tF=0,tR=0;
                         
                       while(rs.next()){
                           if(i==rs.getInt(4)){
                        if("Books".equals(rs.getString(5)))
                            tB=tB+rs.getDouble(7);
                        
                        else if("Stationaries".equals(rs.getString(5)))
                        tS=tS+rs.getDouble(7);
                        
                        else if("Reloads".equals(rs.getString(5)))
                        tR=tR+rs.getDouble(7);
                        
                        else if("Fancy".equals(rs.getString(5)))
                        tF=tF+rs.getDouble(7);
                        
                        else if("Seasonal".equals(rs.getString(5)))
                        ts=ts+rs.getDouble(7);

                        else if("Toys".equals(rs.getString(5)))
                        tT=tT+rs.getDouble(7);
                           }
                       }
                        dataset.setValue(tB, "Books", Integer.toString(i));
                        dataset.setValue(tS, "Stationaries", Integer.toString(i));
                        dataset.setValue(tR, "Reloads", Integer.toString(i));
                        dataset.setValue(tF, "Fancy", Integer.toString(i));
                        dataset.setValue(ts, "Seasonal", Integer.toString(i));
                        dataset.setValue(tT, "Toys", Integer.toString(i));
                        i=i+1;
                        }
                JFreeChart chart = ChartFactory.createBarChart("Daily Purchased Chart", "Date", "Purchased Quantity", dataset);
                ChartFrame frame =new ChartFrame("Category Chart",chart);
                frame.setVisible(true);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("book.png")));
                frame.setSize(1281, 798);
                frame.setLocation(320,115); 
                    }
                }  
            }
            
            
            
            else if(type=="Item"){
                
//****************************************************************************************************************                
//*****************************************************************************************************************               
             if(prof=="Sales"){
//************************************************************************************************************                
           if(period=="Yearly"){
            query1="SELECT * FROM yearly where Category='"+cat+"'";
            DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();

                    while(rs.next()){

                        dataset.setValue(rs.getDouble(5), rs.getString(4), rs.getString(2));

            }
                JFreeChart chart = ChartFactory.createBarChart("Yearly Sales Chart", "Year", "Sold Quantity", dataset);
                ChartFrame frame =new ChartFrame("Item Chart",chart);
                frame.setVisible(true);
               frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("book.png")));
                frame.setSize(1281, 798);
                frame.setLocation(320,115);
         }
//**********************************************************************************************************************           
                    else if(period=="Monthly"){
                       query1="SELECT * FROM monthlysale where year='"+day+"' && category='"+cat+"'";
                        pst =(PreparedStatement)con.prepareStatement(query1);
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                        rs= pst.executeQuery();
                        
                       while(rs.next()){
                          
                        dataset.setValue(rs.getDouble(6), rs.getString(5), rs.getString(3));

                        }
                JFreeChart chart = ChartFactory.createBarChart("Monthly Sales Chart", "Month", "Sold Quantity", dataset);
                ChartFrame frame =new ChartFrame("Item Chart",chart);
                frame.setVisible(true);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("book.png")));
                frame.setSize(1281, 798);
                frame.setLocation(320,115); 
                    } 
//**********************************************************************************************************************                    
                    else if(period=="Daily"){
                       query1="SELECT * FROM dailysale where year='"+month+"'&& category='"+cat+"' && month='"+day+"'";
                        pst =(PreparedStatement)con.prepareStatement(query1);
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                        rs= pst.executeQuery();

                       while(rs.next()){
                        dataset.setValue(rs.getDouble(7), rs.getString(6), rs.getString(4));

                        }
                JFreeChart chart = ChartFactory.createBarChart("Daily Sales Chart", "Date", "Sold Quantity", dataset);
                ChartFrame frame =new ChartFrame("Item Chart",chart);
                frame.setVisible(true);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("book.png")));
                frame.setSize(1281, 798);
                frame.setLocation(320,115); 
                    }
                }
             
//****************************************************************************************************************                
//*****************************************************************************************************************               
             if(prof=="Purchases"){
//************************************************************************************************************                
           if(period=="Yearly"){
            query1="SELECT * FROM purcyearly where Category='"+cat+"'";
            DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
            pst =(PreparedStatement)con.prepareStatement(query1);
            rs= pst.executeQuery();

                    while(rs.next()){

                        dataset.setValue(rs.getDouble(5), rs.getString(4), rs.getString(2));

            }
                JFreeChart chart = ChartFactory.createBarChart("Yearly Purchases Chart", "Year", "Purchased Quantity", dataset);
                ChartFrame frame =new ChartFrame("Item Chart",chart);
                frame.setVisible(true);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("book.png")));
                frame.setSize(1281, 798);
                frame.setLocation(320,115);
         }
//**********************************************************************************************************************           
                    else if(period=="Monthly"){
                       query1="SELECT * FROM purchmonthly where Year='"+day+"' && Category='"+cat+"'";
                        pst =(PreparedStatement)con.prepareStatement(query1);
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                        rs= pst.executeQuery();
                        
                       while(rs.next()){
                          
                        dataset.setValue(rs.getDouble(6), rs.getString(5), rs.getString(3));

                        }
                JFreeChart chart = ChartFactory.createBarChart("Monthly Purchases Chart", "Month", "Purchased Quantity", dataset);
                ChartFrame frame =new ChartFrame("Item Chart",chart);
                frame.setVisible(true);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("book.png")));
                frame.setSize(1281, 798);
                frame.setLocation(320,115); 
                    } 
//**********************************************************************************************************************                    
                    else if(period=="Daily"){
                       query1="SELECT * FROM purchdaily where Year='"+month+"'&& Category='"+cat+"' && Month='"+day+"'";
                        pst =(PreparedStatement)con.prepareStatement(query1);
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                        rs= pst.executeQuery();

                       while(rs.next()){
                        dataset.setValue(rs.getDouble(7), rs.getString(6), rs.getString(4));

                        }
                JFreeChart chart = ChartFactory.createBarChart("Daily Purchases Chart", "Date", "Purchased Quantity", dataset);
                ChartFrame frame =new ChartFrame("Item Chart",chart);
                frame.setVisible(true);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("book.png")));
                frame.setSize(1281, 798);
                frame.setLocation(320,115);
                    }
                }
             
//*************************************************************************************************************************
//**************************************************************************************************************************
                if(prof=="Profit"){
//**************************************************************************************************************************                   
                    if(period=="Yearly"){

                        String query2="SELECT * FROM yearly where Category='"+cat+"'";
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                        String query3="SELECT itemcode,BuyingPrice,unitPrice FROM stock";
                        pst =(PreparedStatement)con.prepareStatement(query2);
                        int i=2018;
                        while(i<=ldate.getYear()){
                        pst1 =(PreparedStatement)con.prepareStatement(query3);
                        rs1=pst.executeQuery();
            
                        
                        while(rs1.next()){
                              double tB = 0;
                           if(i==rs1.getInt(2)){
                                rs2=pst1.executeQuery();
                                while(rs2.next()){
                                if(rs1.getString(4).equals(rs2.getString(1))){
                                   tB=(rs1.getDouble(5)*rs2.getDouble(3))-(rs1.getDouble(5)-rs2.getDouble(2));
                                   dataset.setValue(tB, rs1.getString(4), Integer.toString(i));
                                  }
                                }
                           }
                            }//while
                             i=i+1;
                        } //while
                       
                        
                JFreeChart chart = ChartFactory.createBarChart("Yearly Profit Chart", "Year", "Profit", dataset);
                ChartFrame frame =new ChartFrame("Item Chart",chart);
                frame.setVisible(true);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("book.png")));
                frame.setSize(1281, 798);
                frame.setLocation(320,115);
                        } //if
// //**********************************************************************************************************************                   
                        else if(period=="Monthly"){
                        String query2="SELECT * FROM monthlysale where year='"+day+"' && category='"+cat+"'";
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                        String query3="SELECT itemcode,BuyingPrice,unitPrice FROM stock";
                        pst =(PreparedStatement)con.prepareStatement(query2);
                        int i=1;
                        while(i<=12){
                        pst1 =(PreparedStatement)con.prepareStatement(query3);
                        rs1=pst.executeQuery();
            
                        
                        while(rs1.next()){
                              double tB = 0;
                           if(i==rs1.getInt(3)){
                                rs2=pst1.executeQuery();
                                while(rs2.next()){
                                if(rs1.getString(5).equals(rs2.getString(1))){
                                   tB=(rs1.getDouble(6)*rs2.getDouble(3))-(rs1.getDouble(6)-rs2.getDouble(2));
                                   dataset.setValue(tB, rs1.getString(5), Integer.toString(i));
                                }
                                  }
                                }
                            }//while
                             i=i+1;
                        } //while
                       
                        
                JFreeChart chart = ChartFactory.createBarChart("Monthly Profit Chart", "Month", "Profit", dataset);
                ChartFrame frame =new ChartFrame("Item Chart",chart);
                frame.setVisible(true);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("book.png")));
                frame.setSize(1281, 798);
                frame.setLocation(320,115); 
                    } 
// //**********************************************************************************************************************                       
                    else if(period=="Daily"){
                      String query2="SELECT * FROM dailysale where year='"+month+"'&& category='"+cat+"' && month='"+day+"'";
                        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
                        String query3="SELECT itemcode,BuyingPrice,unitPrice FROM stock";
                        pst =(PreparedStatement)con.prepareStatement(query2);
                        int i=1;
                        while(i<=31){
                        pst1 =(PreparedStatement)con.prepareStatement(query3);
                        rs1=pst.executeQuery();
            
                        
                        while(rs1.next()){
                              double tB = 0;
                           if(i==rs1.getInt(4)){
                                rs2=pst1.executeQuery();
                                while(rs2.next()){
                                if(rs1.getString(6).equals(rs2.getString(1))){
                                   tB=(rs1.getDouble(7)*rs2.getDouble(3))-(rs1.getDouble(7)-rs2.getDouble(2));
                                   dataset.setValue(tB, rs1.getString(6), Integer.toString(i));
                                }
                                  }
                                }
                            }//while
                             i=i+1;
                        } //while
                       
                        
                JFreeChart chart = ChartFactory.createBarChart("Daily Profit Chart", "Date", "Profit", dataset);
                ChartFrame frame =new ChartFrame("Item Chart",chart);
                frame.setVisible(true);
                frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("book.png")));
                frame.setSize(1281, 798);
                frame.setLocation(320,115); 
                }
                    }
                }
        }catch (SQLException ex) {
            Logger.getLogger(DBOperations.class.getName()).log(Level.SEVERE, null, ex);
}
                      }
    public void printsale(String a,String c, String b,String d){
        String dat=setdate();
        try{
            con=(Connection)DriverManager.getConnection(url, username, password);
            String s="Welcome Bookshop\r\n"
                    + "Date\\Time :"+dat +"\r\n" +
                    "Sales Number :"+a +"\r\n" +
            "\tItem \t\t\t Quantity \t Rate \t Price \r\n";
        String query="SELECT * FROM sales where saleNo='"+a+"'";
        pst =(PreparedStatement)con.prepareStatement(query);
        rs=pst.executeQuery();
        while(rs.next()){
            String query1="SELECT name,unitPrice FROM stock where itemcode='"+rs.getString(3)+"'";
            pst1 =(PreparedStatement)con.prepareStatement(query1);
            rs1=pst1.executeQuery();
            while(rs1.next()){
                 s=s+rs1.getString(1)+"\r\n"+rs.getString(2)+"\t\t\t\t\t"+rs.getString(4)+"\t"+rs1.getString(2)+"\t"+(rs.getDouble(4)*rs1.getDouble(2))+"\t"+"\r\n";
            }
        }
        double q=Double.parseDouble(b)+Double.parseDouble(c);
        s=s+"\r\n"+"------------------------------------------------------------------"+"\r\n"+"\t\t\t\tSub Total\t\t"+q+"\r\n"
                +"\t\t\t\tDiscounts\t\t"+(c)+"\r\n"
                +"\t\t\t\tDiscount per bill ("+(d)+"%)\r\n"
                +"\t\t\t\tNet Total\t\t"+(b)+"\r\n"
                + "\r\n"
                + "Thank You, Come Again!";
        toFile(s);
        }
        catch(Exception e){
            
        }
    }
    
        public void printorder(String a,String b,String d){
        String dat=setdate();
        try{
            con=(Connection)DriverManager.getConnection(url, username, password);
            Double amount=Double.parseDouble(b)/(1-(Double.parseDouble(d)/100));
            double disc=amount*(Double.parseDouble(d)/100);
            String s="Welcome Bookshop\r\n"
                    + "Date\\Time :"+dat +"\r\n" +
                    "Sales Number :"+a +"\r\n" +
            "\tItem \t\t\t Quantity \t Rate \t Price \r\n";
        String query="SELECT * FROM orderitems where orderNo='"+a+"'";
        pst =(PreparedStatement)con.prepareStatement(query);
        rs=pst.executeQuery();
        while(rs.next()){
            String query1="SELECT name,unitPrice FROM stock where itemcode='"+rs.getString(3)+"'";
            pst1 =(PreparedStatement)con.prepareStatement(query1);
            rs1=pst1.executeQuery();
            while(rs1.next()){
                s=s+rs1.getString(1)+"\r\n"+rs.getString(2)+"\t\t\t\t\t"+rs.getString(4)+"\t"+Double.parseDouble(rs1.getString(2))+"\t"+rs.getDouble(4)*rs1.getDouble(2)+"\t"+"\r\n";
            }
        }
        s=s+"\r\n"+"------------------------------------------------------------------"+"\r\n"+"\t\t\t\tSub Total\t\t"+amount+"\r\n"
                +"\t\t\t\tDiscounts\t\t"+disc+"\r\n"
                +"\t\t\t\tDiscount per bill ("+(d)+"%)\r\n"
                +"\t\t\t\tNet Total\t\t"+(b)+"\r\n"
                + "\r\n"
                + "Thank You, Come Again!";
        toFile(s);
        }
        catch(Exception e){
            
        }
    }
    public void toFile(String s){
            File newFile=new File("C:/Users/Sandu/Desktop/PrintBILL.txt");
        if(newFile.exists()){
        try{
                FileWriter filew=new FileWriter(newFile);
                BufferedWriter buffw=new BufferedWriter(filew);
                buffw.write(s);
                buffw.close();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        else{
            try{
                newFile.createNewFile();
                }
            catch(Exception e){
                System.out.println(e);
            }
            try{
                FileWriter filew=new FileWriter(newFile);
                BufferedWriter buffw=new BufferedWriter(filew);
                buffw.write(s);
                buffw.close();
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
    }
    
    public void discount(String n,String d,String s){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="INSERT INTO discount VALUES (?,?,?)";
        pst=(PreparedStatement)con.prepareStatement(query);
        pst.setString(1, n);
        pst.setString(2, d);
        pst.setString(3, s);

        
        pst.executeUpdate();
       
        
        }
        catch(Exception e){
            System.out.print(e);
            
        }
    }
    public int getdiscount(String n){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="SELECT percentage FROM discount where saleNo='"+n+"'";
        pst=(PreparedStatement)con.prepareStatement(query);
        rs=pst.executeQuery();
        while(rs.next()){
            return rs.getInt(1);
        }
        }
        catch(Exception e){
            System.out.print(e);
            
        }
        return 0;
    }
    public void updatediscount(String n,String a,String d){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        Double amount=Double.parseDouble(a)/(1-(Double.parseDouble(d)/100));
        double disc=amount*(Double.parseDouble(d)/100);
        String query="update discount set amount='"+disc+"' where saleNO='"+n+"'";
        pst=(PreparedStatement)con.prepareStatement(query);
        pst.executeUpdate();
        }
        catch(Exception e){
            System.out.print(e);
        }
        finally{
            try{
            if(pst!=null){
                pst.close();
            }
            if(con!=null){
                con.close();
            }
            }
            catch(Exception e){
                
            }
        }
    }
    public void deleteCategory(String s){
        try{
        con =(Connection)DriverManager.getConnection(url, username, password);
        String query="DELETE FROM categories where name='"+s+"'";
        String query1="DELETE FROM suppliers where catagory='"+s+"'";
        String query2="DELETE FROM stock where category='"+s+"'";
        pst=(PreparedStatement)con.prepareStatement(query);
        pst.executeUpdate();
        pst=(PreparedStatement)con.prepareStatement(query1);
        pst.executeUpdate();
        pst=(PreparedStatement)con.prepareStatement(query2);
        pst.executeUpdate();
        }
        catch(Exception e){
            System.out.print(e);
        }
    }
}

       

