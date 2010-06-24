package cbclipse.builder;

import cbcdownloader.CommunicationException;
import cbcdownloader.Downloader;
import cbclipse.ConnectionManager;

public abstract class DownloadTask {
	public abstract void executeLast() throws CommunicationException;
	public abstract void executeFirst() throws CommunicationException;

	public Downloader add() throws CommunicationException {
		if(!ConnectionManager.isConnected()) {
			System.out.println("Connect");
			ConnectionManager.connect();
			executeFirst();
		}
		System.out.println("Add()");
		return ConnectionManager.getDownloader();
	}
	
	public void end() throws CommunicationException {
		executeLast();
	}
}
