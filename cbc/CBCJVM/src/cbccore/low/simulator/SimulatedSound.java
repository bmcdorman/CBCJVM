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

import cbccore.low.CBCSimulator;
import cbccore.low.Sound;
import cbccore.NotImplemented;

/**
 * 
 * @author Braden McDorman / Benjamin Woodruff
 *
 */

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
