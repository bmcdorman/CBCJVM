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
 * @author Benjamin Woodruff, based on code By Braden McDorman
 *
 */

public class Servo extends cbccore.low.Servo {
	private int port = 0;
	private cbccore.low.Motor lowMotor = Device.getLowMotorController();
	
	public Servo(int port) throws InvalidPortException {
		if(port < 0 || port > 4) throw new InvalidPortException();
		this.port = port;
	}

	public void disable() {
		disable_servos();
	}
	
	public void enable() {
		enable_servos();
	}
	
	int getPosition() {
		return get_servo_position(port);
	}
	
	int setPosition(int pos) {
		return set_servo_position(port, pos);
	}
}
