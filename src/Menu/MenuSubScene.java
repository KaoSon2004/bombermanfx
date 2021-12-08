package Menu;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class MenuSubScene extends SubScene {
	private String font = "src\\Menu\\resource\\kenvector_future.ttf";
	private String imageString = "Menu/resource/blue_panel.png";
	private boolean isHiden;

	public MenuSubScene() {
		super(new AnchorPane(), 600, 400);
		prefHeight(600);
		prefWidth(400);
		BackgroundImage backgroundImage = new BackgroundImage(new Image(imageString, 600, 400, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		AnchorPane rootAnchorPane = (AnchorPane) this.getRoot();
		rootAnchorPane.setBackground(new Background(backgroundImage));
		setLayoutX(1024);
		setLayoutY(150);
		isHiden = true;
	}
	
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		if (isHiden) {
			transition.setToX(-640);
			isHiden = false;
		} else {
			transition.setToX(0);
			isHiden = true;
		}
		transition.play();
	}
	
	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
 
}

