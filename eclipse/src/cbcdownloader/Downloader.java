package cbcdownloader;
import java.io.File;


public abstract class Downloader {
	public static final String PROJECT_DIRECTORY = "/mnt/user/code";
	
	public abstract boolean setup(DownloadConfiguration config);
	public abstract void connect() throws CommunicationException;
	public abstract void disconnect();
	public abstract boolean download(String destination, File file) throws CommunicationException;
	public abstract boolean supportsExecution();
	public abstract String execute(String exec) throws CommunicationException;
	public abstract boolean supportsDeletion();
	public abstract boolean delete(String destination) throws CommunicationException;
	public abstract DownloadConfiguration getConfigurationObject();
	
	public String[] getProgramList() throws CommunicationException {
		if(!supportsExecution()) { return null; }
		return execute("ls -1 " + PROJECT_DIRECTORY).split("\n");
	}
	
	public boolean supportsProgramList() {
		return supportsExecution();
	}
}
