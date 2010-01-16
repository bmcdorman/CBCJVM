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

public abstract class DriveTrain {
	public abstract void moveAtCmps(double cmps) throws InvalidValueException;
	public abstract void rotateDegrees(double degrees, double degreesPerSecond);
	public abstract void rotateRadians(double radians, double radiansPerSecond);
	public abstract void moveCm(double cm, double cmps) throws InvalidValueException;
	
	protected double moveParser(double cm, double cmps) { //returns new cmps
		//cmps is made to match cm's sign
		return cm<0?-Math.abs(cmps):Math.abs(cmps);
	}
	
	public abstract void stop();
	public abstract void freeze();
	public abstract void kill();
	public abstract double maxCmps();
}
