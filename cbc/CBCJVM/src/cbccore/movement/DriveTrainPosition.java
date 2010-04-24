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

/**
 * A position for the robot, direction and X-Y. Robots typicaly are seen as
 * starting at 0 degrees and (0, 0).
 * 
 * @author Benjamin Woodruff
 * @see	cbccore.movement.DriveTrain
 */

public class DriveTrainPosition {
	private double x;
	private double y;
	private double rawAngle;
	
	public DriveTrainPosition(double x, double y, double rawAngleRadians) {
		this.x = x;
		this.y = y;
		this.rawAngle = rawAngleRadians;
	}
	
	public double getX() { return x; }
	public double getY() { return x; }
	public double getRawAngleRadians() { return rawAngle; }
	public double getRawAngleDegrees() { return Math.toDegrees(getRawAngleRadians()); }
	public double getAngleRadians() { return getRawAngleRadians()%(Math.PI*2.); }
	public double getAngleDegrees() { return Math.toDegrees(getAngleRadians()); }
}
