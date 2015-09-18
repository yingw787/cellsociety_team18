import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SimulationPage {

	private String stageTitle = new String("Hello World!");
	
	public void create(Stage primaryStage) throws Exception{
		primaryStage.setTitle(stageTitle);
		
		MediaControlBar mediaControlBar = new MediaControlBar(); 
		//mediaControlBar.create();
		StackPane root = new StackPane();
		root.getChildren().add(mediaControlBar);
		primaryStage.setScene(new Scene(root));
		
		primaryStage.show();
	}
	
	
	
}
