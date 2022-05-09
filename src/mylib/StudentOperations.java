package mylib;
/** imports all the classes from java.sql package*/
import java.sql.*;
/** imports all the classes form java.util package */
import java.util.*;

/**
 * @author Sharvari
 */
public class StudentOperations {
    static Connection conn;
    /** *  constructor of the class StudentOperations*/
    StudentOperations(){
        conn = ConnectionProvider.conn;
    }

    /** issues the entered book to the student and updates the books table */
    public void issueBook(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name");
        String student = sc.nextLine();
        System.out.println("Enter the name of the book you want to issue");
        String name = sc.nextLine();
        
        try{
            /** stores the sql query which accesses the issued status of the book */
            String q1 = "SELECT ISSUED_TO FROM BOOKS WHERE NAME=?";
            PreparedStatement p = conn.prepareStatement(q1);
            p.setString(1, name);
            ResultSet n = p.executeQuery();
            String res = null;
            while (n.next()){
                res = n.getString("ISSUED_TO");
            }
            if(Objects.equals(res, "NOT ISSUED")){
                /** stores the sql query which updates the issue status of the book */
                String q = "UPDATE BOOKS SET ISSUED_TO=? WHERE NAME=?";
                PreparedStatement s = conn.prepareStatement(q);
                s.setString(1, student);
                s.setString(2, name);
                s.executeUpdate();
                System.out.println("The book "+name+" has been issued successfully.");
            }
            else{
                System.out.println("Sorry! The book has already been issued.");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /** allows the student to return a book and update the books table */
    public void returnBook(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the book you want to return");
        String name = sc.nextLine();
        try{
            /** stores the sql query which sets the issue status of the book to 'NOT ISSUED' */
            String q = "UPDATE BOOKS SET ISSUED_TO='NOT ISSUED' WHERE NAME=?";
            PreparedStatement s = conn.prepareStatement(q);
            s.setString(1, name);
            s.executeUpdate();
            System.out.println("The book has been returned successfully.");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /** allows student to create an account for the library and updates the students table */
    public void signUp(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name");
        String name = sc.nextLine();
        System.out.println("Enter the year you are studying in");
        int year = sc.nextInt();
        try{
            /** stores the sql query which adds a new book and its author to the books table */
            String q = "INSERT INTO STUDENTS (NAME,YEAR) VALUES (?,?)";
            PreparedStatement s = conn.prepareStatement(q);
            s.setString(1, name);
            s.setInt(2, year);
            s.executeUpdate();
            System.out.println("You have registered successfully.");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /** deactivates the account of the student and updates the students table */
    public void deactivate(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name");
        String name = sc.nextLine();
        try{
            /** stores the sql query which deletes a students name and other details from students database */
            String q = "DELETE FROM STUDENTS WHERE NAME=?";
            PreparedStatement s = conn.prepareStatement(q);
            s.setString(1, name);
            s.executeUpdate();
            System.out.println("Your account has been deactivated successfully.");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
