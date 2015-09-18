import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// //****THE IDEAL CODE BELOW IS ALL THAT SHOULD BE IN START 
				SplashPage splashPage = new SplashPage(); 
				splashPage.create(primaryStage);

		// //*****TESTING PURPOSES ONLY 
//		SimulationPage simulationPage = new SimulationPage();
//		simulationPage.create(primaryStage);
		
//		Group root = new Group(); 
//		MediaControlBar mediaControlBar = new MediaControlBar();
//		root.getChildren().add(mediaControlBar);
//		
//		Scene scene = new Scene(root); 
//		primaryStage.setScene(scene);
//		primaryStage.show();
		
	}
}




