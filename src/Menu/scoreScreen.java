package Menu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import uet.oop.bomberman.BombermanGame;

public class scoreScreen {
	static int[] highScore = new int[10];

	public int[] getHighScore() {
		return highScore;
	}

	public void setHighScore(int[] highScore) {
		scoreScreen.highScore = highScore;
	}
	
	public scoreScreen() {
		for (int i = 0; i < 10; i++) {
			scoreScreen.highScore[i] = 0;
		}
	}
	
	public static void updateScore() {
		// import score
		int newScore = BombermanGame.score;
		// update score in score array
		for (int i = 0; i < 10; i++) {
			if (newScore > scoreScreen.highScore[i]) {
			    for (int j = 9; j > i; j--) {
			        highScore[j] = highScore[j - 1];
			    }
			    scoreScreen.highScore[i] = newScore; 
				break;
			}
		}
		// sort again, descending
		/*for (int i = 0; i < 9; i++) {
			if (scoreScreen.highScore[i] < scoreScreen.highScore[i+1]) {
				int temp = scoreScreen.highScore[i];
				scoreScreen.highScore[i] = scoreScreen.highScore[i+1];
				scoreScreen.highScore[i+1] = temp;
			}
		}*/
	}
	
	public static void insertFromFile() {
        try {
            File files = new File("src/Menu/resource/score.txt");
            Scanner n = new Scanner(files);
            int i = 0;
            while (n.hasNextLine()) {
                String line = n.nextLine();
                System.out.println(line);
                highScore[i] = Integer.valueOf(line);
                i++;
            }
            for (int j = 0; j < 10; j++) {
                System.out.println(highScore[j]);
            }
            // close file
        } catch (IOException e) {
            System.out.println("An error");
        } 
    }

    public static void exportToFile() {
        try {
            FileWriter fileWriter = new FileWriter("src/Menu/resource/score.txt");
            BufferedWriter buf = new BufferedWriter(fileWriter);
            // write to file from current dictionary
            for (int i = 0; i < 10; i++) {
                String line = String.valueOf(highScore[i]);
                buf.write(line);
                buf.newLine();
            }
 
            buf.close();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
    }
}
