package uet.oop.bomberman.entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.items.BombItem;
import uet.oop.bomberman.entities.items.FlameItem;
import uet.oop.bomberman.entities.items.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;

public class Doll extends Enemy {
 
    private int time = 60;
    public static int maze[][];
    public int sol[][];
    public List <Integer> path;
    public static boolean visited[][];
    public boolean found;
	int col = -1;
	int row = -1;
	public Doll(int xUnit, int yUnit, Image img) {
		super(xUnit, yUnit, img);
		maze =  BombermanGame.maze;
		direction = - 1;
		sol = new int[18][31];
		path = new ArrayList<>();
		found = false;
	}

	@Override
	public void update() {
        if(isRemoved() == true) {
            if(time > 0) {
                time --;
            }
            else {
                BombermanGame.getEntities().remove(this);
                BombermanGame.enemyDieSound.setFile(6);
                BombermanGame.enemyDieSound.play();
            }
            if(time < 12) {
                img = Sprite.mob_dead3.getFxImage();
            }
            else if(time < 24) {
                img = Sprite.mob_dead2.getFxImage();
            }
            else if(time < 36) {
                img = Sprite.mob_dead1.getFxImage();
            }
            else if (time < 70) {
                img = Sprite.doll_dead.getFxImage();
            }
        } else {
			handlePlayerPosition();
			if(path.isEmpty()) {		
			   found = solveMaze(maze);
			}
			if(x % 32 == 0 && y % 32 == 0 && path.size() >= 2 ) {
				col = path.remove(path.size() - 1);
				row = path.remove(path.size() - 1);
			}
		    int dCol = x / Sprite.SCALED_SIZE;
		    int dRow = y / Sprite.SCALED_SIZE;
		
		    if(col > dCol && row == dRow) {
		    	direction = 0; //right;
		    }
		    else if(col < dCol && row == dRow) {
		    	direction = 1; //left;
		    }
		    else if(col == dCol && row > dRow) {
		    		direction = 3; //down;
		    }
		    else if(col == dCol && row < dRow) {
		    		direction = 2; //up
		    }
		    if(found) {
		    	handleMove();
		    } else {
		    	if(x % 32 == 0 && y % 32 ==0)
		    		direction = random.nextInt(4);
		    	handleMoveRandom();
		    }
			checkPlayer();
			isDied();
            if (countAnimation == 0) {
                num++;
                if (num >= 3) {
                    num = 0;
                }
                countAnimation = 4;
            }
			
	//		for (int i = 0; i < 18; i++) {
	//			for (int j = 0; j < 31; j++) {
	//				System.out.print(maze[i][j] + " ");
	//			}
	//			System.out.println();
	//		}
//			for(int i : path) {
//				System.out.print(i + ",");
//			}
//			System.out.println();
			
        }
	}

