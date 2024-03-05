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
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

// project imports
import impresario.IModel;

/** The class containing the Account View  for the ATM application */
//==============================================================
public class PatronView extends View
{

	// GUI components
	// protected TextField accountNumber;
	// protected TextField acctType;
	// protected TextField balance;
	protected TextField name;
	protected TextField address;
	protected TextField city;
	protected TextField stateCode;
	protected TextField zip;
	protected TextField email;
	protected TextField dateOfBirth;

	protected ComboBox<String> active;

	protected Button submitButton;
	protected Button doneButton;

	// For showing error message
	protected MessageView statusLog;

	// constructor for this class -- takes a model object
	//----------------------------------------------------------
	public PatronView(IModel account)
	{
		super(account, "PatronView");

		// create a container for showing the contents
		VBox container = new VBox(10);
		container.setPadding(new Insets(15, 5, 5, 5));

		// Add a title for this panel
		container.getChildren().add(createTitle());
		
		// create our GUI components, add them to this Container
		container.getChildren().add(createFormContent());

		container.getChildren().add(createStatusLog("             "));

		getChildren().add(container);

		myModel.subscribe("PatronSubmit", this);
	}


	// Create the title container
	//-------------------------------------------------------------
	private Node createTitle()
	{
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);	

		Text titleText = new Text(" Insert Patron ");
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

		Text nameLabel = new Text(" Name : ");
		nameLabel.setFont(myFont);
		nameLabel.setWrappingWidth(150);
		nameLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(nameLabel, 0, 0);

		name = new TextField();
		name.setEditable(true);
		grid.add(name, 1, 0);

		Text addressLabel = new Text(" Address : ");
		addressLabel.setFont(myFont);
		addressLabel.setWrappingWidth(150);
		addressLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(addressLabel, 0, 1);

		address = new TextField();
		address.setEditable(true);
		grid.add(address, 1, 1);

		Text cityLabel = new Text(" City : ");
		cityLabel.setFont(myFont);
		cityLabel.setWrappingWidth(150);
		cityLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(cityLabel, 0, 2);

		city = new TextField();
		city.setEditable(true);
		grid.add(city, 1, 2);

		Text stateCodeLabel = new Text(" State Code : ");
		stateCodeLabel.setFont(myFont);
		stateCodeLabel.setWrappingWidth(150);
		stateCodeLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(stateCodeLabel, 0, 3);

		stateCode = new TextField();
		stateCode.setEditable(true);
		grid.add(stateCode, 1, 3);

		Text zipLabel = new Text(" Zip : ");
		zipLabel.setFont(myFont);
		zipLabel.setWrappingWidth(150);
		zipLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(zipLabel, 0, 4);

		zip = new TextField();
		zip.setEditable(true);
		grid.add(zip, 1, 4);

		Text emailLabel = new Text(" Email : ");
		emailLabel.setFont(myFont);
		emailLabel.setWrappingWidth(150);
		emailLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(emailLabel, 0, 5);

		email = new TextField();
		email.setEditable(true);
		grid.add(email, 1, 5);

		Text dobLabel = new Text(" Date of Birth : ");
		dobLabel.setFont(myFont);
		dobLabel.setWrappingWidth(150);
		dobLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(dobLabel, 0, 6);

		dateOfBirth = new TextField();
		dateOfBirth.setEditable(true);
		grid.add(dateOfBirth, 1, 6);

		Text sLabel = new Text(" Status : ");
		sLabel.setFont(myFont);
		sLabel.setWrappingWidth(150);
		sLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(sLabel, 0, 7);

		active = new ComboBox<String>();
		active.setMinSize(100, 20);
		active.getItems().add("Active");
		active.getItems().add("Inactive");
		active.setValue(active.getItems().get(0));
		grid.add(active, 1, 7);

		HBox doneCont = new HBox(10);
		doneCont.setAlignment(Pos.CENTER);
		submitButton = new Button("Submit");
		submitButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
       		     @Override
       		     public void handle(ActionEvent e) {
       		    	clearErrorMessage();
					Properties partonProperties = new Properties();
					partonProperties.setProperty("name",name.getText());
					partonProperties.setProperty("address",address.getText());
					partonProperties.setProperty("city",city.getText());
					partonProperties.setProperty("stateCode",stateCode.getText());
					partonProperties.setProperty("zip",zip.getText());
					partonProperties.setProperty("email",email.getText());
					partonProperties.setProperty("dateOfBirth",dateOfBirth.getText());
					partonProperties.setProperty("status",active.getValue());
					if (validatePatronProperties(partonProperties))
					{
       		    		myModel.stateChangeRequest("PatronSubmit", partonProperties);
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

	private boolean validatePatronProperties(Properties properites)
	{
		if (properites.getProperty("name") == null | properites.getProperty("name") == "")
			return false;
		if (properites.getProperty("address") == null | properites.getProperty("address") == "") 
			return false;
		if (properites.getProperty("city") == null | properites.getProperty("city") == "") 
			return false;
		if (properites.getProperty("stateCode") == null | properites.getProperty("stateCode") == "") 
			return false;
		if (properites.getProperty("zip") == null | properites.getProperty("zip") == "") 
			return false;
		if (properites.getProperty("email") == null | properites.getProperty("email") == "") 
			return false;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

		try {
			Date earliest = simpleDateFormat.parse("1920-01-01");
        	Date latest = simpleDateFormat.parse("2006-01-01");
			Date actual = simpleDateFormat.parse(properites.getProperty("dateOfBirth"));
			if (actual.before(earliest) | actual.after(latest))
				return false;
		}
		catch (ParseException e) {
			return false;
		}
		
		return true;
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
	/**
	 * Update method
	 */
	//---------------------------------------------------------
	public void updateState(String key, Object value)
	{
		clearErrorMessage();
		if (key.equals("PatronSubmit") == true)
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


