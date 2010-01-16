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

/**
 * 
 * @author Braden McDorman
 *
 */

public class TwoWheelDriver implements IDriver {
	private Motor left = null;
	private Motor right = null;
	private Drive currentDrive = null;

	public TwoWheelDriver(Motor left, Motor right) {
		this.left = left;
		this.right = right;
	}
	@Override
	public void drive(Drive drive) {
		currentDrive = drive;
		while(!currentDrive.isEmpty()) {
			Movement movement = null;
			synchronized (currentDrive) {
				movement = currentDrive.pop();
			}
			Direction dir = movement.getDirection();
			int speed = movement.getSpeed();
			int distance = movement.getDistance();
			if(dir == Direction.Straight) {
				left.moveRelativePosition(speed, distance);
				right.moveRelativePosition(speed, distance);
			} else if(dir == Direction.Left) {
				left.moveRelativePosition(-speed, distance);
				right.moveRelativePosition(speed, distance);
			} else if(dir == Direction.Right) {
				left.moveRelativePosition(speed, distance);
				right.moveRelativePosition(-speed, distance);
			}
		}
	}
	public Drive getCurrentDrive() {
		return currentDrive;
	}
}
