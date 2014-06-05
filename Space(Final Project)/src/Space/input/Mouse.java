package Space.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Space.Main;
import Space.Main.State;
import Space.sound.Sound;

public class Mouse implements MouseListener, MouseMotionListener {

	private int mx = -1, my = -1, mb = -1;
	private boolean able = true, menu = true;;

	public int getMX() {
		return mx;
	}

	public int getMY() {
		return my;
	}

	public int getMB() {
		return mb;
	}

	public void setMX(int x) {
		mx = x;
	}

	public void setMY(int y) {
		my = y;
	}

	public void setMB(int b) {
		mb = b;
	}

	public void setMenu(boolean ans) {
		menu = ans;
	}

	public void mouseDragged(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		mb = e.getButton();

		if (e.getX() >= Main.width * Main.scale / 2 - 50 && e.getX() <= Main.width * Main.scale / 2 - 50 + 100) {
			if (e.getY() >= 80 && e.getY() <= 160) {
				Main.state = State.MENU2;
				able = false;
			}
		}
		if (able) {
			if (e.getX() >= Main.width * Main.scale / 2 - 50 && e.getX() <= Main.width * Main.scale / 2 - 50 + 100) {
				if (e.getY() >= 180 && e.getY() <= 260) {
					Main.state = State.CONTROLS;
				}
			}
			if (e.getX() >= Main.width * Main.scale / 2 - 50 && e.getX() <= Main.width * Main.scale / 2 - 50 + 100) {
				if (e.getY() >= 280 && e.getY() <= 360) {
					System.exit(0);
				}
			}
			//width * scale - 100, height * scale - 60, 80, 40
			if (e.getX() >= Main.width * Main.scale - 100 && e.getX() <= Main.width * Main.scale - 20) {
				if (e.getY() >= Main.height * Main.scale - 60 && e.getY() <= Main.height * Main.scale - 20) {
					Main.state = State.MENU;
				}
			}
		}
		if (!menu) {
			if (e.getX() >= 50 && e.getX() <= 170) {
				if (e.getY() >= 50 && e.getY() <= Main.height * Main.scale - 50) {
					Main.setPlayers(1);
					Sound.count.play();
					Main.state = State.GAME;
				}
			}
			if (e.getX() >= 200 && e.getX() <= 320) {
				if (e.getY() >= 50 && e.getY() <= Main.height * Main.scale - 50) {
					Main.setPlayers(2);
					Sound.count.play();
					Main.state = State.GAME;
				}
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
		mb = -1;
	}

}
