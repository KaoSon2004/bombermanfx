package Menu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import sound.SoundEffect; 


public class IntroSubScene extends SubScene {
    Label introLabel = new Label("CLICK MOUSE TO CONTINUE");
    private Image logoImage = new Image("Menu/resource/LOGO2.png");
    private ImageView logoImageView = new ImageView(logoImage);
    private Image sky = new Image("Menu/resource/Layers/sky.png", width, height, false, false);
    private Image sky2 = new Image("Menu/resource/Layers/sky.png", width, height, false, false);
    private Image cloudbg = new Image("Menu/resource/Layers/clouds_bg.png", width, height, false, false);
    private Image cloudbg2 = new Image("Menu/resource/Layers/clouds_bg.png", width, height, false, false);
    private Image cloudLonely = new Image("Menu/resource/Layers/cloud_lonely.png", width, height, false, false);
    private Image cloudLonely2 = new Image("Menu/resource/Layers/cloud_lonely.png", width, height, false, false);
    private Image moutain = new Image("Menu/resource/Layers/glacial_mountains.png", width, height, false, false);
    private Image moutain2 = new Image("Menu/resource/Layers/glacial_mountains.png", width, height, false, false);
    private Image cloudA = new Image("Menu/resource/Layers/clouds_mg_3.png", width, height, false, false);
    private Image cloudB = new Image("Menu/resource/Layers/clouds_mg_3.png", width, height, false, false);
    private Image cloud2A = new Image("Menu/resource/Layers/clouds_mg_2.png", width, height, false, false);
    private Image cloud2B = new Image("Menu/resource/Layers/clouds_mg_2.png", width, height, false, false);
    private Image cloud3A = new Image("Menu/resource/Layers/clouds_mg_1.png", width, height, false, false);
    private Image cloud3B = new Image("Menu/resource/Layers/clouds_mg_1_lightened.png", width, height, false, false);
    static int width = deConfig.screenWidth;
    static int height = deConfig.screenHeight;
    private ImageView skyImageView = new ImageView(sky);
    private ImageView skyImageView2 = new ImageView(sky2);
    private ImageView cloudbgImageView = new ImageView(cloudbg);
    private ImageView cloudbgImageView2 = new ImageView(cloudbg2);
    private ImageView cloudLonelyImageView = new ImageView(cloudLonely);
    private ImageView cloudLonelyImageView2 = new ImageView(cloudLonely2);
    private ImageView moutainImageView = new ImageView(moutain);
    private ImageView moutainImageView2 = new ImageView(moutain2);
    private ImageView cloudAImageView = new ImageView(cloudA);
    private ImageView cloudBImageView = new ImageView(cloudB);
    private ImageView cloud2AImageView = new ImageView(cloud2A);
    private ImageView cloud2BImageView = new ImageView(cloud2B);
    private ImageView cloud3AImageView = new ImageView(cloud3A);
    private ImageView cloud3BImageView = new ImageView(cloud3B);
    ImageView[] imageViews = new ImageView[] {skyImageView, skyImageView2, cloudbgImageView, cloudbgImageView2, cloudLonelyImageView
            , cloudLonelyImageView2, moutainImageView, moutainImageView2, cloudAImageView, cloudBImageView
            , cloud2AImageView, cloud2BImageView, cloud3AImageView, cloud3BImageView, logoImageView};
    private static boolean isDisappear = false;
    
    public static boolean isDisappear() {
        return isDisappear;
    }

    public IntroSubScene() {
        super(new AnchorPane(), width, height);
        prefHeight(height);
        prefWidth(width);
        for (int i = 0; i < imageViews.length; i++) {
            getPane().getChildren().add(imageViews[i]);
        }
        getPane().getChildren().add(introLabel);
        /*getPane().getChildren().addAll(skyImageView, skyImageView2, cloudbgImageView, cloudbgImageView2, cloudLonelyImageView
                , cloudLonelyImageView2, moutainImageView, moutainImageView2, cloudAImageView, cloudBImageView
                , cloud2AImageView, cloud2BImageView, cloud3AImageView, cloud3BImageView);*/
       // imageViews.add(skyImageView);
        skyImageView2.setLayoutX(-width);
        cloudbgImageView2.setLayoutX(-width);
        cloudLonelyImageView2.setLayoutX(-width);
        moutainImageView2.setLayoutX(-width);
        cloudBImageView.setLayoutX(-width);
        cloud2BImageView.setLayoutX(-width);
        cloud3BImageView.setLayoutX(-width);
        logoImageView.setLayoutX(width / 2 + 200);
        introLabel.setLayoutX((width - 300) / 2);
        introLabel.setLayoutY(height - 100);
        try {
            introLabel.setFont(Font.loadFont(new FileInputStream("src\\Menu\\resource\\OXYGENE1.ttf"), 25));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logoImageView.setLayoutY(-logoImageView.getFitHeight());
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                // TODO Auto-generated method stub
                FadeTransition fade = new FadeTransition();  
                //setting the duration for the Fade transition   
                fade.setDuration(Duration.millis(5000));
                fade.setFromValue(10);  
                fade.setToValue(0);
                fade.setNode(getPane());
                fade.play(); 
                fade.setOnFinished(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent arg0) {
                        // TODO Auto-generated method stub
                        isDisappear = true;
                        getPane().setVisible(false);
                    }
                });
            }
        });
        AnimationTimer animationTimer = new AnimationTimer() {
            
            @Override
            public void handle(long arg0) {
                // TODO Auto-generated method stub
                handleAnimetion();
            }
        };
        SoundEffect.loop("src/Menu/resource/Title.wav");
        animationTimer.start();
    }
    
    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }
    
    public void handleAnimetion() {
        double speed = 0;
        for (int i = 0; i < imageViews.length; i++) {
            if (i == 1 || i == 0) {
                speed = 0.05;
            } else if ( i == 3 || i == 2){
                speed = 0.5;
            } else if (i == 4 || i == 5) {
                speed = 0.75; 
            } else if (i == 6 || i == 7) {
                speed = 0.15;
            } else if (i == 8 || i == 9) {
                speed = 0.25;
            } else if (i == 10 || i == 11) {
                speed = 0.9;
            } else if (i == 12 || i == 13) {
                speed = 0.35;
            } else {
                speed = 0;
            }
            imageViews[i].setLayoutX(imageViews[i].getLayoutX() + speed);
            if (imageViews[i].getLayoutX() >= width) {
                imageViews[i].setLayoutX(-width);
            }
        }
        if (logoImageView.getLayoutY() < height / 2 - 250) {
            logoImageView.setLayoutY(logoImageView.getLayoutY() + 1.25);
        }
    }
}
