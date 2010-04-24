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

import cbccore.motors.Servo;

public class ServoMotor {
	private long begin = 0;
	private int ms = 0;
	private int delta = 0;
	private int curPos = 0;
	private boolean moving = false;
	private Servo servo = null;

	public ServoMotor(Servo servo) {
		this.servo = servo;
	}

	public boolean isMoving() {
		return moving;
	}

	public void moveTo(int ms, int newPos) {
		curPos = servo.getPosition();
		delta = newPos - curPos;
		begin = System.currentTimeMillis();
		moving = true;
		this.ms = ms;
		ServoMotorThread.get().addServoMotor(this);
	}

	public void update() {
		if (!moving)
			return;

		if (System.currentTimeMillis() > begin + ms) {
			getServo().setPosition(curPos + delta);
			moving = false;
		}

		double frac = ((double) delta / (double) ms);
		int y = (int) (frac * (System.currentTimeMillis() - begin) + curPos);
		if (y > curPos + delta) {
			y = curPos + delta;
			moving = false;
		}
		servo.setPosition(y);
	}
	
	public Servo getServo() {
		return servo;
	}
}

/*
 * 
 * y = ((y2-y1)/(x2-x1)) * x + y1
 */