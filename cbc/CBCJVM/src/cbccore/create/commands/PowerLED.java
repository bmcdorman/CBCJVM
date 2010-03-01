package cbccore.create.commands;

import cbccore.create.Create;

public class PowerLED implements Command {
	private int color = 0;
	private int brightness = 0;
	public PowerLED(int color, int brightness) {
		this.color = color;
		this.brightness = brightness;
	}
	@Override
	public void add(Create create) {
		create.powerLed(color, brightness);
	}

}
