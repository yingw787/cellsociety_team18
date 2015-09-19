import java.io.File;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MediaControlBar extends HBox {
	
	private String XMLFileDirectoryName = "XMLFiles";
	
    public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
    private ResourceBundle myResources; 
    
//	private double WidthOfMediaControlBar = 0;
	
	public MediaControlBar(String language){
		
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.BOTTOM_CENTER);
		
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		
		// create the buttons in the media control bar 
		Button playButton = new Button(myResources.getString("PlayButton")); 
		Button pauseButton = new Button(myResources.getString("PauseButton"));
		Button stopButton = new Button(myResources.getString("StopButton")); 
		Button fastForwardButton = new Button(myResources.getString("FastForwardButton")); 
		Button stepForwardButton = new Button(myResources.getString("StepForwardButton")); 
		Button slowDownButton = new Button(myResources.getString("SlowDownButton")); 
		Button resetButton = new Button(myResources.getString("ResetButton"));
		ComboBox<String> uploadNewXMLFileButton = new ComboBox<String>();
		Button sendNewXMLFileButton = new Button(myResources.getString("SendNewXMLFileButton"));
				
		// set event handlers for the buttons 
		
		// play handler 
		playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("play clicked");
            }
        });
		
		pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("play clicked");
            }
        });
		
		stopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("play clicked");
            }
        });
		
		fastForwardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("play clicked");
            }
        });
		
		stepForwardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("play clicked");
            }
        });
		
		slowDownButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("play clicked");
            }
        });
		
		resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("play clicked");
            }
        });
		
		sendNewXMLFileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                if(uploadNewXMLFileButton.getValue() != null){
                	System.out.println(uploadNewXMLFileButton.getValue());
                }
                else{
                	System.out.println("No file selected");
                }
            }
        });
		
		
		// add the buttons to the media control bar 
		hbox.getChildren().add(playButton);
		hbox.getChildren().add(pauseButton);
		hbox.getChildren().add(stopButton);
		hbox.getChildren().add(fastForwardButton);
		hbox.getChildren().add(stepForwardButton);
		hbox.getChildren().add(slowDownButton);
		hbox.getChildren().add(resetButton);
		hbox.getChildren().add(uploadNewXMLFileButton);
		hbox.getChildren().add(sendNewXMLFileButton);
		
		// get list of XML files from local eclipse directory 
		File folder = new File("./" + XMLFileDirectoryName);
		File[] listOfFiles = folder.listFiles(); 
		ArrayList<String> XMLList = new ArrayList<String>();
		for(File file : listOfFiles){
			XMLList.add(file.getName());
		}
		uploadNewXMLFileButton.getItems().addAll(XMLList);
		
		// positions everything 
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.BOTTOM_CENTER);
		vbox.getChildren().add(hbox);
		this.getChildren().add(vbox);
		
	}
	
}
