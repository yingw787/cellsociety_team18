import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// instantiates the simulation visualization and media controls 
public class SimulationPage {

	private ResourceBundle myResources; 
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	public void create(Stage primaryStage, String language) throws Exception{
		
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		primaryStage.setTitle(myResources.getString("SimulationWindow"));
		
		primaryStage.show();

		
		VBox vbox = new VBox(); 
		
		MediaControlBar mediaControlBar = new MediaControlBar(language); 
		StackPane root = new StackPane();
		
		vbox.getChildren().add(mediaControlBar);
		
		root.getChildren().add(vbox);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene); 
		
		// javaFX only knows the scene width after primaryStage.show()! 
		primaryStage.setWidth(scene.getWidth() + 5); // don't know why I need the constant 5 there, but otherwise the buttons compress together 

		Visualization visualization = new Visualization(); 
		visualization.init(3, 5, scene.getWidth() + 5);
		vbox.getChildren().add(visualization);
		
		// sigh hardcode the button height cause I can't find out what it is 
		
		primaryStage.setHeight(53 + visualization.getVisualizationHeight()); // 53 is the empirically measured button height
		primaryStage.setResizable(false);



		
	}
	
}
