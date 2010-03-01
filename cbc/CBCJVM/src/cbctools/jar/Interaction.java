package cbctools.jar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

import cbccore.Device;

public class Interaction implements Runnable {
	ServerSocket server = null;
	Socket client = null;
	InputStream inStream;
	OutputStream outStream;
	DataInputStream inDataStream;
	DataOutputStream outDataStream;
	final double version = 1.1;
	Object main = null;
	public boolean isActive = false;

	public Interaction(Object main) {
		this.main = main;
	}

	public void run() {
		isActive = true;

		// 8411 == BALL in 13375P34K
		try {
			server = new ServerSocket(8411);
			client = server.accept();
			outStream = client.getOutputStream();
			inStream = client.getInputStream();
			outDataStream = new DataOutputStream(outStream);
			inDataStream = new DataInputStream(inStream);
			outDataStream.writeUTF("CBCJava Interaction Server " + version);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (;;) {
			try {
				String in = inDataStream.readUTF();

				String method = in;
				if (method.compareTo("interaction:close") == 0) {
					outDataStream.writeUTF("Bye!");
					break;
				} else if (method.compareTo("interaction:version") == 0) {
					outDataStream.writeUTF("CBCJava Interaction Server "
							+ version);
				} else {
					String error = null;
					// Get only method name
					method = method.substring(0, method.indexOf('('));
					// Get object if need be
					int l = method.lastIndexOf('.');
					Object object = null;
					if (l == -1) {
						object = main;
					} else {
						try {
							method = method.substring(0, l);
							Class<?> classToCall;
							if (method.compareTo("CBC.motor") == 0) {
								object = Device.getLowMotorController();
							} else if (method.compareTo("CBC.sensor") == 0) {
								object = Device.getLowSensorController();
							} else if (method.compareTo("CBC.servo") == 0) {
								object = Device.getLowServoController();
							} else if (method.compareTo("CBC.sound") == 0) {
								object = Device.getLowSoundController();
							} else if (method.compareTo("CBC.display") == 0) {
								object = Device.getLowDeviceController();
							} else if (method.compareTo("System.out") == 0) {
								object = System.out;
							} else if (method.compareTo("CBC.camera") == 0) {
								object = Device.getLowCameraController();
							}
							if (method.compareTo("CBC.input") == 0) {
								object = Device.getLowInputController();
							} else {
								classToCall = Class.forName(method);
								object = classToCall.newInstance();
							}
						} catch (InstantiationException e) {
							error = e.toString();
						} catch (IllegalAccessException e) {
							error = e.toString();
						} catch (ClassNotFoundException e) {
							error = e.toString();
						}
					}
					in = in.substring(l + 1);
					cbccore.interaction.Command cmd = cbccore.interaction.Command.parse(object, in);
					Object returnVal = null;
					try {
						returnVal = cmd.invoke();
					} catch (IllegalAccessException e) {
						error = e.toString();
					} catch (InvocationTargetException e) { 
						e.printStackTrace();
						error = e.toString();
					}

					if (error != null) {
						outDataStream.writeUTF(error);
					} else {
						if (returnVal.getClass() == Void.class) {
							outDataStream.writeUTF("Successfully Executed!");
						} else {
							outDataStream.writeUTF(returnVal.toString());
						}
					}
				}

			} catch (IOException e) {
				try {
					outDataStream.writeUTF(e.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		try {
			client.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		isActive = false;
	}
}
