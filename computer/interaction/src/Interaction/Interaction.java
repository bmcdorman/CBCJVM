package Interaction;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Interaction {
	Socket connection = null;
	InputStream inStream;
	OutputStream outStream;
	DataInputStream inDataStream;
	DataOutputStream outDataStream;
	String ip = "";

	public Interaction(String ip) {
		this.ip = ip;
	}
	public String getInput() {
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      String input = null;
	      try {
	         input = br.readLine();
	      } catch (IOException ioe) {
	         ioe.printStackTrace();
	         System.exit(1);
	      }
		return input;
	}
	public void client() {
		try {
			try {
				connection = new Socket(ip, 8411);
				System.out.println("Connected to CBC at " + ip + "!");
			} catch (UnknownHostException e) {
				System.out.println("Failed to connect to CBC!");
			}

			inStream = connection.getInputStream();
			outStream = connection.getOutputStream();
			inDataStream = new DataInputStream(inStream);
			outDataStream = new DataOutputStream(outStream);
			try {
				for(;;) {
					String message = inDataStream.readUTF();
					System.out.println(message);
					System.out.print("> ");
					outDataStream.writeUTF(getInput());
				} // end while
			} // end try for input
			catch (EOFException except) {
				connection.close();
				System.out.println("Connection closed.");
			} // end catch
			catch (IOException except) {
				except.printStackTrace();
			} // end catch

		} // end try for connection
		catch (IOException except) {
			except.printStackTrace();
		} // end catch
	}

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage: java Interaction [IP Address]");
			System.exit(0);
		}
		Interaction interaction = new Interaction(args[0]);
		interaction.client();

	}
}
