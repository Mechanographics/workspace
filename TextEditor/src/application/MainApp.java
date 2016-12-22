package application;
	
import java.awt.Graphics;
import java.io.IOException;
import java.util.LinkedList;

import graphics_rendering.renderForces;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import languageProcessing.InitialProcessing;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	TextProController controller;
	LinkedList<Double> dF,uF,rF,lF;
	// called at start of application
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		
		this.primaryStage.setTitle("TextProApp");
		
		try {
			// Load root layout from fxml
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            
            // min height and width calculated from components in TextAppLayout
            primaryStage.setMinHeight(430);
            primaryStage.setMinWidth(334);
            primaryStage.show();
            
          
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		showTextProApp();
	}
	
	/**
     * Shows the main TextApplication scene
     */
    public void showTextProApp() {
        try {
            // Load the fxml file and set into the center of the main layout
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/TextAppLayout.fxml"));
            
            HBox textProPage = (HBox) loader.load();
            rootLayout.setCenter(textProPage);
            
            // Connect controller and main app
            controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }
    
    public void showInputErrorDialog(String inErr) {
    	Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Input Error");
		alert.setContentText(inErr);

		alert.showAndWait();
    }
    
    /**
     * Displays dialog that allows user to select local text file to display in TextArea
     * 
     * @param ta - reference to TextArea to display loaded text file
     * 
     */
    public void showLoadFileDialog(InitialProcessing ip) {
    	
    	// Load the fxml file and create a new stage for the popup
		FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/DependencyLayout.fxml"));
		
		VBox dialogVbox = new VBox(200);
		
		
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Dependencies");
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		
		dialogVbox.getChildren().add(new Text("THE NUMERCICAL : "+controller.text_from_textbox+"\n\n"+
				"Object name : "+ip.pt.objectName +" weights "+ip.pt.objectW.toString()+"\n"+
				"Angle of inclination : "+ip.pt.inclination_angle+"\n"+
				"Co-efficient of Friction : "+ip.pt.friction_coeff+"\n"+
				
				"\n\nThe different forces acting on it are : "+"\n\n"+ 
				"Upward Force : "+ip.pt.upForce+"\n"+
				"Downward Force : "+ip.pt.downForce+"\n"+
				"Left Force : "+ip.pt.leftForce+"\n"+
				"Right Force : "+ip.pt.rightForce+"\n"+
				
				"\n\nJUST INCASE THE DATA IS NOT CORRECT, ENTER THE QUESTION AGAIN\n"+
				"BUT THIS TIME IN A DIFFRERENT FORM " +ip.pt.downForce.size()
				));

		dF = ip.pt.downForce;	uF = ip.pt.upForce;
		rF = ip.pt.rightForce;	lF = ip.pt.leftForce;
		
		Scene dialogScene = new Scene(dialogVbox, 600,600);		
		dialogStage.setScene(dialogScene);
		dialogStage.showAndWait();
		
    }
    
	public void showFBD(InitialProcessing ip){
    	FXMLLoader fbd = new FXMLLoader(MainApp.class.getResource("view/FBDLayout.fxml"));
//    	VBox fbdVbox = new VBox(200);
		Stage stage = new Stage();
		stage.setTitle("Free Body Diagram");
		stage.initModality(Modality.WINDOW_MODAL);
    	 Group root = new Group();
    	    Scene scene = new Scene(root, 500, 500);
    	    stage.setScene(scene);

    	    Group g = new Group();

    	    Polygon polygon = new Polygon();
    	    polygon.getPoints().addAll(new Double[]{
    	        175.0, 175.0,
    	        175.0, 245.0,
    	        245.0, 245.0,
    	        245.0, 175.0  
    	        });
    	    
    	    g.getChildren().add(polygon);
    	    
	    	  float x1 = 200,	x2 = 200,  yd1 = 245,	yd2 = 300,  yu1 = 175,	yu2 = 120;
//		      int numLeft = ip.pt.leftForce.size();
//		      int numRight = ip.pt.rightForce.size();
	//	      int numUp = 2;//ip.pt.upForce.size();
		      int numDown = ip.pt.downForce.size();
		     // System.out.println(ip.pt.downForce.size());
		      
  	      for(int i=0; i<numDown; i++){
  	    	  	    	  
  	    	  Line line = new Line();
  	          line.setStartX(x1+(i*10)*1.0);
  	          line.setStartY(yd1);
  	          line.setEndX(x2+(i*10)*1.0);
  	          line.setEndY(yd2);
  	          g.getChildren().add(line);
  	      }
//  	      for(int i=0; i<numUp; i++){
//  	    	  g.drawLine(x1+(i*10), yu1, x2+(i*10), yu2);
  //	      }
/*  	      for(int i=0; i<numLeft; i++){
//  		      g.drawLine(yd1, x1+(i*10), yd2, x2+(i*10));
  	      }
  	      for(int i=0; i<numRight; i++){
//  		      g.drawLine(yu1, x1+(i*10), yu2, x2+(i*10));
  	      }
    	    
*/    	    
    	    
    	    
    	    scene.setRoot(g);
    	    stage.show();				

		
		
 }
 /*
    public void showEditDistanceDialog(String selectedText) {
    	try {
    		// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/EditDistanceLayout.fxml"));
			VBox page = (VBox) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Calculate Edit Distance");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set reference to stage in controller
			//EditDistanceDialogController controller = loader.getController();
			//controller.setDialogStage(dialogStage);
			//controller.setMainApp(this);
			//controller.setField(selectedText);
			
			
			// give controller reference to scene (cursor)

			// Show the dialog and wait until the user closes it
		    dialogStage.showAndWait();
		    
		    
		

    	} catch (IOException e) {
    		// Exception gets thrown if the fxml file could not be loaded
    		e.printStackTrace();
    	}
    	
    }
    
    public void showEDResult(List<String> path) {
        // intialize alert/dialog to display edit distance result
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Result");
    	alert.setHeaderText("Word Path : ");
    	alert.initModality(Modality.NONE);
    	alert.setResizable(true);
    	
    	// create layout for content
    	VBox box = new VBox();
    	HBox midBox = new HBox();
    	box.setPadding(new Insets(35,0,35,0));
    	box.setSpacing(35);
    	midBox.setSpacing(15);
    	
    	Label pathLabel = new Label();
    	Label numStepsLabel = new Label("Number of steps : ");
    	Label numSteps = new Label();
    	Font font = new Font(14);
    	pathLabel.setFont(font);
    	numStepsLabel.setFont(font);    	
    	numSteps.setFont(Font.font(font.getFamily(), FontWeight.BOLD, 14));
    	
    	midBox.getChildren().add(numStepsLabel);
    	midBox.getChildren().add(numSteps);
    	midBox.setAlignment(Pos.CENTER);
    	
    	box.getChildren().add(pathLabel);
    	box.getChildren().add(midBox);
    	box.setAlignment(Pos.CENTER);
    	alert.getDialogPane().setPrefWidth(300);
    	
    	// check for path
    	if(path != null) {
    		numSteps.setText(Integer.toString(path.size()-1));
	    	pathLabel.setText(String.join(" -> ", path));
	    	
	    	Text text = new Text(pathLabel.getText());
	    	text.setFont(font);
	    	if(text.getLayoutBounds().getWidth() > 200) {
		    	alert.getDialogPane().setPrefWidth(text.getLayoutBounds().getWidth()+100);
	    	}
	    	
    	}
    	// no path found
    	else {
    		pathLabel.setText("No Path Found.");
    		numSteps.setText("N/A");
    	}
    	
    	// set content and styling
    	alert.getDialogPane().setContent(box);
    	alert.getDialogPane().getStylesheets().add(
    			   getClass().getResource("application.css").toExternalForm());
    	alert.getDialogPane().getStyleClass().add("myDialog");
    	alert.showAndWait();
    }
    
    
    public void showMarkovDialog(textgen.MarkovTextGenerator mtg) {
    	try {
    		// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/MarkovLayout.fxml"));
			BorderPane page = (BorderPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Markov Text Generator");
			//dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set reference to stage in controller
			//BUG -- when first displayed results don't show up until resize window
			MarkovController controller = loader.getController();
			//controller.setDialogStage(dialogStage);
			controller.setMainApp(this);
			controller.setMTG(mtg);

			// Show the dialog and wait until the user closes it
		    dialogStage.showAndWait();
		    
		    
		

    	} catch (IOException e) {
    		// Exception gets thrown if the fxml file could not be loaded
    		e.printStackTrace();
    	}
    	
    	
    }
    */
    public void showLoadStage(Stage loadStage, String text) {
    	loadStage.initModality(Modality.APPLICATION_MODAL);
    	loadStage.initOwner(primaryStage);
        VBox loadVBox = new VBox(20);
        loadVBox.setAlignment(Pos.CENTER);
        Text tNode = new Text(text);
        tNode.setFont(new Font(16));
        loadVBox.getChildren().add(new HBox());
        loadVBox.getChildren().add(tNode);
        loadVBox.getChildren().add(new HBox());
        Scene loadScene = new Scene(loadVBox, 300, 200);
        loadStage.setScene(loadScene);
        loadStage.show();
    }
    
    // MAIN
	public static void main(String[] args) {
		launch(args);
		
	}
	

	public Stage getStage() {
		return this.primaryStage;
	}
	
}
