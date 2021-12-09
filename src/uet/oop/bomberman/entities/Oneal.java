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
    int vertical = -1;
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
        if (x % 32 == 0 && y % 32 == 0 && (collide(BombermanGame.getEntity(x, y + 32))
                && collide(BombermanGame.getEntity(x, y - 32)) 
                && (collide(BombermanGame.getEntity(x + 32, y))
                || collide(BombermanGame.getEntity(x - 32, y))))) {
            direction = calculateDirection();
        }
        if (x % 32 == 0 && y % 32 == 0 && ((collide(BombermanGame.getEntity(x, y + 32))
                || collide(BombermanGame.getEntity(x, y - 32)))
                && collide(BombermanGame.getEntity(x + 32, y))
                && collide(BombermanGame.getEntity(x - 32, y)))) {
            direction = calculateDirection();
        }
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
                System.out.println("x va y ");
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
        
        Entity bomb = BombermanGame.getBomb(nextX_1 * size, nextY_1 * size);
        Entity bomb2 = BombermanGame.getBomb(nextX_2 * size, nextY_2 * size);
        Entity bomb3 = BombermanGame.getBomb(nextX_3 * size, nextY_3 * size);
        Entity bomb4 = BombermanGame.getBomb(nextX_4 * size, nextY_4 * size);
        if(bomb != null || bomb2 != null || bomb3 != null || bomb4 != null) {
            return false;
        }
        
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
    
    public int calculateDirection() {
        int vertical = random.nextInt(2);
        if(vertical == 1) {
            int v = calculateRowDirection();
            if(v != -1)
                return v;
            else
                return calculateColDirection();
            
        } else {
            int h = calculateColDirection();
            
            if(h != -1)
                return h;
            else
                return calculateRowDirection();
        }
        
    }
    
    protected int calculateColDirection() {
        if(player.x < this.x)
            return 1;
        else if(player.x > this.x)
            return 0;
        return -1;
    }
    
    protected int calculateRowDirection() {
        if(player.y < this.y)
            return 2;
        else if(player.y > this.y)
            return 3;
        return -1;
    }
}
