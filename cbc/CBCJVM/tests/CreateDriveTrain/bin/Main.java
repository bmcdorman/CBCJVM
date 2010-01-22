import cbccore.movement.*;

public class Main {
	public static void main(String [] args) {
		CreateDriveTrain dt = new CreateDriveTrain(1., false);
		dt.moveCm(100., dt.maxCmps()*.8);
		dt.rotateDegrees(180., 40.);
	}
}
