package application;
	
import java.io.IOException;
import java.io.PrintWriter;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import javafx.application.Application;
import processing.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
			
			Scene scene = new Scene(grid, 800, 675);
			primaryStage.setTitle("Mechanographics Welcome");
			
			Text scenetitle = new Text("MECHANOGRAPHICS");
			scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			grid.add(scenetitle, 0, 0, 6, 1);
			
//			Label userName = new Label("Enter the Text:");
			TextArea inputTextField = new TextArea();
			grid.add(inputTextField, 0, 1);

			Button btn = new Button("Create");
			VBox VbBtn = new VBox(10);
			VbBtn.setAlignment(Pos.BOTTOM_RIGHT);
			VbBtn.getChildren().add(btn);
			grid.add(VbBtn, 1, 1);
			
			final Text actiontarget = new Text();
	        grid.add(actiontarget, 2, 1);
	        
			GridPane gridsp = new GridPane();
			ScrollPane sp = new ScrollPane(gridsp);
			grid.add(sp,0,3);

	        TextArea dependencyTextField = new TextArea();
			gridsp.add(dependencyTextField, 0, 3);
//			sp.setFitToWidth(true);

			String textfile = "input3.txt";
	        
			btn.setOnAction(new EventHandler<ActionEvent>() {
				 
			    @Override
			    public void handle(ActionEvent e) {
			        actiontarget.setFill(Color.FIREBRICK);
			        
			        final String inputtext = inputTextField.getText();
			        System.out.println(inputTextField.getText());
					
					WriteIntoFile wif = new WriteIntoFile(textfile, inputtext); 
					try {
						wif.makeAFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			        ParserDemo parserdemo = new ParserDemo(textfile);
			        parserdemo.demoDP(parserdemo.lp, textfile);
			        
			        dependencyTextField.setText(parserdemo.typed_dependencies.toString());
			    }
			});
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
