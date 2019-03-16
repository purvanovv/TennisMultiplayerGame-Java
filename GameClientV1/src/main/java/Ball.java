import java.awt.Color;
import java.awt.Graphics;

class Ball {
	double x, y, xVel, yVel;

	public Ball() {
		this.yVel = -0;
		this.xVel = -2;
		this.x = 350;
		this.y = 250;
	}

	public void draw(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillOval((int) this.x, (int) this.y, 20, 20);
	}

	public void move(int p1Y, int p2Y) {
		if (this.y >= 500 - 20 || this.y <= 0) {
			this.yVel *= -1;
		} else {
			this.yVel *= 1;
		}
		if (this.x <= 0) {
			TennisV2.isGameEnd = true;
		} else if (this.x >= 700 - 20) {
			TennisV2.isGameEnd = true;
		} else if (this.y >= p1Y && this.x <= 40 && this.y <= p1Y + 80) {
			this.xVel *= -1;
		} else if (this.y >= p2Y && this.x >= 640 && this.y <= p2Y + 80) {
			this.xVel *= -1;
		} else {
			this.xVel *= 1;
		}
		this.y += this.yVel;
		this.x += this.xVel;
	}
}
