// specify the package
package model;

import java.sql.SQLException;
// system imports
import java.util.Hashtable;
import java.util.Properties;

import javafx.stage.Stage;
import javafx.scene.Scene;

// project imports
import impresario.IModel;
import impresario.ISlideShow;
import impresario.IView;
import impresario.ModelRegistry;

import exception.InvalidPrimaryKeyException;
import exception.PasswordMismatchException;
import event.Event;
import userinterface.MainStageContainer;
import userinterface.View;
import userinterface.ViewFactory;
import userinterface.WindowPosition;

/** The class containing the Teller  for the ATM application */
//==============================================================
public class Librarian implements IView, IModel
// This class implements all these interfaces (and does NOT extend 'EntityBase')
// because it does NOT play the role of accessing the back-end database tables.
// It only plays a front-end role. 'EntityBase' objects play both roles.
{
	// For Impresario
	private Properties dependencies;
	private ModelRegistry myRegistry;

	private BookCollection bookCollection;
	private PatronCollection patronCollection;

	// GUI Components
	private Hashtable<String, Scene> myViews;
	private Stage myStage;

	// constructor for this class
	//----------------------------------------------------------
	public Librarian()
	{
		myStage = MainStageContainer.getInstance();
		myViews = new Hashtable<String, Scene>();

		// STEP 3.1: Create the Registry object - if you inherit from
		// EntityBase, this is done for you. Otherwise, you do it yourself
		myRegistry = new ModelRegistry("Librarian");
		if(myRegistry == null)
		{
			new Event(Event.getLeafLevelClassName(this), "Librarian",
				"Could not instantiate Registry", Event.ERROR);
		}

		// STEP 3.2: Be sure to set the dependencies correctly
		setDependencies();

		// Set up the initial view
		createAndShowLibrarianView();
	}

	//-----------------------------------------------------------------------------------
	private void setDependencies()
	{
		dependencies = new Properties();
		// dependencies.setProperty("Login", "LoginError");
		// dependencies.setProperty("Deposit", "TransactionError");
		// dependencies.setProperty("Withdraw", "TransactionError");
		// dependencies.setProperty("Transfer", "TransactionError");
		// dependencies.setProperty("BalanceInquiry", "TransactionError");
		// dependencies.setProperty("ImposeServiceCharge", "TransactionError");

		myRegistry.setDependencies(dependencies);
	}

	/**
	 * Method called from client to get the value of a particular field
	 * held by the objects encapsulated by this object.
	 *
	 * @param	key	Name of database column (field) for which the client wants the value
	 *
	 * @return	Value associated with the field
	 */
	//----------------------------------------------------------
	public Object getState(String key) {
		if (key.equals("BookCollection") == true)
		{
			if (bookCollection != null)
			{
				return bookCollection;
			}
			else
				return "Undefined";
		}
		else if (key.equals("PatronCollection") == true)
		{
			if (patronCollection != null)
			{
				return patronCollection;
			}
			else
				return "Undefined";
		}
		else
			return "";
	}

	//----------------------------------------------------------------
	public void stateChangeRequest(String key, Object value)
	{
		if (key.equals("SearchBooks")) {
			createAndShowSearchBooksView();
		}
		else if (key.equals("BookCollection")) {
			if (value != null) {
				BookCollection titleSearch = new BookCollection();
				try {
					String title = ((Properties)value).getProperty("title");
					titleSearch.findBooksWithTitleLike(title);
					bookCollection = (BookCollection)titleSearch.getState("BookCollection");
					createAndShowBookCollectionView();
				}
				catch (SQLException exc) {
					System.err.println("Error while finding books");
					exc.printStackTrace();
				}
			}
		}
		else if (key.equals("CancelBookCollection")) {
			createAndShowSearchBooksView();
		}
		else if (key.equals("CancelBookSearch")) {
			createAndShowLibrarianView();
		}
		else if (key.equals("SearchPatrons")) {
			createAndShowSearchPatronsView();
		}
		else if (key.equals("PatronCollection")) {
			if (value != null) {
				PatronCollection nameSearch = new PatronCollection();
				try {
					String name = ((Properties)value).getProperty("name");
					nameSearch.findPatronsWithNameLike(name);
					patronCollection = (PatronCollection)nameSearch.getState("PatronCollection");
					createAndShowPatronCollectionView();
				}
				catch (Exception exc) {
					System.err.println("Error while finding patrons");
					exc.printStackTrace();
				}
			}
		}
		else if (key.equals("CancelPatronCollection")) {
			createAndShowSearchPatronsView();
		}
		else if (key.equals("CancelPatronSearch")) {
			createAndShowLibrarianView();
		}
		
		myRegistry.updateSubscribers(key, this);
	}

	/** Called via the IView relationship */
	//----------------------------------------------------------
	public void updateState(String key, Object value)
	{
		// DEBUG System.out.println("Teller.updateState: key: " + key);

		stateChangeRequest(key, value);
	}

	
	//------------------------------------------------------------
	private void createAndShowLibrarianView()
	{
		Scene currentScene = (Scene)myViews.get("LibrarianView");

		if (currentScene == null)
		{
			// create our initial view
			View newView = ViewFactory.createView("LibrarianView", this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("LibrarianView", currentScene);
		}
				
		swapToView(currentScene);
		
	}

	private void createAndShowSearchBooksView()
	{
		Scene currentScene = (Scene)myViews.get("SearchBooksView");

		if (currentScene == null)
		{
			// create our initial view
			View newView = ViewFactory.createView("SearchBooksView", this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("SearchBooksView", currentScene);
		}
				
		swapToView(currentScene);
		
	}

	private void createAndShowBookCollectionView()
	{
		Scene currentScene;

		View newView = ViewFactory.createView("BookCollectionView", this); // USE VIEW FACTORY
		currentScene = new Scene(newView);
		myViews.put("BookCollectionView", currentScene);
				
		swapToView(currentScene);
		
	}

	private void createAndShowSearchPatronsView()
	{
		Scene currentScene = (Scene)myViews.get("SearchPatronsView");

		if (currentScene == null)
		{
			// create our initial view
			View newView = ViewFactory.createView("SearchPatronsView", this); // USE VIEW FACTORY
			currentScene = new Scene(newView);
			myViews.put("SearchPatronsView", currentScene);
		}
				
		swapToView(currentScene);
		
	}

	private void createAndShowPatronCollectionView()
	{
		Scene currentScene;

		View newView = ViewFactory.createView("PatronCollectionView", this); // USE VIEW FACTORY
		currentScene = new Scene(newView);
		myViews.put("PatronCollectionView", currentScene);
				
		swapToView(currentScene);
		
	}


	/** Register objects to receive state updates. */
	//----------------------------------------------------------
	public void subscribe(String key, IView subscriber)
	{
		// DEBUG: System.out.println("Cager[" + myTableName + "].subscribe");
		// forward to our registry
		myRegistry.subscribe(key, subscriber);
	}

	/** Unregister previously registered objects. */
	//----------------------------------------------------------
	public void unSubscribe(String key, IView subscriber)
	{
		// DEBUG: System.out.println("Cager.unSubscribe");
		// forward to our registry
		myRegistry.unSubscribe(key, subscriber);
	}



	//-----------------------------------------------------------------------------
	public void swapToView(Scene newScene)
	{

		
		if (newScene == null)
		{
			System.out.println("Librarian.swapToView(): Missing view for display");
			new Event(Event.getLeafLevelClassName(this), "swapToView",
				"Missing view for display ", Event.ERROR);
			return;
		}

		myStage.setScene(newScene);
		myStage.sizeToScene();
		
			
		//Place in center
		WindowPosition.placeCenter(myStage);

	}

}

