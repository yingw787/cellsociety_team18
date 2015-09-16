import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class MediaPlayerButtonAbstract extends Button {

	private String name = new String("Hello World!");
	
	public MediaPlayerButtonAbstract(){
		
	}
	
	public void init(){
		Button button = new Button(); 
		button.setText(name);
		button.setOnAction(new EventHandler<ActionEvent>(){
			
			public void handle(ActionEvent event){
				System.out.println("Hello World!");
			}
		});
	}
	
	
}
