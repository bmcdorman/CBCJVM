/*
 * This file is part of CBCJVM.
 * CBCJVM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * CBCJVM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with CBCJVM.  If not, see <http://www.gnu.org/licenses/>.
 */

package cbc.actions;

import java.io.*;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.swt.widgets.DirectoryDialog;
import cbc.helpers.*;

/**
 * 
 * @author Braden McDorman
 *
 */

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
