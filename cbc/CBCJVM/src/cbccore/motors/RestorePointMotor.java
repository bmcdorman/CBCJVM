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

package cbccore.motors;

import java.util.ArrayList;
import java.util.List;

/**
 * A special motor controller that can remember certain set positions, and allow
 * you to move to them. (Like open and close positions for a simple claw)
 * 
 * @author Benjamin Woodruff
 * @see    Motor
 */

public class RestorePointMotor extends ArrayList<Integer> {
	private static final long serialVersionUID = 8569618068678782909L;
	
	protected Motor controlMotor;
	
	
	public RestorePointMotor(int port) {
		this(new Motor(port));
	}
	
	
	
	public RestorePointMotor(int port, int ... positions) {
		this(new Motor(port), positions);
	}
	
	
	
	public RestorePointMotor(int port, List<Integer> positions) {
		this(new Motor(port), positions);
	}
	
	
	
	public RestorePointMotor(Motor controlMotor) {
		super();
		this.controlMotor = controlMotor;
	}
	
	
	
	public RestorePointMotor(Motor controlMotor, int ... positions) {
		super(positions.length);
		this.controlMotor = controlMotor;
		addPositions(positions);
	}
	
	
	
	public RestorePointMotor(Motor controlMotor, List<Integer> positions) {
		super(positions);
		this.controlMotor = controlMotor;
	}
	
	
	
	//contructor helper method, Arrays.asList would not work here because we need the wrapper class
	private void addPositions(int[] a) {
		for(int i : a) {
			add(Integer.valueOf(i));
		}
	}
	
	
	
	/**
	 * Gets the stored motor position at a specific location, and move to it,
	 * "restores" the motor position to that archived point.
	 * 
	 * @see           #get
	 */
	public void setToPositionIndex(int speed, int posIndex) {
		controlMotor.moveToPosition(speed, get(posIndex).intValue());
	}
	
	
	
	/**
	 * Stops the motor regardless of position, a convenience method
	 * 
	 * @see           Motor#off
	 * @see           #getMotor
	 */
	public void cancel() {
		controlMotor.off();
	}
	
	
	
	/**
	 * A convenience method
	 * 
	 * @see           #blockMotorDone
	 * @see           #getMotor
	 */
	public void waitForDone() {
		controlMotor.waitForDone();
	}
	
	
	
	/**
	 * A convenience method
	 * 
	 * @see           #waitForDone
	 * @see           #getMotor
	 */
	public void blockMotorDone() {
		controlMotor.blockMotorDone();
	}
	
	
	
	/**
	 * Gets the controller motor, passed into the constructor
	 * 
	 * @see           #waitForDone
	 * @see           #getMotor
	 */
	public Motor getMotor() {
		return controlMotor;
	}
}
