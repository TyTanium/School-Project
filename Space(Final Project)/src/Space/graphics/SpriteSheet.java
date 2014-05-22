package Space.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage image;

	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getImage32(int x, int y) {
		BufferedImage img = image.getSubimage(x * 32, y * 32, 32, 32);
		return img;
	}

	public BufferedImage getImage64(int x, int y) {
		BufferedImage img = image.getSubimage(x * 64, y * 64, 64, 64);
		return img;
	}

}
