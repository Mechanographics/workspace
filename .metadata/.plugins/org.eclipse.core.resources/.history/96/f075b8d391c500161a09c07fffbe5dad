package application;

import javafx.application.Application;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class appli2 extends Application{

	  public static void main(String[] args) {
	    Application.launch(args);
	  }

	  @Override
	  public void start(Stage primaryStage) {

		  String inputq;
		  
		primaryStage.setTitle("MECHANOGRAPHICS");
	    BorderPane bp = new BorderPane();
	    bp.setPadding(new Insets(10, 20, 10, 20));

	    //Button btnTop = new Button("Top");
	    Text scenetitle = new Text("MECHANOGRAPHICS");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	    bp.setTop(scenetitle);

	    TextArea inputTF = new TextArea();		//Text Field(TF) for the user to enter input
	    
	    inputTF.setPrefHeight(700);
	    inputTF.setPrefColumnCount(25);
	    bp.setLeft(inputTF);
	    
	    Label depTF = new Label();// Text element to show Parser output
	    depTF.setText("OUTPUT");
	    bp.setRight(depTF);
	    
	    Button btnViewDep = new Button("View Dependencies"); // View Dependencies Button 
	    bp.setCenter(btnViewDep);
	    
		btnViewDep.setOnAction(new EventHandler<ActionEvent>() {
			 
		    @Override
		    public void handle(ActionEvent e) {
				//	depTF.setFill(Color.FIREBRICK);
		    	//	depTF.setText("the ball");
		    	String inputq = inputTF.getText();
		    	
		    	depTF.setText(String.valueOf(inputq));
		        
		    }
		});

	    Button btnBottom = new Button("Draw FBD");
	    bp.setBottom(btnBottom);

	    Scene scene = new Scene(bp, 800, 500);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	  }
	  }