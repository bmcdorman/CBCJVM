package cbccore.motors;

import java.util.ArrayList;

public class ServoMotorThread extends Thread {
	public static ServoMotorThread get() {
		if (instance == null) {
			instance = new ServoMotorThread();
			instance.start();
		}
		return instance;
	}
	private ArrayList<ServoMotor> servoMotors = new ArrayList<ServoMotor>();

	private boolean exit = false;

	private static ServoMotorThread instance = null;

	public ServoMotorThread() {
		setDaemon(true);
	}

	public void addServoMotor(ServoMotor servoMotor) {
		synchronized (servoMotors) {
			servoMotors.add(servoMotor);
		}
	}

	public void exit() {
		exit = true;
	}

	@Override
	public void run() {
		ArrayList<ServoMotor> removes = new ArrayList<ServoMotor>();
		while (!exit) {
			removes.clear();
			synchronized (servoMotors) {
				for (ServoMotor servoMotor : servoMotors) {
					servoMotor.update();
					if (!servoMotor.isMoving()) {
						removes.add(servoMotor);
					}
				}
				servoMotors.removeAll(removes);
			}
		}
	}
}
