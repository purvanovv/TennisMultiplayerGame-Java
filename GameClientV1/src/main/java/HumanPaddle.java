
import java.awt.Color;
import java.awt.Graphics;

public class HumanPaddle {
	double y, yVel;
	boolean upAccel, downAccel;
	int player, x;
	final double GRAVITY = 0.94;

	public HumanPaddle(int player) {
		upAccel = false;
		downAccel = false;
		y = 210;
		yVel = 0;
		if (player == 1) {
			x = 20;
		} else {
			x = 660;
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, (int) y, 20, 80);

	}

	public void move() {
		if (upAccel) {
			yVel -= 2;
		} else if (downAccel) {
			yVel += 2;
		} else if (!upAccel && !downAccel) {
			yVel = 0;
		}
		y += yVel;

	}

	public boolean isUpAccel() {
		return upAccel;
	}

	public void setUpAccel(boolean input) {
		this.upAccel = input;
	}

	public boolean isDownAccel() {
		return downAccel;
	}

	public void setDownAccel(boolean input) {
		this.downAccel = input;
	}

	public int getY() {
		return (int) y;
	}

}
