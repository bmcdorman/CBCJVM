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
	
	private void __setPixel(int i, Pixel p) {
		__setBytes(i, p);
		buffer[i] = p;
	}
	
	private void __setBytes(int i, Pixel p) {
		byte[] pixel = p.getRGB565();
		bytes[i * 2] = pixel[0];
		bytes[i * 2 + 1] = pixel[1];
	}
	
	public void setPixel(int i, Pixel p) {
		__setPixel(i, p);
	}
	
	public void fill(Pixel p) {
		fillBlock(0, getBufferSize() * 2, p);
	}
	
	public void fillBlock(int i1, int i2, Pixel p) {
		Arrays.fill(buffer, p);
		byte[] pixel = p.getRGB565();
		for(int i = i1; i < i2; i += 2) {
			bytes[i] = pixel[0];
			bytes[i + 1] = pixel[1];
		}
	}
	
	public void fastFill(Pixel p) {
		fastFillBlock(0, getBufferSize() * 2, p);
	}
	
	public void fastFillBlock(int i1, int i2, Pixel p) {
		for(int i = i1; i < i2; i += 2) {
			__setBytes(i, p);
		}
	}
	
	public void fastCopy(byte[] bytes, int foff, int offset, int length) {
		System.arraycopy(bytes, foff * 2, this.bytes, offset * 2, length * 2);
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
				__setPixel(width * iy + ix, p);
			}
		}
	}
	
	public void blit(int x, int y, Pixmap p) {
		Pixel[] pp = p.getPixels();
		for(int iy = 0; iy < p.getHeight(); ++iy) {
			for(int ix = 0; ix < p.getWidth(); ++ix) {
				if(iy + y >= height) break;
				if(ix + x >= width) break;
				__setPixel((width * (iy + y)) + (ix + x), pp[p.getWidth() * iy + ix]);
			}
		}
	}
	
	/**
	 * Does not update pixel array, only byte array.
	 * @param x
	 * @param y
	 * @param p
	 */
	public void fastBlit(int x, int y, Pixmap p) {
		byte[] pp = p.getBytes();
		for(int iy = 0; iy < p.getHeight(); ++iy) {
				//if(iy + y >= height) break;
				fastCopy(pp, iy * p.getWidth(), width * (y + iy) + x, p.getWidth());
		}
	}
	
	/**
	 * Get pixmap height
	 * @return height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Get pixmap width
	 * @return width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns the size of the pixel buffer
	 * @return buffer size
	 */
	public int getBufferSize() {
		return width * height;
	}
	
	/**
	 * Get the byte buffer associated with this pixmap
	 * @return Byte Array that is getBufferSize() * 2
	 */
	public byte[] getBytes() {
		return bytes;
	}
	
	/**
	 * Get the pixel buffer associated with this pixmap
	 * @return Pixel Array that is getBufferSize()
	 */
	public Pixel[] getPixels() {
		return buffer;
	}

}
