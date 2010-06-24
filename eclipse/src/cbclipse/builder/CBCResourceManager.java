package cbclipse.builder;

import org.eclipse.core.resources.IResource;

import cbcdownloader.CommunicationException;
import cbcdownloader.Downloader;

public class CBCResourceManager {
	private ProjectDownloadTask task = null;
	
	public CBCResourceManager(ProjectDownloadTask task) {
		this.task = task;
	}
	
	public void handleAdd(IResource resource) throws CommunicationException {
		System.out.println("Add");
		if(resource.getLocation().lastSegment().endsWith(".class")) {
			System.out.println("Class Add");
			task.add().download(Downloader.PROJECT_DIRECTORY + resource.getFullPath(), resource.getLocation().toFile());
			return;
		}
		if(resource.getType() == IResource.FOLDER) {
			task.add().execute("mkdir -p \"" + Downloader.PROJECT_DIRECTORY + resource.getFullPath().toPortableString() + "\"");
			return;
		}
	}
	
	public void handleRemove(IResource resource) throws CommunicationException {
		System.out.println("Remove");
		if(resource.getType() == IResource.PROJECT) {
			task.add().execute("rm -Rf \"" + Downloader.PROJECT_DIRECTORY + resource.getFullPath() + "\"");
			return;
		}
		if(resource.getLocation().lastSegment().endsWith(".class")) {
			task.add().delete(Downloader.PROJECT_DIRECTORY + resource.getFullPath());
			return;
		}
		if(resource.getType() == IResource.FOLDER) {
			task.add().execute("rm -Rf \"" + Downloader.PROJECT_DIRECTORY + resource.getFullPath().toPortableString() + "\"");
			return;
		}
	}
	
	public void handleChange(IResource resource) throws CommunicationException {
		System.out.println("Change");
		if(resource.getLocation().lastSegment().endsWith(".class")) {
			task.add().download(Downloader.PROJECT_DIRECTORY + resource.getFullPath(), resource.getLocation().toFile());
			return;
		}
	}

	public ProjectDownloadTask getTask() {
		return task;
	}
}
