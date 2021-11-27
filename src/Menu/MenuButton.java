package Menu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
public class MenuButton extends Button {
	private String font = "src\\Menu\\resource\\kenvector_future.ttf";
	private final String ButtonFreeStyle = "-fx-background-color: transparent; -fx-background-image: "
			+ "url('Menu/resource/blue_button00.jpg');";
	private final String ButtonPressStyle = "-fx-background-color: transparent; -fx-background-image: "
			+ "url('/Menu/resource/blue_button04.png');";
	
	public MenuButton(String text) {
		setButtonFont();
		setText(text);
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(ButtonFreeStyle);
		initializeButtonListeners();
	}
	
	private void setButtonFont() {
		// TODO Auto-generated method stub
		try {
			setFont(Font.loadFont(new FileInputStream(font), 13));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setButtonPressStyle() {
		setStyle(ButtonPressStyle);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);	
	}
	
	public void setButtonReleaseStyle() {
		setStyle(ButtonFreeStyle);
		setPrefHeight(49);
		setLayoutY(getLayoutY() - 4);
	}
	
	private void initializeButtonListeners() {
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
	}
}
