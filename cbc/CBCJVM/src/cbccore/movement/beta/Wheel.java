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

package cbccore.movement.beta;

import cbccore.InvalidPortException;
import cbccore.motors.Motor;

public class Wheel extends Motor implements Mover {
	protected RotatingCylinder specs;

	public Wheel(int port, RotatingCylinder specs) throws InvalidPortException {
		super(port);
		this.specs = specs;
	}

	public void move(int speed, double cm) {
		double circs = cm / specs.getCircumference();
		moveRelativePosition(speed, (int) (circs * specs.getRotation()
				.getTicks()));
		blockMotorDone();
	}

	public void moveNonBlocking(int speed, double cm) {
		double circs = cm / specs.getCircumference();
		moveRelativePosition(speed, (int) (circs * specs.getRotation()
				.getTicks()));
	}
}
