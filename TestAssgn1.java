import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import model.*;

class TestAssgn1{
    public static void main(String args[]) throws SQLException{
        /* BookCollection bc = new BookCollection();
        bc.findBooksNewerThanDate("1987");
        System.out.println("Books newer than 1987:");
        bc.display();

        bc = new BookCollection();
        bc.findBooksOlderThanDate("2000");
        System.out.println("Books older than 2000:");
        bc.display();

        bc = new BookCollection();
        bc.findBooksWithAuthorLike("joe");
        System.out.println("Books with author like joe:");
        bc.display();

        bc = new BookCollection();
        bc.findBooksWithTitleLike("book 3");
        System.out.println("Books with title like book 3:");
        bc.display(); */

        Scanner s = new Scanner(System.in);

        /*Properties book1Properties = new Properties();
        System.out.println("Enter book title: ");
        book1Properties.setProperty("bookTitle", s.nextLine());
        System.out.println("Enter author: ");
        book1Properties.setProperty("author", s.nextLine());
        System.out.println("Enter publication year: ");
        book1Properties.setProperty("pubYear", s.nextLine());

        Book book1 = new Book(book1Properties);
        book1.update();

        System.out.println(book1.getState("UpdateStatusMessage"));
        s.nextLine();

        Properties parton1Properties = new Properties();
        System.out.println("Enter parton name: ");
        parton1Properties.setProperty("name", s.nextLine());
        System.out.println("Enter address: ");
        parton1Properties.setProperty("address", s.nextLine());
        System.out.println("Enter city: ");
        parton1Properties.setProperty("city", s.nextLine());
        System.out.println("Enter state code: ");
        parton1Properties.setProperty("stateCode", s.nextLine());
        System.out.println("Enter zip: ");
        parton1Properties.setProperty("zip", s.nextLine());
        System.out.println("Enter email: ");
        parton1Properties.setProperty("email", s.nextLine());
        System.out.println("Enter date of birth: ");
        parton1Properties.setProperty("dateOfBirth", s.nextLine());

        Patron parton1 = new Patron(parton1Properties);
        parton1.update();

        System.out.println(parton1.getState("UpdateStatusMessage"));
        s.nextLine();

        BookCollection bc = new BookCollection();
        System.out.println("Enter title to search for: ");
        bc.findBooksWithTitleLike(s.nextLine());
        System.out.println("Result: ");
        bc.display();

        bc = new BookCollection();
        System.out.println("Enter year to find books published before: ");
        bc.findBooksOlderThanDate(s.nextLine());
        System.out.println("Result: ");
        bc.display(); */

        PatronCollection pc = new PatronCollection();
        System.out.println("Enter a date (YYYY-MM-DD) to find patrons born after: ");
        pc.findPatronsYoungerThan(s.nextLine());
        System.out.println("Result: ");
        pc.display();

        pc = new PatronCollection();
        System.out.println("Enter a zip code to find patrons at: ");
        pc.findPatronsAtZipCode(s.nextLine());
        System.out.println("Result: ");
        pc.display();

        s.nextLine();
    }
}