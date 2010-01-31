package cbccore.display;

import java.io.File;

import cbccore.Device;

public class Display {
	private static Framebuffer fb0 = Device.isOnCBC() ? new Framebuffer(new File("/dev/fb0")) : new SimulatedFramebuffer();
	private static Framebuffer fb1 = Device.isOnCBC() ? new Framebuffer(new File("/dev/fb1")) : new SimulatedFramebuffer();
	private static Touchscreen ts0 = Device.isOnCBC() ? new Touchscreen(new File("/dev/input/ts0")) : null;
	private static Framebuffer[] framebuffers = {fb0, fb1};
	public static Framebuffer[] getFramebuffers() {
		return framebuffers;
	}
	public static Touchscreen getTouchscreen() {
		return ts0;
	}
}
