package cbccore.sensors.buttons;

import cbccore.Device;

public class AButton extends AbstractButton {
	private cbccore.low.Input lowInput = Device.getLowInputController();
	@Override
	public boolean getValue() {
		return lowInput.a_button() == 1;
	}
}
