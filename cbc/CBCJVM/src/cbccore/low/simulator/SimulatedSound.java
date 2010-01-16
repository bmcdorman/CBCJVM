package cbccore.low.simulator;

import cbccore.low.CBCSimulator;
import cbccore.low.Sound;
import cbccore.NotImplemented;

public class SimulatedSound extends Sound {
	
	protected CBCSimulator cbc;
	
	public SimulatedSound(CBCSimulator c) {
		cbc = c;
	}
	
	@NotImplemented public void tone(int frequency, int duration) { /* makes a sound at frequency for duration ms */
		
	}
	
	@NotImplemented public void beep() { /* make a beep */
		
	}
}
