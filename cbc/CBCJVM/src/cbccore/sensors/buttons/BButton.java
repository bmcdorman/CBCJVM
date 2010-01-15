package cbccore.sensors.buttons;

import cbccore.Device;
import cbccore.sensors.IBooleanSensor;

public class BButton implements IBooleanSensor {
	private cbccore.low.Input lowInput = Device.getLowInputController();
	@Override
	public boolean getValue() {
		return lowInput.b_button() == 1;
	}
}