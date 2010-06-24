package cbcdownloader;
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

public class Ssh {
	private JSch jsch = new JSch();
	private Session session = null;
	
	public Ssh(String ip) throws IOException {
		if (!checkIp(ip)) {
			throw new IOException(
					"IPv4 Address is an invalid format!  Must be: [0-255].[0-255].[0-255].[0-255]");
		}
		try {
			// Establish connection
			session = jsch.getSession("root", ip, 22);
			MyUserInfo ui = new MyUserInfo();
			session.setUserInfo(ui);
			session.connect(4000);
		} catch (JSchException e) {
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
	
	private String convertPath(String path) {
		path = path.replace("$", "\\$");
		return path;
	}

	public void sendFile(String path, File toSend) throws IOException {
		// Start remote scp
		String command = "scp -p -t " + "\"" + convertPath(path) + "\"";
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
			command += "\"" + toSend.getName() + "\"";
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
			e.printStackTrace();
		}
	}
	
	public String sendCommand(String command) {
		String ret = new String();
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
					ret += new String(tmp, 0, i);
				}
				if (channel.isClosed()) {
					break;
				}
				try {
					Thread.sleep(100);
				} catch (Exception ee) {

				}
			}
			channel.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (IOException e) {
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

	public static class MyUserInfo implements UserInfo, UIKeyboardInteractive {
		public String getPassword() {
			return "";
		}

		public boolean promptYesNo(String str) {
			return true;
		}

		public String getPassphrase() {
			return null;
		}

		public boolean promptPassphrase(String message) {
			return true;
		}

		public boolean promptPassword(String message) {
			return true;
		}

		public void showMessage(String message) {
		}

		public String[] promptKeyboardInteractive(String destination,
				String name, String instruction, String[] prompt, boolean[] echo) {
			return null;
		}
	}
}
