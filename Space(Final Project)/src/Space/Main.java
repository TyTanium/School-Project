package Space;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import Space.entity.Bullet;
import Space.entity.Controller;
import Space.entity.EntityA;
import Space.entity.EntityB;
import Space.entity.Player;
import Space.entity.Player2;
import Space.entity.PowerDown;
import Space.entity.PowerUp;
import Space.entity.SpecialEnemy;
import Space.graphics.ImageLoader;
import Space.graphics.Menu;
import Space.graphics.Texture;
import Space.input.Keyboard;
import Space.input.Mouse;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private static String title = "Space Attack";
	public static int width = 125;
	public static int height = 150;
	public static int scale = 3;

	private boolean running = false;
	private JFrame frame;
	private Thread thread;
	private Mouse mouse;
	private Keyboard key;
	private Player p;
	private Player2 p2;
	private Texture tex;
	private Menu menu;
	private Controller c;
	private PowerUp up;
	private PowerDown down;
	private SpecialEnemy special;

	private static int players;
	public int score = 0;
	public int health = 100;
	public int health2 = 100;
	public static int enemyCount = 3;
	public static int enemyKilled = 0;
	private int speed = 2;
	private int speed2 = 2;

	public List<EntityA> ea;
	public List<EntityB> eb;

	private BufferedImage image = new BufferedImage(width * scale, height * scale, BufferedImage.TYPE_INT_RGB);
	public BufferedImage ss = null;
	public BufferedImage background = null;
	public BufferedImage controls = null;
	public BufferedImage pauseScreen = null;

	public enum State {
		MENU, GAME, CONTROLS, MENU2, END
	};

	public static State state = State.MENU;

	private Random random = new Random();

	public Main() {
		Dimension d = new Dimension(width * scale, height * scale);
		setPreferredSize(d);

		ImageLoader loader = new ImageLoader();
		try {
			ss = loader.loadImage("/spritesheets/spritesheet.png");
			background = loader.loadImage("/backgrounds/space.png");
			pauseScreen = loader.loadImage("/backgrounds/PauseBackground.png");
			controls = loader.loadImage("/misc/Controls.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

		frame = new JFrame();
		mouse = new Mouse();
		key = new Keyboard();
		tex = new Texture(this);
		c = new Controller(this, tex);
		p = new Player(width / 2 + 100, (height * scale) - 70, this, tex, c, speed);
		p2 = new Player2(width / 2 + 100, (height * scale) - 70, this, tex, c, speed2);
		menu = new Menu(width * scale, height * scale);
		up = new PowerUp(100, 0, tex, this, 2);
		down = new PowerDown(200, 0, tex, this, 2);

		c.addEnemy(enemyCount);
		ea = c.getEntityA();
		eb = c.getEntityB();
		ea.add(p);
		if (players == 2) {
			ea.add(p2);
		}

		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addKeyListener(key);
	}

	public BufferedImage getSS() {
		return ss;
	}

	public Controller getC() {
		return c;
	}

	public static void setPlayers(int play) {
		players = play;
	}

	public static int getPlayers() {
		return players;
	}

	public int getEnemyKilled() {
		return enemyKilled;
	}

	public void setEnemyKilled(int killed) {
		enemyKilled = killed;
	}

	private synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	private synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int counter = 0;
	public boolean able = false;
	public int upCounter = 0;
	public int downCounter = 0;
	public int time = 0;
	public int speedTimer = 0;
	private int specialCounter = 0;

	public void countdown(Graphics g) {
		int i = 5 - counter;
		g.setColor(Color.BLACK);
		Font f = new Font("Ariel", Font.PLAIN, 200);
		g.setFont(f);
		if (i == 4)
			g.drawString("3", width / 2 + 65, 250);
		if (i == 3)
			g.drawString("2", width / 2 + 65, 250);
		if (i == 2)
			g.drawString("1", width / 2 + 60, 250);
		if (i == 1)
			g.drawString("G0", width / 2 - 10, 250);
		if (i == 0)
			able = true;
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if (paused) {
					frame.setTitle(title + "   |   " + updates + " ups, " + frames + " fps " + " (PAUSED)");
				} else {
					frame.setTitle(title + "   |   " + updates + " ups, " + frames + " fps");
				}
				updates = 0;
				frames = 0;
				// System.out.println("Count: " + enemyCount);
				// System.out.println("Killed: " + enemyKilled);
				// System.out.println("Score: " + score);
				// System.out.println("Health 2: " + health2);
				if (state == State.GAME) {
					counter++;
				}
				upCounter++;
				downCounter++;
				if (rapidFire || rapidFire2) {
					rapidFireTimer++;
					if (rapidFireTimer == 20) {
						rapidFireTimer = 0;
						rapidFire = false;
						rapidFire2 = false;
					}
				}
				if (round) {
					time++;
				}
				if (p.speedRunner || p2.speedRunner2) {
					speedTimer++;
					System.out.println("SPEED");
					if (speedTimer == 20) {
						speed = 2;
						p.speedRunner = false;
						p2.speedRunner2 = false;
					}
				}
				specialCounter++;
				if (specialCounter == 30) {
					c.addEntity(new SpecialEnemy(-10, random.nextInt(width * scale - 32), this, tex, c));
					specialCounter = 0;
				}
			}
		}
		stop();
	}

	public int x = 0, y = 0;
	public int x2 = 0, y2 = 0;
	public static boolean isShooting = false;
	public static boolean dead1 = false;
	public static boolean dead2 = false;
	public static boolean advanceToEnd = false;
	public static boolean rapidFire = false;
	private int rapidFireTimer = 0;
	private boolean round = false;
	private int roundNum = 1;
	private boolean paused = false;
	private boolean newEnemy = false;
	private boolean update = true;
	public static boolean rapidFire2 = false;

	public void update() {
		key.update();
		if (update) {
			if (state == State.GAME && able) {
				c.update();
				p.update();
				if (players == 2) {
					p2.update();
				}
			}
			if (enemyKilled >= enemyCount) {
				enemyKilled = 0;
				enemyCount += 1;
				round = true;
				roundNum++;
				newEnemy = true;
				for (int i = 0; i < ea.size(); i++) {
					EntityA tempEnt = ea.get(i);
					if (tempEnt instanceof Bullet) {
						c.removeEntity(tempEnt);
					}
				}
				for (int i = 0; i < eb.size(); i++) {
					EntityB tempEnt = eb.get(i);
					c.removeEntity(tempEnt);
				}
			}
			if (!round && newEnemy) {
				c.addEnemy(enemyCount);
				newEnemy = false;
			}
			if (upCounter == random.nextInt(60) + 30) {
				c.addEntity(new PowerUp(random.nextInt(width * scale), 0, tex, this, 2));
				upCounter = 0;
			}
			if (downCounter == random.nextInt(30) + 15) {
				c.addEntity(new PowerDown(random.nextInt(width * scale), 0, tex, this, 2));
				downCounter = 0;
			}
			p.setOff(x, y);
			if (key.up) {
				y -= 2;
			}
			if (key.down) {
				y += 2;
			}
			if (key.left) {
				x -= 2;
			}
			if (key.right) {
				x += 2;
			}
			if (!dead1) {
				if (!rapidFire) {
					if (key.enter && !isShooting) {
						c.addEntity(new Bullet(p.getXnXoff(), p.getYnYoff(), tex, this, c));
						isShooting = true;
					}
				}
				if (rapidFire) {
					if (key.enter) {
						c.addEntity(new Bullet(p.getXnXoff(), p.getYnYoff(), tex, this, c));
					}
				}
			}
			if (players == 1) {
				if (health <= 0) {
					dead1 = true;
					state = State.END;
				}
			}
			if (players == 2) {
				if (health <= 0) {
					dead1 = true;
				}
				if (health2 <= 0) {
					dead2 = true;
				}
				if (dead1 && dead2) {
					state = State.END;
				}
			}
			if (players == 2) {
				p2.setOff(x2, y2);
				if (key.up2) {
					y2 -= 2;
				}
				if (key.down2) {
					y2 += 2;
				}
				if (key.left2) {
					x2 -= 2;
				}
				if (key.right2) {
					x2 += 2;
				}
				if (!dead2) {
					if (!rapidFire2) {
						if (key.space && !isShooting) {
							c.addEntity(new Bullet(p2.getXnXoff(), p2.getYnYoff(), tex, this, c));
							isShooting = true;
						}
					}
					if (rapidFire2) {
						if (key.space) {
							c.addEntity(new Bullet(p2.getXnXoff(), p2.getYnYoff(), tex, this, c));
						}
					}
				}
			}
		}
		if (key.p && !paused) {
			paused = true;
			update = false;
		}
		if (key.esc && paused) {
			paused = false;
			update = true;
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// ///////////////////////////////
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

		if (state == State.GAME) {
			countdown(g);
			if (able) {
				c.render(g);
				if (!dead1) {
					p.render(g);
				}
				g.setColor(Color.GRAY);
				g.fillRect(10, 10, 100, 20);
				g.drawString("P1", 120, 25);
				g.setColor(Color.GREEN);
				g.fillRect(10, 10, health, 20);
				if (players == 2) {
					if (!dead2) {
						p2.render(g);
					}
					g.setColor(Color.GRAY);
					g.fillRect(width * scale - 110, 10, 100, 20);
					g.drawString("P2", width * scale - 135, 25);
					g.setColor(Color.GREEN);
					g.fillRect(width * scale - 110, 10, health2, 20);
				}
			}
			if (round) {
				Font f = new Font("ariel", Font.BOLD, 40);
				g.setFont(f);
				g.setColor(Color.RED);
				g.drawString("ROUND:", width * scale / 2 - 75, 100);
				g.drawString(Integer.toString(roundNum), width * scale / 2 - 10, 150);
				if (time == 3) {
					round = false;
					time = 0;
				}
			}
		}
		if (state == State.CONTROLS) {
			g.drawImage(controls, 0, 0, getWidth(), getHeight(), null);
			g.setColor(Color.GRAY);
			g.fillRect(width * scale - 100, height * scale - 60, 80, 40);
			g.setColor(Color.WHITE);
			g.setFont(new Font("ariel", Font.BOLD, 20));
			g.drawString("BACK", width * scale - 90, height * scale - 35);
		}
		if (state == State.MENU) {
			menu.render(g);
		}
		if (state == State.MENU2) {
			menu.render2(g);
			mouse.setMenu(false);
		}
		if (state == State.END) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, width * scale, height * scale);
			g.setColor(Color.WHITE);
			g.setFont(new Font("ariel", Font.BOLD, 40));
			g.drawString("GAME", 130, 50);
			g.drawString("OVER", 130, 80);
			g.setFont(new Font("ariel", Font.BOLD, 30));
			g.drawString("SCORE:", 135, 250);
			String s = Integer.toString(score);
			g.drawString(s, 170, 300);
		}
		if (paused) {
			g.drawImage(pauseScreen, 0, 0, getWidth(), getHeight(), null);
			g.setColor(Color.WHITE);
			g.setFont(new Font("ariel", Font.BOLD, 20));
			g.drawString("Enemies Left:", 20, 30);
			g.drawString(Integer.toString(enemyCount), 170, 30);
			g.drawString("Score:", 20, 50);
			g.drawString(Integer.toString(score), 170, 50);
		}

		// ///////////////////////////////

		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.frame.setResizable(false);
		main.frame.setTitle(title);
		main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.frame.add(main);
		main.frame.pack();
		main.frame.setLocationRelativeTo(null);
		main.frame.setVisible(true);

		main.start();
	}

}
