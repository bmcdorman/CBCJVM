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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

/**
 * 
 * @author Braden McDorman, Benjamin Woodruff
 *
 */

public class Ssh {
	JSch jsch = new JSch();
	Session session = null;
	String user;
	String ip;
	String hosts = System.getProperty("user.home") + File.separatorChar;

	public Ssh(String user, String ip) throws IOException {
		if (File.separatorChar == '\\') {
			// Winblows doesn't like periods
			hosts += "ssh";
		} else {
			hosts += ".ssh";
		}
		new File(hosts).mkdirs();
		hosts += File.separatorChar + "known_hosts";
		this.user = user;
		this.ip = ip;
		if (!checkIp(ip)) {
			throw new IOException(
					"IPv4 Address is an invalid format!  Must be: [0-255].[0-255].[0-255].[0-255]");
		}
		try {
			// Establish connection
			session = jsch.getSession(user, ip, 22);
			File known_hosts = new File(hosts);
			known_hosts.createNewFile();
			String absolute = known_hosts.getAbsolutePath();
			jsch.setKnownHosts(absolute);
			MyUserInfo ui = new MyUserInfo();
			session.setUserInfo(ui);
			session.connect();
		} catch (JSchException e) {
			throw new IOException(e.toString());
		} catch (IOException e) {
			throw new IOException(e.toString());
		} catch (Exception e) {
			throw new IOException(e.toString());
		}
	}

	public static boolean checkIp(String sip) {
		String[] parts = sip.split("\\.");
		for (String s : parts) {
			int i = Integer.parseInt(s);
			if (i < 0 || i > 255) {
				return false;
			}
		}
		return true;
	}

	public void sendFile(String path, File toSend) throws IOException {
		// Start remote scp
		String command = "scp -p -t " + path;
		Channel channel = null;
		try {

			channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);
			OutputStream out = channel.getOutputStream();
			InputStream in = channel.getInputStream();
			channel.connect();
			int check = checkAck(in);
			if (check != 0) {
				throw new IOException(
						"CheckAck failed when starting remote scp (" + check
								+ ")");
			}
			// Send file properties
			long filesize = toSend.length();
			command = "C0644 " + filesize + " ";
			command += toSend.getName();
			command += "\n";
			System.out.print(command);
			out.write(command.getBytes());
			out.flush();
			check = checkAck(in);
			if (check != 0) {
				throw new IOException(
						"CheckAck failed when sending file properties ("
								+ check + ")");
			}
			// Send file
			FileInputStream fis = new FileInputStream(toSend);
			byte[] buf = new byte[1024]; 
			while (true) {
				int len = fis.read(buf, 0, buf.length);
				if (len <= 0)
					break;
				out.write(buf, 0, len);
			}
			fis.close();
			fis = null;
			buf[0] = 0;
			out.write(buf, 0, 1);
			out.flush();
			check = checkAck(in);
			if (check != 0) {
				throw new IOException(
						"CheckAck failed when sending file data (" + check
								+ ")");
			}
			// Cleanup
			out.close();
			channel.disconnect();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int sendCommand(String command) {
		int ret = -1;
		try {
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(command);
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);

			InputStream in = channel.getInputStream();
			channel.connect();
			byte[] tmp = new byte[1024];
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0)
						break;
					System.out.print(new String(tmp, 0, i));
				}
				if (channel.isClosed()) {
					ret = channel.getExitStatus();
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {

				}
			}
			channel.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public void close() {
		session.disconnect();
	}

	int checkAck(InputStream in) throws IOException {
		int b = in.read();
		if (b == 0)
			return b;
		if (b == -1)
			return b;
		if (b == 1 || b == 2) {
			StringBuffer sb = new StringBuffer();
			int c;
			do {
				c = in.read();
				sb.append((char) c);
			} while (c != '\n');
			if (b == 1) { // error
				System.out.print(sb.toString());
			}
			if (b == 2) { // fatal error
				System.out.print(sb.toString());
			}
		}
		return b;
	}

	public static class MyUserInfo implements UserInfo {
		
		Helper helper = new Helper("");
		String passwd;
		
		public String getPassword() {
			return passwd;
		}

		public boolean promptYesNo(String str) {
			while(true) {
				char yn = helper.getInputFromUser(str, "Y/n").toLowerCase().charAt(0);
				if(yn == 'y') { return true; }
				else if(yn == 'n') { return false; }
				helper.message("Invalid input ('y' or 'n').");
			}
		}

		public String getPassphrase() {
			return null;
		}

		public boolean promptPassphrase(String message) {
			return true;
		}

		public boolean promptPassword(String message) {
			passwd = helper.getInputFromUser(message + "(Default is empty)", "");
			return true;
		}

		public void showMessage(String message) {
			helper.message(message);
		}
	}
}
