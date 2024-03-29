package Menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class InfoLabel extends Label {
	public final static String FONT_PATH = "src/Menu/resource/PixelEmulator-xq08.ttf";
	
	public InfoLabel(String text) {
		setPrefWidth(600);
		setPrefHeight(400);
		setPadding(new Insets(40, 40, 40, 40));
		setText(text);
		setWrapText(true);
		setLabelFont();
	}
	
	public InfoLabel(int number) {
		setPrefWidth(600);
		setPrefHeight(400);
		setPadding(new Insets(40, 40, 40, 40));
		setText(String.valueOf(number));
		setWrapText(true);
		setLabelFont();
	}
	
	private void setLabelFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 23));
		} catch (FileNotFoundException e) {
			//setFont(Font.font("Verdana", 23));
		}
	}
}
