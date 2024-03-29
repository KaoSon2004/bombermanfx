package Menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uet.oop.bomberman.BombermanGame;

public class Menu {
    private double xOffset = 0;
    private double yOffset = 0;
	private AnchorPane menuPane;
	private Scene menuScene;
	private Stage menuStage;
	private List<MenuButton> menuButtons = new ArrayList<>();
	private MenuSubScene startSubScene;
	private MenuSubScene scoreSubScene;
	private MenuSubScene helpSubScene;
	private MenuSubScene creditSubScene;
	private MenuSubScene exitSubScene;
	private MenuSubScene currentSubScene;
	private IntroSubScene introSubScene;
	private boolean isStarted;
	MenuButton startButton;
	List<InfoLabel> scoreLabels = new ArrayList<>();
	
	public Stage getMenuStage() {
		return menuStage;
	}

	public Menu() {
	    scoreScreen.insertFromFile();
	    introSubScene = new IntroSubScene();
		isStarted = false;
		menuPane = new AnchorPane();
		menuScene = new Scene(menuPane, deConfig.screenWidth, deConfig.screenHeight);
		menuStage = new Stage();
		menuStage.setScene(menuScene);
		createBackGround();
		createButton();
		createSubScene();
	    menuPane.getChildren().add(introSubScene);
	    menuStage.initStyle(StageStyle.UNDECORATED);
        menuScene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
            
        //move around here
        menuScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                menuStage.setX(event.getScreenX() - xOffset);
                menuStage.setY(event.getScreenY() - yOffset);
            }
        });
	}

	private void createButton() {
		// TODO Auto-generated method stub
		createStartButton();
		createScoreButton();
		createHelpButton();
		createCreditButton();
		createExitButton();
	}

	private void createCreditButton() {
		// TODO Auto-generated method stub
		MenuButton menuButton = new MenuButton("Credit");
		menuButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				BombermanGame.cliSound.setFile(7);
				BombermanGame.cliSound.play();
				// TODO Auto-generated method stub
				showSubScene(creditSubScene);
			}
		});
		addMenuButton(menuButton);
	}

	private void createExitButton() {
		// TODO Auto-generated method stub
		MenuButton menuButton = new MenuButton("Exit");
		menuButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				BombermanGame.cliSound.setFile(7);
				BombermanGame.cliSound.play();
			    scoreScreen.exportToFile();
				menuStage.close();
			}
		});
		addMenuButton(menuButton);
	}

	private void createHelpButton() {
		// TODO Auto-generated method stub
		MenuButton menuButton = new MenuButton("Help");
		menuButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				BombermanGame.cliSound.setFile(7);
				BombermanGame.cliSound.play();
				// TODO Auto-generated method stub
				showSubScene(helpSubScene);
			}
		});
		addMenuButton(menuButton);
	}

	private void createScoreButton() {
		// TODO Auto-generated method stub
		MenuButton menuButton = new MenuButton("Score");
		menuButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				BombermanGame.cliSound.setFile(7);
				BombermanGame.cliSound.play();
				// TODO Auto-generated method stub
				showSubScene(scoreSubScene);
			}
		});
		addMenuButton(menuButton);
	}

	private void createStartButton() {
		// TODO Auto-generated method stub
		startButton = new MenuButton("Start");

		addMenuButton(startButton);
	}
	
	public MenuButton getStartButton() {
		return startButton;
	}
	public boolean getIsStart() {
		return isStarted;
	}
	public void addMenuButton(MenuButton menuButton) {
		menuButton.setLayoutX(100);
		menuButton.setLayoutY(100 + menuButtons.size()*100);
		menuButtons.add(menuButton);
		menuPane.getChildren().add(menuButton);
	}

	private void createBackGround() {
		// TODO Auto-generated method stub
		Image bg = new Image("/Menu/resource/lXBZK8F-bomberman-wallpaper.jpg", deConfig.screenWidth, deConfig.screenHeight, false, true);
		BackgroundImage backgroundImage = new BackgroundImage(bg, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		menuPane.setBackground(new Background(backgroundImage));
	}
	
	private void createSubScene() {
		createScoreSubScene();
		createHelpSubScene();
		createCreditSubScene();
	}

	public void createScoreSubScene() {
		scoreSubScene = new MenuSubScene();
		menuPane.getChildren().add(scoreSubScene);
		
		
		InfoLabel banner = new InfoLabel("HIGH SCORE");
		banner.setLayoutX(120);
		banner.setLayoutY(-120);
		scoreSubScene.getPane().getChildren().add(banner);
		
		for (int i = 0; i < 10; i++) {
			InfoLabel scoreOut = new InfoLabel(scoreScreen.highScore[i]);
			scoreOut.setLayoutX(120);
			scoreOut.setLayoutY(-80 + 20*i);
			scoreSubScene.getPane().getChildren().add(scoreOut);
			scoreLabels.add(scoreOut);
		}
	}
	
	public void setScore() {
	    for (int i = 0; i < scoreLabels.size(); i++) {
	        scoreLabels.get(i).setText(scoreScreen.highScore[i] + "");
	    }
	}
	
	public String toString(int i) {
		return "i";
	}
	
	private void createHelpSubScene() {
		helpSubScene = new MenuSubScene();
		menuPane.getChildren().add(helpSubScene);
		
		InfoLabel banner = new InfoLabel("INSTRUCTIONS");
		banner.setLayoutX(120);
		banner.setLayoutY(-120);
		helpSubScene.getPane().getChildren().add(banner);
		
		InfoLabel move = new InfoLabel("MOVE WITH W/A/S/D");
		move.setLayoutX(120);
		move.setLayoutY(-75);
		helpSubScene.getPane().getChildren().add(move);
		
		InfoLabel bomb = new InfoLabel("BOMB WITH SPACE");
		bomb.setLayoutX(120);
		bomb.setLayoutY(-35);
		helpSubScene.getPane().getChildren().add(bomb);
		
		InfoLabel target = new InfoLabel("CROSS THE PORTAL TO WIN");
		target.setLayoutX(120);
		target.setLayoutY(-0);
		helpSubScene.getPane().getChildren().add(target);
	}

	private void createCreditSubScene() {
		creditSubScene = new MenuSubScene();
		menuPane.getChildren().add(creditSubScene);
		
		InfoLabel creators = new InfoLabel("CREATORS");
		creators.setLayoutX(120);
		creators.setLayoutY(-120);
		creditSubScene.getPane().getChildren().add(creators);
		
		InfoLabel vdh = new InfoLabel("VU DUC HIEU");
		vdh.setLayoutX(120);
		vdh.setLayoutY(10);
		creditSubScene.getPane().getChildren().add(vdh);
		
		InfoLabel cxs = new InfoLabel("CAO XUAN SON");
		cxs.setLayoutX(120);
		cxs.setLayoutY(-75);
		creditSubScene.getPane().getChildren().add(cxs);
		
		InfoLabel nht = new InfoLabel("NGUYEN DUC TUNG");
		nht.setLayoutX(120);
		nht.setLayoutY(-35);
		creditSubScene.getPane().getChildren().add(nht);
	}

	private void showSubScene(MenuSubScene subScene) {
		if (currentSubScene != null && currentSubScene != subScene) {
			currentSubScene.moveSubScene();
		}
		subScene.moveSubScene();
		if (currentSubScene == subScene) {
			currentSubScene = null;
		} else {
			currentSubScene = subScene;
		}
	}
}
