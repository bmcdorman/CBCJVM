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

package actions;

import java.io.*;
import helpers.Helper;
import helpers.Ssh;

/**
 * 
 * @author Braden McDorman, Benjamin Woodruff
 *
 */

public class CBCAction {
	
	public void run(String ipAddr, String dirName) {
		Helper helper = new Helper(dirName);
		String bin = helper.binDir;
		try {
			Ssh cbc = new Ssh("root", ipAddr);
			if(cbc.sendCommand("/mnt/user/jvm/cjm/dlprep \"" + dirName + "\"") != 0) {
				helper.error("CBC Reported error preparing for download. It is advised to delete the project from the cbc, and try again. Please consider reinstalling CBCJVM on the CBC.");
				return;
			}
			cbc.sendFile("/mnt/user/code/" + dirName + "/" + dirName + ".c", helper.getTmpCFile(dirName));
			// "/" notifies top level, needed for recursive mode
			helper.sendDirectory(cbc, dirName, true, "/", new File(bin));
			cbc.close();
			helper.message("Download Complete!");
		} catch (IOException e) {
			helper.error("Exception occured: " + e.toString());
			e.printStackTrace();
		}
	}
}
