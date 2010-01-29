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
 * 
 * @author Benjamin Woodruff
 *
 */

public class MotorDriveTrain extends DriveTrain {
	
	public static final int ticksPerRotation = 1100;
	
	protected Wheel _leftWheel;
	protected Wheel _rightWheel;
	private double _maxRps;
	private double _maxCmps;
	private double _trainWidth;
	
	public MotorDriveTrain(Wheel leftWheel, Wheel rightWheel, double trainWidth) {
		_leftWheel = leftWheel;
		_rightWheel = rightWheel;
		_maxRps = Math.min(_leftWheel.maxRps(), _rightWheel.maxRps());
		_maxCmps = Math.min(_leftWheel.maxCmps(), _rightWheel.maxCmps());
		_trainWidth = trainWidth;
	}
	
	public void moveAtVelocity(int tps) throws InvalidValueException {
		moveAtTps(tps);
	}
	
	public void moveAtTps(int tps) throws InvalidValueException {
		_leftWheel.moveAtTps(tps);
		_rightWheel.moveAtTps(tps);
	}
	
	public void moveAtRps(double rps) throws InvalidValueException {
		_leftWheel.moveAtRps(rps);
		_rightWheel.moveAtRps(rps);
	}
	
	public void moveAtCmps(double cmps) throws InvalidValueException {
		_leftWheel.moveAtCmps(cmps);
		_rightWheel.moveAtCmps(cmps);
	}
	
	protected void moveLeftCmps(double cmps) {
		_leftWheel.moveAtCmps(cmps);
	}
	
	protected void moveRightCmps(double cmps) {
		_rightWheel.moveAtCmps(cmps);
	}
	
	public void stop() {
		freeze();
	}
	
	public void freeze() {
		_leftWheel.freeze();
		_rightWheel.freeze();
	}
	
	public void kill() {
		_leftWheel.moveAtTps(0);
		_rightWheel.moveAtTps(0);
	}
	
	public double maxRps() {
		return _maxRps;
	}
	
	public double maxRadiansPS() {
		return maxCmps() * _trainWidth /2./Math.PI;
	}
	
	public double maxCmps() {
		return _maxCmps;
	}
	
	public double getTrainWidth() {
		return _trainWidth;
	}
}
