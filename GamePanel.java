package PONG_GAME;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GamePanel extends JPanel implements Runnable {

	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = 600;
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);// lớp bọc chiều rộng, chiều cao 1 thành phần
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	private PongDatabase pongdata;
	private boolean isRunning = true;
	private boolean isPaused = false;
	private Thread soundThread;

	GamePanel() {

		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE);
		gameThread = new Thread(this);

		startGame();
	}

	public void startGame() {
		newPaddles();
		newBall();
		gameThread.start();

	}

	public void newGame() {
		newPaddles();
		newBall();
		score.player1 = 0;
		score.player2 = 0;
		draw(getGraphics());
	}

	public void gameOver(Graphics g) {

		g.setColor(Color.RED);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		FontMetrics fontMetrics1 = getFontMetrics(g.getFont());
		g.drawString("Score player1 :" + score.player1,
				(GAME_WIDTH - fontMetrics1.stringWidth("Score of:" + score.player1)) / 2 - 60, GAME_HEIGHT / 6);

		g.setColor(Color.RED);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		FontMetrics fontMetrics2 = getFontMetrics(g.getFont());
		g.drawString("Score player2 :" + score.player2,
				(GAME_WIDTH - fontMetrics2.stringWidth("Score of:" + score.player2)) / 2 - 60, GAME_HEIGHT - 100);

		g.setColor(Color.RED);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics fontMetrics3 = getFontMetrics(g.getFont());
		g.drawString("GAME OVER", (GAME_WIDTH - fontMetrics3.stringWidth("GAME OVER")) / 2, GAME_HEIGHT / 2);
	}

	public void newBall() {
		random = new Random();
		ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt((GAME_HEIGHT) - BALL_DIAMETER),
				BALL_DIAMETER, BALL_DIAMETER);
	}

	public void newPaddles() {
		paddle1 = new Paddle(0, ((GAME_HEIGHT) / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, ((GAME_HEIGHT) / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);
	}

	public void paint(Graphics g) {
		try {
			image = createImage(getWidth(), getHeight());
			graphics = image.getGraphics();
			draw(graphics);
			g.drawImage(image, 0, 0, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) {

		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);

		if (score.player1 == 11 || score.player2 == 11) {
			gameOver(g);
			isPaused = true;
		}
	}

	public void move() {
		paddle1.move();
		paddle2.move();
		ball.move();
	}

	public void checkCollision() {

		if (ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
		}

		if (ball.y >= GAME_HEIGHT - BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		}

		if (ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++;

			if (ball.yVelocity > 0) {
				ball.yVelocity++;
			} else {
				ball.yVelocity--;
			}
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
			// sound.play();
		}

		if (ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++;
			if (ball.yVelocity > 0) {
				ball.yVelocity++;
			} else {
				ball.yVelocity--;
			}
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
			//sound.play();
		}

		if (paddle1.y <= 0)
			paddle1.y = 0;

		if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;

		if (paddle2.y <= 0)
			paddle2.y = 0;
		if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;

		if (ball.x <= 0) {
			score.player2++;
			newPaddles();
			newBall();
			pongdata.saveScore("player2", score.player2);

		}
		if (ball.x >= GAME_WIDTH - BALL_DIAMETER) {
			score.player1++;
			newPaddles();
			newBall();
			pongdata.saveScore("player1", score.player1);
		}
	}

	public void display(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics fontMetrics3 = getFontMetrics(g.getFont());
		g.drawString("PAUSE", (GAME_WIDTH - fontMetrics3.stringWidth("PAUSE")) / 2, GAME_HEIGHT / 2);
	}

	public void pause() {
		isPaused = true;
	}

	public void resume() {
		isPaused = false;

	}

	public void stop() {
		isRunning = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;

		// Luồng âm thanh
//		Sound sound = new Sound(
//				"C:\\Users\\WIN\\eclipse-workspace\\A_PONG_GAME\\src\\PONG_GAME\\game-music-loop-2-144037.wav");
		Sound sound = new Sound("C:\\Users\\WIN\\Desktop\\game-music-loop-2-144037.wav");
		soundThread = new Thread(sound);
		soundThread.start();
		boolean isSoundPlaying = true;

		while (isRunning) {
			long now = System.nanoTime();

			if (!isPaused) {
				long sleepTime = (long) ((lastTime - now + ns) / 1000000);
				if (sleepTime > 0) {
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				lastTime = System.nanoTime();
				move();
				checkCollision();
				repaint();
				
				// kiểm tra các điều kiện chạy âm thanh
				if (isSoundPlaying && !soundThread.isAlive() ) {
					soundThread = new Thread(sound);
					soundThread.start();

				}

				if (!isSoundPlaying && !soundThread.isAlive() ) {
					soundThread = new Thread(sound);
					soundThread.start();
					isSoundPlaying = true;
				}

				if (isSoundPlaying && !soundThread.isAlive() && isPaused) {
					sound.stop();
					isSoundPlaying = false;
				}
			} else {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public class AL extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				gameOver(getGraphics());
				stop();
			}

			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				pause();
				display(getGraphics());
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				resume();
			}

		}

		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}

	}

}
