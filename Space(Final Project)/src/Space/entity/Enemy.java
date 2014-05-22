package Space.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Space.Main;
import Space.graphics.Animation;
import Space.graphics.Texture;

public class Enemy extends GameObject implements EntityB {

	private Main main;
	private Texture tex;
	private Animation anim;
	private Controller c;

	private Random random = new Random();

	private int speed = random.nextInt(3) + 1;

	public Enemy(double x, double y, Main main, Texture tex, Controller c) {
		super(x, y);
		this.main = main;
		this.tex = tex;
		this.c = c;

		anim = new Animation(5, tex.enemy[0], tex.enemy[1], tex.enemy[2]);
	}

	public void update() {
		y += speed;

		if (y > Main.height * Main.scale) {
			y = -10;
			x = random.nextInt(Main.width * Main.scale - 32);
		}

		for (int i = 0; i < main.ea.size(); i++) {
			EntityA tempEnt = main.ea.get(i);

			if (Physics.collision(this, tempEnt)) {
				c.removeEntity(tempEnt);
				c.removeEntity(this);
				main.score++;
				main.enemyKilled++;
			}
		}

		anim.runAnimation();
	}

	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 2);
		//Graphics2D g2 = (Graphics2D) g;
		//g2.setColor(Color.GREEN);
		//g2.draw(new Rectangle((int) x + 8, (int) y + 10, 44, 44));
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
