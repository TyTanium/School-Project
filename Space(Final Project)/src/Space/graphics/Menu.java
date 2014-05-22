package Space.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {

	private int width, height;

	private Rectangle play;
	private Rectangle help;
	private Rectangle quit;
	private Rectangle P1;
	private Rectangle P2;

	private Font f1 = new Font("ariel", Font.BOLD, 30);
	private Font f2 = new Font("impact", Font.PLAIN, 60);

	public Menu(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, width, height);
		g2.setColor(Color.WHITE);
		play = new Rectangle(width / 2 - 50, 80, 100, 80);
		help = new Rectangle(width / 2 - 50, 180, 100, 80);
		quit = new Rectangle(width / 2 - 50, 280, 100, 80);
		g2.draw(play);
		g2.draw(help);
		g2.draw(quit);
		g.setFont(f1);
		g.drawString("PLAY", (int) play.getX() + 7, (int) play.getY() + 50);
		g.drawString("HELP", (int) help.getX() + 7, (int) help.getY() + 50);
		g.drawString("QUIT", (int) quit.getX() + 7, (int) quit.getY() + 50);
		g.setFont(f2);
		g.setColor(Color.BLUE);
		g.drawString("SPACE", width / 2 - 73, 55);
		g2.setStroke(new BasicStroke(4));
		g2.drawLine(width / 2 - 73, 62, width / 2 + 78, 62);
	}

	public void render2(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, width, height);
		g2.setColor(Color.WHITE);
		P1 = new Rectangle(50, 50, 120, height - 100);
		P2 = new Rectangle(200, 50, 120, height - 100);
		g.setFont(f1);
		g.drawString("PLAYERS ?", 105, 30);
		g.setFont(f2);
		g.setColor(Color.GRAY);
		g.fillRect((int) P1.getX(), (int) P1.getY(), 120, height - 100);
		g.fillRect((int) P2.getX(), (int) P2.getY(), 120, height - 100);
		g2.draw(P1);
		g2.draw(P2);
		g.setColor(Color.WHITE);
		g.drawString("1", 95, height / 2 + 10);
		g.drawString("2", 245, height / 2 + 10);

	}
}
