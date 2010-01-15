package cbccore.simulator;

import cbccore.CBCSimulator;
import cbccore.low.Sensor;

public class SimulatedSensor extends Sensor {
	
	protected CBCSimulator cbc;
	
	public SimulatedSensor(CBCSimulator c) {
		cbc = c;
	}
	
	public int digital(int port) { /* returns a 1 or 0 reflecting the state of port (0 to 7) */
		return 0;
	}
	
	public int set_digital_output_value(int port, int value) { /*sets port (0 to 7)to value (0 or 1) */
		return 0;
	}
	
	public int analog10(int port) { /* returns 10-bit value from analog port (ports 8 to 15) */
		return 0;
	}
	
	public int analog(int port) { /* returns 8-bit value from analog port (ports 8 to 15) */
		return 0;
	}
	
	public int accel_x() { /* returns x acceleration (-2047 to 2047, +/- 1.5 gee) */    
		return 0;
	}
	              
	public int accel_y() { /* returns y acceleration (-2047 to 2047, +/- 1.5 gee) */
		return -(int)(2047./1.5*1.);
	}
	              
	public int accel_z() { /* returns z acceleration (-2047 to 2047, +/- 1.5 gee) */
		return 0;
	}
	
	public int sonar(int port) { /* returns range in mm for sonar plugged into port (13-15)*/        
		return 0; //stub
	}
	  
	public int sonar_inches(int port) { /* returns range in whole inches for sonar plugged into port (13-15)*/
		return 0;
	}
}
