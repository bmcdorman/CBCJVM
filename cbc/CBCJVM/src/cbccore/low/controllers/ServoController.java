package cbccore.low.controllers;

import cbccore.Device;
import cbccore.InvalidPortException;
import cbccore.low.Servo;

public class ServoController extends cbccore.low.Servo {
	private int port;
	private Servo servo = Device.getLowServoController();
	public ServoController(int port) throws InvalidPortException {
		if(port < 0 || port > 4)
		{
			throw new InvalidPortException();
		}
		this.port = port;
	}
	public void setPosition(int pos)
	{
		servo.set_servo_position(this.port, pos);
	}
	public int getPosition()
	{
		return servo.get_servo_position(this.port);
	}
	public void enableServos()
	{
		servo.enable_servos();
	}
	public void disableServos()
	{
		servo.disable_servos();
	}
}
