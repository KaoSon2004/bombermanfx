package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    //Tá»�a Ä‘á»™ X tÃ­nh tá»« gÃ³c trÃ¡i trÃªn trong Canvas
    protected int x;

    //Tá»�a Ä‘á»™ Y tÃ­nh tá»« gÃ³c trÃ¡i trÃªn trong Canvas
    protected int y;

    protected Image img;
    
    private boolean isRemoved = false;

    //Khá»Ÿi táº¡o Ä‘á»‘i tÆ°á»£ng, chuyá»ƒn tá»« tá»�a Ä‘á»™ Ä‘Æ¡n vá»‹ sang tá»�a Ä‘á»™ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();

    public boolean compareCoordinate(int x, int y) {
        return this.x == x && this.y == y;
    }
    
    public void remove() {
    	isRemoved = true;
    }
    
    public boolean isRemoved() {
    	return isRemoved;
    }
    
    
}
