package Space.graphics;

import java.awt.image.BufferedImage;

import Space.Main;

public class Texture {

	public BufferedImage[] player = new BufferedImage[3];
	public BufferedImage[] enemy = new BufferedImage[3];
	public BufferedImage[] bullet = new BufferedImage[1];
	public BufferedImage[] powerUp = new BufferedImage[3];
	public BufferedImage[] specialEnemy = new BufferedImage[3];

	private SpriteSheet ss = null;

	public Texture(Main main) {
		ss = new SpriteSheet(main.getSS());

		getTextures();
	}

	private void getTextures() {
		player[0] = ss.getImage64(0, 0);
		player[1] = ss.getImage64(1, 0);
		player[2] = ss.getImage64(2, 0);
		enemy[0] = ss.getImage64(0, 1);
		enemy[1] = ss.getImage64(1, 1);
		enemy[2] = ss.getImage64(2, 1);
		bullet[0] = ss.getImage64(0, 2);
		powerUp[0] = ss.getImage64(1, 2);
		powerUp[1] = ss.getImage64(2, 2);
		powerUp[2] = ss.getImage64(3, 2);
		specialEnemy[0] = ss.getImage64(0, 3);
		specialEnemy[1] = ss.getImage64(1, 3);
		specialEnemy[2] = ss.getImage64(2, 3);
	}

	public double getWidth(BufferedImage image) {
		if (image.getWidth() == 32) {
			return 32;
		} else return 64;
	}

	public double getHeight(BufferedImage image) {
		if (image.getHeight() == 32) {
			return 32;
		} else return 64;
	}

}
