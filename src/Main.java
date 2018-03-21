import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		ImageProcessor im = new ImageProcessor();
		im.openFile("img9.jpg");
		boolean[][] pix = im.makeArray(150);
		for (boolean[] b : pix) {
			System.out.println(Arrays.toString(b));
		}
		Midi song = new Midi();
		song.setupMidi();
		for (int j = 0; j < pix[0].length; j++) {
			for (int i = 0; i < pix.length; i++) {
				if (pix[i][j]) {
					int start = i;
					int end = i;
					while (i < pix.length-1) {
						if (pix[i+1][j]) {
							i++;
							end = i;
						}
						else break;
					}
					song.makeNote(pix[0].length-j-1, 24*start, 24*end+24);
				}
			}
		}
		song.write("newMidi10");
	}
}