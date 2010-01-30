package cbccore.display;

public class Autobuffer extends Pixmap {
	private byte[] cleanBytes = null;
	
	public Autobuffer(int width, int height) {
		super(width, height);
		cleanBytes = new byte[getBufferSize() * 2];
	}
	
	public void setClean() {
		System.arraycopy(bytes, 0, cleanBytes, 0, getBufferSize() * 2);
	}
	
	public void clean() {
		System.arraycopy(cleanBytes, 0, bytes, 0, getBufferSize() * 2);
	}
}
