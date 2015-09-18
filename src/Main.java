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

		SplashPage splashPage = new SplashPage(); 
		splashPage.create(primaryStage);

	}
}




