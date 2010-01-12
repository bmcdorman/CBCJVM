package cbccore.sensors.buttons;

import cbccore.DeviceSingleton;
import cbccore.sensors.IBooleanSensor;

public class LeftButton implements IBooleanSensor {
	private cbccore.low.Input lowInput = DeviceSingleton.getInstance().getLowInputController();
	@Override
	public boolean getValue() {
		return lowInput.left_button() == 1;
	}
}
