import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class textinput extends Application{
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        TextField tf1 = new TextField();
//        HBox hb = new HBox();
//        hb.getChildren().addAll

//        GridPane grid = new GridPane();
        tf1.setPromptText("Enter the question!");
        
        btn.setText("Print 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	System.out.println(tf1.getText());
                System.out.println("Hello World!");
            }
        });
   
        StackPane root = new StackPane();
        root.getChildren().add(tf1);
        root.getChildren().add(btn);
        

 Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 public static void main(String[] args) {
        launch(args);
    }
}