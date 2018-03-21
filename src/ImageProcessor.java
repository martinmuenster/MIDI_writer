import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageProcessor {
	public BufferedImage img;
	
	public void openFile(String fname) {
		img = null;
		File f = new File(fname);
	    try {
			img = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public boolean[][] makeArray(int threshold) {
		boolean[][] dark = new boolean[img.getHeight()][img.getWidth()];
		for (int i = 0; i < img.getHeight(); i++) {
			for (int j = 0; j < img.getWidth(); j++) {
				dark[i][j] = ((img.getRGB(i, j) & 0x000000ff) + ((img.getRGB(i, j)>>8) & 0x0000ff) + ((img.getRGB(i, j)>>16) & 0x00ff))/3 < threshold;
			}
		}
		return dark;
	}
}
