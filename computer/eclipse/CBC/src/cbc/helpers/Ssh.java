package cbc.helpers;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

import org.eclipse.jface.dialogs.MessageDialog;

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

	public static class MyUserInfo implements UserInfo, UIKeyboardInteractive {
		public String getPassword() {
			return passwd;
		}

		public boolean promptYesNo(String str) {
			MessageDialog dialog = new MessageDialog(null, "CBC Plug-in", null,
					str, MessageDialog.QUESTION, new String[] { "Yes", "No" },
					0); // yes is the default
			int result = dialog.open();
			return result == 0;
		}

		String passwd;
		JTextField passwordField = (JTextField) new JPasswordField(20);

		public String getPassphrase() {
			return null;
		}

		public boolean promptPassphrase(String message) {
			return true;
		}

		public boolean promptPassword(String message) {
			Object[] ob = { passwordField };
			int result = JOptionPane.showConfirmDialog(null, ob, message,
					JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				passwd = passwordField.getText();
				return true;
			} else {
				return false;
			}
		}

		public void showMessage(String message) {
			JOptionPane.showMessageDialog(null, message);
		}

		final GridBagConstraints gbc = new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0);
		private Container panel;

		public String[] promptKeyboardInteractive(String destination,
				String name, String instruction, String[] prompt, boolean[] echo) {
			panel = new JPanel();
			panel.setLayout(new GridBagLayout());

			gbc.weightx = 1.0;
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.gridx = 0;
			panel.add(new JLabel(instruction), gbc);
			gbc.gridy++;

			gbc.gridwidth = GridBagConstraints.RELATIVE;

			JTextField[] texts = new JTextField[prompt.length];
			for (int i = 0; i < prompt.length; i++) {
				gbc.fill = GridBagConstraints.NONE;
				gbc.gridx = 0;
				gbc.weightx = 1;
				panel.add(new JLabel(prompt[i]), gbc);

				gbc.gridx = 1;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.weighty = 1;
				if (echo[i]) {
					texts[i] = new JTextField(20);
				} else {
					texts[i] = new JPasswordField(20);
				}
				panel.add(texts[i], gbc);
				gbc.gridy++;
			}

			if (JOptionPane.showConfirmDialog(null, panel, destination + ": "
					+ name, JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
				String[] response = new String[prompt.length];
				for (int i = 0; i < prompt.length; i++) {
					response[i] = texts[i].getText();
				}
				return response;
			} else {
				return null; // cancel
			}
		}
	}
}
