package cbccore.display;

import java.util.Arrays;

public class Pixmap {
	private Pixel[] buffer = null;
	protected byte[] bytes = null;
	private int width = 0;
	private int height = 0;
	private static Pixel blank = new Pixel(0, 0, 0);
	
	public Pixmap(int width, int height) {
		this(width, height, blank);
	}
	
	public Pixmap(int width, int height, Pixel fill) {
		this.width = width;
		this.height = height;
		buffer = new Pixel[width * height];
		bytes = new byte[width * height * 2];
		// Create initial buffer s
		fill(fill);
	}
	
	public void setPixel(int i, Pixel p) {
		byte[] pixel = p.getRGB565();
		
		bytes[i * 2] = pixel[0];
		bytes[i * 2 + 1] = pixel[1];
		buffer[i] = p;
	}
	
	public void fillBlock(int i1, int i2, Pixel p) {
		for(int i = i1; i < i2; ++i) {
			setPixel(i, p);
		}
	}
	
	/**
	 * Fills a rectangle bound by the top left and bottom right point with a given pixel.
	 * @param x Starting x
	 * @param y Starting y
	 * @param x1 Ending x
	 * @param y1 Ending y
	 * @param p Pixel to fill
	 */
	public void fillRectangle(int x, int y, int x1, int y1, Pixel p) {
		for(int iy = y; iy < y1; ++iy) {
			for(int ix = x; ix < x1; ++ix) {
				setPixel(width * iy + ix, p);
			}
		}
	}
	
	public void blit(int x, int y, Pixmap p) {
		Pixel[] pp = p.getPixels();
		for(int iy = 0; iy < p.getHeight(); ++iy) {
			for(int ix = 0; ix < p.getWidth(); ++ix) {
				if(iy + y >= height) break;
				if(ix + x >= width) break;

				setPixel((width * (iy + y)) + (ix + x), pp[p.getWidth() * iy + ix]);
			}
		}
	}
	
	/**
	 * Not fully working. Needed for image blitting.
	 * @param x
	 * @param y
	 * @param p
	 */
	public void fastBlit(int x, int y, Pixmap p) {
		byte[] pp = p.getBytes();
		for(int iy = 0; iy < p.getHeight(); ++iy) {
			for(int ix = 0; ix < p.getWidth(); ++ix) {
				if(iy + y >= height) break;
				if(ix + x >= width) break;
				int oi = ((width * (iy + y)) + (ix + x)) * 2;
				int ti = (p.getWidth() * iy + ix) * 2;
				bytes[oi] = pp[ti];
				bytes[oi + 1] = pp[ti + 1];
			}
		}
	}
	
	public void fill(Pixel p) {
		Arrays.fill(buffer, p);
		byte[] pixel = p.getRGB565();
		for(int i = 0; i < bytes.length; i += 2) {
			bytes[i] = pixel[0];
			bytes[i + 1] = pixel[1];
		}
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getBufferSize() {
		return width * height;
	}
	
	public byte[] getBytes() {
		return bytes;
	}
	
	public Pixel[] getPixels() {
		return buffer;
	}

}
