package cbc.actions;

import java.io.*;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import cbc.helpers.Helper;
import cbc.helpers.Ssh;

public class CBCAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	String m_IP = "";
	final String m_CBCPlugin = "CBC Networked Downloading Plug-in";
	Helper helper = null;
	public void run(IAction action) {
		String workspace = helper.getWorkspace();
		String projectName = helper.getProjectName(workspace);
		String bin = workspace + helper.m_BinDir;
		m_IP = helper.getInputFromUser("Please input the CBC's IP:", m_IP);
		try {
			Ssh cbc = new Ssh("root", m_IP);
			if(cbc.sendCommand("/mnt/user/jvm/cjm/dlprep \"" + projectName + "\"") != 0) {
				helper.error("CBC Reported error preparing for download. It is advised to delete the project from the cbc, and try again. Please consider reinstalling CBCJava on the CBC.");
				return;
			}
			cbc.sendFile("/mnt/user/code/" + projectName + "/" + projectName + ".c", helper.getTmpCFile(projectName));
			// "/" notifies top level, needed for recursive mode
			helper.sendDirectory(cbc, projectName, true, "/", new File(bin));
			cbc.close();
			helper.message("Download Complete!");
		} catch (IOException e) {
			helper.error("Exception occured: " + e.toString());
			e.printStackTrace();
		}
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