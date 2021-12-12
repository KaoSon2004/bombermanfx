package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Balloon;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Oneal;
import uet.oop.bomberman.entities.Portal;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.entities.Explosion.Explosion;
import uet.oop.bomberman.entities.items.BombItem;
import uet.oop.bomberman.entities.items.FlameItem;
import uet.oop.bomberman.entities.items.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;
import Menu.GameButton;
import Menu.Menu;
import Menu.MenuButton;
import Menu.PauseSubScreen;
import Menu.scoreScreen;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 18;
    private String font = "src\\Menu\\resource\\RUBBBB__.ttf";
    public static int level = 1;
    private GraphicsContext gc;
    private Canvas canvas;
    private static List<Entity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();
    private static List<Entity> explosions = new ArrayList<>();
    private static List<Entity> items = new ArrayList<>();
    private static List<Entity> bombs = new ArrayList<>();
	private Bomber bomberman;
	private static Portal portal;
	public static int numBomb = 1;
	public static int score = 0;
	private int second = 300;
	//create pause subScreen
	private PauseSubScreen pauseSubScreen = new PauseSubScreen();
	//load logo img
	private Image logoImage = new Image("Menu/resource/logo.png");
	private ImageView logoImageView = new ImageView(logoImage);
	
	//load bomber death
	private Image image = new Image("Menu/resource/maxresdefault.jpg", Sprite.SCALED_SIZE * WIDTH + 128, Sprite.SCALED_SIZE * HEIGHT, false, true);
	private ImageView imageView = new ImageView(image);
	
	private MenuButton playAgain = new MenuButton("Play Again");
	private MenuButton menuButton = new MenuButton("Menu");
	private GameButton quitButton = new GameButton("/Menu/resource/gameButton.png","QUIT");
	private GameButton pauseGameButton = new GameButton("/Menu/resource/pause.png", "");
	private GameButton continueGameButton = new GameButton("/Menu/resource/gameButton.png", "CONTINUE");
	private Label timeCountLabel = new Label("Time: " + second);
	private Label bombCountLabel = new Label("" + numBomb);
	private Label flameCountLabel = new Label("" + Bomb.getFlameLength());
	private Label speedCountLabel = new Label("" + 0);
	private Label scorelLabel = new Label("SCORE");

	//load clock png
	Image clockImage = new Image("Menu/resource/imgbin_pixel-art-drawing-png.png", 32, 32, false, true);
    ImageView clockImageView = new ImageView(clockImage);
    //load bomb item png
    ImageView bombCountView = new ImageView(Sprite.powerup_bombpass.getFxImage());
    //load flame item png
    ImageView flameCountView = new ImageView(Sprite.powerup_flamepass.getFxImage());
    //load speed item png
    ImageView speedCountView = new ImageView(Sprite.powerup_speed.getFxImage());

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        playAgain.setTextFill(Color.RED);
        menuButton.setTextFill(Color.RED);
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        //create time counting
        createLabel();
        imageView.setVisible(false);
        playAgain.setVisible(false);
        menuButton.setVisible(false);
        canvas.requestFocus();
        canvas.setFocusTraversable(true);
        canvas.addEventFilter(MouseEvent.ANY, (e) -> canvas.requestFocus());
        // xử lí khi nhấn phím
        canvas.setOnKeyPressed(( KeyEvent event ) -> {
            if (event.getCode() == KeyCode.D) {
                bomberman.setDirectionRight(true);
            }
            if (event.getCode() == KeyCode.A) {
                bomberman.setDirectionLeft(true);
            }
            if (event.getCode() == KeyCode.W) {
                bomberman.setDirectionUp(true);
            }
            if (event.getCode() == KeyCode.S) {
                bomberman.setDirectionDown(true);
            }
            if(event.getCode() == KeyCode.SPACE && bombs.size() < numBomb) {
            	Bomb bomb = new Bomb((bomberman.getX() + (Sprite.SCALED_SIZE / 2)) / Sprite.SCALED_SIZE
            			, (bomberman.getY() + (Sprite.SCALED_SIZE / 2)) / Sprite.SCALED_SIZE, Sprite.bomb.getFxImage());
            	bombs.add(bomb);
            }
        });
        //xử lí khi release
        canvas.setOnKeyReleased((KeyEvent event) -> {
            if (event.getCode() == KeyCode.D) {
                bomberman.setDirectionRight(false);
            }
            if (event.getCode() == KeyCode.A) {
                bomberman.setDirectionLeft(false);
            }
            if (event.getCode() == KeyCode.W) {
                bomberman.setDirectionUp(false);
            }
            if (event.getCode() == KeyCode.S) {
                bomberman.setDirectionDown(false);
            }
        });
		Menu menu = new Menu();
		MenuButton startButton = menu.getStartButton();
		Stage menuStage = menu.getMenuStage();
		AnimationTimer timer = new AnimationTimer() {
            long lastTime = 0;

            @Override
            public void handle(long l) {
                render();
                update();
                if (lastTime != 0) {
                    if (l > lastTime + 1_000_000_000 && second > 0) {
                        if (second > 0) {
                            second--;
                            timeCountLabel.setText("Time: " + second);
                        }
                        if (Bomber.getTimeSpeed() > 0) {
                            Bomber.setTimeSpeed(Bomber.getTimeSpeed() - 1);
                        }
                        lastTime = l;

                    }
                    
                } else {
                    lastTime = l;
                }
            }
        };
        
        playAgain.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                playAgain();
            }
        });
        
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
		        AnchorPane root = new AnchorPane();
		        root.setStyle("-fx-background-color: green");
		        root.getChildren().add(canvas);
		        root.getChildren().addAll(timeCountLabel, clockImageView, bombCountView, bombCountLabel, flameCountView, 
		                flameCountLabel, speedCountLabel, speedCountView, logoImageView, scorelLabel, pauseGameButton, pauseSubScreen,
		                imageView, playAgain, menuButton);
		        Scene scene = new Scene(root, Sprite.SCALED_SIZE * WIDTH + 128, Sprite.SCALED_SIZE * HEIGHT);
		     // xử lí khi nhấn phím
		        
		        //Stage gameStage = new Stage();
		        stage.setScene(scene);
		        menuStage.hide();
		        stage.show();
		        timer.start();
			}
		});
		
        
        menuButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                playAgain();
                stage.hide();
                timer.stop();
                menuStage.show();
                
            }
            
        });
        
        quitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                // TODO Auto-generated method stub
                pauseSubScreen.moveSubScene();
                pauseGameButton.setDisable(false);
                playAgain();
                stage.hide();
                timer.stop();
                menuStage.show();
            }
        });
        
        pauseGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                // TODO Auto-generated method stub
                timer.stop();
                pauseSubScreen.moveSubScene();
                pauseGameButton.setDisable(true);
            }
        });
        
        continueGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                // TODO Auto-generated method stub
                timer.start();
                pauseSubScreen.moveSubScene();
                pauseGameButton.setDisable(false);
            }
        });
        
        createPauseSubScreen();
        
	    try {
			createMap();
		} catch (IOException e) {
			System.out.println("Exception in create map");
			e.printStackTrace();
		}
	    menuStage.show();
    }

    public void createMap() throws IOException {
    	entities.clear();
    	stillObjects.clear();
    	items.clear();
    	explosions.clear();
    	bombs.clear();
    	canvas.setDisable(false);
		FileReader fileReader = new FileReader("res/levels/Level" + level + ".txt");
		BufferedReader buf = new BufferedReader(fileReader);
		int rowCount = 0;
		String line;
		while ((line = buf.readLine()) != null) {
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == '#') {
					stillObjects.add(new Wall(i , rowCount , Sprite.wall.getFxImage()));
				} 
				else if(line.charAt(i) == ' ') {
					stillObjects.add(new Grass(i , rowCount , Sprite.grass.getFxImage()));
				}
				else if(line.charAt(i) == '*') {
					stillObjects.add(new Grass(i , rowCount , Sprite.grass.getFxImage()));
					entities.add(new Brick(i , rowCount , Sprite.brick.getFxImage()));
						
				}
				else if(line.charAt(i) == 'x') {		
						stillObjects.add(new Grass(i , rowCount , Sprite.grass.getFxImage()));
						portal = new Portal(i, rowCount, Sprite.portal.getFxImage());
						entities.add(new Brick(i , rowCount , Sprite.brick.getFxImage()));
						
				}
				else if(line.charAt(i) == 'p') {
						bomberman = new Bomber(i , rowCount , Sprite.player_right.getFxImage());
						stillObjects.add(new Grass(i , rowCount , Sprite.grass.getFxImage()));
						entities.add(bomberman);
				}
				else if(line.charAt(i) == '1') {
						entities.add(new Balloon(i , rowCount , Sprite.balloom_left1.getFxImage()));
						stillObjects.add(new Grass(i , rowCount , Sprite.grass.getFxImage()));
						
				}
				else if(line.charAt(i) == '2') {
						entities.add(new Oneal(i , rowCount , Sprite.oneal_left1.getFxImage()));
						stillObjects.add(new Grass(i , rowCount , Sprite.grass.getFxImage()));
				}
				else if(line.charAt(i) == 's') {
                    items.add(new SpeedItem(i , rowCount , Sprite.powerup_speed.getFxImage()));
                    stillObjects.add(new Grass(i , rowCount , Sprite.grass.getFxImage()));
                    //entities.add(new Brick(i , rowCount , Sprite.brick.getFxImage()));   
                }
				else if(line.charAt(i) == 'b') {
                    items.add(new BombItem(i , rowCount , Sprite.powerup_bombs.getFxImage()));
                    stillObjects.add(new Grass(i , rowCount , Sprite.grass.getFxImage()));
                    //entities.add(new Brick(i , rowCount , Sprite.brick.getFxImage()));   
                }
				else if(line.charAt(i) == 'f') {
                    items.add(new FlameItem(i , rowCount , Sprite.powerup_flames.getFxImage()));
                    stillObjects.add(new Grass(i , rowCount , Sprite.grass.getFxImage()));
                    //entities.add(new Brick(i , rowCount , Sprite.brick.getFxImage()));   
                }
			}
			rowCount++;
		}
    }

    public void update() {
        if (second == 0) {
            bomberman.remove();
        }
    	if(bomberman.isRemoved()) {
    		System.out.println("Died");
    		canvas.setDisable(true);
    		if (!entities.contains(bomberman)) {
    		      imageView.setVisible(true);
    		      playAgain.setVisible(true);
    		      menuButton.setVisible(true);
    		}
    	}
    	if(nextLevel() == true && ((bomberman.getX() / 32) * 32) == portal.getX() 
    			&& ((bomberman.getY() / 32) * 32) == portal.getY()) {
    		try {
    		    createLabel();
				createMap();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			entity.update();
//			if (entity.isRemoved()) {
//				entities.remove(i);
//			}
		}
		//System.out.println(bomberman.getSpeed());
		if (!items.isEmpty()) {
		    for (int i = 0; i < items.size(); i++) {
	            Entity entity = items.get(i);
	            //System.out.println(i + " " + entity.isRemoved());
	            if (entity.isRemoved()) {
	                //System.out.println("?");
	                items.remove(i); 
	            }
	            entity.update();
	        }
		}
		if (!bombs.isEmpty()) {
            for (int i = 0; i < bombs.size(); i++) {
                Bomb bomb = (Bomb) bombs.get(i);
                bomb.update();
                if (bomb.isRemoved()) {
                    bombs.remove(i);
                }
            }
        }
		if (!explosions.isEmpty()) {
			for (int i = 0; i < explosions.size(); i++) {
				Entity entity = explosions.get(i);
				entity.update();
				if (entity.isRemoved()) {
					explosions.remove(i);
				}
			}
		}
		bombCountLabel.setText(numBomb + "");
		flameCountLabel.setText(Bomb.getFlameLength() + 1 + "");
		speedCountLabel.setText(Bomber.getTimeSpeed() + "");
		scorelLabel.setText("SCORE: " + score);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        if (items != null) {
            items.forEach(g -> g.render(gc));
        }
        portal.render(gc);
        if (bombs != null) {
            bombs.forEach(g -> g.render(gc));
        }
        if (!explosions.isEmpty()) {
            explosions.forEach(g -> g.render(gc));
        }
        entities.forEach(g -> g.render(gc));
    }
    
    // get entity
    public static Entity getEntity(int x , int y) {
        for (Entity e : entities) {
            if (e.compareCoordinate(x , y)) return e;
        }
        if (bombs != null) {
            for (int i = 0; i < bombs.size(); i++) {
                Bomb bomb = (Bomb) bombs.get(i);
                if (bomb.compareCoordinate(x , y) && bomb.inBomb == false) {
                    return bomb;
                }
            }
        }
        for (Entity e : stillObjects) {
            if (e.compareCoordinate(x , y)) return e;
        }
        return null;
    }
    
    public static Entity getPlayer() {
        for (Entity e : entities) {
            if (e instanceof Bomber) return e;
        }
        return null;
    }
    
    public static List<Entity> getEntities() {
    	return entities;
    }
    
    public static List<Entity> getExplosions() {
    	return explosions;
    }
    
    public static Entity getItem(int x, int y) {
        if (!items.isEmpty()) {
            for (int i = 0; i < items.size(); i++) {
                Entity entity = items.get(i);
                if (entity.compareCoordinate(x, y)) {
                    return entity;
                }
            }
        }
        return null;
    }
    
    public static Entity getExplosion(int x, int y) {
        if (!explosions.isEmpty()) {
            for (int i = 0; i < explosions.size(); i++) {
                Entity entity = explosions.get(i);
                if (entity.compareCoordinate(x, y)) {
                    return entity;
                }
            }
        }
        return null;
    }
    
    public static Entity getBomb(int x, int y) {
        if (bombs != null) {
            for (int i = 0; i < bombs.size(); i++) {
                Bomb bomb = (Bomb) bombs.get(i);
                if (bomb.compareCoordinate(x , y)) {
                    return bomb;
                }
            }
        }
        return null;
    }
    
    public static boolean nextLevel() {
    	for(Entity entity : entities) {
    		if(entity instanceof Balloon || entity instanceof Oneal) {
    			return false;
    		}
    	}
    	level = 2;
    	return true;
    }
    
    public void playAgain() {
        createLabel();
        bomberman = null;
        numBomb = 1;
        Bomb.setFlameLength(0);
        Bomber.setTimeSpeed(0);
        score = 0;
        try {
            createMap();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        imageView.setVisible(false);
        playAgain.setVisible(false);
        menuButton.setVisible(false);
    }
    
    public void createLabel() {
        second = 300;
        //set time
        clockImageView.setLayoutX(WIDTH * Sprite.SCALED_SIZE + 10);
        clockImageView.setLayoutY(96);
        timeCountLabel.setStyle("-fx-background-color: black");
        timeCountLabel.setTextFill(Color.WHITE);
        timeCountLabel.setLayoutX(WIDTH * Sprite.SCALED_SIZE + 54);
        timeCountLabel.setLayoutY(96 + 10);
        timeCountLabel.setMinWidth(50);
        try {
            timeCountLabel.setFont(Font.loadFont(new FileInputStream(font), 13));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //set bomb
        bombCountLabel.setLayoutX(WIDTH * Sprite.SCALED_SIZE + 54);
        bombCountLabel.setLayoutY(160 + 10);
        bombCountLabel.setStyle("-fx-background-color: black");
        bombCountLabel.setTextFill(Color.WHITE);
        try {
            bombCountLabel.setFont(Font.loadFont(new FileInputStream(font), 14));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        bombCountView.setLayoutX(WIDTH * Sprite.SCALED_SIZE + 10);
        bombCountView.setLayoutY(160);
        //set flame
        flameCountLabel.setLayoutX(WIDTH * Sprite.SCALED_SIZE + 54);
        flameCountLabel.setLayoutY(224 + 10);
        flameCountLabel.setStyle("-fx-background-color: black");
        flameCountLabel.setTextFill(Color.WHITE);
        try {
            flameCountLabel.setFont(Font.loadFont(new FileInputStream(font), 14));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        flameCountView.setLayoutX(WIDTH * Sprite.SCALED_SIZE + 10);
        flameCountView.setLayoutY(224);
        //set speed
        speedCountLabel.setLayoutX(WIDTH * Sprite.SCALED_SIZE + 54);
        speedCountLabel.setLayoutY(288 + 10);
        speedCountLabel.setStyle("-fx-background-color: black");
        speedCountLabel.setTextFill(Color.WHITE);
        try {
            speedCountLabel.setFont(Font.loadFont(new FileInputStream(font), 14));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        speedCountView.setLayoutX(WIDTH * Sprite.SCALED_SIZE + 10);
        speedCountView.setLayoutY(288);
        // set logo
        logoImageView.setLayoutX(WIDTH * Sprite.SCALED_SIZE);
        logoImageView.setLayoutY(4);
        //set pause button
        pauseGameButton.setWidth(150);
        pauseGameButton.setLayoutY(HEIGHT * Sprite.SCALED_SIZE - 64);
        pauseGameButton.setLayoutX(WIDTH * Sprite.SCALED_SIZE);
        //set play again button
        playAgain.setLayoutX((Sprite.SCALED_SIZE * WIDTH + 128)/ 2 - 95);
        playAgain.setLayoutY(Sprite.SCALED_SIZE * HEIGHT / 2 - 45);
        //set menu button
        menuButton.setLayoutX((Sprite.SCALED_SIZE * WIDTH + 128) / 2 - 95);
        menuButton.setLayoutY(Sprite.SCALED_SIZE * HEIGHT / 2 + 45); 
        //set score label
        scorelLabel.setLayoutX(WIDTH * Sprite.SCALED_SIZE + 10);
        scorelLabel.setLayoutY(352);
        scorelLabel.setStyle("-fx-background-color: black");
        scorelLabel.setTextFill(Color.WHITE);
        try {
            scorelLabel.setFont(Font.loadFont(new FileInputStream("src\\Menu\\resource\\OXYGENE1.ttf"), 17));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void createPauseSubScreen() {
        Label pauseTextLabel = new Label("PAUSE");
        try {
            pauseTextLabel.setFont(Font.loadFont(new FileInputStream("src\\Menu\\resource\\OXYGENE1.ttf"), 17));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        pauseTextLabel.setLayoutX(pauseSubScreen.getWidth() / 2 - 24);
        pauseTextLabel.setLayoutY(5);
        pauseSubScreen.getPane().getChildren().addAll(pauseTextLabel, continueGameButton, quitButton);
        continueGameButton.setLayoutY(40);
        continueGameButton.setLayoutX((pauseSubScreen.getWidth() - continueGameButton.getPrefWidth()) / 2);
        quitButton.setLayoutX((pauseSubScreen.getWidth() - continueGameButton.getPrefWidth()) / 2);
        quitButton.setLayoutY(95);
    }
    
}
