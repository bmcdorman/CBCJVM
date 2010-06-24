package cbcdownloader;
import java.io.File;
import java.io.IOException;


public class NetworkDownloader extends Downloader {
	
	protected class NetworkConfiguration extends DownloadConfiguration {
		public NetworkConfiguration() {
			addRequirement("ip", "The IPv4 address of the device");
		}
	}
	
	private String ip = null;
	private Ssh ssh = null;
	
	public void connect() throws CommunicationException {
		if(ssh == null) disconnect();
		try {
			ssh = new Ssh(ip);
			System.out.println("Connected via ssh to " + ip);
		} catch (IOException e) {
			throw new CommunicationException("Unable to connect");
		}
	}
	
	private void checkConnection() throws CommunicationException {
		if(ssh == null) throw new CommunicationException("Not connected to " + ip);
	}
	
	@Override
	public boolean delete(String destination) throws CommunicationException {
		checkConnection();
		ssh.sendCommand("rm -Rf \"" + destination + "\"");
		return true;
	}

	@Override 
	public boolean download(String destination, File file)
			throws CommunicationException {
		System.out.println("Download");
		checkConnection();
		try {
			ssh.sendFile(destination, file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new CommunicationException();
		}
		return true;
	}

	@Override
	public String execute(String exec) throws CommunicationException {
		checkConnection();
		return ssh.sendCommand(exec);
	}

	@Override
	public boolean setup(DownloadConfiguration config) {
		ip = config.getValueFor("ip");
		return true;
	}

	@Override
	public boolean supportsDeletion() {
		return true;
	}

	@Override
	public boolean supportsExecution() {
		return true;
	}

	@Override
	public void disconnect() {
		if(ssh == null) return;
		ssh.close();
		ssh = null;
	}
	
	@Override
	public DownloadConfiguration getConfigurationObject() {
		return new NetworkConfiguration();
	}
	
	@Override
	public String toString() {
		return "Network (Internet / LAN) Downloader";
	}
}
