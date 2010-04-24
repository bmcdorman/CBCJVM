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

package cbccore.sensors.analog;

import cbccore.Device;
import cbccore.sensors.IAnalogSensor;

/**
 * Access to the analog sensor data from the CBOB
 * 
 * @author Braden McDorman, Benjamin Woodruff
 */

public class Analog implements IAnalogSensor {
	private int port = 0;
	protected static boolean[] floatingSensors = new boolean[8];
	
	private cbccore.low.Sensor lowSensor = Device.getLowSensorController();
	
	public Analog(int port) {
		this.port = port;
	}
	
	/**
	 * Returns getValueHigh()/4, for Handy-Board backwards compatabilty. This
	 * method is deprecated. You should be using getValueHigh which corrisponds
	 * to the userlib analog10 function.
	 */
	
	@Deprecated
	@Override
	public int getValue() {
		return lowSensor.analog(port);
	}
	
	/**
	 * This is the function you want!
	 */
	
	@Override
	public int getValueHigh() {
		return lowSensor.analog10(port);
	}
	
	
	/**
	 * Enables or disables sensor floating mode, required for ET sensor.
	 * 
	 * @param  floating  true turns on floating mode, false turns it off
	 */
	public void setFloating(boolean floating) {
		floatingSensors[port] = floating;
		updateFloatingPorts();
	}
	
	/**
	 * Updates the floating point ports with the floatingSensors array. It must
	 * be done this way due to the userlib api design
	 */
	protected void updateFloatingPorts() {
		lowSensor.set_each_analog_state(floatingSensors[0]?1:0, floatingSensors[1]?1:0, floatingSensors[2]?1:0, floatingSensors[3]?1:0, floatingSensors[4]?1:0, floatingSensors[5]?1:0, floatingSensors[6]?1:0, floatingSensors[7]?1:0);
	}
}
