package cbclipse.builder;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;

import cbcdownloader.CommunicationException;
import cbcdownloader.Downloader;
import cbclipse.ConnectionManager;

public class ProjectDownloadTask extends DownloadTask {
	
	public ProjectDownloadTask(IJavaProject project) {
		super(project);
	}
	
	@Override
	public void executeFirst() throws CommunicationException {
		try {
			System.out.println("mkdir -p \"" + Downloader.PROJECT_DIRECTORY + resource.getOutputLocation().toPortableString() + "\"");
			ConnectionManager.getConnection(resource.getProject()).getDownloader().execute("mkdir -p \"" + Downloader.PROJECT_DIRECTORY + resource.getOutputLocation().toPortableString() + "\"");
		} catch (CommunicationException e) {
			e.printStackTrace();
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void executeLast() {
		
	}

	public IJavaProject getProject() {
		return resource;
	}

}
