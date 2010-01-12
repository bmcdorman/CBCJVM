package cbccore.sensors.buttons;

import cbccore.Device;
import cbccore.sensors.IBooleanSensor;

public class UpButton implements IBooleanSensor {
	private cbccore.low.Input lowInput = Device.getLowInputController();
	@Override
	public boolean getValue() {
		return lowInput.up_button() == 1;
	}
}
