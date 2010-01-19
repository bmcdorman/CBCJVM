import cbccore.Device;
import cbccore.motors.Motor;
import cbccore.sensors.IBooleanSensor;


public class BooleanMotorController implements Runnable {
	private Motor motor = null;
	private IBooleanSensor sensor = null;
	private boolean exit = false;
	public BooleanMotorController(Motor motor, IBooleanSensor sensor) throws NullPointerException {
		this.motor = motor;
		this.sensor = sensor;
		if(motor == null || sensor == null) {
			throw new NullPointerException();
		}
	}
	@Override
	public void run() {
		while(!exit) {
			motor.motor(sensor.getValue() == false ? 0 : 1 * 100);
			Thread.yield();
		}
	}
	
	public void exit() {
		exit = true;
	}
}
