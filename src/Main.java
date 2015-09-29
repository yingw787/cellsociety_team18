import gui.SimulationPage;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Class that contains the executable function.
 */
public class Main extends Application {

    private final String language = "English";

    /**
     * The method that is executed.
     *
     * @param args the arguments
     */
    public static void main (String[] args) {
        launch(args);
    }

    /* (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start (Stage primaryStage) throws Exception {
        SimulationPage simulationPage = new SimulationPage();
        simulationPage.create(primaryStage, language);
    }
}
