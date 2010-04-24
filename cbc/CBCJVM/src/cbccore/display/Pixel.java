package cbccore.display;

public class Pixel {
	public static final Pixel whitePixel = new Pixel(255, 255, 255);
	public static final Pixel redPixel = new Pixel(255, 0, 0);
	public static final Pixel greenPixel = new Pixel(0, 255, 0);
	public static final Pixel bluePixel = new Pixel(0, 0, 255);
	private int r = 0;
	private int g = 0;
	private int b = 0;
	private int a = 255;
	private byte[] bytes = new byte[2];
	private boolean dirty = false;
	private static int[] lookup5 = { 0, 8, 16, 25, 33, 41, 49,  58, 66, 74, 82, 90, 99, 107, 115, 123, 132, 140, 148, 156, 165, 173, 181, 189,  197, 206, 214, 222, 230, 239, 247, 255};
	private static int[] lookup6 = { 0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 45,
			49, 53, 57, 61, 65, 69, 73, 77, 81, 85, 89, 93, 97, 101, 105, 109,
			113, 117, 121, 125, 130, 134, 138, 142, 146, 150, 154, 158, 162,
			166, 170, 174, 178, 182, 186, 190, 194, 198, 202, 206, 210, 215,
			219, 223, 227, 231, 235, 239, 243, 247, 251, 255 };

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
	
	public void setAlpha(int a) {
		this.a = a;
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
	
	public int getAlpha() {
		return a;
	}
	
	public byte getAlphaByte() {
		return fromUnsigned(a);
	}

	public void updateRGB565() {
		bytes = rgb8ToRgb565(r, g, b);
	}
	
	// I have spent over 3 hours writing this method.
	// Damn you java gods, can haz unsigned?
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

	/**
	 * Must check alpha condition, otherwise undefined behavior.
	 * @return the RGB565 value
	 */
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
		int b1 = (int) bytes[0];
		int b2 = (int) bytes[1];
		int b = b1 & 0xF8 >> 3;
		int gh = b1 & 0x07 << 3;
		int gl = b2 & 0xE0 >> 5;
		int g = gh | gl;
		int r = b2 & 0x1F;
		r = lookup5[r];
		g = lookup6[g];
		b = lookup5[b];
		SuperPixel.pixel.setRed(r);
		SuperPixel.pixel.setGreen(g);
		SuperPixel.pixel.setBlue(b);
		return SuperPixel.pixel;
	}
}
