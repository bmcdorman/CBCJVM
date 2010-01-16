package cbccore.sensors.buttons;

import cbccore.Device;

public class LeftButton extends AbstractButton {
	private cbccore.low.Input lowInput = Device.getLowInputController();
	@Override
	public boolean getValue() {
		return lowInput.left_button() == 1;
	}
}
