package cbccore.display;

public class Drawer extends Autobuffer {

	public Drawer(int width, int height) {
		super(width, height);
	}
	
	public Drawer(int width, int height, Pixel p) {
		super(width, height, p);
	}
	
	public Drawer(Pixmap p) {
		super(p.getWidth(), p.getHeight());
		blit(0, 0, p);
	}
	
	public void drawHLine(int x, int x1, int y, Pixel p) {
		if(y < 0 || y >= getHeight()) return;
		
		if(x1 < x) {
			int t = x;
			x = x1;
			x1 = t;
		}
		if(x1 >= getWidth()) x1 = getWidth() - 1;
		if(x < 0) x = 0;
		int start = (y * getWidth()) + x;
		int end = (y * getWidth()) + x1;
		for(int i = start; i < end; ++i) {
			setPixel(i, p);
		}
	}
	
	public void drawVLine(int y, int y1, int x, Pixel p) {
		if(x < 0 || x >= getWidth()) return;
		
		if(y1 < y) {
			int t = y;
			y = y1;
			y1 = t;
		}
		if(y1 >= getHeight()) y1 = getWidth() - 1;
		if(y < 0) y = 0;
		for(int i = y; i < y1; ++i) {
			setPixel(i * getWidth() + x, p);
		}
	}
	
	public void drawLine(int x0, int y0, int x1, int y1, Pixel p) {
		if(y0 == y1) {
			drawHLine(x0, x1, y0, p);
			return;
		}
		if(x0 == x1) {
			drawVLine(y0, y1, x0, p);
			return;
		}
		//ported from example code at: http://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm
		int deltax = x1 - x0;
		int deltay = y1 - y0;
		int error = 0;
		//avoiding floating points
		int deltaerr = deltay << 5 / deltax << 5;    // Assume deltax != 0 (line is not vertical),
		// note that this division needs to be done in a way that preserves the fractional part
		int y = y0;
		int x, endx;
		if(x1 > x0) {
			x = x0;
			endx = x1;
		} else {
			x = x1;
			endx = x0;
		}
		for(; x < endx; ++x) {
			setPixel(y*getWidth()+x, p);
			error += deltaerr;
			if(Math.abs(error) > (1<<4)) {
				y += 1;
				error -= 1<<5;
			}
		}
		// There you go, Mr. Braindead
	}
}
