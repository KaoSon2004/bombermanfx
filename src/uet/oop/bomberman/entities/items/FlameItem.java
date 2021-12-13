package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Item{

    public FlameItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    	if(set()) {
    		Bomb.upFlameLength();
    	}
        
    }

 
}
