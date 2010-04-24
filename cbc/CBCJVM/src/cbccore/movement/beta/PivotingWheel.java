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

public class PivotingWheel extends Wheel {
	private double circumference = 0;
	private double deg = 0;

	public PivotingWheel(int port, double radius, RotatingCylinder specs)
			throws InvalidPortException {
		super(port, specs);
		circumference = radius * 2 * Math.PI;
	}

	public double getDegrees() {
		return deg;
	}
	
	public void setDegrees(double deg) {
		this.deg = deg;
	}

	public void rotateDegrees(int speed, double deg) {
		double circ = circumference * ((double) deg / 360.0);
		move(speed, circ);
		this.deg += deg;
	}

	public void rotateDegreesAbsolute(int speed, double deg) {
		double delta = deg - this.deg;
		rotateDegrees(speed, delta);
	}
	
	@Override
	public String toString() {
		return "PivotingWheel [circumference=" + circumference + ", deg=" + deg
				+ "]";
	}
}
