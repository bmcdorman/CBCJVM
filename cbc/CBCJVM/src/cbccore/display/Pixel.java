package cbccore.display;

public class Pixel {
	public static final Pixel whitePixel = new Pixel(255, 255, 255);
	public static final Pixel redPixel = new Pixel(255, 0, 0);
	public static final Pixel greenPixel = new Pixel(0, 255, 0);
	public static final Pixel bluePixel = new Pixel(0, 0, 255);
	private int r = 0;
	private int g = 0;
	private int b = 0;
	private byte[] bytes = new byte[2];
	private boolean dirty = false;

	public Pixel() {

	}

	public Pixel(int r, int g, int b) {
		setColor(r, g, b);
	}

	public void setColor(int r, int g, int b) {
		setRed(r);
		setGreen(g);
		setBlue(b);
	}

	public void setRed(int r) {
		this.r = r;
		dirty = true;
	}

	public void setGreen(int g) {
		this.g = g;
		dirty = true;
	}

	public void setBlue(int b) {
		this.b = b;
		dirty = true;
	}

	public int getRed() {
		return r;
	}

	public int getGreen() {
		return g;
	}

	public int getBlue() {
		return b;
	}

	// I have spent over 3 hours writing this method. 
	// Damn you java gods, can haz unsigned?
	public void updateRGB565() {
		bytes = rgb8ToRgb565(r, g, b);
	}
	
	public static byte[] rgb8ToRgb565(int r, int g, int b) {
		
		byte[] concatbytes = new byte[2];
		int r16 = (r >> 3) & 0xFF;
		int g16 = (g >> 2) & 0xFF;
		int b16 = (b >> 3) & 0xFF;
		
		int rc = (r16 << 3) & 0xFF;
		int gc1 = (g16 >> 3) & 0xFF;
		int gc2 = (g16 << 5) & 0xFF;
		int bc = b16 & 0xFF;
		
		int high = rc | gc1;
		int low = gc2 | bc;
		
		concatbytes[1] = fromUnsigned(high);
		concatbytes[0] = fromUnsigned(low);
		return concatbytes;
	}

	public byte[] getRGB565() {
		if (dirty)
			updateRGB565();
		return bytes;
	}

	public static byte fromUnsigned(int c) {
		return (byte) ((c > 127) ? c - 256 : c);
	}
	
	public static Pixel fromRGB8(int rgb) {
		return new Pixel(rgb & 0xFF0000, rgb & 0x00FF00, rgb & 0x0000FF);
	}
	
	public static Pixel fromRGB565(byte[] bytes) {
		int b1 = (int)bytes[0];
		int b2 = (int)bytes[1];
		int r = b1 & 0xF8;
		int g = b1 & 0x07 << 5;
		g |= b2 & 0xE0 >> 5;
		int b = b2 & 0x1F;
		r = (r * 255) / 31;
		g = (g * 255) / 63;
		b = (b * 255) / 31;
		
		return new Pixel(r, g, b);
	}
}
