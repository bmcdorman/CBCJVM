package cbcdownloader;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class USBDownloader extends Downloader {

	protected class USBConfiguration extends DownloadConfiguration {
		public USBConfiguration() {
			addRequirement("port", "The com port for the CBC.");
		}
	}

	private String port = null;
	private SerialPort serialPort = null;

	@Override
	public void connect() throws CommunicationException {
		if (serialPort != null)
			disconnect();
		CommPort commPort = null;
		CommPortIdentifier portId = null;
		try {
			portId = CommPortIdentifier.getPortIdentifier(port);
		} catch (NoSuchPortException e) {
			throw new CommunicationException("No such port " + port);
		}
		try {
			commPort = portId.open(this.getClass().getName(), 2000);
		} catch (PortInUseException e) {
			throw new CommunicationException("Port " + port
					+ " is currently in use");
		}
		if (!(commPort instanceof SerialPort)) {
			throw new CommunicationException("Port " + port
					+ " is not a serial port, and cannot be a CBC");
		}
		serialPort = (SerialPort) commPort;
		try {
			serialPort.setSerialPortParams(38400, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		} catch (UnsupportedCommOperationException e) {
			throw new CommunicationException("Unable to set serial params");
		}

	}

	@Override
	public boolean delete(String destination) throws CommunicationException {
		execute("rm -Rf " + destination);
		return false;
	}

	@Override
	public void disconnect() {
		if (serialPort == null)
			return;
		serialPort.close();
		serialPort = null;
	}

	@Override
	public boolean download(String destination, File file)
			throws CommunicationException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = serialPort.getInputStream();
			out = serialPort.getOutputStream();
		} catch (IOException e) {
			throw new CommunicationException(
					"Unable to obtain input/output streams of serial port");
		}
		try {
			sendFile(out, in, file, destination);
		} catch (IOException e) {
			throw new CommunicationException(e.getMessage());
		}
		return true;
	}

	@Override
	public String execute(String exec) throws CommunicationException {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = serialPort.getInputStream();
			out = serialPort.getOutputStream();
		} catch (IOException e) {
			throw new CommunicationException(
					"Unable to obtain input/output streams of serial port");
		}
		String output = null;
		try {
			output = sendCommand(out, in, exec);
		} catch (IOException e) {
			throw new CommunicationException(e.getMessage());
		}
		return output;
	}
	
	private String sendCommand(OutputStream out, InputStream in, String exec) throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream dStream = new DataOutputStream(stream);
		dStream.writeByte(1);
		dStream.writeShort(exec.length());
		dStream.write(exec.getBytes());
		byte[] data = stream.toByteArray();
		new Packet(data).write(out, in);
		Packet ret = Packet.readPacket(in, out);
		return new String(ret.getBytes());
	}

	@Override
	public DownloadConfiguration getConfigurationObject() {
		return new USBConfiguration();
	}

	@Override
	public boolean setup(DownloadConfiguration config) {
		port = config.getValueFor("port");
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

	private static boolean sendFile(OutputStream out, InputStream in,
			File file, String path) throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream dStream = new DataOutputStream(stream);
		
		FileInputStream fIn = new FileInputStream(file);
		ByteArrayOutputStream bOut = new ByteArrayOutputStream();
		byte[] buff = new byte[512];
		int len = 0;
		while((len = fIn.read(buff)) != -1) bOut.write(buff, 0, len);
		
		byte[] fileData = bOut.toByteArray();
		
		System.out.println(new String());
		
		
		dStream.writeByte(3);
		dStream.writeShort(2 + path.length() + 2 + fileData.length);
		
		dStream.writeShort(path.length());
		dStream.write(path.getBytes());
		
		dStream.writeShort(bOut.toByteArray().length);
		dStream.write(bOut.toByteArray());
		
		new Packet(stream.toByteArray()).write(out, in);

		return true;
	}
	

	@Override
	public String toString() {
		return "USB Cable Downloader";
	}
}
