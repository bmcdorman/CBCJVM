package cbclipse;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.resources.IResource;

import cbcdownloader.CommunicationException;
import cbcdownloader.DownloadConfiguration;
import cbcdownloader.Downloader;

public class ConnectionManager {
	
	private static HashMap<IResource, Connection> connection = new HashMap<IResource, Connection>();
	
	private ConnectionManager() {
		
	}
	
	public static void createConnection(IResource resource, Connection connection) {
		ConnectionManager.connection.put(resource, connection);
	}
	
	public static Connection getConnection(IResource resource) {
		return connection.get(resource);
	}
}

