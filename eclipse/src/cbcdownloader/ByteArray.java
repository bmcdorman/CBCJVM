package cbcdownloader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class ByteArray extends ArrayList<Byte> {
	private static final long serialVersionUID = 888410407666630588L;

	public void chop(int n) {
		List<Byte> terminal = subList(0, size() - n);
		clear();
		addAll(terminal);
	}
	
	
	
	public byte[] getBytes() {
		byte[] data = new byte[size()];
		for(int i = 0; i < size(); ++i) {
			data[i] = get(i);
		}
		return data;
	}
}
