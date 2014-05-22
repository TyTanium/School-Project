package Space.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import Space.Main;
import Space.graphics.Texture;

public class Bullet extends GameObject implements EntityA {

	private Texture tex;
	private Main main;
	Controller c;

	public Bullet(double x, double y, Texture tex, Main main, Controller c) {
		super(x, y);
		this.tex = tex;
		this.main = main;
		this.c = c;
	}

	public void update() {
		y -= 3;
		if (y <= 0) {
			c.removeEntity(this);
		}
	}

	public void render(Graphics g) {
		g.drawImage(tex.bullet[0], (int) x, (int) y, null);
		if (x <= 0) c.removeEntity(this);
		//Graphics2D g2 = (Graphics2D) g;
		//g2.setColor(Color.GREEN);
		//g2.draw(new Rectangle((int) x + 20, (int) y + 20, 22, 22));
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x + 20, (int) y + 20, 22, 22);
	}

	public void setOff(int xOff, int yOff) {
	}

}
