package com.sr.crud;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.text.SimpleDateFormat;
import com.sr.connection.JdbcConnection;
import java.util.Scanner;
import java.sql.Connection;

public class CrudOperations
{
    private Connection con=JdbcConnection.getCon();
    private Scanner sc;
    
   
    public void insert() {
         SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
         PreparedStatement ps = null;
         System.out.println("\nEnter Student_No::");
         int number = sc.nextInt();
         sc = new Scanner(System.in);
         System.out.println("\nEnter Student_Name::");
         String name = sc.nextLine();
         System.out.println("\nEnter Student_DOB(dd-mm-yyyy)");
         String dob = sc.next();
         System.out.println("\nEnter Student_DOJ(dd-mm-yyyy)");
         String doj = sc.next();
        try {
             java.util.Date udob = format.parse(dob);
             Date sdob = new Date(udob.getTime());
             java.util.Date udoj = format.parse(doj);
             Date sdoj = new Date(udoj.getTime());
            ps = con.prepareStatement("INSERT INTO STUDENT VALUES(?,?,?,?)");
            ps.setInt(1, number);
            ps.setString(2, name);
            ps.setDate(3, sdob);
            ps.setDate(4, sdoj);
            if (ps.executeUpdate() != 0) {
                System.out.println("\nInserted Successfully.....");
            }
            else {
                System.out.println("\nFailed to Insert.....");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void display() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement("SELECT * FROM STUDENT ORDER BY STUDENT_NO ASC");
            rs = ps.executeQuery();
            System.out.println("\nStudent_No\tStudent_Name\t\tStudent_DOB\tStudent_DOJ");
            while (rs.next()) {
                 SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                 java.util.Date udob = new java.util.Date(rs.getDate(3).getTime());
                 java.util.Date udoj = new java.util.Date(rs.getDate(4).getTime());
                System.out.println(String.valueOf(rs.getInt(1)) + "\t\t" + rs.getString(2) + "\t\t" + format.format(udob) + "\t" + format.format(udoj));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            System.out.println("\nEnter STUDENT_NO to update record::");
             int no =  sc.nextInt();
            ps = con.prepareStatement("SELECT * FROM STUDENT WHERE STUDENT_NO=?");
            ps.setInt(1, no);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("\nEntered STUDENT_NO not found.....");
                return;
            }
            System.out.println("\nWhich Field you want to update?");
            System.out.println("1.STUDENT_NO\n2.STUDENT_NAME\n3.STUDENT_DOB\n4.STUDENT_DOJ");
             int choice = sc.nextInt();
            String field = null;
            switch (choice) {
                case 1: {
                    field = "STUDENT_NO";
                    break;
                }
                case 2: {
                    field = "STUDENT_NAME";
                    break;
                }
                case 3: {
                    field = "STUDENT_DOB";
                    break;
                }
                case 4: {
                    field = "STUDENT_DOJ";
                    break;
                }
                default: {
                    field = "STUDENT_NO";
                    break;
                }
            }
                 sc = new Scanner(System.in);
            System.out.println("\nEnter New Value for " + field);
             String val = sc.nextLine();
            ps = con.prepareStatement("UPDATE STUDENT SET " + field + "=" + "? WHERE STUDENT_NO=?");
            if (field.endsWith("NO")) {
                ps.setInt(1, Integer.parseInt(val));
            }
            else {
                ps.setString(1, val);
            }
            ps.setInt(2, no);
            if (ps.executeUpdate() == 0) {
                System.out.println("\nFailed to update.....");
            }
            else {
                System.out.println("\nUpdated Successfully.....");
            }
        }
        catch (Exception e) {
            System.out.println("\nFailed to update.....");
            e.printStackTrace();
        }
    }
    
    public void delete() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int status = 0;
        System.out.println("\nEnter STUDENT_NO to delete::");
         int id =  sc.nextInt();
        try {
            ps = con.prepareStatement("SELECT * FROM STUDENT WHERE STUDENT_NO=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("\nEntered STUDENT_NO not found.....");
                return;
            }
            ps = con.prepareStatement("DELETE FROM STUDENT WHERE STUDENT_NO=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();
            if (status == 0) {
                System.out.println("\nFailed to delete.....");
            }
            else {
                System.out.println("\nDeleted Successfully");
            }
        }
        catch (Exception e) {
            System.out.println("\nFailed to delete.....");
            e.printStackTrace();
        }
    }
    
    public void perticular() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println("\nEnter STUDENT_NO to Search::");
         int id = sc.nextInt();
        try {
            ps = con.prepareStatement("SELECT * FROM STUDENT WHERE STUDENT_NO=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (!rs.next()) {
                System.out.println("\nEntered STUDENT_NO not found.....");
                return;
            }
            System.out.println("\nStudent_No\tStudent_Name\t\tStudent_DOB\tStudent_DOJ");
             SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
             java.util.Date udob = new java.util.Date(rs.getDate(3).getTime());
             java.util.Date udoj = new java.util.Date(rs.getDate(4).getTime());
            System.out.println(String.valueOf(rs.getInt(1)) + "\t\t" + rs.getString(2) + "\t\t" + format.format(udob) + "\t" + format.format(udoj));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
