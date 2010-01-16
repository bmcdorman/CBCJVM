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

package cbccore.low.simulator;

import cbccore.NotImplemented;
import cbccore.low.CBCSimulator;
import cbccore.low.Sensor;

/**
 * 
 * @author Braden McDorman / Benjamin Woodruff
 *
 */

public class SimulatedSensor extends Sensor {
	
	protected CBCSimulator cbc;
	
	@NotImplemented public SimulatedSensor(CBCSimulator c) {
		cbc = c;
	}
	
	@NotImplemented public int digital(int port) { /* returns a 1 or 0 reflecting the state of port (0 to 7) */
		return 0;
	}
	
	@NotImplemented public int set_digital_output_value(int port, int value) { /*sets port (0 to 7)to value (0 or 1) */
		return 0;
	}
	
	@NotImplemented public int analog10(int port) { /* returns 10-bit value from analog port (ports 8 to 15) */
		return 0;
	}
	
	@NotImplemented public int analog(int port) { /* returns 8-bit value from analog port (ports 8 to 15) */
		return 0;
	}
	
	@NotImplemented public int accel_x() { /* returns x acceleration (-2047 to 2047, +/- 1.5 gee) */    
		return 0;
	}
	              
	@NotImplemented public int accel_y() { /* returns y acceleration (-2047 to 2047, +/- 1.5 gee) */
		return -(int)(2047./1.5*1.);
	}
	              
	@NotImplemented public int accel_z() { /* returns z acceleration (-2047 to 2047, +/- 1.5 gee) */
		return 0;
	}
	
	@NotImplemented public int sonar(int port) { /* returns range in mm for sonar plugged into port (13-15)*/        
		return 0; //stub
	}
	  
	@NotImplemented public int sonar_inches(int port) { /* returns range in whole inches for sonar plugged into port (13-15)*/
		return 0;
	}
}
