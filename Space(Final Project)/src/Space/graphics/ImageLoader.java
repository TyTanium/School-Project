package Space.graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ImageLoader {

	private BufferedImage image;

	public BufferedImage loadImage(String path) throws Exception {
		image = ImageIO.read(getClass().getResource(path));
		return image;
	}

}
