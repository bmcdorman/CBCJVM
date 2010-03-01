package cbccore.display;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
public class Framebuffer extends Autobuffer {
	private RandomAccessFile out = null;
	
	@SuppressWarnings("unused")
	private File pipe = null;
	
	public Framebuffer() {
		super(320, 240);
	}
	
	public Framebuffer(File pipe) {
		super(320, 240);
		try {
			out = new RandomAccessFile(pipe, "rw");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.pipe = pipe; 
	}
	
	public void sync() throws IOException {
		out.seek(0);
		out.write(getBytes());
	}
}
