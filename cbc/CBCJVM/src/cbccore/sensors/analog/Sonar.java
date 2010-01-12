package cbccore.sensors.analog;

import cbccore.Device;
import cbccore.sensors.IAnalogSensor;

public class Sonar implements IAnalogSensor {
	private int port = 0;
	private cbccore.low.Sensor lowSensor = Device.getLowSensorController();
	public Sonar(int port) {
		this.port = port;
	}
	@Override
	public int getValue() {
		return lowSensor.analog(port);
	}
	@Override
	public int getValueHigh() {
		return lowSensor.analog10(port);
	}
	
}
