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
	protected TextField author;
	protected TextField title;
	protected TextField publicationYear;

	protected Button cancelButton;

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

		populateFields();

		myModel.subscribe("UpdateStatusMessage", this);
	}


	// Create the title container
	//-------------------------------------------------------------
	private Node createTitle()
	{
		HBox container = new HBox();
		container.setAlignment(Pos.CENTER);	

		Text titleText = new Text(" Brockport Library ");
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
        
        Text prompt = new Text("BOOK INFORMATION");
        prompt.setWrappingWidth(400);
        prompt.setTextAlignment(TextAlignment.CENTER);
        prompt.setFill(Color.BLACK);
        grid.add(prompt, 0, 0, 2, 1);

		Text authorLabel = new Text(" Author : ");
		Font myFont = Font.font("Helvetica", FontWeight.BOLD, 12);
		authorLabel.setFont(myFont);
		authorLabel.setWrappingWidth(150);
		authorLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(authorLabel, 0, 1);

		author = new TextField();
		author.setEditable(false);
		grid.add(author, 1, 1);

		Text titleLabel = new Text(" Title : ");
		titleLabel.setFont(myFont);
		titleLabel.setWrappingWidth(150);
		titleLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(titleLabel, 0, 2);

		title = new TextField();
		title.setEditable(false);
		grid.add(title, 1, 2);

		Text publicationYearLabel = new Text(" Publication Year : ");
		publicationYearLabel.setFont(myFont);
		publicationYearLabel.setWrappingWidth(150);
		publicationYearLabel.setTextAlignment(TextAlignment.RIGHT);
		grid.add(publicationYearLabel, 0, 3);

		publicationYear = new TextField();
		publicationYear.setEditable(false);
		grid.add(publicationYear, 1, 3);

		// Text scLabel = new Text(" Service Charge : ");
		// scLabel.setFont(myFont);
		// scLabel.setWrappingWidth(150);
		// scLabel.setTextAlignment(TextAlignment.RIGHT);
		// grid.add(scLabel, 0, 4);

		// serviceCharge = new TextField();
		// serviceCharge.setEditable(true);
		// serviceCharge.setOnAction(new EventHandler<ActionEvent>() {

  		//      @Override
  		//      public void handle(ActionEvent e) {
  		//     	clearErrorMessage();
  		//     	myModel.stateChangeRequest("ServiceCharge", serviceCharge.getText());
       	//      }
        // });
		// grid.add(serviceCharge, 1, 4);

		HBox doneCont = new HBox(10);
		doneCont.setAlignment(Pos.CENTER);
		cancelButton = new Button("Back");
		cancelButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {

       		     @Override
       		     public void handle(ActionEvent e) {
       		    	clearErrorMessage();
       		    	myModel.stateChangeRequest("BookCancelled", null);   
            	  }
        	});
		doneCont.getChildren().add(cancelButton);
	
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
		author.setText((String)myModel.getState("author"));
		title.setText((String)myModel.getState("title"));
		publicationYear.setText((String)myModel.getState("pubYear"));
	}

	/**
	 * Update method
	 */
	//---------------------------------------------------------
	public void updateState(String key, Object value)
	{
		clearErrorMessage();
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


