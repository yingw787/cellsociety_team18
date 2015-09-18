import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SplashPage {
	
	private String stageName = new String("Hello World!");
	private String fileUploadButtonName = new String("Upload a file:");
	
	public void create(Stage primaryStage){
		primaryStage.setTitle(stageName);
		
		FileUploadButton fileUploadButton = new FileUploadButton(); 
		StackPane root = new StackPane(); 
		root.getChildren().add(fileUploadButton);
		primaryStage.setScene(new Scene(root, 300, 250));
		
		primaryStage.show();
	}

}
