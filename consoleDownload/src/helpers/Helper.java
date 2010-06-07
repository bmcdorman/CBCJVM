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

package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.Arrays;

/**
 * 
 * @author Braden McDorman, Benjamin Woodruff
 *
 */

public class Helper {
	private String name = "";
	public String srcDir = File.separatorChar + "src";
	public String binDir = File.separatorChar + "bin";
	private Scanner userInput = new Scanner(System.in);
	
	public Helper(String name) {
		this.name = name;
	}
	
	private String findExistingFile(String prefix, String ... possiblities) {
		for(String i : possiblities) {
			if(new File(prefix + i).exists()) {
				return i;
			}
		}
		error("File not found from list of possiblities: " + Arrays.toString(possiblities));
		return null; //will never happen
	}
	
	public void error(String error) {
		System.out.println("[CBCJVMTool] Error: " + error);
		System.exit(0);
	}
	
	public void message(String message) {
		System.out.println("[CBCJVMTool] Message: ");
	}
	
	public String getCFile(String projectName, boolean beep) {
		String cFile = "int main() {";
		if(beep) {
			cFile += "beep();";
		}
		cFile += "chdir(\"/mnt/user/code/" + projectName + "/bin" + "\");"
			     + "system(\"/mnt/user/jvm/java Main\");" +
		"}";
		return cFile;
	}
	
	public File getTmpCFile(String projectName) throws IOException {
		File tmp = File.createTempFile("CBCJava", ".c");
		FileWriter c = new FileWriter(tmp);
		c.append(getCFile(projectName, false));
		c.close();
		return tmp;
	}
	
	public String getInputFromUser(String text, String predef) {
		System.out.println(text + "["+predef+"]");
		String in = userInput.nextLine();
		if(in.equals("")) { in = predef; }
		return in;
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
