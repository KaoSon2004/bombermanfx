package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Item extends Entity {
	Bomber bomber = (Bomber) BombermanGame.getPlayer();
	public Item(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
    public boolean set() {
        // TODO Auto-generated method stub
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
            
            Entity entity = BombermanGame.getItem(nextX_1 * size, nextY_1 * size);
            Entity entity2 = BombermanGame.getItem(nextX_2 * size, nextY_2 * size);
            Entity entity3 = BombermanGame.getItem(nextX_3 * size, nextY_3 * size);
            Entity entity4 = BombermanGame.getItem(nextX_4 * size, nextY_4 * size);
            if (entity instanceof Item) {
                if (entity.equals(this)) {
                	BombermanGame.itemSound.setFile(5);
                	BombermanGame.itemSound.play();
                    
                    remove();
                    return true;
                }
            }
            else if (entity2 instanceof Item) {
                if (entity2.equals(this)) {
                	BombermanGame.itemSound.setFile(5);
                	BombermanGame.itemSound.play();
                    
                    remove();
                    return true;
                }
            }
            else if (entity3 instanceof Item) {
                if (entity3.equals(this)) {
                	BombermanGame.itemSound.setFile(5);
                	BombermanGame.itemSound.play();
                    
                    remove();
                    return true;
                }
            }
            else if (entity4 instanceof Item) {
                if (entity4.equals(this)) {
                	BombermanGame.itemSound.setFile(5);
                	BombermanGame.itemSound.play();
                    //Bomb.upFlameLength();
                    remove();
                    return true;
                }
            }
        }
        return false;
    }

}
