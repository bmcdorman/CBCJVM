package cbccore.sensors.buttons;

import cbccore.Device;
import cbccore.sensors.IBooleanSensor;

public class AButton implements IBooleanSensor {
	private cbccore.low.Input lowInput = Device.getLowInputController();
	@Override
	public boolean getValue() {
		return lowInput.a_button() == 1;
	}
}
