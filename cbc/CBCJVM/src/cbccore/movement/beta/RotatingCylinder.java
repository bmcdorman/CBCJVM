package cbccore.movement.beta;

public class RotatingCylinder {
	private Rotation ticks;
	private double circumference;

	public RotatingCylinder(double circumference, Rotation ticks) {
		this.circumference = circumference;
		this.ticks = ticks;
	}

	public double getCircumference() {
		return circumference;
	}

	public Rotation getRotation() {
		return ticks;
	}

	public void setCircumference(double circumference) {
		this.circumference = circumference;
	}

	public void setRotation(Rotation ticks) {
		this.ticks = ticks;
	}
}
