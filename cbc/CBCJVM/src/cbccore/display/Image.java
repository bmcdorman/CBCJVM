package cbccore.display;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Image {
	private int width;
	private int[] bytes;
	public Image(File image) throws IOException {
		InputStream in = new FileInputStream(image);
		DataInputStream dIn = new DataInputStream(in);
		width = dIn.readInt();
		bytes = new int[dIn.available()];
		int i = 0;
		while(dIn.available() > 0) {
			bytes[i] = dIn.readInt();
			++i;
		}
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return bytes.length / width;
	}
	public int[] getBytes() {
		return bytes;
	}
}
 