/*
 * This file is part of CBCJVM.
 * CBCJVM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * CBCJVM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with CBCJVM.  If not, see <http://www.gnu.org/licenses/>.
*/

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
			System.out.println("Added servo motor.");
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
