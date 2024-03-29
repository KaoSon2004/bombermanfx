package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Explosion.Explosion;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    private int count = 4;
    private int num = 0;
    private static int timeSpeed = 0;
    // các hướng di chuyển
    private boolean directionUp = false;
    private boolean directionDown = false;
    private boolean directionLeft = false;
    private boolean directionRight = false;
    private int speed = Sprite.SCALED_SIZE / 16;
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {
        if(isRemoved() == true) {
            if(BombermanGame.bombExSound != null) {
            	BombermanGame.bombExSound.stop();
            }
            if(BombermanGame.enemyDieSound != null) {
            	BombermanGame.enemyDieSound.stop();
            }
            if(time > 0) {
                time --;
                if(time < 15) {
                    img = Sprite.player_dead3.getFxImage();
                }
                else if(time < 30) {
                    img = Sprite.player_dead2.getFxImage();
                }
                else if (time < 60) {
                    img = Sprite.player_dead1.getFxImage();
                }
            }
            else {
                BombermanGame.getEntities().remove(this);
            	BombermanGame.gameSound.stop();
            	BombermanGame.failSound.setFile(2);
            	BombermanGame.failSound.loop();
            }
        } else {
            handleMove();
            isDied();
            count--;
            if (count == 0) {
                num++;
                if (num >= 3) {
                    num = 0;
                }
                count = 4;
            }
            if (!directionDown && !directionLeft && !directionRight && !directionUp) {
                img = Sprite.player_down.getFxImage();
            }
            if (timeSpeed <= 0) {
                speed = 2;
            }
        }
    }
    
    public void setDirectionUp(boolean directionUp) {
        this.directionUp = directionUp;
    }

    public void setDirectionDown(boolean directionDown) {
        this.directionDown = directionDown;
    }

    public void setDirectionLeft(boolean directionLeft) {
        this.directionLeft = directionLeft;
    }

    public void setDirectionRight(boolean directionRight) {
        this.directionRight = directionRight;
    }

    // xử lí di chuyển
    public void handleMove() {
            if (directionRight && !directionLeft) {
                if (isCanMove(x + speed, y))
                x += speed;
                if (num == 0) {
                    img = Sprite.player_right.getFxImage();
                } else if (num == 1) {
                    img = Sprite.player_right_1.getFxImage();
                } else {
                    img = Sprite.player_right_2.getFxImage();
                }
            }
            if (directionLeft && !directionRight) {
                if (isCanMove(x - speed, y))
                x -= speed;
                if (num == 0) {
                    img = Sprite.player_left.getFxImage();
                } else if (num == 1) {
                    img = Sprite.player_left_1.getFxImage();
                } else {
                    img = Sprite.player_left_2.getFxImage();
                }
            }
            if (directionUp && !directionDown) {
                if (isCanMove(x, y - speed))
                y -= speed;
                if (num == 0) {
                    img = Sprite.player_up.getFxImage();
                } else if (num == 1) {
                    img = Sprite.player_up_1.getFxImage();
                } else {
                    img = Sprite.player_up_2.getFxImage();
                }
            }
            if (directionDown && !directionUp) {
                if (isCanMove(x, y + speed))
                y += speed;
                if (num == 0) {
                    img = Sprite.player_down.getFxImage();
                } else if (num == 1) {
                    img = Sprite.player_down_1.getFxImage();
                } else {
                    img = Sprite.player_down_2.getFxImage();
                }
            }
    }
    
   /* private boolean isCanMove() {
        int left = x + 4;
        int right = x + Sprite.SCALED_SIZE - 10;
        int top = y + 6;
        int bottom = y + Sprite.SCALED_SIZE - 6;
        
        int leftCol = left / Sprite.SCALED_SIZE;
        int rightCol = right / Sprite.SCALED_SIZE;
        int topRow = top / Sprite.SCALED_SIZE;
        int bottomRow = bottom / Sprite.SCALED_SIZE;
        
        if (directionUp) {
            topRow = (top - speed) / Sprite.SCALED_SIZE;
            Entity num1 = BombermanGame.getEntity(leftCol * Sprite.SCALED_SIZE, topRow * Sprite.SCALED_SIZE);
            Entity num2 = BombermanGame.getEntity(rightCol * Sprite.SCALED_SIZE, topRow * Sprite.SCALED_SIZE);
            if (!collide(num2) || ! collide(num1)) {
                return false;
            }
            return true;
        } else if (directionDown) {
            bottomRow = (bottom + speed) / Sprite.SCALED_SIZE;
            Entity num1 = BombermanGame.getEntity(leftCol * Sprite.SCALED_SIZE, bottomRow * Sprite.SCALED_SIZE);
            Entity num2 = BombermanGame.getEntity(rightCol * Sprite.SCALED_SIZE, bottomRow * Sprite.SCALED_SIZE);
            if (!collide(num2) || ! collide(num1)) {
                return false;
            }
            return true;
        } else if (directionLeft) {
            leftCol = (left - speed) / Sprite.SCALED_SIZE;
            Entity num1 = BombermanGame.getEntity(leftCol * Sprite.SCALED_SIZE, topRow * Sprite.SCALED_SIZE);
            Entity num2 = BombermanGame.getEntity(leftCol * Sprite.SCALED_SIZE, bottomRow * Sprite.SCALED_SIZE);
            if (!collide(num2) || ! collide(num1)) {
                return false;
            }
            return true;
        } else if (directionRight) {
            rightCol = (right + speed) / Sprite.SCALED_SIZE;
            Entity num1 = BombermanGame.getEntity(rightCol * Sprite.SCALED_SIZE, topRow * Sprite.SCALED_SIZE);
            Entity num2 = BombermanGame.getEntity(rightCol * Sprite.SCALED_SIZE, bottomRow * Sprite.SCALED_SIZE);
            if (!collide(num2) || ! collide(num1)) {
                return false;
            }
            return true;
        }
        return true;
    }*/
    
    // kiểm tra xem di chuyển được nhân vật hay không
    private boolean isCanMove(int a, int b) {
        int size = Sprite.SCALED_SIZE;
        int nextX_1 = (a / size);
        int nextY_1 = b / size;

        int nextX_2 = (a + size - 10) / size;
        int nextY_2 = b / size;

        int nextX_3 = a / size;
        int nextY_3 = (b + size - 4) / size;

        int nextX_4 = (a + size - 10) / size;
        int nextY_4 = (b + size - 4) / size;
        
        Entity entity = BombermanGame.getEntity(nextX_1 * size, nextY_1 * size);
        Entity entity2 = BombermanGame.getEntity(nextX_2 * size, nextY_2 * size);
        Entity entity3 = BombermanGame.getEntity(nextX_3 * size, nextY_3 * size);
        Entity entity4 = BombermanGame.getEntity(nextX_4 * size, nextY_4 * size);

        return collide(entity4) && collide(entity3) && collide(entity2) && collide(entity);
    }
    
    // kiểm tra va chạm
    protected boolean collide(Entity e) {
        return (e instanceof Grass || e instanceof Bomber);
    }
    
    public int getX() {
    	return x;
    }
    
    public int getY() {
    	return y;
    }
    
    public void setSpeed(int speed) {
        if (timeSpeed == 0) {
            this.speed = speed;
        }
        setTimeSpeed(timeSpeed + 10);
    }

    public int getSpeed() {
        return speed;
    }
    public void isDied() {
    	int a = this.getX();
    	int b = this.getY();
        int size = Sprite.SCALED_SIZE;
        int nextX_1 = (a / size);
        int nextY_1 = b / size;

        int nextX_2 = (a + size - 10) / size;
        int nextY_2 = b / size;

        int nextX_3 = a / size;
        int nextY_3 = (b + size - 4) / size;

        int nextX_4 = (a + size - 10) / size;
        int nextY_4 = (b + size - 4) / size; 
        Entity entity5 = BombermanGame.getExplosion(nextX_1 * size, nextY_1 * size);
        Entity entity6 = BombermanGame.getExplosion(nextX_2 * size, nextY_2 * size);
        Entity entity7 = BombermanGame.getExplosion(nextX_3 * size, nextY_3 * size);
        Entity entity8 = BombermanGame.getExplosion(nextX_4 * size, nextY_4 * size);
        if(entity5 != null || entity6 != null || entity7 != null || entity8 != null ) {
        	remove();
        }  
    }
    
    public static void setTimeSpeed(int time) {
        timeSpeed = time;
    }
    
    public static int getTimeSpeed() {
        return timeSpeed;
    }
}
