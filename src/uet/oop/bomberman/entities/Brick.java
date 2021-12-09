package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity {
	private int time = 100;
	public Brick(int xUnit, int yUnit, Image img) {
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
			if(time < 30) {
				img = Sprite.brick_exploded2.getFxImage();
				System.out.println("?");
			}
			else if(time < 50) {
				img = Sprite.brick_exploded1.getFxImage();
			} else {
	             img = Sprite.brick_exploded.getFxImage();
			}
		}
		
	}

}
