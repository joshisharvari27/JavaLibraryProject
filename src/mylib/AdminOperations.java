package mylib;
/** imports all the classes from java.sql package*/
import java.sql.*;
/** imports all the classes form java.util package */
import java.util.*;


public class AdminOperations {
    static Connection conn;
    /** constructor of the class AdminOperations*/
    AdminOperations(){
        conn = ConnectionProvider.conn;
    }

    /**
     * determines the authentication of the admin
     * @return returns true if the authentication details are correct
     */
    public boolean adminAccess(){
        System.out.println("Enter the password to access the Admin Portal");
        Scanner sc = new Scanner(System.in);
        int access = sc.nextInt();
        if(access == 1234){
            System.out.println("Admin Portal - Access granted!");
            return true;
        }
        return false;
    }

    /** gives the list of all the books and their information from the books table */
    public void showBooks(){
        /** stores the sql query which displays the books table */
        String q = "SELECT * FROM BOOKS";
        try{
            Statement s = conn.createStatement();
            ResultSet set = s.executeQuery(q);
            System.out.println("List of all the books available in library:");
            System.out.println("ID\t BOOK NAME\t\t\t AUTHOR\t\t\t\t STATUS");
            while (set.next()){
                int id = set.getInt("ID");
                String name = set.getString("NAME");
                String author = set.getString("AUTHOR");
                String status = set.getString("ISSUED_TO");
                System.out.printf("%-5d%-20s%-20s%-5s" ,id, name, author, status);
                System.out.println();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /** gives the list of all the students and their information from the students table */
    public void showStudents(){
        /** stores the sql query which displays the students table */
        String q = "SELECT ROLL_NO, NAME, YEAR FROM STUDENTS";
        try{
            Statement s = conn.createStatement();
            ResultSet set = s.executeQuery(q);
            System.out.println("List of the Students having access to the Central Library:");
            System.out.println("Roll No\t\tName\t\t\tYear");
            while (set.next()){
                int rollno = set.getInt("ROLL_NO");
                String name = set.getString("NAME");
                int year = set.getInt("YEAR");
                System.out.printf("%-12d%-18s%-3d\n",rollno,name,year);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /** adds an entered book into the books table */
    public void addBook(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the book");
        String name = sc.nextLine();
        System.out.println("Enter the author of the book");
        String author = sc.nextLine();
        try{
            /** stores the sql query which adds a new book into the books table */
            String q = "INSERT INTO BOOKS (NAME, AUTHOR,ISSUED_TO) VALUES(?,?,'NOT ISSUED')";
            PreparedStatement s = conn.prepareStatement(q);
            s.setString(1, name);
            s.setString(2, author);
            s.executeUpdate();
            System.out.println("The book '"+name+"' has been successfully added to the database.");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /** removes an entered book from the books database */
    public void removeBook(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the book");
        String name = sc.nextLine();
        try{
            /** stores the sql query which deletes a book from the books table */
            String q = "DELETE FROM BOOKS WHERE NAME=?";
            PreparedStatement s = conn.prepareStatement(q);
            s.setString(1, name);
            s.executeUpdate();
            System.out.println("The book '"+name+"' has been successfully removed from the database.");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
