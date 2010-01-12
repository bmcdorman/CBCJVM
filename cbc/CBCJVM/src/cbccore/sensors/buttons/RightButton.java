package cbccore.sensors.buttons;

import cbccore.Device;
import cbccore.sensors.IBooleanSensor;

public class RightButton implements IBooleanSensor {
	private cbccore.low.Input lowInput = Device.getLowInputController();
	@Override
	public boolean getValue() {
		return lowInput.right_button() == 1;
	}
}
