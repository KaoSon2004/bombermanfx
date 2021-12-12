package Menu;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;
import uet.oop.bomberman.graphics.Sprite;

public class PauseSubScreen extends SubScene {
    private String imageString = "Menu/resource/PAUSEPRESET.png";
    private boolean isHiden;
    
    public PauseSubScreen() {
        super(new AnchorPane(), 181, 298);
        prefHeight(298 * 2);
        prefWidth(181 * 2);
        BackgroundImage backgroundImage = new BackgroundImage(new Image(imageString, 181, 298, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane rootAnchorPane = (AnchorPane) this.getRoot();
        rootAnchorPane.setBackground(new Background(backgroundImage));
        setLayoutX(31 * Sprite.SCALED_SIZE + 128);
        setLayoutY(Sprite.SCALED_SIZE * 13 / 2 - 298 / 2);
        isHiden = true;
    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);
        if (isHiden) {
            transition.setToX(- (31 * Sprite.SCALED_SIZE / 2 + 181));
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
