package cbccore.sensors.buttons;

import cbccore.sensors.IBooleanSensor;

public abstract class AbstractButton implements IBooleanSensor {
	public boolean isPushed() {
		return getValue();
	}
	public boolean isNotPushed() {
		return !getValue();
	}
}
