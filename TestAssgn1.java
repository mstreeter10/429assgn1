import java.util.Properties;
import model.*;

class TestAssgn1{
    public static void main(String args[]){
        Properties book1Properties = new Properties();
        book1Properties.setProperty("bookTitle", "Snow White");
        book1Properties.setProperty("author", "Joe");
        book1Properties.setProperty("pubYear", "1000");
        book1Properties.setProperty("status", "Active");

        Book book1 = new Book(book1Properties);
        book1.update();
    }
}