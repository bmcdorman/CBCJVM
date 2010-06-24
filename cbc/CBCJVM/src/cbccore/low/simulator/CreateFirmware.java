package cbccore.low.simulator;

import java.util.LinkedList;
import java.util.Queue;

public class CreateFirmware extends Thread {
	private static CreateFirmware instance = null;
	private boolean exit = false;
	private Queue<Integer> buffer = new LinkedList<Integer>();
	private static final double WHEEL_DIAMETER = 255;
	private double rightVel = 0.0;
	private double leftVel = 0.0;
	private double vel = 0.0;
	private double distance = 0.0;
	private double angle = 0.0;
	private long lastUpdate = 0;

	public static CreateFirmware get() {
		if (instance == null) {
			instance = new CreateFirmware();
			instance.start();
		}
		return instance;
	}

	private CreateFirmware() {
		clear();
	}

	public void sendByte(int i) {
		synchronized (buffer) {
			buffer.add(i);
		}
	}

	private int poll() {
		Integer i = null;
		while ((i = buffer.poll()) == null)
			Thread.yield();
		return i;
	}
	
	private int eat8() {
		Integer i = poll();
		buffer.remove();
		return i;
	}

	public int eat16() {
		int h = eat8();
		int l = eat8();
		return (h << 8) | l;
	}
	
	private void clear() {
		update();
		distance = 0;
		angle = 0;
	}
	
	private void update() {
		if(lastUpdate == 0) {
			lastUpdate = System.currentTimeMillis();
			return;
		}
			
		long delta = System.currentTimeMillis() - lastUpdate;
		distance += vel * ((double)delta / 1000.0);
		lastUpdate = System.currentTimeMillis();
	}
	
	@Override
	public void run() {
		while (!exit) {
			update();
			int op = poll(); 
			switch(op) {
			case 137: // Drive
				eat8();
				int v = eat16();
				int r = eat16();
				double half = (WHEEL_DIAMETER / 2);
				double cl = (r - half) * 2 * Math.PI; 
				double cr = (r + half) * 2 * Math.PI; 
				leftVel = cl / v;
				rightVel = cr / v;
				vel = v;
				break;
			case 157:
				
				break;
			
			}
			Thread.yield();
		}
	}

	public void exit() {
		exit = true;
	}
}
