import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileUploadButton extends Button{

	private String fileChooserTitle = new String("Open XML File:");
	
	public FileUploadButton(String name){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(fileChooserTitle);
		
		this.setText(name);
		this.setOnAction(new EventHandler<ActionEvent>(){
			
			public void handle(ActionEvent event){
				File file = fileChooser.showOpenDialog(new Stage());
				if (file != null){
					System.out.println("File upload successful");
				}
				else{
					System.out.println("File upload unsuccessful");
				}
			}
		});
	}
	
}
