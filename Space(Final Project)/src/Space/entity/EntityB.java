package Space.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityB {

	public void update();

	public void render(Graphics g);

	public double getX();

	public double getY();

	public Rectangle getBounds();

	public void setOff(int xOff, int yOff);

}
