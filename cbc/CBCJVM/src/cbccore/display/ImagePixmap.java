package cbccore.display;

import java.io.IOException;




public class ImagePixmap extends Pixmap {

	public ImagePixmap(Image image) throws IOException {
		super(image.getWidth(), image.getHeight());
		int[] in = image.getBytes();
		if(getBufferSize() != in.length) {
			throw new IOException("Input and Image size do not match!");
		}
		
		for(int i = 0; i < in.length; ++i) {
			int r = in[i] & 0xFF0000 >> 16;
			int g = in[i] & 0x00FF00 >> 8;
			int b = in[i] & 0x0000FF;
			byte[] ret = Pixel.rgb8ToRgb565(r, g, b);
			bytes[i * 2] = ret[0];
			bytes[i * 2 + 1] = ret[1];
		}
	}
}
