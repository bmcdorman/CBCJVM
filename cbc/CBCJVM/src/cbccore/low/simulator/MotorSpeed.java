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

/**
 * 
 * @author Benjamin Woodruff
 *
 */


public class MotorSpeed {
	
	public int speed;
	public boolean bemf;
	
	public MotorSpeed(int speed, boolean bemf) {
		reconstruct(speed, bemf);
	}
	
	/**
	 * Faster than constructing a new, modifies this current instance as if
	 * constructing new.
	 */
	public void reconstruct(int speed, boolean bemf) {
		this.speed = speed;
		this.bemf = bemf;
	} //breaks current SimulatedCBOB Api
	
	public int getTpsSpeed() {
		return bemf?speed:speed*10;
	}
}
