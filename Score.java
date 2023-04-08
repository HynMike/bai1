package PONG_GAME;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Score extends Rectangle {

	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player1;
	int player2;

	public Score(int GAME_WIDTH, int GAME_HEIGHT) {
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Consolas", Font.PLAIN, 60));
		g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);

		// hiện điểm của 2 người chơi
	g.drawString(String.valueOf(player1 / 10) + String.valueOf(player1 % 10), GAME_WIDTH / 2 -98, 50);
		g.drawString(String.valueOf(player2 / 10) + String.valueOf(player2 % 10), GAME_WIDTH / 2 + 30, 50);
	}

}
