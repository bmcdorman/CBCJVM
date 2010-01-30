package cbccore.display;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Framebuffer extends Autobuffer {
	private FileOutputStream out = null;
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
		out.write(getBytes());
		out.close();
		out = new FileOutputStream(pipe);
	}
}
