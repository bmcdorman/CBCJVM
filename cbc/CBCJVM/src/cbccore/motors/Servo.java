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
 * 
 * @author Benjamin Woodruff, Braden McDorman
 *
 */

public class Servo {
	private int port = 0;
	private static cbccore.low.Servo lowServo = Device.getLowServoController();
	
	public Servo(int port) throws InvalidPortException {
		if(port < 0 || port > 4) throw new InvalidPortException();
		this.port = port;
	}

	public static void disable() {
		lowServo.disable_servos();
	}
	
	public static void enable() {
		lowServo.enable_servos();
	}
	
	public int getPosition() {
		return lowServo.get_servo_position(port);
	}
	
	public int setPosition(int pos) {
		return lowServo.set_servo_position(port, pos);
	}
	
	public int getPort() {
		return port;
	}
}
