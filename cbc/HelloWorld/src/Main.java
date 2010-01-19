import cbccore.motors.Motor;
import cbccore.sensors.buttons.AbstractButton;
import cbccore.sensors.buttons.BlackButton;
import cbccore.sensors.digital.Touch;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Motor left = new Motor(0);
		Motor right = new Motor(3);
		BooleanMotorController controller = new BooleanMotorController(left, new Touch(8));
		Thread controllerThread = new Thread(controller);
		AbstractButton button = new BlackButton();
		controllerThread.start();
		while(button.isNotPushed());
		controller.exit();
	}
}
