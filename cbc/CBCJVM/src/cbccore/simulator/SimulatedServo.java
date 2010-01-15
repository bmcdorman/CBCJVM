package cbccore.simulator;

import cbccore.low.Servo;
import cbccore.CBCSimulator;
import cbccore.NotImplemented;

public class SimulatedServo extends Servo {
	
	protected CBCSimulator cbc;
	
	public SimulatedServo(CBCSimulator c) {
		cbc = c;
	}
	
	@NotImplemented public void enable_servos() { /* powers up the servos */
		
	}
	
	@NotImplemented public void disable_servos() { /* powers down the servos */
		
	}
	
	@NotImplemented public int set_servo_position(int servo, int pos) { /* sets servo (1 to 4) to pos (0 to 2047) */
		return 0;
	}
	
	@NotImplemented public int get_servo_position(int servo) { /* returns int of last setting for servo (1 to 4) */
		return 0; //stub
	}
}
