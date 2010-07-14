package cbclipse.builder;

import org.eclipse.jdt.core.IJavaProject;

import cbcdownloader.CommunicationException;
import cbcdownloader.Downloader;
import cbclipse.ConnectionManager;

public class CleanProjectDownloadTask extends ProjectDownloadTask {

	public CleanProjectDownloadTask(IJavaProject project) {
		super(project);
	}
	
	public void executeFirst() throws CommunicationException {
		ConnectionManager.getConnection(resource.getProject()).getDownloader().execute("rm -Rf \"" + Downloader.PROJECT_DIRECTORY + "/" + resource.getProject().getName() + "\"");
		super.executeFirst();
	}

}
