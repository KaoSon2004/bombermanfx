package uet.oop.bomberman.entities;

import java.util.Random;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Entity {
    // dem time de animation
    private int countAnimation = 4;
    private int num = 0;
    private int speed = 2;
    private Random random = new Random();
    private int direction = random.nextInt(4);
    //count = 0 -> new direction
    private int count = 32;

	public Balloon(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
	}
	
	@Override
	public void update() {
	    if(isRemoved() == true) {
            if(time > 0) {
                time --;
            }
            else {
                BombermanGame.getEntities().remove(this);
            }
            if(time < 12) {
                img = Sprite.mob_dead3.getFxImage();
            }
            else if(time < 24) {
                img = Sprite.mob_dead2.getFxImage();
            }
            else if(time < 36) {
                img = Sprite.mob_dead1.getFxImage();
            }
            else if (time < 70) {
                img = Sprite.balloom_dead.getFxImage();
            }
        } else {
            handleMove();
            checkPlayer();
            isDied();
            count --;
            countAnimation --;
            if (x % 32 == 0 && y % 32 == 0 && (collide(BombermanGame.getEntity(x, y + 32))
                    && collide(BombermanGame.getEntity(x, y - 32)) 
                    && (collide(BombermanGame.getEntity(x + 32, y))
                    || collide(BombermanGame.getEntity(x - 32, y))))) {
                direction = random.nextInt(4);
            }
            if (x % 32 == 0 && y % 32 == 0 && ((collide(BombermanGame.getEntity(x, y + 32))
                    || collide(BombermanGame.getEntity(x, y - 32)))
                    && collide(BombermanGame.getEntity(x + 32, y))
                    && collide(BombermanGame.getEntity(x - 32, y)))) {
                direction = random.nextInt(4);
            }
            if (countAnimation == 0) {
                num++;
                if (num >= 3) {
                    num = 0;
                }
                countAnimation = 4;
            }
        }
	}

	public void handleMove() {
	    if (direction == 0) { // right
            if (isCanMove(x + speed , y)) {
                x += speed;
                if (num == 0) {
                    img = Sprite.balloom_right1.getFxImage();
                } else if (num == 1) {
                    img = Sprite.balloom_right2.getFxImage();
                } else {
                    img = Sprite.balloom_right3.getFxImage();
                }
            } else {
                direction = random.nextInt(4);
            }
        } else if (direction == 1) { // left
            //System.out.println("fe");
            if (isCanMove(x - speed , y)) {
                x -= speed;
                if (num == 0) {
                    img = Sprite.balloom_left1.getFxImage();
                } else if (num == 1) {
                    img = Sprite.balloom_left2.getFxImage();
                } else {
                    img = Sprite.balloom_left3.getFxImage();
                }
            } else {
                direction = random.nextInt(4);
            }
        } else if (direction == 2) { // up
            if (isCanMove(x , y - speed)) {
                y -= speed;
                if (num == 0) {
                    img = Sprite.balloom_left1.getFxImage();
                } else if (num == 1) {
                    img = Sprite.balloom_left2.getFxImage();
                } else {
                    img = Sprite.balloom_left3.getFxImage();
                }
            } else {
                direction = random.nextInt(4);
            }
        } else if (direction == 3) { // down
            if (isCanMove(x , y + speed)) {
                y += speed;
                if (num == 0) {
                    img = Sprite.balloom_right1.getFxImage();
                } else if (num == 1) {
                    img = Sprite.balloom_right2.getFxImage();
                } else {
                    img = Sprite.balloom_right3.getFxImage();
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
        return (e instanceof Grass || e instanceof Bomber || e instanceof Balloon);
    }
    
    protected boolean collideBomb(Entity e){
        return (e instanceof Bomb);
    }
    
    public void isDied() {
    	int a = this.getX();
    	int b = this.getY();
        int size = Sprite.SCALED_SIZE;
        int nextX_1 = (a / size);
        int nextY_1 = b / size;

        int nextX_2 = (a + size - 2) / size;
        int nextY_2 = b / size;

        int nextX_3 = a / size;
        int nextY_3 = (b + size - 2) / size;

        int nextX_4 = (a + size - 2) / size;
        int nextY_4 = (b + size - 2) / size; 
        Entity entity5 = BombermanGame.getExplosion(nextX_1 * size, nextY_1 * size);
        Entity entity6 = BombermanGame.getExplosion(nextX_2 * size, nextY_2 * size);
        Entity entity7 = BombermanGame.getExplosion(nextX_3 * size, nextY_3 * size);
        Entity entity8 = BombermanGame.getExplosion(nextX_4 * size, nextY_4 * size);
        if(entity5 != null || entity6 != null || entity7 != null || entity8 != null ) {
            BombermanGame.score += 100;
        	remove();
        }
    }
    
    public void checkPlayer() {
        Bomber bomber = (Bomber) BombermanGame.getPlayer();
        if (bomber != null) {
            //System.out.println(bomber.isRemoved());
            if(bomber.getX() <= x + 30 && bomber.getY() <= y + 30
                    && bomber.getX() + 22 >= x + 30 && bomber.getY() + 28 >= y + 30) {
                bomber.remove();
            }
            if(bomber.getX() + 22 >= x && bomber.getY() <= y + 30
                    && bomber.getX() <= x && bomber.getY() + 28 >= y + 30) {
                bomber.remove();
            }
            if(bomber.getX() <= x + 30 && bomber.getY() + 28 >= y 
                    && bomber.getX() + 22 >= x + 30 && bomber.getY() <= y) {
                bomber.remove();
            }
            if(bomber.getX() + 22 >= x && bomber.getY() + 28 >= y 
                    && bomber.getX() <= x && bomber.getY() <= y) {
                bomber.remove();
            }
            if(bomber.getX() >= x && bomber.getY() >= y 
                    && bomber.getX() + 22 <= x && bomber.getY() + 28<= y) {
                bomber.remove();
            }
        }
    }
}
