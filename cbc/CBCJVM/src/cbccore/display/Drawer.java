package cbccore.display;

public class Drawer {
	
	private Pixmap pmap;
	
	/*public Drawer(int width, int height) {
		super(width, height);
	}
	
	public Drawer(int width, int height, Pixel p) {
		super(width, height, p);
	}*/
	
	public Drawer(Pixmap p) {
		pmap = p;
	}
	
	public void drawHLine(int x, int x1, int y, Pixel p) {
		if(y < 0 || y >= pmap.getHeight()) return;
		
		if(x1 < x) {
			int t = x;
			x = x1;
			x1 = t;
		}
		if(x1 >= pmap.getWidth()) x1 = pmap.getWidth() - 1;
		if(x < 0) x = 0;
		int start = (y * pmap.getWidth()) + x;
		int end = (y * pmap.getWidth()) + x1;
		for(int i = start; i < end; ++i) {
			pmap.setPixel(i, p);
		}
	}
	
	public void drawVLine(int y, int y1, int x, Pixel p) {
		if(x < 0 || x >= pmap.getWidth()) return;
		
		if(y1 < y) {
			int t = y;
			y = y1;
			y1 = t;
		}
		if(y1 >= pmap.getHeight()) y1 = pmap.getWidth() - 1;
		if(y < 0) y = 0;
		for(int i = y; i < y1; ++i) {
			pmap.setPixel(i * pmap.getWidth() + x, p);
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
		boolean steep = Math.abs(y1 - y0) > Math.abs(x1 - x0);
		if(steep) {
			int k; //swap var
			//swap x0 and y0
			k = x0;
			x0 = y0;
			y0 = k;
			//swap x1 and y1
			k = x1;
			x1 = y1;
			y1 = k;
		}
		if(x0 > x1) {
			int k; //swap var
			//swap x0 and x1
			k = x0;
			x0 = x1;
			x1 = k;
			//swap y0 and y1
			k = y0;
			y0 = y1;
			y1 = k;
		}
		int deltax = x1 - x0;
		int deltay = Math.abs(y1 - y0);
		int error = deltax / 2;
		int ystep;
		int y = y0;
		ystep = y0 < y1? 1 : -1;
		for(int x = x0; x < x1; ++x) {
			if(steep) { pmap.setPixel(x*pmap.getWidth()+y, p); } else { pmap.setPixel(y*pmap.getWidth()+x, p); }
			error = error - deltay; //subtraction is faster than addition
			if(error < 0) {
				y += ystep;
				error = error + deltax;
			}
		// There you go, Mr. Braindead
		}
	}
}