    public void handleMove() {
        if (direction == 0) { // right
              x += speed;
                if (num == 0) {
                     img = Sprite.doll_right1.getFxImage();
                } else if (num == 1) {
                     img = Sprite.doll_right2.getFxImage();
                } else {
                    img = Sprite.doll_right3.getFxImage();
                }
          
        } else if (direction == 1) { // left
               x -= speed;
               	if (num == 0) {
                    img = Sprite.doll_left1.getFxImage();
               	} else if (num == 1) {
               		img = Sprite.doll_left2.getFxImage();
                } else {
                     img = Sprite.doll_left3.getFxImage();
                }
               
       } else if (direction == 2) { // up        
                y -= speed;
                if (num == 0) {
                    img = Sprite.doll_left1.getFxImage();
                } else if (num == 1) {
                   img = Sprite.doll_left2.getFxImage();
                } else {
                  img = Sprite.doll_left3.getFxImage();
                }
              
       } else if (direction == 3) { // down
              	y += speed;
              	if (num == 0) {
              		img = Sprite.doll_right1.getFxImage();
              	} else if (num == 1) {
              		img = Sprite.doll_right2.getFxImage();
              	} else {
              		img = Sprite.doll_right3.getFxImage();
              	}
       } 
            
        
    	
    }
    public void handlePlayerPosition() {
    	if(BombermanGame.getPlayer() != null) {
	    	for(int i = 0; i < 18; i++) {
	    		for(int j = 0; j < 31; j ++) {
	    			if(maze[i][j] == 9) {
	    				maze[i][j] = 0;
	    			}
	    		}
	    	}
	    	maze[BombermanGame.getPlayer().getY() / Sprite.SCALED_SIZE]
	    			[BombermanGame.getPlayer().getX() / Sprite.SCALED_SIZE] = 9; 
    	}
    }

    
    @Override
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
        Entity entity = BombermanGame.getEntity(nextX_1 * size, nextY_1 * size);
        Entity entity2 = BombermanGame.getEntity(nextX_2 * size, nextY_2 * size);
        Entity entity3 = BombermanGame.getEntity(nextX_3 * size, nextY_3 * size);
        Entity entity4 = BombermanGame.getEntity(nextX_4 * size, nextY_4 * size);
        return collide(entity4) && collide(entity3) && collide(entity2) && collide(entity);
    }
    public void handleMoveRandom() {
        if (direction == 0) { // right
            if (isCanMove(x + speed , y)) {
                x += speed;
                if (num == 0) {
                    img = Sprite.doll_right1.getFxImage();
                } else if (num == 1) {
                    img = Sprite.doll_right2.getFxImage();
                } else {
                    img = Sprite.doll_right3.getFxImage();
                }
            } else {
                direction = random.nextInt(4);
            }
        } else if (direction == 1) { // left
            if (isCanMove(x - speed , y)) {
                x -= speed;
                if (num == 0) {
                    img = Sprite.doll_right1.getFxImage();
                } else if (num == 1) {
                    img = Sprite.doll_right2.getFxImage();
                } else {
                    img = Sprite.doll_right2.getFxImage();
                }
            } else {
                direction = random.nextInt(4);
            }
        } else if (direction == 2) { // up
            if (isCanMove(x , y - speed)) {
                y -= speed;
                if (num == 0) {
                    img = Sprite.doll_left1.getFxImage();
                } else if (num == 1) {
                    img = Sprite.doll_left2.getFxImage();
                } else {
                    img = Sprite.doll_left3.getFxImage();
                }
            } else {
                direction = random.nextInt(4);
            }
        } else if (direction == 3) { // down
            if (isCanMove(x , y + speed)) {
                y += speed;
                if (num == 0) {
                    img = Sprite.doll_right1.getFxImage();
                } else if (num == 1) {
                    img = Sprite.doll_right2.getFxImage();
                } else {
                    img = Sprite.doll_right3.getFxImage();
                }
            } else {
                direction = random.nextInt(4);
            }
        }
    }
    boolean isSafe(
            int maze[][], int x, int y)
    {
        return (x >= 0 && x < 18 && y >= 0
                && y < 31 && (maze[x][y] == 0 || maze[x][y] == 9));
    }


    boolean solveMaze(int maze[][])
    {
    	sol = new int[18][31];
    	//path = new ArrayList<>();
        if (solveMazeUtil(maze, y / Sprite.SCALED_SIZE, x / Sprite.SCALED_SIZE , sol) == false) {
            System.out.println("Solution doesn't exist");
            return false;
        }
//		for (int i = 0; i < 18; i++) {
//			for (int j = 0; j < 31; j++) {
//				System.out.print(maze[i][j] + " ");
//			}
//		System.out.println();
//		}
//		for (int i = 0; i < 18; i++) {
//			for (int j = 0; j < 31; j++) {
//				System.out.print(sol[i][j] + " ");
//			}
//			System.out.println();
//		}
        return true;
    }
    boolean solveMazeUtil(int maze[][], int x, int y,
                          int sol[][])
    {

        if ( isSafe(maze,x,y) && maze[x][y] == 9 ) {
            sol[x][y] = 9;
            path.add(x);
            path.add(y);
            return true;
        }


        if (isSafe(maze, x, y) == true) {

            if (sol[x][y] == 1)
                return false;

            sol[x][y] = 1;

            if (solveMazeUtil(maze, x + 1, y, sol)) {
                path.add(x);
                path.add(y);
                return true;
            }

            if (solveMazeUtil(maze, x, y + 1, sol)) {
                path.add(x);
                path.add(y);
                return true;
            }

            if (solveMazeUtil(maze, x - 1, y, sol)) {
                path.add(x);
                path.add(y);
                return true;
            }

            if (solveMazeUtil(maze, x, y - 1, sol)) {
                path.add(x);
                path.add(y);
                return true;
            }
            sol[x][y] = 0;
            return false;
        }

        return false;
    }
    protected boolean collide(Entity e){
        return (e instanceof Grass || e instanceof Bomber || e instanceof Doll);
    }
}
