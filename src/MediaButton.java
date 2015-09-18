import javafx.scene.control.Button;
import javafx.scene.shape.Circle;

public class MediaButton extends Button{

	private int scaleFactor = 20; 
	private double autoAdjustWidthToMakeActualCircle = 0.15; // needs to be adjusted based on what scaleFactor is
	
	private int buttonHeightScale = scaleFactor; 
	private double buttonWidthScale = scaleFactor * autoAdjustWidthToMakeActualCircle; 
	
	private MediaButton(String buttonText){
		this.setShape(new Circle(0.5));
		this.setPrefSize(25*buttonWidthScale, 3.5*buttonHeightScale);
		this.setText(buttonText);
	}
	
	public MediaButton(){
		this("");
	}
	
	public void makePlayButton(){ // sets as play button
		this.setText(">");
	}
	
	public void makePauseButton(){
		this.setText("||");
	}
	
	public void makeFastForwardButton(){
		this.setText(">>");
	}
	
	public void makeStepForwardButton(){
		this.setText("-->");
	}
	
	public void makeSlowDownButton(){
		this.setText("<<");
	}
	
}
