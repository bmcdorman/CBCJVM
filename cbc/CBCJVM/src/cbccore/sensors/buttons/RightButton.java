package cbccore.sensors.buttons;

import cbccore.Device;

public class RightButton extends AbstractButton {
	private cbccore.low.Input lowInput = Device.getLowInputController();
	@Override
	public boolean getValue() {
		return lowInput.right_button() == 1;
	}
}
