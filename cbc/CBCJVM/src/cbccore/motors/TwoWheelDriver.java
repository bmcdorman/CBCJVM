package cbccore.motors;

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