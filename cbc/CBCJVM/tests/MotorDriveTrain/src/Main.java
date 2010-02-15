import cbccore.movement.*;

public class Main {
	public static void main(String [] args) {
		MotorDriveTrain dt = new MotorDriveTrain(new Wheel(0, 15.5744, 1.), new Wheel(1, 15.5744, 1.), 11.);
		dt.moveCm(100., dt.getMaxCmps()*.8);
		System.out.println("First Part Done.");
		dt.rotateDegrees(180., 40.);
	}
}
