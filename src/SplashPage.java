import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SplashPage {
	
	private String stageName = new String("Welcome to CA Simulator");
	private String fileUploadButtonName = new String("Browse for file (DOESN'T WORK YET):");
	private String XMLDirectoryName = new String("XMLFiles");
	private String NameTheComboBox = new String("Or get one from the list below!");
	
	public void create(Stage primaryStage){
		primaryStage.setTitle(stageName);
		
		// create a centered menu of buttons
		VBox vbox = new VBox(); 
		vbox.setAlignment(Pos.CENTER);
		
		// create a file upload button in order to upload from anywhere on local directory
		FileUploadButton fileUploadButton = new FileUploadButton(fileUploadButtonName); 
		
		// create a file upload button in order to upload XML file from eclipse directory 
		ComboBox<String> menuButton = new ComboBox<String>();
		
		// retrieve the string names from all XML files within eclipse directory 
		File folder = new File("./" + XMLDirectoryName);
		File[] listOfFiles = folder.listFiles(); 
		
		// add string names to an arraylist 
		ArrayList<String> XMLList = new ArrayList<String>();
		
		
			for(File file : listOfFiles){
				XMLList.add(file.getName());
			}
		
		// add arraylist to the combobox 
		menuButton.getItems().addAll(XMLList);
		
		Label labelComboBox = new Label(NameTheComboBox);
		Button enterButton = new Button("Enter");
		
		// set event handling for successful file upload 
		// refactor this later on 
		enterButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(menuButton.getValue() != null){
					System.out.println(menuButton.getValue()); // debug purposes 
					primaryStage.close(); // closes the splash page stage in order to open a new stage 
					SimulationPage simulationPage = new SimulationPage(); 
					
					InitializeSimulation initializeSimulation = new InitializeSimulation(); 
					try {
						initializeSimulation.init(menuButton.getValue());
					} catch (ParserConfigurationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SAXException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					Stage simStage = new Stage();
					try {
						simulationPage.create(simStage);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					System.out.println("Need to select an option!");
				}
			}
			
		});
		
		StackPane root = new StackPane(); 
		
		vbox.getChildren().add(fileUploadButton);
		vbox.getChildren().add(labelComboBox);
		vbox.getChildren().add(menuButton);
		vbox.getChildren().add(enterButton);
		
		root.getChildren().add(vbox);
		primaryStage.setScene(new Scene(root, 300, 250));
		
		primaryStage.show();
	}

}
