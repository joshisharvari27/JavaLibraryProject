package mylib;

import java.util.*;
import java.sql.*;

/**
 * @author Sharvari
 */
public class Main{

    /**
     * main class : execution of program starts from here
     * @param args arguments are given to the main method which have String data type
     */
    public static void main(String[] args) {

        /** Calls the connect method of ConnectionProvider class */
        ConnectionProvider.connect();

        System.out.println("***** Welcome to the Central Library *****");

        Scanner sc = new Scanner(System.in);

        /** * Creates an object admin of class AdminOperations */
        AdminOperations admin = new AdminOperations();

        /** * Creates an object student of class StudentOperations */
        StudentOperations student = new StudentOperations();

        boolean loop = true;

        /** iterates the user portal till given variable is true*/
        while(loop){
        System.out.println("Press 1 for ADMIN access\t\tPress 2 for STUDENT access\t\tPress 0 to exit");
        byte access = sc.nextByte();

        /** accesses the admin portal*/
            if(access == 1){
                boolean loop1 = true;
                /** determines the authentication for admin */
                boolean permit = admin.adminAccess();
                /** iterates the admin portal operations */
                while(loop1){
                    if(permit){
                        System.out.println("Press 1 to get the list of all books in the library\nPress 2 to get the details of the students\nPress 3 to add new book to the database\nPress 4 to remove a book from the database\nPress 0 to exit");
                        byte ops = sc.nextByte();
                        switch (ops) {
                            case 1 -> admin.showBooks();
                            case 2 -> admin.showStudents();
                            case 3 -> admin.addBook();
                            case 4 -> admin.removeBook();
                            case 0 -> loop1=false;
                            default -> System.out.println("wrong input. try again");
                        }
                    }
                    else{
                        System.out.println("Access denied! You entered a wrong password.");
                        loop1 = false;
                    }
                }

            }
            /** accesses the student portal */
            else if(access == 2){
                System.out.println("Student Portal - Access Granted!");
                boolean loop2 = true;
                /** iterates the student portal operations */
                while(loop2){
                    try{
                        System.out.println("Press 1 to issue a book\nPress 2 to return a book\nPress 3 to register yourself\nPress 4 to deactivate your account\nPress 0 to exit");
                        byte ops = sc.nextByte();
                        switch (ops) {
                            case 1 -> student.issueBook();
                            case 2 -> student.returnBook();
                            case 3 -> student.signUp();
                            case 4 -> student.deactivate();
                            case 0 -> loop2=false;
                            default -> System.out.println("Wrong input. Try again!");
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        loop2 = false;
                    }
                }


            }
            else if(access == 0){
                loop = false;
            }
            else{
                System.out.println("Wrong input. Try again!");
                loop = false;
            }
        }

    }
}
