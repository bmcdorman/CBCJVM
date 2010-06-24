package cbclipse;

import java.util.ArrayList;

import cbcdownloader.CommunicationException;
import cbcdownloader.DownloadConfiguration;
import cbcdownloader.Downloader;

public class ConnectionManager {
	private static Downloader downloader = null;
	private static DownloadConfiguration config = null;
	private static boolean connected = false;
	private static ArrayList<Runnable> listeners = new ArrayList<Runnable>();
	
	private ConnectionManager() {
		
	}
	
	public static void setDownloader(Downloader downloader, DownloadConfiguration config) {
		ConnectionManager.downloader = downloader;
		ConnectionManager.config = config;
		downloader.setup(config);
	}
	
	public static void connect() throws CommunicationException {
		if(connected) return;
		System.out.println("Downloader connecting..");
		downloader.connect();
		connected = true;
	}
	
	public static boolean isConnected() {
		return connected;
	}
	
	public static void disconnect() {
		if(!connected) return;
		downloader.disconnect();
		connected = false;
	}
	
	public static void addListener(Runnable listener) {
		listeners.add(listener);
	}
	
	public static void removeListener(Runnable listener) {
		listeners.remove(listener);
	}
	
	public static Downloader getDownloader() {
		return downloader;
	}
	
	public static void updateListeners() throws CommunicationException {
		downloader.connect();
		System.out.println("Updating " + listeners.size() + " listener(s).");
		for(Runnable listener : listeners) {
			listener.run();
		}
		downloader.disconnect();
	}
	
	public static boolean isDownloaderSet() {
		return downloader != null;
	}
}

