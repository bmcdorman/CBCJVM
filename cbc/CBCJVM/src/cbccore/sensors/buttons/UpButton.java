package cbccore.sensors.buttons;

import cbccore.Device;

public class UpButton extends AbstractButton {
	private cbccore.low.Input lowInput = Device.getLowInputController();
	@Override
	public boolean getValue() {
		return lowInput.up_button() == 1;
	}
}
