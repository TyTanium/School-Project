package Space.entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Space.Main;
import Space.graphics.Texture;

public class Controller {

	private List<EntityA> ea = new ArrayList<EntityA>();
	private List<EntityB> eb = new ArrayList<EntityB>();

	private Main main;
	private Texture tex;
	private EntityA entityA;
	private EntityB entityB;

	private Random random = new Random();

	public Controller(Main main, Texture tex) {
		this.main = main;
		this.tex = tex;
	}

	public void update() {
		//A CLASS
		for (int i = 0; i < ea.size(); i++) {
			entityA = ea.get(i);

			entityA.update();
		}

		//B CLASS
		for (int i = 0; i < eb.size(); i++) {
			entityB = eb.get(i);

			entityB.update();
		}
	}

	public void render(Graphics g) {
		//A CLASS
		for (int i = 0; i < ea.size(); i++) {
			entityA = ea.get(i);

			entityA.render(g);
		}
		//B CLASS
		for (int i = 0; i < eb.size(); i++) {
			entityB = eb.get(i);

			entityB.render(g);
		}
	}

	public void addEnemy(int enemyCount) {
		for (int i = 0; i < enemyCount; i++) {
			addEntity(new Enemy(random.nextInt(Main.width * Main.scale) - 32, -10, main, tex, this));
		}
	}

	public void addEntity(EntityA block) {
		ea.add(block);
	}

	public void removeEntity(EntityA block) {
		ea.remove(block);
	}

	public void addEntity(EntityB block) {
		eb.add(block);
	}

	public void removeEntity(EntityB block) {
		eb.remove(block);
	}

	public List<EntityA> getEntityA() {
		return ea;
	}

	public List<EntityB> getEntityB() {
		return eb;
	}

}
