package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
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
import uet.oop.bomberman.entities.items.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;
import Menu.Menu;
import Menu.MenuButton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 18;
    public int level = 1;
    private GraphicsContext gc;
    private Canvas canvas;
    private static List<Entity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();
    private static List<Entity> explosions = new ArrayList<>();
    private static List<Entity> items = new ArrayList<>();
    private static List<Entity> bombs = new ArrayList<>();
	private Bomber bomberman;
	private static Portal portal;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        canvas.requestFocus();
        canvas.setFocusTraversable(true);
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
            if(event.getCode() == KeyCode.SPACE && bombs.size() < 2) {
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
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
		        root.getChildren().add(canvas);
		        Scene scene = new Scene(root);
		        Stage gameStage = new Stage();
		        gameStage.setScene(scene);
		        menuStage.hide();
		        gameStage.show();
			}
		});
		menuStage.show();
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        try {
			createMap();
		} catch (IOException e) {
			System.out.println("Exception in create map");
			e.printStackTrace();
		}
    }

    public void createMap() throws IOException {
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
			}
			rowCount++;
		}
    }

    public void update() {
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			entity.update();
//			if (entity.isRemoved()) {
//				entities.remove(i);
//			}
		}
		for (int i = 0; i < items.size(); i++) {
            Entity entity = items.get(i);
            entity.update();
            if (entity.isRemoved()) {
                items.remove(i);
            }
        }
		if (bombs != null) {
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
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        if (items != null) {
            items.forEach(g -> g.render(gc));
        }
        portal.render(gc);
        entities.forEach(g -> g.render(gc));
		if (bombs != null) {
			bombs.forEach(g -> g.render(gc));
		}
		if (!explosions.isEmpty()) {
			explosions.forEach(g -> g.render(gc));
		}
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
                    System.out.println(true);
                    return bomb;
                }
            }
        }
        for (Entity e : stillObjects) {
            if (e.compareCoordinate(x , y)) return e;
        }
        return null;
    }
    
	public static void bombExplode( List<Entity> exs ) {
	    
		//bomb = null;
		explosions = exs;
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
    
    public static Entity getItem(int x, int y) {
        for (Entity e : items) {
            if (e.compareCoordinate(x , y)) return e;
        }
        return null;
    }
    
    public static Entity getBomb(int x, int y) {
        if (bombs != null) {
            for (int i = 0; i < bombs.size(); i++) {
                Bomb bomb = (Bomb) bombs.get(i);
                if (bomb.compareCoordinate(x , y)) {
                    System.out.println(true);
                    return bomb;
                }
            }
        }
        return null;
    }
}
