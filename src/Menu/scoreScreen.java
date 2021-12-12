package Menu;

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
	
	public void updateScore(BombermanGame finishedGame) {
		// import score
		int newScore = BombermanGame.score;
		// update score in score array
		for (int i = 0; i < 10; i++) {
			if (newScore > scoreScreen.highScore[i]) {
				scoreScreen.highScore[i] = newScore; 
				break;
			}
		}
		// sort again, descending
		for (int i = 0; i < 10; i++) {
			if (scoreScreen.highScore[i] < scoreScreen.highScore[i+1]) {
				int temp = scoreScreen.highScore[i];
				scoreScreen.highScore[i] = scoreScreen.highScore[i+1];
				scoreScreen.highScore[i+1] = temp;
			}
		}
	}
}
