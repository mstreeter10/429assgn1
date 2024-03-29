package model;

import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

public class BookCollection extends EntityBase {
    private static final String myTableName = "Book";
    private Vector<Book> bookList;
    public BookCollection() {
        super(myTableName);
        bookList = new Vector<Book>();
    }

    public void display() {
        if (bookList.size() == 0) {
            System.out.println("No books in collection");
        }
        else {
            for (int i = 0; i < bookList.size(); i++) {
                System.out.println(bookList.elementAt(i).toString());
            }
        }
    }

    public void findBooksOlderThanDate(String year) throws SQLException {
        String query = "SELECT * FROM " + myTableName + " WHERE pubYear < '" + year + "'";
        Vector<Properties> result = getSelectQueryResult(query);
        if (result != null) {
			for (int i = 0; i < result.size(); i++) {
				Properties nextBookData = result.elementAt(i);
				Book book = new Book(nextBookData);
				if (book != null) {
					bookList.add(book);
				}
			}
		}
    }

    public void findBooksNewerThanDate(String year) throws SQLException {
        String query = "SELECT * FROM " + myTableName + " WHERE pubYear > '" + year + "'";
        Vector<Properties> result = getSelectQueryResult(query);
        if (result != null) {
			for (int i = 0; i < result.size(); i++) {
				Properties nextBookData = result.elementAt(i);
				Book book = new Book(nextBookData);
				if (book != null) {
					bookList.add(book);
				}
			}
		}
    }

    public void findBooksWithTitleLike(String title) throws SQLException {
        String query = "SELECT * FROM " + myTableName + " WHERE bookTitle LIKE '%" + title + "%' ORDER BY author ASC;";
        Vector<Properties> result = getSelectQueryResult(query);
        if (result != null) {
			for (int i = 0; i < result.size(); i++) {
				Properties nextBookData = result.elementAt(i);
				Book book = new Book(nextBookData);
				if (book != null) {
					bookList.add(book);
				}
			}
		}
    }

    public void findBooksWithAuthorLike(String author) throws SQLException {
        String query = "SELECT * FROM " + myTableName + " WHERE author LIKE '%" + author + "%';";
        Vector<Properties> result = getSelectQueryResult(query);
        if (result != null) {
            for (int i = 0; i < result.size(); i++) {
                Properties nextBookData = result.elementAt(i);
                Book book = new Book(nextBookData);
                if (book != null) {
                    bookList.add(book);
                }
            }
        }
    }

    public Object getState(String key) {
		if (key.equals("Books"))
			return bookList;
		else if (key.equals("BookCollection"))
			return this;
		return null;
	}

    public void stateChangeRequest(String key, Object value) {
		myRegistry.updateSubscribers(key, this);
	}

    protected void initializeSchema(String tableName) {
		if (mySchema == null) {
			mySchema = getSchemaInfo(tableName);
		}
	}
}
