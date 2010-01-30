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
	
	public void drawLine(int x, int y, int x1, int y1, Pixel p) {
		if(y == y1) {
			drawHLine(x, x1, y, p);
			return;
		}
		if(x == x1) {
			drawVLine(y, y1, x, p);
			return;
		}
		// TODO: Uhh... Braindead
	}
}
