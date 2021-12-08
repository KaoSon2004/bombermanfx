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
	List<Entity> explosions;
	private int length = 2;
	public Bomb(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
		explosions = new ArrayList<>();
	}

	@Override
	public void update() {
		if(timeToExplode > 0) {
			timeToExplode --;
		} else {
			addExplosion();
			BombermanGame.bombExplode(explosions);
			bombExplode();
		}
		if (timeToExplode < 100) {
			img = Sprite.bomb_2.getFxImage();
		} else if (timeToExplode < 150) {
			img = Sprite.bomb_1.getFxImage();
		}
		
	}

	private void bombExplode() {
		// TODO Auto-generated method stub
		
	}

	private void addExplosion() {
		
		for(int i = 1; i <= length + 1; i++) {
			if(!(BombermanGame.getEntity((x - i * Sprite.SCALED_SIZE) , (y) ) instanceof Wall) && i == length + 1) {
				explosions.add(new Explosion((x - (length + 1) * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE 
						, (y) / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_left_last.getFxImage(), 4));
			}
				
			if(!(BombermanGame.getEntity((x - i * Sprite.SCALED_SIZE) , (y) ) instanceof Wall) && i <= length) {
				explosions.add(new Explosion((x - i * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE 
						, (y) / Sprite.SCALED_SIZE, Sprite.explosion_horizontal.getFxImage(), 1));
				System.out.println(BombermanGame.getEntity((x - i * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE, (y) / Sprite.SCALED_SIZE));
			}
			else break;
		}
		for (int i = 1; i <= length + 1; i++) {
			if(!(BombermanGame.getEntity((x + i * Sprite.SCALED_SIZE)  , (y) ) instanceof Wall) && i == length + 1) {
				explosions.add(new Explosion((x + (length + 1) * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE 
						, (y) / Sprite.SCALED_SIZE, Sprite.explosion_horizontal_right_last.getFxImage(), 5));
			}
			if(!(BombermanGame.getEntity((x + i * Sprite.SCALED_SIZE)  , (y) ) instanceof Wall) && i <= length) {
				explosions.add(new Explosion((x + i * Sprite.SCALED_SIZE) / Sprite.SCALED_SIZE 
						, (y) / Sprite.SCALED_SIZE, Sprite.explosion_horizontal.getFxImage(), 1));
			}
			else break;
		}
		for (int i = 1; i <= length + 1; i++) {
			if(!(BombermanGame.getEntity((x)  , (y - i * Sprite.SCALED_SIZE) ) instanceof Wall) && i == length + 1) {
				explosions.add(new Explosion((x) / Sprite.SCALED_SIZE 
						, (y - (length + 1)  * Sprite.SCALED_SIZE ) / Sprite.SCALED_SIZE, Sprite.explosion_vertical_top_last.getFxImage(), 6));
			}
			if(!(BombermanGame.getEntity((x)  , (y - i * Sprite.SCALED_SIZE) ) instanceof Wall) && i <= length) {
					explosions.add(new Explosion((x) / Sprite.SCALED_SIZE 
					, (y - i * Sprite.SCALED_SIZE ) / Sprite.SCALED_SIZE, Sprite.explosion_vertical.getFxImage(), 2));
			}
			else break;
		}
		for(int i = 1; i <= length + 1; i++) {
			if(!(BombermanGame.getEntity((x) , (y + i * Sprite.SCALED_SIZE) ) instanceof Wall) && i == length + 1) {
				explosions.add(new Explosion((x) / Sprite.SCALED_SIZE 
						, (y + (length + 1) * Sprite.SCALED_SIZE ) / Sprite.SCALED_SIZE, Sprite.explosion_vertical_down_last.getFxImage(), 3));
			}
			if(!(BombermanGame.getEntity((x) , (y + i * Sprite.SCALED_SIZE) ) instanceof Wall) && i <= length) {
				explosions.add(new Explosion((x) / Sprite.SCALED_SIZE 
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
		explosions.add(new Explosion(x / Sprite.SCALED_SIZE, y / Sprite.SCALED_SIZE
				, Sprite.bomb_exploded.getFxImage(), 0));
	}
	
	public void setImage(Image image) {
		this.img = image;
	}
	
	public Bomb getBomb() {
		return this;
	}
}
