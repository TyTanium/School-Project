package Space.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Space.Main;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[200];
	public boolean up, down, left, right, enter;
	public boolean up2, down2, left2, right2, space;
	public boolean p, esc;
	public int countP;

	public void update() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		enter = keys[KeyEvent.VK_ENTER];
		if (Main.getPlayers() == 2) {
			up2 = keys[KeyEvent.VK_W];
			down2 = keys[KeyEvent.VK_S];
			left2 = keys[KeyEvent.VK_A];
			right2 = keys[KeyEvent.VK_D];
			space = keys[KeyEvent.VK_SPACE];
		}
		p = keys[KeyEvent.VK_P];
		esc = keys[KeyEvent.VK_ESCAPE];
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		Main.isShooting = false;
	}

	public void keyTyped(KeyEvent e) {
	}

}
