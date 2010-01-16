import cbccore.Device;
import cbccore.motors.TwoWheelDriver;
import cbccore.motors.Motor;
import cbccore.

public class Main {
	public static void main(String[] args) {
		System.out.println("Welcome to CBCJava");
		Device.init();
		Drive d = new Drive();
		d.add(new StraightMovement(700, 10000));
		new TwoWheelDriver(new Motor(0), new Motor(1)).drive(new Drive());
	}
}
