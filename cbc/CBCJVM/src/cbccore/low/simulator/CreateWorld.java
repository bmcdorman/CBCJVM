package cbccore.low.simulator;

import java.awt.Graphics;

import javax.swing.JPanel;

public class CreateWorld extends JPanel implements Runnable {
	private static final long serialVersionUID = -7591540311617637479L;
	private SimulatedCreate create = null;
	private boolean exit = false;
	
	public CreateWorld(SimulatedCreate create) {
		this.create = create;
	}
	
	public void paint(Graphics g) {
		g.drawOval(create.getX() - 50, create.getY() - 50, 100, 100);
		g.drawLine(create.getX() - 50, create.getY() - 50, create.getX(), create.getY());
	}

	@Override
	public void run() {
		while(!exit) {
			repaint();
			Thread.yield();
		}
	}
	
	public void exit() {
		exit = true;
	}
}
