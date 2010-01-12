package cbccore.sensors.buttons;

import cbccore.DeviceSingleton;
import cbccore.sensors.IBooleanSensor;

public class DownButton implements IBooleanSensor {
	private cbccore.low.Input lowInput = DeviceSingleton.getInstance().getLowInputController();
	@Override
	public boolean getValue() {
		return lowInput.down_button() == 1;
	}
}
