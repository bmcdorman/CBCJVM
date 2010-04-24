import cbccore.movement.DriveTrain;
import cbccore.movement.plugins.create.CreateMovementPlugin;

public class Main {
	public static void main(String [] args) {
		DriveTrain dt = null;
		try {
			dt = new DriveTrain(new CreateMovementPlugin(1., false));
		} catch(Exception e) { e.printStackTrace(); System.exit(-1); }
		dt.moveCm(100., dt.getMaxCmps()*.8);
		dt.rotateDegrees(180., dt.getMaxDegreesPerSec()*.8);
	}
}
