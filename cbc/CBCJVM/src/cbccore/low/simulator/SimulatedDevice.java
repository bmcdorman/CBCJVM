package cbccore.low.simulator;

import cbccore.low.CBCSimulator;
import cbccore.low.Device;

public class SimulatedDevice extends Device {
	
	protected CBCSimulator cbc;
	
	public SimulatedDevice(CBCSimulator c) {
		cbc = c;
	}
	
	public float power_level() { /* returns a float battery voltage */
		return 9.f;
	}
}
