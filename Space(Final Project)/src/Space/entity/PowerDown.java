package Space.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Space.Main;
import Space.graphics.Texture;

public class PowerDown extends GameObject implements EntityB {

	private int chosen = -1;
	private int xOff = 0, yOff = 0;
	private int speed;

	private Texture tex;
	private Main main;

	private Random random = new Random();

	public PowerDown(double x, double y, Texture tex, Main main, int speed) {
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
