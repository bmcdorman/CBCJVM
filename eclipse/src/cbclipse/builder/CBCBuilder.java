package cbclipse.builder;

import java.util.Map;

import org.eclipse.core.internal.runtime.Log;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import cbcdownloader.CommunicationException;
import cbclipse.Connection;
import cbclipse.ConnectionManager;
import cbclipse.properties.CBCPropertyPage;
import cbclipse.properties.ConnectionInfo;

public class CBCBuilder extends IncrementalProjectBuilder {
	
	class CBCDeltaVisitor extends CBCResourceManager implements
			IResourceDeltaVisitor {

		public CBCDeltaVisitor(ProjectDownloadTask task) {
			super(task);
		}

		public boolean visit(IResourceDelta delta) throws CoreException {

			IResource resource = delta.getResource();
			try {
				switch (delta.getKind()) {
				case IResourceDelta.ADDED:
					handleAdd(resource);
					break;
				case IResourceDelta.REMOVED:
					handleRemove(resource);
					break;
				case IResourceDelta.CHANGED:
					handleChange(resource);
					break;
				}
			} catch (CommunicationException e) {
				throw new CoreException(new Status(IStatus.ERROR, BUILDER_ID, "Communication with CBC failed, not downloaded", new RuntimeException()));
			}
			return true;
		}
	}

	class CBCResourceVisitor extends CBCResourceManager implements
			IResourceVisitor {

		public CBCResourceVisitor(ProjectDownloadTask task) {
			super(task);
		}

		public boolean visit(IResource resource) {
			try {
				handleAdd(resource);
			} catch (CommunicationException e) {
				e.printStackTrace();
			}
			return true;
		}
	}

	public static final String BUILDER_ID = "CBClipse.cbcBuilder";


	@SuppressWarnings("unchecked")
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
			throws CoreException {
		if (kind == FULL_BUILD) {
			fullBuild(monitor);
		} else {
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else {
				incrementalBuild(delta, monitor);
			}
		}
		if(ConnectionManager.getConnection(getProject()) != null)
		ConnectionManager.getConnection(getProject()).disconnect();
		return null;
	}

	protected void fullBuild(final IProgressMonitor monitor)
			throws CoreException {
		IJavaProject jP = JavaCore.create(getProject());
		Connection c = ConnectionInfo.getConnection(getProject());
		if(c == null || !c.isDownloaderSet()) {
			System.out.println("Downloader not set, skipping CBC Builder..");
			throw new CoreException(new Status(IStatus.WARNING, BUILDER_ID, "Downloader not set in project properties.", new RuntimeException()));
		}
		
		try {
			ProjectDownloadTask task = new CleanProjectDownloadTask(jP);
			getProject().accept(new CBCResourceVisitor(task));
			task.end();
		} catch (CommunicationException e) {
			e.printStackTrace();
		}
	}

	protected void incrementalBuild(IResourceDelta delta,
			IProgressMonitor monitor) throws CoreException {
		IJavaProject jP = JavaCore.create(getProject());
		Connection c = ConnectionInfo.getConnection(getProject());
		if(c == null || !c.isDownloaderSet()) {
			System.out.println("Downloader not set, skipping CBC Builder..");
			throw new CoreException(new Status(IStatus.WARNING, BUILDER_ID, "Downloader not set in project properties.", new RuntimeException()));
		}
		
		monitor.beginTask("Sending to CBC", 3);
		ProjectDownloadTask task = new ProjectDownloadTask(jP);
		monitor.worked(1);
		delta.accept(new CBCDeltaVisitor(task));
		monitor.worked(1);
		try {
			task.end();
		} catch (CommunicationException e) {
			e.printStackTrace();
		}
		monitor.done();
	}
}
