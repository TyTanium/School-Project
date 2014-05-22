package Space.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Space.Main;
import Space.graphics.Texture;

public class PowerUp extends GameObject implements EntityB {

	private int chosen = -1;
	private int xOff = 0, yOff = 0;
	private int speed;

	private Texture tex;
	private Main main;

	private Random random = new Random();

	public PowerUp(double x, double y, Texture tex, Main main, int speed) {
		super(x, y);
		this.tex = tex;
		this.main = main;
		this.speed = speed;

		chosen = random.nextInt(3);
	}

	public void update() {
		y += speed;
	}

	public void render(Graphics g) {
		//Graphics2D g2 = (Graphics2D) g;
		//g2.setColor(Color.GREEN);
		//g2.draw(new Rectangle((int) x + 7, (int) y + 7, 50, 50));
		if (chosen == 0) {
			g.drawImage(tex.powerUp[0], (int) x, (int) y, null);
		}
		if (chosen == 1) {
			g.drawImage(tex.powerUp[1], (int) x, (int) y, null);
		}
		if (chosen == 2) {
			g.drawImage(tex.powerUp[2], (int) x, (int) y, null);
		}
	}

	public int getType() {
		return chosen;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x + 7, (int) y + 7, 50, 50);
	}

	public void setOff(int xOff, int yOff) {
	}

}
