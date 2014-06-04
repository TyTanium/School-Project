package Space.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Space.Main;
import Space.graphics.Animation;
import Space.graphics.Texture;

public class SpecialEnemy extends GameObject implements EntityB {

	private double x, y;

	private Main main;
	private Texture tex;
	private Controller c;
	private Animation anim;

	private Random random = new Random();

	private int speed = random.nextInt(3) + 1;

	public SpecialEnemy(double x, double y, Main main, Texture tex, Controller c) {
		super(x, y);
		this.x = x;
		this.y = y;
		this.main = main;
		this.tex = tex;
		this.c = c;

		anim = new Animation(5, tex.specialEnemy[0], tex.specialEnemy[1], tex.specialEnemy[2]);
	}

	public void update() {
		x += speed;

		if (x > main.width * main.scale) {
			c.removeEntity(this);
			main.enemyCount--;
		}

		for (int i = 0; i < main.ea.size(); i++) {
			EntityA tempEnt = main.ea.get(i);

			if (Physics.collision(this, tempEnt)) {
				c.removeEntity(tempEnt);
				c.removeEntity(this);
				main.score += 2;
				main.enemyKilled++;
			}
		}

		anim.runAnimation();
	}

	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 2);
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
