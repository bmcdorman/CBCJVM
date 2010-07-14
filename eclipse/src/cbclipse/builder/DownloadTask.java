package cbclipse.builder;

import org.eclipse.jdt.core.IJavaProject;

import cbcdownloader.CommunicationException;
import cbcdownloader.Downloader;
import cbclipse.ConnectionManager;

public abstract class DownloadTask {
	protected IJavaProject resource = null;
	
	public abstract void executeLast() throws CommunicationException;
	public abstract void executeFirst() throws CommunicationException;

	public DownloadTask(IJavaProject jP) {
		resource = jP;
	}
	
	public Downloader add() throws CommunicationException {
		if(!ConnectionManager.getConnection(resource.getProject()).isConnected()) {
			System.out.println("Connect");
			ConnectionManager.getConnection(resource.getProject()).connect();
			executeFirst();
		}
		System.out.println("Add()");
		return ConnectionManager.getConnection(resource.getProject()).getDownloader();
	}
	
	public void end() throws CommunicationException {
		executeLast();
	}
}
