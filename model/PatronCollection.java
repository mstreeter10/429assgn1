package model;

import java.util.Properties;
import java.util.Vector;
import java.util.regex.*;

public class PatronCollection extends EntityBase
{
	private static final String myTableName = "Patron";
	private Vector<Patron> patronList;

	public PatronCollection()
	{
		super(myTableName);
		patronList = new Vector<Patron>();
	}

	public void findPatronsOlderThan(String date) throws IllegalArgumentException
	{
		if (date == null || !Pattern.matches("^[0-9]{4}-(0[1-9]|1[0-2])-([0-2][0-9]|3(0|1))$", date)) throw new IllegalArgumentException();
		String query = "SELECT * FROM " + myTableName + " WHERE dateOfBirth < '" + date + "'";
		Vector<Properties> result = getSelectQueryResult(query);
		if (result != null) populatePatronList(result);
		else System.out.println("Found no results for partons older than " + date);
	}

	public void findPatronsYoungerThan(String date) throws IllegalArgumentException
	{
		if (date == null || !Pattern.matches("^[0-9]{4}-(0[1-9]|1[0-2])-([0-2][0-9]|3(0|1))$", date)) throw new IllegalArgumentException();
		String query = "SELECT * FROM " + myTableName + " WHERE dateOfBirth > '" + date + "'";
		Vector<Properties> result = getSelectQueryResult(query);
		if (result != null) populatePatronList(result);
		else System.out.println("Found no results for partons younger than " + date);
	}

	public void findPatronsAtZipCode(String zip) throws IllegalArgumentException
	{
		if (zip == null || !Pattern.matches("^[0-9]{5}$", zip)) throw new IllegalArgumentException();
		String query = "SELECT * FROM " + myTableName + " WHERE (zip = " + zip + " );";
		Vector<Properties> result = getSelectQueryResult(query);
		if (result != null) populatePatronList(result);
		else System.out.println("Found no results for partons at zip code " + zip);
	}

	public void findPatronsWithNameLike(String name) throws IllegalArgumentException
	{
		if (name == null) throw new IllegalArgumentException();
		String query = "SELECT * FROM " + myTableName + " WHERE name LIKE '%" + name + "%'';";
		Vector<Properties> result = getSelectQueryResult(query);
		if (result != null) populatePatronList(result);
		else System.out.println("Found no results for partons with name like " + name);
	}

	private void populatePatronList(Vector<Properties> queryResult)
	{
		for (int i = 0; i < queryResult.size(); i++) {
			Properties nextQueryItem = queryResult.elementAt(i);
			Patron parton = new Patron(nextQueryItem);
			patronList.add(parton);
		}
	}

	public void display()
	{
		if (patronList.size() == 0)
			System.out.println("No patrons in collection");
		else 
			for (int i = 0; i < patronList.size(); i++) 
				patronList.elementAt(i).display();
	}

	public Object getState(String key)
	{
		if (key.equals("patronList"))
			return patronList;
		return null;
	}

	public void stateChangeRequest(String key, Object value)
	{
		myRegistry.updateSubscribers(key, this);
	}

	public void updateState(String key, Object value)
	{
		stateChangeRequest(key, value);
	}

	protected void initializeSchema(String tableName)
	{
		if (mySchema == null)
		{
			mySchema = getSchemaInfo(tableName);
		}
	}
}
