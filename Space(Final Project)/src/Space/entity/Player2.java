package Space.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import Space.Main;
import Space.graphics.Animation;
import Space.graphics.Texture;

public class Player2 extends GameObject implements EntityA {

	private int xOff, yOff;
	private double xa;
	private double ya;

	private Main main;
	private Texture tex;
	private Animation anim;
	private Controller c;

	public Player2(double x, double y, Main main, Texture tex, Controller c) {
		super(x, y);
		this.main = main;
		this.tex = tex;
		this.c = c;

		anim = new Animation(5, tex.player[0], tex.player[1], tex.player[2]);
	}

	public void update() {
		if (x + main.x2 <= 0) x += 2;
		if (y + main.y2 <= 0) y += 2;
		if (x + main.x2 >= (main.width * main.scale) - tex.getWidth(tex.player[0])) x -= 2;
		if (y + main.y2 >= (main.height * main.scale) - tex.getHeight(tex.player[0])) y -= 2;

		anim.runAnimation();

		for (int i = 0; i < main.eb.size(); i++) {
			EntityB tempEnt = main.eb.get(i);
			if (Physics.collision(this, tempEnt)) {
				c.removeEntity(tempEnt);
				main.health2 -= 20;
				main.score++;
				Main.enemyKilled++;
			}
		}
	}

	public void render(Graphics g) {
		xa = x + xOff;
		ya = y + yOff;
		anim.drawAnimation(g, xa, ya, 0);
		g.setColor(Color.WHITE);
		g.setFont(new Font("ariel", Font.PLAIN, 15));
		g.drawString("P2", (int) xa + 27, (int) ya + 62);
		//Graphics2D g2 = (Graphics2D) g;
		//g2.setColor(Color.GREEN);
		//g2.draw(new Rectangle((int) (this.x + xOff) + 20, (int) (this.y + yOff), 26, 64));
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getXnXoff() {
		return x + xOff;
	}

	public double getYnYoff() {
		return y + yOff;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) (this.x + xOff) + 20, (int) (this.y + yOff), 26, 64);
	}

	public void setOff(int xOff, int yOff) {
		this.xOff = xOff;
		this.yOff = yOff;
	}

}
