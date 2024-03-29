// specify the package
package userinterface;

// system imports
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Properties;

// project imports
import impresario.IModel;

/** The class containing the Account View  for the ATM application */
//==============================================================
public class BookView extends View
{

	// GUI components
	// protected TextField accountNumber;
	// protected TextField acctType;
	// protected TextField balance;
	protected TextField author;
	protected TextField title;
	protected TextField pubYear;

	protected ComboBox<String> active;

	protected Button submitButton;
	protected Button doneButton;

	// For showing error message
	protected MessageView statusLog;

	// constructor for this class -- takes a model object
	//----------------------------------------------------------
	public BookView(IModel account)
	{
		super(account, "BookView");

		// create a container for showing the contents
		VBox container = new VBox(10);
		container.setPadding(new Insets(15, 5, 5, 5));

		// Add a title for this panel
		container.getChildren().add(createTitle());
		
		// create our GUI components, add them to this Container
		container.getChildren().add(createFormContent());

		container.getChildren().add(createStatusLog("             "));

		getChildren().add(container);

		myModel.subscribe("BookSubmit", this);
	}


	// Create the title container
	//-------------------------------------------------------------
	private Node createTitle()
	{
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);	

		Text titleText = new Text(" Insert Book ");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		titleText.setWrappingWidth(300);
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setFill(Color.DARKGREEN);
		container.getChildren().add(titleText);
		
		return container;
	}

	// Create the main form content
	//-------------------------------------------------------------
	private VBox createFormContent()
	{
		VBox vbox = new VBox(10);

		GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
       	grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

		Font myFont = Font.font("Helvetica", FontWeight.BOLD, 12);

		Text aLabel = new Text(" Author : ");
		aLabel.setFont(myFont);
		aLabel.setWrappingWidth(150);
		aLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(aLabel, 0, 0);

		author = new TextField();
		author.setEditable(true);
		grid.add(author, 1, 0);

		Text tLabel = new Text(" Title : ");
		tLabel.setFont(myFont);
		tLabel.setWrappingWidth(150);
		tLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(tLabel, 0, 1);

		title = new TextField();
		title.setEditable(true);
		grid.add(title, 1, 1);

		Text pyLabel = new Text(" Publication Year : ");
		pyLabel.setFont(myFont);
		pyLabel.setWrappingWidth(150);
		pyLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(pyLabel, 0, 2);

		pubYear = new TextField();
		pubYear.setEditable(true);
		grid.add(pubYear, 1, 2);

		Text sLabel = new Text(" Status : ");
		sLabel.setFont(myFont);
		sLabel.setWrappingWidth(150);
		sLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(sLabel, 0, 3);

		active = new ComboBox<String>();
		active.setMinSize(100, 20);
		active.getItems().add("Active");
		active.getItems().add("Inactive");
		active.setValue(active.getItems().get(0));
		grid.add(active, 1, 3);

		HBox doneCont = new HBox(10);
		doneCont.setAlignment(Pos.CENTER);
		submitButton = new Button("Submit");
		submitButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
       		     @Override
       		     public void handle(ActionEvent e) {
       		    	clearErrorMessage();
					Properties bookProperties = new Properties();
					bookProperties.setProperty("bookTitle",title.getText());
					bookProperties.setProperty("author",author.getText());
					bookProperties.setProperty("pubYear",pubYear.getText());
					bookProperties.setProperty("status",active.getValue());
					if (validateBookProperties(bookProperties))
					{
       		    		myModel.stateChangeRequest("BookSubmit", bookProperties);
					}
					else
					{
						displayErrorMessage("Invalid attribute field(s)");
					}
            	  }
        	});
		doneCont.getChildren().add(submitButton);

		doneButton = new Button("Done");
		doneButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		doneButton.setOnAction(new EventHandler<ActionEvent>() {

       		     @Override
       		     public void handle(ActionEvent e) {
       		    	clearErrorMessage();
       		    	myModel.stateChangeRequest("BookDone", null);   
            	  }
        	});
		doneCont.getChildren().add(doneButton);
	
		vbox.getChildren().add(grid);
		vbox.getChildren().add(doneCont);

		return vbox;
	}


	// Create the status log field
	//-------------------------------------------------------------
	protected MessageView createStatusLog(String initialMessage)
	{
		statusLog = new MessageView(initialMessage);

		return statusLog;
	}

	//-------------------------------------------------------------
	public void populateFields()
	{
		// accountNumber.setText((String)myModel.getState("AccountNumber"));
		// acctType.setText((String)myModel.getState("Type"));
		// balance.setText((String)myModel.getState("Balance"));
	 	// serviceCharge.setText((String)myModel.getState("ServiceCharge"));
	}

	private boolean validateBookProperties(Properties properites)
	{
		if (properites.getProperty("author") == null | properites.getProperty("author") == "")
			return false;
		if (properites.getProperty("bookTitle") == null | properites.getProperty("bookTitle") == "") 
			return false;
		if (Integer.parseInt(properites.getProperty("pubYear")) < 1800 | Integer.parseInt(properites.getProperty("pubYear")) > 2024) 
			return false;
		
		return true;
	}
	/**
	 * Update method
	 */
	//---------------------------------------------------------
	public void updateState(String key, Object value)
	{
		clearErrorMessage();
		if (key.equals("BookSubmit") == true)
		{
			System.out.println("Key: " + key);
			String val = (String)value;
			System.out.println("Value: " + val);
			displayMessage(val);
		}
	}

	/**
	 * Display error message
	 */
	//----------------------------------------------------------
	public void displayErrorMessage(String message)
	{
		statusLog.displayErrorMessage(message);
	}

	/**
	 * Display info message
	 */
	//----------------------------------------------------------
	public void displayMessage(String message)
	{
		statusLog.displayMessage(message);
	}

	/**
	 * Clear error message
	 */
	//----------------------------------------------------------
	public void clearErrorMessage()
	{
		statusLog.clearErrorMessage();
	}

}

//---------------------------------------------------------------
//	Revision History:
//


