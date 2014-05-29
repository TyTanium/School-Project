package Space.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Space.Main;
import Space.graphics.Texture;

public class SpecialEnemy extends GameObject implements EntityB {

	private double x, y;

	private Main main;
	private Texture tex;
	private Controller c;

	private Random random = new Random();

	private int speed = random.nextInt(3) + 1;

	public SpecialEnemy(double x, double y, Main main, Texture tex, Controller c) {
		super(x, y);
		this.x = x;
		this.y = y;
		this.main = main;
		this.tex = tex;
		this.c = c;
	}

	public void update() {
		x += speed;

		if (x > main.width * main.scale) {
			x = -10;
			y = random.nextInt(main.height * main.scale - 32);
		}
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
		return new Rectangle((int) x + 8, (int) y + 10, 44, 44);
	}

	public void setOff(int xOff, int yOff) {
	}

}
