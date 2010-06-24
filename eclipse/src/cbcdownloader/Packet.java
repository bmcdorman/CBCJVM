package cbcdownloader;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Packet {
	public static final int PACKET_KEY = 0xB07BA11;
	private static final int CRC16_TABLE[] = { 0x0000, 0x1081, 0x2102, 0x3183,
			0x4204, 0x5285, 0x6306, 0x7387, 0x8408, 0x9489, 0xa50a, 0xb58b,
			0xc60c, 0xd68d, 0xe70e, 0xf78f };

	private static final int SERIAL_MAX_RETRY = 5;
	private static final int SERIAL_MESSAGE_OK = 1;
	private static final int SERIAL_START = 0xCBC;

	private static byte[] data = null;

	public Packet(byte[] data) {
		this.data = data;
	}

	private static boolean checkAck(InputStream in) throws IOException {
		int ret = 0;
		ret = in.read();
		if (ret == SERIAL_MESSAGE_OK) {
			return true;
		}

		return false;
	}

	private static void sendAck(OutputStream out) throws IOException {
		out.write(SERIAL_MESSAGE_OK);
	}

	private static void sendErr(OutputStream out) throws IOException {
		out.write(SERIAL_MESSAGE_OK + 1);
	}

	public boolean write(OutputStream out, InputStream in) throws IOException {
		DataOutputStream dOut = new DataOutputStream(out);

		byte[] dataNullTerm = nullTerminate(data);
		for (int i = 0; i < SERIAL_MAX_RETRY; i++) {
			dOut.writeInt(PACKET_KEY);
			dOut.writeInt(dataNullTerm.length);
			dOut.write(dataNullTerm);
			if (checkAck(in))
				return true;
		}
		return false;
	}

	private static int checksum(byte[] data) {
		int crc = 0xffff;
		byte c;
		int i = 0;
		int len = data.length;
		while (len-- != 0) {
			c = data[i++];
			crc = ((crc >> 4) & 0x0fff) ^ CRC16_TABLE[((crc ^ c) & 15)];
			c >>= 4;
			crc = ((crc >> 4) & 0x0fff) ^ CRC16_TABLE[((crc ^ c) & 15)];
		}
		return ~crc & 0xffff;
	}

	public static byte[] nullTerminate(byte[] data) {
		byte[] dataNullTerm = new byte[data.length + 1];
		System.arraycopy(data, 0, dataNullTerm, 0, data.length);
		dataNullTerm[data.length] = 0;
		return dataNullTerm;
	}

	public static Packet readPacket(InputStream in, OutputStream out)
			throws IOException {
		DataInputStream dIn = new DataInputStream(in);
		while (true) {

			int key = dIn.readInt();
			if (key != PACKET_KEY) {
				continue;
			}
			int len = dIn.readInt();
			byte[] data = new byte[len];
			dIn.readFully(data);
			sendAck(out);
			return new Packet(data);
		}
	}

	public byte[] getBytes() {
		return data;
	}

	public String toString() {
		return "Packet[len=" + getBytes().length + "]";
	}
}
