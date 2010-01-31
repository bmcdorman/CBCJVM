package cbccore.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class SimulatedFramebuffer extends Framebuffer {
	private JPanel window = new JPanel();
	private Graphics2D g = null;
	
	public SimulatedFramebuffer(String name) {
		window.setMinimumSize(new Dimension(320, 240));
		window.setPreferredSize(new Dimension(320, 240));
		window.setMaximumSize(new Dimension(320, 240));
		window.setVisible(true);
	}
	
	@Override
	public void sync() {
		if(!window.isVisible()) return;
		g = (Graphics2D)window.getGraphics();
		for(int iy = 0; iy < getHeight(); ++iy) {
			for(int ix = 0; ix < getWidth(); ++ix) {
				int i = iy * getWidth() + ix;
				byte[] working = { bytes[i * 2], bytes[i * 2 + 1] };
				Pixel p = Pixel.fromRGB565(working);
				g.setColor(new Color(p.getRed(), p.getGreen(), p.getBlue()));
				g.drawLine(ix, iy, ix, iy);
			}
		}
	}
	
	public JPanel getPanel() {
		return window;
	}
}
