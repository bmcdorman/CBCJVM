package cbccore.sensors.buttons;

import cbccore.Device;

public class BButton extends AbstractButton {
	private cbccore.low.Input lowInput = Device.getLowInputController();
	@Override
	public boolean getValue() {
		return lowInput.b_button() == 1;
	}
}
