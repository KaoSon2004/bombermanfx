package Menu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class GameButton extends Button{
    
    private String font = "src\\Menu\\resource\\OXYGENE1.ttf";
    
    public GameButton(String path, String text) {
        //setButtonFont();
       setText(text);
        try {
            setFont(Font.loadFont(new FileInputStream(font), 17));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setPrefWidth(162);
        setPrefHeight(45);
        setStyle("-fx-background-color: transparent; -fx-background-image: url('" + path + "');");
        initializeButtonListeners();
    }
    public void setButtonPressStyle() {
        setPrefHeight(getPrefHeight() - 5);
        setLayoutY(getLayoutY() + 5);   
    }
    
    public void setButtonReleaseStyle() {
        setPrefHeight(getPrefHeight() + 5);
        setLayoutY(getLayoutY() - 5);
    }

    private void initializeButtonListeners() {
        // TODO Auto-generated method stub
        setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                // TODO Auto-generated method stub
                setEffect(new DropShadow());
            }
        });
        
        
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                // TODO Auto-generated method stub
                setEffect(null);
            }
            
        });
        setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                // TODO Auto-generated method stub
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressStyle();
                }
            }
            
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                // TODO Auto-generated method stub
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleaseStyle();
               }
            }
            
        });
    }

    private void setButtonFont() {
        // TODO Auto-generated method stub
       // setFont(Font.loadFont(new, 11)));
    }
    
    public void setWidth(int width) {
        setPrefWidth(width);
    }
    
    public void setHeight(int height) {
        setPrefHeight(height);
    }
    
    
}
