package cbc.actions;

import java.io.*;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.swt.widgets.DirectoryDialog;
import cbc.helpers.*;

public class CBCFDAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	final String m_CBCPlugin = "CBCJava Project Installer Plug-in";
	Helper helper = null;
	public void run(IAction action) {
		String workspace = helper.getWorkspace();
		String projectName = helper.getProjectName(workspace);
		String bin = workspace + helper.m_BinDir;
		try {
			DirectoryDialog fd = new DirectoryDialog(window.getShell());
			fd.setMessage("Please select a directory to save the CBCJava Project Install for " + projectName);
			String dir = fd.open();
			if(dir == null) return;
			File dirF = new File(dir + File.separatorChar + projectName);
			dirF.mkdir();
			helper.copyFile(helper.getTmpInstallerFile(projectName), new File(dirF.getAbsolutePath() + File.separatorChar + ".." + File.separatorChar + "robot.c"));
			helper.copyFile(helper.getTmpCFile(projectName), new File(dirF.getAbsolutePath() + File.separatorChar + projectName + ".c"));
			helper.copyDirectory(new File(bin), new File(dirF.getAbsolutePath() + File.separatorChar + "bin"));
			helper.message("Save Complete!");
		} catch (IOException e) {
			helper.error("Exception occured: " + e.toString());
			e.printStackTrace();
		}
		return;
	}
	public void selectionChanged(IAction action, ISelection selection) {
	}
	public void dispose() {
	}
	public void init(IWorkbenchWindow window) {
		this.window = window;
		helper = new Helper(this.window, m_CBCPlugin);
	}
	
}