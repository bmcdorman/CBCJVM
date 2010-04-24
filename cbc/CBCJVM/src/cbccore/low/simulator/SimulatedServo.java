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
import cbccore.low.Servo;
import cbccore.NotImplemented;

/**
 * 
 * @author Braden McDorman / Benjamin Woodruff
 *
 */

public class SimulatedServo extends Servo {
	
	protected CBCSimulator cbc;
	
	private int[] pos = {0, 0, 0, 0};
	
	public SimulatedServo(CBCSimulator c) {
		cbc = c;
	}
	
	@NotImplemented public void enable_servos() { /* powers up the servos */
		CBCSimulator.NYI("enable_servos");
	}
	
	@NotImplemented public void disable_servos() { /* powers down the servos */
		CBCSimulator.NYI("disable_servos");
	}
	
	public int set_servo_position(int servo, int pos) { /* sets servo (1 to 4) to pos (0 to 2047) */
		this.pos[servo] = pos;
		if(pos < 0 || pos > 2048) {
			System.out.println("Servo(" + servo + ") attempted to move to " + pos + ". Bounds: 0 - 2048");
			pos = pos < 0 ? 0 : pos;
			pos = pos > 2048 ? 2048 : pos;
		}
		return 0;
	}
	
	public int get_servo_position(int servo) { /* returns int of last setting for servo (1 to 4) */
		return this.pos[servo]; //stub
	}
}
