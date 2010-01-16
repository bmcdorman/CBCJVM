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

package cbccore.low.controllers;

import cbccore.Device;
import cbccore.InvalidPortException;
import cbccore.low.Servo;

/**
 * 
 * @author Braden McDorman
 *
 */

public class ServoController extends cbccore.low.Servo {
	private int port;
	private Servo servo = Device.getLowServoController();
	public ServoController(int port) throws InvalidPortException {
		if(port < 0 || port > 4)
		{
			throw new InvalidPortException();
		}
		this.port = port;
	}
	public void setPosition(int pos)
	{
		servo.set_servo_position(this.port, pos);
	}
	public int getPosition()
	{
		return servo.get_servo_position(this.port);
	}
	public void enableServos()
	{
		servo.enable_servos();
	}
	public void disableServos()
	{
		servo.disable_servos();
	}
}
