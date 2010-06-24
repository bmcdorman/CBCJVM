package cbcdownloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.io.IOException;

public class DummyDownloader extends Downloader {
	
	protected class DummyConfiguration extends DownloadConfiguration {
		public DummyConfiguration() {
			addRequirement("dl", "The folder (download location) for the virtual CBC filesystem", "virtualcbc");
		}
	}
	
	private File downloadRoot;
	private boolean isValidLoc;
	
	public void connect() throws CommunicationException {
		if(downloadRoot.isFile()) {
			throw new CommunicationException(
				downloadRoot.getAbsolutePath() + "Is a file. It should be a directory."
			);
		}
		if(!downloadRoot.exists()) {
			downloadRoot.mkdirs();
		}
		
	}
	
	private void checkConnection() throws CommunicationException {
		if(!isValidLoc) {
			throw new CommunicationException(
				"Not connected to virtual CBC filesystem"
			);
		}
	}
	
	@Override
	public boolean delete(String destination) throws CommunicationException {
		checkConnection();
		return new File(
			downloadRoot.getPath() + File.separator + destination
		).delete();
	}

	@Override 
	public boolean download(String destination, File file)
			throws CommunicationException {
		File dest = new File(downloadRoot.getPath() + File.separator + destination);
		if(file.isFile()) {
			File parent = dest.getParentFile();
			if(parent != null) { parent.mkdirs(); }
		} else {
			dest.mkdirs();
		}
		try {
			copyFile(file, dest);
		} catch(IOException ex) {
			throw new CommunicationException(
				"An error occured while attempting to copy a file: " +
				ex.getMessage()
			);
		}
		return true;
	}
	
	private void copyFile(File from, File to) throws IOException {
		if(from.isFile()) {
			to.delete(); to.createNewFile();
			FileChannel in = new FileInputStream(from).getChannel();
    		FileChannel out = new FileOutputStream(to).getChannel();
			in.transferTo(0, in.size(), out);
			in.close();
			out.close();
		} else {
			to.mkdir();
			for(File k : from.listFiles()) {
				copyFile(
					k,
					new File(to.getPath() + File.separator + k.getName())
				);
			}
		}
	}

	@Override
	public String execute(String exec) throws CommunicationException {
		return null;
	}

	@Override
	public boolean setup(DownloadConfiguration config) {
		downloadRoot = new File(config.getValueFor("dl"));
		return true;
	}

	@Override
	public boolean supportsDeletion() {
		return true;
	}

	@Override
	public boolean supportsExecution() {
		return false;
	}

	@Override
	public void disconnect() {
		isValidLoc = false;
	}
	
	@Override
	public DownloadConfiguration getConfigurationObject() {
		return new DummyConfiguration();
	}
	
	@Override
	public String[] getProgramList() throws CommunicationException {
		return downloadRoot.list();
	}
	
	@Override
	public String toString() {
		return "Dummy Downloader";
	}
}
