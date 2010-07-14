package cbclipse;

import java.util.ArrayList;

import org.eclipse.core.resources.IResource;

import cbcdownloader.CommunicationException;
import cbcdownloader.DownloadConfiguration;
import cbcdownloader.Downloader;

public class Connection {
	private Downloader downloader = null;
	private DownloadConfiguration config = null;
	private ArrayList<Runnable> listeners = new ArrayList<Runnable>();
	private static boolean connected = false;
	
	public Connection(Downloader downloader, DownloadConfiguration config) {
		super();
		setDownloader(downloader, config);
	}
	
	public void setDownloader(Downloader downloader, DownloadConfiguration config) {
		this.downloader = downloader;
		this.config = config;
		downloader.setup(config);
	}
	
	public void connect() throws CommunicationException {
		if(connected) return;
		System.out.println("Downloader connecting..");
		downloader.connect();
		connected = true;
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public void disconnect() {
		if(!connected) return;
		downloader.disconnect();
		connected = false;
	}
	
	public void addListener(IResource resource, Runnable listener) {
		listeners.add(listener);
	}
	
	public void removeListener(IResource resource, Runnable listener) {
		listeners.remove(listener);
	}
	
	public void updateListeners() throws CommunicationException {
		downloader.connect();
		System.out.println("Updating " + listeners.size() + " listener(s).");
		for(Runnable listener : listeners) {
			listener.run();
		}
		downloader.disconnect();
	}
	
	public boolean isDownloaderSet() {
		return downloader != null;
	}

	public Downloader getDownloader() {
		return downloader;
	}
	
	public DownloadConfiguration getConfig() {
		return config;
	}
}
