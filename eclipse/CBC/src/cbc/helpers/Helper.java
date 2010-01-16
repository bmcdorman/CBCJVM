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

package cbc.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author Braden McDorman
 *
 */

public class Helper {
	private IWorkbenchWindow window = null;
	private String name = "";
	public final String m_SrcDir = File.separatorChar + "src";
	public final String m_BinDir = File.separatorChar + "bin";
	public Helper(IWorkbenchWindow window, String name) {
		this.window = window;
		this.name = name;
	}
	public void error(String error) {
		MessageDialog.openError(window.getShell(), name, "Error: " + error);
	}
	public void message(String message) {
		MessageDialog.openInformation(window.getShell(), name, message);
	}
	public String getCFile(String projectName, boolean beep) {
		String cFile = "int main()" + "{";
		if (beep)
			cFile += "beep();";
		cFile += "chdir(\"/mnt/user/code/" + projectName + "/bin" + "\");"
				+ "system(\"/mnt/user/jvm/java Main\");" + "}";
		return cFile;

	}
	public File getTmpCFile(String projectName) throws IOException {
		File tmp = File.createTempFile("CBCJava", ".c");
		FileWriter c = new FileWriter(tmp);
		c.append(getCFile(projectName, false));
		c.close();
		return tmp;
	}
	public String getInstallerFile(String projectName) {
		String cFile = "int main()\n{\n";
		cFile += "system(\"mkdir /mnt/user/tmpusb\"); // Make the mount point for the flash drive\n";
		cFile += "system(\"mount /dev/sdb1 /mnt/user/tmpusb -t vfat\"); //mount the flash drive\n";
		cFile += "msleep(1000); //make sure it completely mounts\n";
		cFile += "system(\"cp -R \\\"/mnt/user/tmpusb/" + projectName + "/\\\" /mnt/user/code/\"); //Copy the project\n";
		cFile += "system(\"umount /mnt/user/tmpusb\"); //Unmount flash drive\n";
		cFile += "system(\"rm -Rf /mnt/user/tmpusb\"); //Remove mount point\n";
		cFile += "printf(\"Done Installing " + projectName + "!\"); //Inform user we are done\n";
		cFile += "}\n";
		return cFile;
	}
	public File getTmpInstallerFile(String projectName) throws IOException {
		File tmp = File.createTempFile("CBCJavaInstaller", ".c");
		FileWriter c = new FileWriter(tmp);
		c.append(getInstallerFile(projectName));
		c.close();
		return tmp;
	}
	public String getWorkspace() {
		IEditorPart activeEditor = getActiveEditor();
		String currentFile = activeEditor.getTitle();
		String fileLocation = ((IPathEditorInput) activeEditor.getEditorInput()).getPath().toString();
		return fileLocation.substring(0, fileLocation.length() - (m_SrcDir.length() + 1 + currentFile.length()));
	}
	public String getProjectName(String workspace) {
		int ch = workspace.lastIndexOf('/');
		if(ch == -1) {
			ch = workspace.lastIndexOf(File.separatorChar);
			if(ch == -1) {
				error("Unable to determine project name from \"" + workspace + "\"");
				return null;
			}
		}
		return workspace.substring(ch + 1);
	}
	public String getInputFromUser(String text, String predef) {
		InputDialog input = new InputDialog(window.getShell(), name, text, predef, null);
		input.open();
		input.close();
		return input.getValue();
	}
	public IEditorPart getActiveEditor() {
		IWorkbench iworkbench = PlatformUI.getWorkbench();
		if (iworkbench == null) {
			error("Unable to fetch workbench.");
			return null;
		}
		IWorkbenchWindow iworkbenchwindow = iworkbench
				.getActiveWorkbenchWindow();
		if (iworkbenchwindow == null) {
			error("Unable to fetch workbench window.");
			return null;
		}
		IWorkbenchPage iworkbenchpage = iworkbenchwindow.getActivePage();
		if (iworkbenchpage == null) {
			error("Unable to fetch workbench page.");
			return null;
		}
		IEditorPart ieditorpart = iworkbenchpage.getActiveEditor();
		if (ieditorpart == null) {
			error("Unable to fetch active editor. You probably have no editors open.");
			return null;
		}
		return ieditorpart;
	}
	public void copyFile(File source, File destination) {
		
		try {
			destination.createNewFile();
			InputStream in = new FileInputStream(source);
			OutputStream out = new FileOutputStream(destination);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (FileNotFoundException ex) {
			error(ex.getMessage() + " in the specified directory.");
		} catch (IOException e) {
			error(e.getMessage());
		}
	}
	public void copyDirectory(File src, File dst) throws IOException {
		File[] files = src.listFiles();
		dst.mkdirs();
		for (int it = 0; it < files.length; ++it) {
			copyFile(files[it], new File(dst.getAbsolutePath() + File.separatorChar + files[it].getName()));
		}
	}
	// Recursive sending of a directory
	public void sendDirectory(Ssh cbc, String projectName, boolean recursive, String prefix, File dir) throws IOException {
		File[] files = dir.listFiles();
		for(int it = 0; it < files.length; ++it) {
			if(files[it].isDirectory() && recursive) {
				sendDirectory(cbc, projectName, recursive, prefix + files[it].getName() + "/", files[it]);
			} else {
				cbc.sendFile("/mnt/user/code/" + projectName + "/bin" + prefix + files[it].getName(), files[it]);
			}
		}
	}
	public File[] getDirectoryFiles(File dir) {
		return dir.listFiles();
	}
}
