package cbclipse.properties;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;

import cbcdownloader.DownloadConfiguration;
import cbcdownloader.Downloader;
import cbcdownloader.DummyDownloader;
import cbcdownloader.NetworkDownloader;
import cbcdownloader.USBDownloader;
import cbclipse.Connection;
import cbclipse.ConnectionManager;

public class ConnectionInfo {
	public static final QualifiedName qualifiedDownloadName = new QualifiedName(
			"", "DOWNLOADER");
	public static final QualifiedName qualifiedPropertyName = new QualifiedName(
			"", "PROPERTY");
	public static final QualifiedName qualifiedPropertyValue = new QualifiedName(
			"", "PROPERTY_VALUE");
	
	public static final Downloader[] downloaders = new Downloader[] {
			new NetworkDownloader(), new USBDownloader(), new DummyDownloader() };

	private static DownloadConfiguration getConfig(IResource resource,
			Downloader d) throws CoreException {
		if (d == null)
			return null;
		String property = resource
				.getPersistentProperty(qualifiedPropertyName);
		String value = resource
				.getPersistentProperty(qualifiedPropertyValue);
		
		if(value == null)
			value = "";
		
		
		
		DownloadConfiguration config = d.getConfigurationObject();
		
		if(property == null)
			property = config.getRequirements().toArray()[0].toString();
		
		config.setValueFor(property.trim(), value.trim());
		return config;
	}
	
	private static Downloader getDownloader(IResource resource) throws CoreException {
		Downloader current = null;
		String selection = resource.getPersistentProperty(qualifiedDownloadName);
		if (selection == null)
			selection = downloaders[2].toString();
		for (Downloader d : downloaders) {
			if (selection.equals(d.toString())) {
				current = d;
				break;
			}
		}
		return current;
	}

	public static Connection getConnection(IResource resource)
			throws CoreException {
		
		Downloader downloader = getDownloader(resource);
		DownloadConfiguration config = getConfig(resource, downloader);
		
		
		Connection c =  new Connection(downloader, config);
		ConnectionManager.createConnection((IResource)resource.getAdapter(IResource.class), c);
		return c;
	}
	
	public static void save(IResource resource, Connection c) throws CoreException {
		resource.setPersistentProperty(qualifiedDownloadName, c.getDownloader().toString());
		String property = c.getConfig().getRequirements().toArray()[0].toString();
		resource.setPersistentProperty(qualifiedPropertyName, property);
		resource.setPersistentProperty(qualifiedPropertyValue, c.getConfig().getValueFor(property));
		ConnectionManager.createConnection((IResource)resource.getAdapter(IResource.class), c);
	}
}
