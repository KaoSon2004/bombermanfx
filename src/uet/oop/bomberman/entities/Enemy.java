package uet.oop.bomberman.entities;

import java.util.Random;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Enemy extends Entity {
    public Enemy(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
		// TODO Auto-generated constructor stub
	}
	protected int countAnimation = 4;
	protected int num = 0;
	protected int speed = 2;
	protected Random random = new Random();
	protected int direction = random.nextInt(4);
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	public boolean isCanMove(int a, int b) {
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
    protected boolean collide(Entity e){
        return (e instanceof Grass || e instanceof Bomber || e instanceof Enemy);
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
