package cbccore.display;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Touchscreen {
	private InputStream out = null;
	@SuppressWarnings("unused")
	private File pipe = null;
	
	public Touchscreen(File pipe) {
		try {
			out = new FileInputStream(pipe);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.pipe = pipe;
	}
	
	public void update() throws IOException {
		int s = out.available();
		if(s == 0) return;
		byte[] bytes = new byte[s];
		out.read(bytes);
		for(int i = 0; i < s; ++i)
		System.out.println(bytes[i]);
	}
}
