package cbccore.sensors.buttons;

import cbccore.Device;

public class BlackButton extends AbstractButton {
	private cbccore.low.Input lowInput = Device.getLowInputController();
	@Override
	public boolean getValue() {
		return lowInput.black_button() == 1;
	}
}
