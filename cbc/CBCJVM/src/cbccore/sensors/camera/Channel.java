package cbccore.sensors.camera;

public class Channel {
	private cbccore.low.Camera lowCamera;
	private int ch = 0;
	public Channel(cbccore.low.Camera lowCamera, int ch) {
		this.lowCamera = lowCamera;
		this.ch = ch;
	}
	public int getBlobCount() {
		return lowCamera.track_count(ch);
	}
	public Blob getBlob(int i) {
		return new Blob(this, i);
	}
	public int getChannelID() {
		return ch;
	}
}
