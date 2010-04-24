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

package cbccore.sensors.digital;

import cbccore.Device;
import cbccore.InvalidPortException;
import cbccore.sensors.IBooleanSensor;

/**
 * 
 * @author Braden McDorman
 *
 */

public class BreakBeam implements IBooleanSensor {
	private cbccore.low.Sensor lowSensor = Device.getLowSensorController();
	private int port = 0;
	public BreakBeam(int port) throws InvalidPortException {
		if(port < 0 || port > 7) { throw new InvalidPortException(); }
		this.port = port;
	}
	public boolean getValue() {
		return lowSensor.digital(port) != 0;
	}
}
