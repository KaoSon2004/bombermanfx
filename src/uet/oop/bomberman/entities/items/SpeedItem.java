package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Entity{

    public SpeedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        //System.out.println(isRemoved());
        
        setSpeed();
    }
    
    public void setSpeed() {
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
            //System.out.println("x va y " + nextX_1 * size + " " + nextY_1 * size);
            Entity entity = BombermanGame.getItem(nextX_1 * size, nextY_1 * size);
            /*for (int i = 0; i < BombermanGame.items.size(); i++) {
                System.out.println(entity == BombermanGame.items.get(i)); 
            }
            //System.out.println(entity instanceof SpeedItem);
            //System.out.println(x + " " + y);
            
            if (nextX_1 * size == x && nextY_1 * size == y) {
                remove();
            }*/
            Entity entity2 = BombermanGame.getItem(nextX_2 * size, nextY_2 * size);
            Entity entity3 = BombermanGame.getItem(nextX_3 * size, nextY_3 * size);
            Entity entity4 = BombermanGame.getItem(nextX_4 * size, nextY_4 * size);
            if (entity instanceof SpeedItem) {
                if (entity.equals(this)) {
                    bomber.setSpeed(bomber.getSpeed() + 1);
                    remove();
                }
            }
            else if (entity2 instanceof SpeedItem) {
                if (entity2.equals(this)) {
                    bomber.setSpeed(bomber.getSpeed() + 1);
                    remove();
                }
            }
            else if (entity3 instanceof SpeedItem) {
                if (entity3.equals(this)) {
                    bomber.setSpeed(bomber.getSpeed() + 1);
                    remove();
                }
            }
            else if (entity4 instanceof SpeedItem) {
                if (entity4.equals(this)) {
                    bomber.setSpeed(bomber.getSpeed() + 1);
                    remove();
                }
            }
        }
    }

}
