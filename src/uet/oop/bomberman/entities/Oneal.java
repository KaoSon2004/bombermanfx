package uet.oop.bomberman.entities;

import java.util.Random;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Enemy {
    int vertical = -1;
    private Bomber player;

	public Oneal(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
		player = (Bomber) BombermanGame.getPlayer();
		if (BombermanGame.level <= 3)
	        speed = BombermanGame.level;
	}
	
	@Override
	public void update() {
        if(isRemoved() == true) {
            if(time > 0) {
                time --;
            }
            else {
                BombermanGame.getEntities().remove(this);
                BombermanGame.enemyDieSound.setFile(6);
                BombermanGame.enemyDieSound.play();
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
                img = Sprite.oneal_dead.getFxImage();
            }
        } else {
            handleMove();
            checkPlayer();
            isDied();
            countAnimation --;
            if (x % 32 == 0 && y % 32 == 0 && (collide(BombermanGame.getEntity(x, y + 32))
                    && collide(BombermanGame.getEntity(x, y - 32)) 
                    && (collide(BombermanGame.getEntity(x + 32, y))
                    || collide(BombermanGame.getEntity(x - 32, y))))) {
                direction = calculateDirection();
                //direction = random.nextInt(4);
            }
            if (x % 32 == 0 && y % 32 == 0 && ((collide(BombermanGame.getEntity(x, y + 32))
                    || collide(BombermanGame.getEntity(x, y - 32)))
                    && collide(BombermanGame.getEntity(x + 32, y))
                    && collide(BombermanGame.getEntity(x - 32, y)))) {
                direction = calculateDirection();
               // direction = random.nextInt(4);
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
    

    
    // kiểm tra va chạm
    
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
        if (player != null) {
            if(player.x <= this.x)
                return 1;
            else if(player.x > this.x)
                return 0;
        }
        return -1;
    }
    
    protected int calculateRowDirection() {
        if (player != null) {
            if(player.y < this.y)
                return 2;
            else if(player.y >= this.y)
                return 3; 
        }
        return -1;
    }
    

}
