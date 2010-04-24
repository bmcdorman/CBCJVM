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
	private ArrayList<NewServoMotor> servoMotors = new ArrayList<NewServoMotor>();

	private boolean exit = false;

	private static ServoMotorThread instance = null;

	public ServoMotorThread() {
		setDaemon(true);
	}

	public void addServoMotor(NewServoMotor servoMotor) {
		synchronized (servoMotors) {
			servoMotors.add(servoMotor);
		}
	}

	public void exit() {
		exit = true;
	}

	@Override
	public void run() {
		ArrayList<NewServoMotor> removes = new ArrayList<NewServoMotor>();
		while (!exit) {
			removes.clear();
			synchronized (servoMotors) {
				for (NewServoMotor servoMotor : servoMotors) {
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
