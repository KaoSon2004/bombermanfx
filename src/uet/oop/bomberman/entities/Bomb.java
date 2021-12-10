package uet.oop.bomberman.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

import uet.oop.bomberman.entities.Explosion.Explosion;

import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.SpriteSheet;

public class Bomb extends Entity{
	private int timeToExplode = 200;
	//List<Entity> explosions;
	private static int length = 0;
	public boolean inBomb = true;
	public Bomb(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
		//explosions = new ArrayList<>();
	}

	@Override
	public void update() {
		if(timeToExplode > 0) {
			timeToExplode --;
			setInBomb();
		} else {
		    this.remove();
			addExplosion();
			//BombermanGame.bombExplode(explosions);
			bombExplode();
		}
		if (timeToExplode < 100) {
			img = Sprite.bomb_2.getFxImage();
		} else if (timeToExplode < 150) {
			img = Sprite.bomb_1.getFxImage();
		}
		
	}
	private void bombExplode() {
		for(int i = 1; i <= length + 1; i++) {
			if(BombermanGame.getEntity((x - i * Sprite.SCALED_SIZE) , (y) ) instanceof Wall) {
				break;
			}
			if(BombermanGame.getEntity((x - i * Sprite.SCALED_SIZE) , (y) ) instanceof Brick) {
				BombermanGame.getEntity((x - i * Sprite.SCALED_SIZE) , (y)).remove();
				break;
			}
		}
		
		for(int i = 1; i <= length + 1; i++) {
			if(BombermanGame.getEntity((x + i * Sprite.SCALED_SIZE) , (y) ) instanceof Wall) {
				break;
			}
			if(BombermanGame.getEntity((x + i * Sprite.SCALED_SIZE) , (y) ) instanceof Brick) {
				BombermanGame.getEntity((x + i * Sprite.SCALED_SIZE) , (y)).remove();
				break;
			}
		}
		for(int i = 1; i <= length + 1; i++) {
			if(BombermanGame.getEntity((x ) , (y- i * Sprite.SCALED_SIZE) ) instanceof Wall) {
				break;
			}
			if(BombermanGame.getEntity((x ) , (y- i * Sprite.SCALED_SIZE) ) instanceof Brick) {
				BombermanGame.getEntity((x ) , (y- i * Sprite.SCALED_SIZE)).remove();
				break;
			}
		}
		for(int i = 1; i <= length + 1; i++) {
			if(BombermanGame.getEntity((x ) , (y + i * Sprite.SCALED_SIZE) ) instanceof Wall) {
				break;
			}
			if(BombermanGame.getEntity((x ) , (y + i * Sprite.SCALED_SIZE) ) instanceof Brick) {
				BombermanGame.getEntity((x ) , (y +  i * Sprite.SCALED_SIZE)).remove();
				break;
			}
		}
//		for(int i = 0; i < BombermanGame.getExplosions().size(); i++) {
//			if (BombermanGame.getEntity(BombermanGame.getExplosions().get(i).getX()
//					, BombermanGame.getExplosions().get(i).getY()) instanceof Brick) {
//				BombermanGame.getEntity(BombermanGame.getExplosions().get(i).getX()
//						, BombermanGame.getExplosions().get(i).getY()).remove();
//			}
//		}
		
	}

