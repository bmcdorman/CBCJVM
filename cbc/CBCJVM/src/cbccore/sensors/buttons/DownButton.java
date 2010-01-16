package cbccore.sensors.buttons;

import cbccore.Device;

public class DownButton extends AbstractButton {
	private cbccore.low.Input lowInput = Device.getLowInputController();
	@Override
	public boolean getValue() {
		return lowInput.down_button() == 1;
	}
}
