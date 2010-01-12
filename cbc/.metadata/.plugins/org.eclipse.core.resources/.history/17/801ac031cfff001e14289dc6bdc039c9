package cbccore.sensors.buttons;

import cbccore.DeviceSingleton;
import cbccore.sensors.IBooleanSensor;

public class UpButton implements IBooleanSensor {
	private cbccore.low.Input lowInput = DeviceSingleton.getInstance().getLowInputController();
	@Override
	public boolean getValue() {
		return lowInput.up_button() == 1;
	}
}