	private void addExplosion() {
		
		for(int i = 1; i <= length + 1; i++) {
			if(!(BombermanGame.getEntity((x - i * Sprite.SCALED_SIZE) , (y) ) instanceof Wall 
					|| BombermanGame.getEntity((x - i * Sprite.SCALED_SIZE) , (y) ) instanceof Brick )  
					&& i == length + 1) {
				BombermanGame.getExplosions().add(new Explosion((x - (length + 1) * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE 
						, (y) / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_left_last.getFxImage(), 4));
			}
				
			if(!(BombermanGame.getEntity((x - i * Sprite.SCALED_SIZE) , (y) ) instanceof Wall 
					|| BombermanGame.getEntity((x - i * Sprite.SCALED_SIZE) , (y)) instanceof Brick)
					&& i <= length) {
				BombermanGame.getExplosions().add(new Explosion((x - i * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE 
						, (y) / Sprite.SCALED_SIZE, Sprite.explosion_horizontal.getFxImage(), 1));
				System.out.println(BombermanGame.getEntity((x - i * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE, (y) / Sprite.SCALED_SIZE));
			}
			else break;
		}
		for (int i = 1; i <= length + 1; i++) {
			if(!(BombermanGame.getEntity((x + i * Sprite.SCALED_SIZE)  , (y) ) instanceof Wall 
					|| BombermanGame.getEntity((x + i * Sprite.SCALED_SIZE) , (y) ) instanceof Brick ) 
					&& i == length + 1) {
				BombermanGame.getExplosions().add(new Explosion((x + (length + 1) * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE 
						, (y) / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_right_last.getFxImage(), 5));
			}
			if(!(BombermanGame.getEntity((x + i * Sprite.SCALED_SIZE)  , (y) ) instanceof Wall 
					 || BombermanGame.getEntity((x + i * Sprite.SCALED_SIZE) , (y) ) instanceof Brick ) 
					&& i <= length) {
				BombermanGame.getExplosions().add(new Explosion((x + i * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE 
						, (y) / Sprite.SCALED_SIZE, Sprite.explosion_horizontal.getFxImage(), 1));
			}
			else break;
		}
		for (int i = 1; i <= length + 1; i++) {
			if(!(BombermanGame.getEntity((x)  , (y - i * Sprite.SCALED_SIZE) ) instanceof Wall
					|| BombermanGame.getEntity((x)  , (y - i * Sprite.SCALED_SIZE) ) instanceof Brick) 
					&& i == length + 1) {
				BombermanGame.getExplosions().add(new Explosion((x) / Sprite.SCALED_SIZE 
						, (y - (length + 1)  * Sprite.SCALED_SIZE ) / Sprite.SCALED_SIZE, Sprite.explosion_vertical_top_last.getFxImage(), 6));
			}
			if(!(BombermanGame.getEntity((x)  , (y - i * Sprite.SCALED_SIZE) ) instanceof Wall 
					|| BombermanGame.getEntity((x)  , (y - i * Sprite.SCALED_SIZE) ) instanceof Brick) 
					&& i <= length) {
					BombermanGame.getExplosions().add(new Explosion((x) / Sprite.SCALED_SIZE 
					, (y - i * Sprite.SCALED_SIZE ) / Sprite.SCALED_SIZE, Sprite.explosion_vertical.getFxImage(), 2));
			}
			else break;
		}
		for(int i = 1; i <= length + 1; i++) {
			if(!(BombermanGame.getEntity((x) , (y + i * Sprite.SCALED_SIZE) ) instanceof Wall 
					|| BombermanGame.getEntity((x)  , (y + i * Sprite.SCALED_SIZE) ) instanceof Brick) 
					&& i == length + 1) {
				BombermanGame.getExplosions().add(new Explosion((x) / Sprite.SCALED_SIZE 
						, (y + (length + 1) * Sprite.SCALED_SIZE ) / Sprite.SCALED_SIZE, Sprite.explosion_vertical_down_last.getFxImage(), 3));
			}
			if(!(BombermanGame.getEntity((x) , (y + i * Sprite.SCALED_SIZE) ) instanceof Wall 
					|| BombermanGame.getEntity((x)  , (y + i * Sprite.SCALED_SIZE) ) instanceof Brick) 
					&& i <= length) {
				BombermanGame.getExplosions().add(new Explosion((x) / Sprite.SCALED_SIZE 
					, (y + i * Sprite.SCALED_SIZE ) / Sprite.SCALED_SIZE, Sprite.explosion_vertical.getFxImage(), 2));
			}
			else break;
		}
//		explosions.add(new Explosion((x - (length + 1) * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE 
//				, (y) / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_left_last.getFxImage(), 4));
//		
//		explosions.add(new Explosion((x + (length + 1) * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE 
//				, (y) / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_right_last.getFxImage(), 5));
//		explosions.add(new Explosion((x) / Sprite.SCALED_SIZE 
//				, (y - (length + 1)  * Sprite.SCALED_SIZE ) / Sprite.SCALED_SIZE, Sprite.explosion_vertical_top_last.getFxImage(), 6));
//		explosions.add(new Explosion((x) / Sprite.SCALED_SIZE 
//				, (y + (length + 1) * Sprite.SCALED_SIZE ) / Sprite.SCALED_SIZE, Sprite.explosion_vertical_down_last.getFxImage(), 3));
		BombermanGame.getExplosions().add(new Explosion(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE
				, Sprite.bomb_exploded.getFxImage(), 0));
	}
	
	public void setImage(Image image) {
		this.img = image;
	}
	
	public Bomb getBomb() {
		return this;
	}
	
	public void setInBomb() {
	    Bomber bomber = (Bomber) BombermanGame.getPlayer();
	    if (bomber != null) {
	        int a = bomber.getX();
	        int b = bomber.getY();
	        int size = Sprite.SCALED_SIZE;
	        int nextX_1 = a / size;
	        int nextY_1 = b / size;

	        int nextX_2 = (a + size - 10) / size;
	        int nextY_2 = b / size;

	        int nextX_3 = a / size;
	        int nextY_3 = (b + size - 4) / size;

	        int nextX_4 = (a + size - 10) / size;
	        int nextY_4 = (b + size - 4) / size;
	        
	        Entity entity = BombermanGame.getBomb(nextX_1 * size, nextY_1 * size);
	        Entity entity2 = BombermanGame.getBomb(nextX_2 * size, nextY_2 * size);
	        Entity entity3 = BombermanGame.getBomb(nextX_3 * size, nextY_3 * size);
	        Entity entity4 = BombermanGame.getBomb(nextX_4 * size, nextY_4 * size);        
	        if ((entity instanceof Bomb)
	                || (entity2 instanceof Bomb)
	                || (entity3 instanceof Bomb)
	                || (entity4 instanceof Bomb)) {
	          //  inBomb = false;
	        } else {
	            inBomb = false;
	        }
	    }
    }
	
	public static void setFlameLength(int l) {
	    length = l;
	}
	
	public static void upFlameLength() {
	    length++;
	}

}
