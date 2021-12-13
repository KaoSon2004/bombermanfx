package uet.oop.bomberman.entities;

import java.util.Random;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemy {
    // dem time de animation

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
	

    
    // kiểm tra va chạm

}
