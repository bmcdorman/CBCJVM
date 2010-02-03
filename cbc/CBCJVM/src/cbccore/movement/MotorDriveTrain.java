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

package cbccore.movement;

import cbccore.InvalidValueException;

/**
 * A basic DriveTrain with support for a dual-wheel-based motor system
 * 
 * @author Benjamin Woodruff
 */

public class MotorDriveTrain extends DriveTrain {
	
	public static final int ticksPerRotation = 1100;
	
	protected Wheel leftWheel;
	protected Wheel rightWheel;
	private double maxRps;
	private double maxCmps;
	private double trainWidth;
	
	public MotorDriveTrain(Wheel leftWheel, Wheel rightWheel, double trainWidth) {
		this.leftWheel = leftWheel;
		this.rightWheel = rightWheel;
		maxRps = Math.min(leftWheel.maxRps(), rightWheel.maxRps());
		//maxCmps = Math.min(leftWheel.maxCmps(), rightWheel.maxCmps());
		this.trainWidth = trainWidth;
	}
	
	public void moveAtVelocity(int tps) throws InvalidValueException {
		moveAtTps(tps);
	}
	
	public void moveAtTps(int tps) throws InvalidValueException {
		leftWheel.moveAtTps(tps);
		rightWheel.moveAtTps(tps);
	}
	
	public void moveAtRps(double rps) throws InvalidValueException {
		leftWheel.moveAtRps(rps);
		rightWheel.moveAtRps(rps);
	}
	
	/** {@inheritDoc} */
	public void moveAtCmps(double cmps) throws InvalidValueException {
		leftWheel.moveAtCmps(cmps);
		rightWheel.moveAtCmps(cmps);
	}
	
	/** {@inheritDoc} */
	protected void directDrive(double leftCmps, double rightCmps) {
		//an old trick I learned a long time ago from the old botball forums
		//should keep wheels near perfectly straight
		leftWheel.moveAtCmps(leftCmps*.5);
		rightWheel.moveAtCmps(rightCmps);
		leftWheel.moveAtCmps(leftCmps);
	}
	
	/** {@inheritDoc} */
	public void freeze() {
		leftWheel.freeze();
		rightWheel.freeze();
	}
	
	public double maxRps() {
		return maxRps;
	}
	
	public double maxRadiansPS() {
		return getMaxCmps() * getTrainWidth() /2./Math.PI;
	}
	
	/** {@inheritDoc} */
	public double getLeftMaxCmps() {
		return leftWheel.maxCmps();
	}
	
	/** {@inheritDoc} */
	public double getRightMaxCmps() {
		return leftWheel.maxCmps();
	}
	
	/** {@inheritDoc} */
	public double getTrainWidth() {
		return trainWidth;
	}
}
