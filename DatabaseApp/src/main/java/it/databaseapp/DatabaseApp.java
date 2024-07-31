/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package it.databaseapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class DatabaseApp {
    
    Connection con;

    public DatabaseApp() {
        
        try {
            // Loading the driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Class Loaded...");
       
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false", "root", "");
           
            System.out.println("Connection  established !!!");
            
            
            } catch (SQLException ex) {
                
                 System.out.println("Connection could not be established");
            }
            
        
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Not Found");
         }
        
    }
  
    public static void main(String[] args) {
        
        DatabaseApp app = new DatabaseApp();
//       app.removeEmployee(21);
//        app.addEmployee(2, "Sabri", 31000.0);
        app.updateEmployee(1,"Salman", 2000.0);
        app.showemployees();
        
        System.out.println("Hello World!");
    }
    
    private void updateEmployee(int empno,String name,double salary){
        try {
            String sql = "update employee set name=?,salary=? where empno=?"; 
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,name);
            stmt.setDouble(2,salary);
            stmt.setInt(3,empno);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void removeEmployee(int empno)
    {
        //String del = "delete from employee where empno="+empno;
        String pdel = "delete from employee where empno=?";
         try {
//            Statement stmt = con.createStatement();
//        
//        
//        stmt.executeUpdate(del);
        
        PreparedStatement psmt = con.prepareStatement(pdel);

        psmt.setInt(1, empno);
        psmt.executeUpdate();
        con.commit();
        //con.close();
        
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    private void addEmployee(int empno, String ename, double salary)
    {
        try {
           /* Statement stmt = con.createStatement();
        
        String insert = "insert into employee values("+ empno+",'"+ ename+ "',"+salary+")";
        
       
       stmt.executeUpdate(insert);*/
       
         String pinsert = "insert into employee values(?,?,?)";
          
         PreparedStatement pstmt = con.prepareStatement(pinsert);
         pstmt.setInt(1, empno);
         pstmt.setString(2, ename);
         pstmt.setDouble(3, salary);
       
        pstmt.executeUpdate();
        
        
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        
    }
            
    
    
    private void showemployees()
    {
        try {
            String query = "select * from employee";
            Statement stmt = con.createStatement();
            
            ResultSet rs = stmt.executeQuery(query);
            
            ResultSetMetaData rmeta = rs.getMetaData();
            
            
            
            while(rs.next())
            {
            
                System.out.println(rs.getString(1)+"\t"+ rs.getString(2)+"\t"+ rs.getString(3));
                
            
            }
        
        
        } catch (SQLException ex) {
       
        ex.printStackTrace();
        }
        
        
        
    }
    
    
    
    
}
