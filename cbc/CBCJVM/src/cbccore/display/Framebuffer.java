package cbccore.display;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Framebuffer extends Pixmap {
	private OutputStream out = null;
	private File pipe = null;
	
	public Framebuffer(File pipe) {
		super(320, 240);
		try {
			out = new FileOutputStream(pipe);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.pipe = pipe;
	}
	
	public void sync() throws IOException {
		out.write((byte[])getBytes());
		out.close();
		// Why is this necessary??! Seems uber slow
		out = new FileOutputStream(pipe);
	}
}
