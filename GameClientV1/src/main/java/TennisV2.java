import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TennisV2 extends Applet implements Runnable, KeyListener {
	public static boolean isGameStarted;
	public static boolean isGameEnd;
	public static boolean isFirstPlayerReady;
	public static boolean isSecondPlayerReady;

	final int WIDTH = 700, HEIGHT = 500;

	Thread thread;
	HumanPaddle p1;
	HumanPaddle p2;
	Ball ball;
	BufferedReader mSocketReader;
	PrintWriter mSocketWriter;
	Socket socket;

	public void init() {
		this.resize(WIDTH, HEIGHT);
		this.addKeyListener(this);
		thread = new Thread(this);
		p1 = new HumanPaddle(1);
		p2 = new HumanPaddle(2);
		this.ball = new Ball();

		isGameStarted = false;
		isGameEnd = false;

		try {
			socket = new Socket(Constants.SERVER_HOST, Constants.SERVER_PORT);
			mSocketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			mSocketWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			CommandHandler commandHandler = new CommandHandler(mSocketReader, p1, p2);
			commandHandler.start();
			thread.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		p1.draw(g);
		p2.draw(g);
		ball.draw(g);
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
		this.mSocketWriter.println(Constants.KEY_PRESSED + e.getKeyCode());
		this.mSocketWriter.flush();
	}

	public void keyReleased(KeyEvent e) {
		this.mSocketWriter.println(Constants.KEY_RELEASED + e.getKeyCode());
		this.mSocketWriter.flush();
	}

	public void run() {
		while (true) {
			p1.move();
			p2.move();
			if (isGameStarted && !isGameEnd) {
				ball.move((int) p1.y, (int) p2.y);
			} else if (isGameEnd) {
				ball.x = 350;
				ball.y = 250;
				isGameStarted = false;
				isFirstPlayerReady = false;
				isSecondPlayerReady = false;
				isGameEnd = false;
			}
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
