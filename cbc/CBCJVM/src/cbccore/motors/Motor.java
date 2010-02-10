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

import cbccore.Device;
import cbccore.InvalidPortException;

/**
 * Adds a clean API to replace cbccore.low.Camera, and even adds a few minor
 * features & workarounds for known motor controlling issues.
 * 
 * @author Braden McDorman, Benjamin Woodruff
 * 
 */

public class Motor {
	private int port = 0;
	private cbccore.low.Motor lowMotor = Device.getLowMotorController();
	private long destTime = -1;
	
	public Motor(int port) throws InvalidPortException {
		if(port < 0 || port > 4) throw new InvalidPortException();
		this.port = port;
	}
	public void motor(int percent) {
		lowMotor.motor(port, percent);
	}

	public int clearPositionCounter() {
		return lowMotor.clear_motor_position_counter(port);
	}

	public int moveAtVelocity(int velocity) {
		return lowMotor.mav(port, velocity);
	}

	public int moveToPosition(int speed, int goalPos) {
		setDestTime(speed, goalPos-getPositionCounter());
		return lowMotor.mtp(port, speed, goalPos);
	}

	public int moveRelativePosition(int speed, int deltaPos) {
		setDestTime(speed, deltaPos);
		return lowMotor.mrp(port, speed, deltaPos);
	}
	
	//a helper method
	private void setDestTime(int speed, int deltaPos) {
		destTime = System.currentTimeMillis() + Math.abs(deltaPos*1000/speed);
	}
	
	/**
	 * A safer "blockMotorDone()", does not present the possiblity of a program
	 * locking up because a motor might get stuck. The only downside is that a
	 * motor might not have finished it's movements by the time the function has
	 * returned. Might return before the motor has actually stopped moving. If
	 * you want to ensure if has stopped, you can call <code>off()</code> or
	 * <code>freeze()</code>.
	 * 
	 * @see     #blockMotorDone
	 * @see     #moveToPosition
	 * @see     #moveRelativePosition
	 * @see     #stop
	 * @see     #freeze
	 */
	public void waitForDone() {
		long time = destTime-System.currentTimeMillis();
		if(time > 0l) {
			try {
				Thread.sleep(time);
			} catch (Exception e) { return; }
		}
	}
	
	public void setPidGains(int p, int i, int d, int pd, int id, int dd) {
		lowMotor.set_pid_gains(port, p, i, d, pd, id, dd);
	}

	public int freeze() {
		return lowMotor.freeze(port);
	}

	public int getDone() {
		return lowMotor.get_motor_done(port);
	}

	public int getPositionCounter() {
		return lowMotor.get_motor_position_counter(port);
	}

	public void blockMotorDone() {
		lowMotor.bmd(port);
	}

	public int setPwm(int pwm) {
		return lowMotor.setpwm(port, pwm);
	}

	public int getPwm() {
		return lowMotor.getpwm(port);
	}

	public void forward() {
		lowMotor.fd(port);
	}

	public void backward() {
		lowMotor.bk(port);
	}

	public void off() {
		lowMotor.off(port);
	}
}
