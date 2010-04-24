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
