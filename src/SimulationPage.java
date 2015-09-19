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
		
		
//		primaryStage.setTitle(myResources.getString("SimulationWindow"));
		
//		primaryStage.show();

		
		// media control bar window 
		Stage mediaControlBarStage = new Stage(); 
		mediaControlBarStage.setTitle(myResources.getString("MediaControlBarWindow"));
		MediaControlBar mediaControlBar = new MediaControlBar(language); 
		StackPane root = new StackPane();
		root.getChildren().add(mediaControlBar);
		Scene mediaControlBarScene = new Scene(root);
		mediaControlBarStage.setScene(mediaControlBarScene); 
		mediaControlBarStage.show(); 
		// javaFX only knows the scene width after mediaControlBarStage.show()!
		mediaControlBarStage.setWidth(mediaControlBarScene.getWidth() + 5);
		mediaControlBarStage.setHeight(53);
		mediaControlBarStage.setResizable(false);
		

//		Visualization visualization = new Visualization(); 
//		visualization.init(3, 5, scene.getWidth() + 5);
//		vbox.getChildren().add(visualization);



		
	}
	
}
