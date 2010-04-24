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
