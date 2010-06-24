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
		ConnectionManager.getDownloader().execute("rm -Rf \"" + Downloader.PROJECT_DIRECTORY + "/" + project.getProject().getName() + "\"");
		super.executeFirst();
	}

}
