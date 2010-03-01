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
import cbccore.InvalidPercentageException;
import cbccore.InvalidPortException;
import cbccore.InvalidValueException;
import cbccore.low.Motor;

/**
 * 
 * @author Braden McDorman
 *
 */

public class MotorController {
	private int port;
	private Motor motor = Device.getLowMotorController();
	public MotorController(int port) throws InvalidPortException {
		if(port < 0 || port > 4)
		{
			throw new InvalidPortException();
		}
		this.port = port ;
	}
	public void moveAtPercent(int percent) throws InvalidPercentageException
	{
		if(percent > 100 || percent < -100)
		{
			throw new InvalidPercentageException();
		}
		motor.motor(this.port, percent);
	}
	public void moveToPosition(int speed, int goal)
	{
		if(speed > 1000 || speed < -1000)
		{
			throw new InvalidValueException();
		}
	}
	public void moveAtVelocity(int velocity) throws InvalidValueException
	{
		if(velocity > 1000 || velocity < -1000)
		{
			throw new InvalidValueException();
		}
		motor.mav(this.port, velocity);
	}
	public void moveRelativePosition(int speed, int delta) throws InvalidValueException
	{
		if(speed > 1000 || speed < -1000)
		{
			throw new InvalidValueException();
		}
		motor.mrp(this.port, speed, delta);
	}
	public void blockMotorDone()
	{
		motor.bmd(this.port);
	}
	public int getPositionCounter()
	{
		return motor.get_motor_position_counter(this.port);
	}
	public void stop()
	{
		motor.off(this.port);
	}
	public void freeze()
	{
		motor.freeze(this.port);
	}
}
