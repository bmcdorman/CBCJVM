package cbccore.display;

import java.io.File;

public class Display {
	private static Framebuffer fb0 = new Framebuffer(new File("/dev/fb0"));
	private static Framebuffer fb1 = new Framebuffer(new File("/dev/fb1"));
	private static Framebuffer[] framebuffers = {fb0, fb1};
	public static Framebuffer[] getFramebuffers() {
		return framebuffers;
	}
}
