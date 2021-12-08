package uet.oop.bomberman.entities.Explosion;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Explosion extends Entity {
	private int num;
	private int time = 200;
	public Explosion(int xUnit, int yUnit, Image img, int num) {
		super(xUnit, yUnit, img);
		this.num = num;
	}
	@Override
	public void update() {
		switch (num) {
		case 0: //center
			if(time > 0) {
				time --;
			} else {
				this.remove();
				
			}
			if(time < 30) {
				img = Sprite.bomb_exploded2.getFxImage();
			}
			else if(time < 60) {
				img = Sprite.bomb_exploded1.getFxImage();
			}
			break;
		case 1: //horizon
			if(time > 0) {
				time --;
			} else {
				this.remove();
				
			}
			if(time < 30) {
				img = Sprite.explosion_horizontal2.getFxImage();
			}
			else if(time < 60) {
				img = Sprite.explosion_horizontal1.getFxImage();
				
			}
			break;
		case 2: //vertical
			if(time > 0) {
				time --;
			} else {
				this.remove();
				
			}
			if(time < 30) {
				img = Sprite.explosion_vertical2.getFxImage();
			}
			else if(time < 60) {
				img = Sprite.explosion_vertical1.getFxImage();
			}
			break;
		case 3: //botlast
			if(time > 0) {
				time --;
			} else {
				this.remove();
			
			}
			if(time < 30) {
				img = Sprite.explosion_vertical_down_last2.getFxImage();
			}
			else if(time < 60) {
				img = Sprite.explosion_vertical_down_last1.getFxImage();
				
			}
			break;
		case 4: //leftlast
			if(time > 0) {
				time --;
			} else {
				this.remove();
			
			}
			if(time < 30) {
				img = Sprite.explosion_horizontal_left_last2.getFxImage();
			}
			else if(time < 60) {
				img = Sprite.explosion_horizontal_left_last1.getFxImage();
				
			}
			break;
		case 5: //rightlast
			if(time > 0) {
				time --;
			} else {
				this.remove();
				
			}
			if(time < 30) {
				img = Sprite.explosion_horizontal_right_last2.getFxImage();
			}
			else if(time < 60) {
				img = Sprite.explosion_horizontal_right_last1.getFxImage();
				
			}
			break;
		case 6: //uplast
			if(time > 0) {
				time --;
			} else {
				this.remove();
			
			}
			if(time < 30) {
				img = Sprite.explosion_vertical_top_last2.getFxImage();
			}
			else if(time < 60) {
				img = Sprite.explosion_vertical_top_last1.getFxImage();
				
			}
			break;
		}
	}
	
}
