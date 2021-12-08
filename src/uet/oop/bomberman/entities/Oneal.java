package uet.oop.bomberman.entities;

import java.util.Random;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Entity {
    private int countAnimation = 4;
    private int num = 0;
    private int speed = 2;
    private Random random = new Random();
    private int direction = random.nextInt(4);
    private Bomber player;

	public Oneal(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
		player = (Bomber) BombermanGame.getPlayer();
	}
	
	@Override
	public void update() {
	    //System.out.println("x va y " + player.x + " " + player.y);
        handleMove();
        countAnimation --;
        if (countAnimation == 0) {
            num++;
            if (num >= 3) {
                num = 0;
            }
            countAnimation = 4;
        }
	}
	
	public void handleMove() {
        if (direction == 0) { // right
            if (isCanMove(x + speed , y)) {
                x += speed;
                if (num == 0) {
                    img = Sprite.oneal_right1.getFxImage();
                } else if (num == 1) {
                    img = Sprite.oneal_right2.getFxImage();
                } else {
                    img = Sprite.oneal_right3.getFxImage();
                }
            } else {
                direction = random.nextInt(4);
            }
        } else if (direction == 1) { // left
            if (isCanMove(x - speed , y)) {
                //System.out.println("x va y ");
                x -= speed;
                if (num == 0) {
                    img = Sprite.oneal_left1.getFxImage();
                } else if (num == 1) {
                    img = Sprite.oneal_left2.getFxImage();
                } else {
                    img = Sprite.oneal_left3.getFxImage();
                }
            } else {
                direction = random.nextInt(4);
            }
        } else if (direction == 2) { // up
            if (isCanMove(x , y - speed)) {
                y -= speed;
                if (num == 0) {
                    img = Sprite.oneal_left1.getFxImage();
                } else if (num == 1) {
                    img = Sprite.oneal_left2.getFxImage();
                } else {
                    img = Sprite.oneal_left3.getFxImage();
                }
            } else {
                direction = random.nextInt(4);
            }
        } else if (direction == 3) { // down
            if (isCanMove(x , y + speed)) {
                y += speed;
                if (num == 0) {
                    img = Sprite.oneal_right1.getFxImage();
                } else if (num == 1) {
                    img = Sprite.oneal_right2.getFxImage();
                } else {
                    img = Sprite.oneal_right3.getFxImage();
                }
            } else {
                direction = random.nextInt(4);
            }
        }
    }
    
    private boolean isCanMove(int a, int b) {
        int size = Sprite.SCALED_SIZE;
        int nextX_1 = (a / size);
        int nextY_1 = b / size;

        int nextX_2 = (a + size - 2) / size;
        int nextY_2 = b / size;

        int nextX_3 = a / size;
        int nextY_3 = (b + size - 2) / size;

        int nextX_4 = (a + size - 2) / size;
        int nextY_4 = (b + size - 2) / size;
        
        Entity entity = BombermanGame.getEntity(nextX_1 * size, nextY_1 * size);
        Entity entity2 = BombermanGame.getEntity(nextX_2 * size, nextY_2 * size);
        Entity entity3 = BombermanGame.getEntity(nextX_3 * size, nextY_3 * size);
        Entity entity4 = BombermanGame.getEntity(nextX_4 * size, nextY_4 * size);
        return collide(entity4) && collide(entity3) && collide(entity2) && collide(entity);
    }
    
    // kiểm tra va chạm
    protected boolean collide(Entity e){
        return (e instanceof Grass || e instanceof Bomber || e instanceof Oneal);
    }
}
