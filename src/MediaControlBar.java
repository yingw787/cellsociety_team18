
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class MediaControlBar extends HBox {
    private Simulation mySimulation;
    private KeyFrame frame;
    private Timeline animation;
    private String XMLFileDirectoryName = "XMLFiles";
    private String currentXMLFile;
    private double speedMultiplier = 1;
    private Stage primaryStage;
    private boolean firstLaunch = true;
    Visualization visualization=null; 

    private VisualizationFactory myVisualizationFactory;
    private HBox hbox;

    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    private ResourceBundle myResources;


    public MediaControlBar (String language) {

        hbox = new HBox();
        hbox.setAlignment(Pos.BOTTOM_CENTER);

        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);

        //         create the buttons in the media control bar
        Button playButton = new Button(myResources.getString("PlayButton"));
        Button pauseButton = new Button(myResources.getString("PauseButton"));
        Button stopButton = new Button(myResources.getString("StopButton"));
        Button fastForwardButton = new Button(myResources.getString("FastForwardButton"));
        Button stepForwardButton = new Button(myResources.getString("StepForwardButton"));
        Button slowDownButton = new Button(myResources.getString("SlowDownButton"));
        Button resetButton = new Button(myResources.getString("ResetButton"));
        ComboBox<String> uploadNewXMLFileButton = new ComboBox<String>();
        Button sendNewXMLFileButton = new Button(myResources.getString("SendNewXMLFileButton"));
        SwitchButton switchButton = new SwitchButton();

        //         set event handlers for the buttons

        //         play handler
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                animation.play();
            }
        });

        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                animation.pause();
            }
        });

        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("play clicked");
                animation.stop();
                try {
                    initializeAndStartSimAndTimeline();
                    animation.pause();
                }
                catch (ParserConfigurationException e1) {
                    //          TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                catch (SAXException e1) {
                    //          TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                catch (IOException e1) {
                    //          TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        fastForwardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                speedMultiplier = speedMultiplier * 2;
                animation.setRate(speedMultiplier);
            }
        });

        stepForwardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                mySimulation.step();
                visualization.drawCells(((SwitchButton)hbox.getChildren().get(0)).switchOnProperty().get());
            }
        });

        slowDownButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                speedMultiplier = speedMultiplier / 2;
                animation.setRate(speedMultiplier);
            }
        });

        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("play clicked");
            }
        });

        sendNewXMLFileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e) {
                if (uploadNewXMLFileButton.getValue() != null) {
                    try {
                        currentXMLFile = uploadNewXMLFileButton.getValue();
                        initializeAndStartSimAndTimeline();
                    }
                    catch (ParserConfigurationException e1) {
                        //                         TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    catch (SAXException e1) {
                        //                         TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    catch (IOException e1) {
                        //                         TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                else {
                    System.out.println("No file selected");
                }
            }
        });

        //         add the buttons to the media control bar
        hbox.getChildren().add(switchButton);
        hbox.getChildren().add(playButton);
        hbox.getChildren().add(pauseButton);
        hbox.getChildren().add(stopButton);
        hbox.getChildren().add(fastForwardButton);
        hbox.getChildren().add(stepForwardButton);
        hbox.getChildren().add(slowDownButton);
        hbox.getChildren().add(resetButton);
        hbox.getChildren().add(uploadNewXMLFileButton);
        hbox.getChildren().add(sendNewXMLFileButton);

        //         get list of XML files from local eclipse directory
        File folder = new File("./" + XMLFileDirectoryName);
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> XMLList = new ArrayList<String>();
        for (File file : listOfFiles) {
            XMLList.add(file.getName());
        }
        uploadNewXMLFileButton.getItems().addAll(XMLList);

        //         positions everything
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.getChildren().add(hbox);
        getChildren().add(vbox);

    }

    public void initializeAndStartSimAndTimeline () throws ParserConfigurationException,
    SAXException, IOException {

        InitializeSimulation.init(currentXMLFile);
        mySimulation = InitializeSimulation.getNewSimulation();

        if (firstLaunch) {
            firstLaunch = false;
            primaryStage = new Stage();
        }
        

        
        String testString = "Hexagon"; // change this to Rectangle, Triangle, or Hexagon for specific tesselations of different types 
        
        if(testString.equals("Rectangle")){
        	visualization = new RectangularVisualization(mySimulation.getCellSocietyGrid());
        }
        else if(testString.equals("Triangle")){
        	visualization = new TriangularVisualization(mySimulation.getCellSocietyGrid());
        }
        else if(testString.equals("Hexagon")){
        	visualization = new HexagonalVisualization(mySimulation.getCellSocietyGrid());
        }
        
        
        
//        Visualization visualization = new TriangularVisualization(mySimulation.getCellSocietyGrid());
//        Visualization visualization = new HexagonalVisualization(mySimulation.getCellSocietyGrid()); 
//        Visualization visualization = new RectangularVisualization(mySimulation.getCellSocietyGrid());
//        visualization = myVisualizationFactory.createVisualizationGrid("Rectangle", mySimulation);
        
        Scene visualizationScene = visualization.init(500.00, 500.00, ((SwitchButton)hbox.getChildren().get(0)).switchOnProperty().get());
        primaryStage.setScene(visualizationScene);
        primaryStage.setTitle(myResources.getString("SimulationWindow"));
        primaryStage.show();
        primaryStage.setHeight(visualization.getVisualizationHeight());
        primaryStage.setWidth(visualization.getVisualizationWidth());
//        primaryStage.setResizable(false);

        // TODO: init scene
        frame = new KeyFrame(Duration.millis(2000),
                             p -> {
                                 mySimulation.step();
                                 visualization.drawCells(((SwitchButton)hbox.getChildren().get(0)).switchOnProperty().get());
                                 // update scene
                             });
        animation = new Timeline();
        mySimulation.playAndLoop(animation, frame);
        animation.pause();
    }
}

