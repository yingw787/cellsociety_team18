import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class MediaControlBar extends HBox {
	
	public MediaControlBar(){
		this.setAlignment(Pos.CENTER);
		MediaButton playPauseButton = new MediaButton(); 
		playPauseButton.makePlayButton();
		this.getChildren().add(playPauseButton);
		
	}
	
}
