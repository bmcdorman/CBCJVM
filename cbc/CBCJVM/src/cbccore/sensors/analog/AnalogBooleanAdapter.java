package cbccore.sensors.analog;

import cbccore.sensors.IBooleanSensor;

public class AnalogBooleanAdapter implements IBooleanSensor {
	private Analog sensor = null;
	private int pivot = 0;

	public AnalogBooleanAdapter(Analog sensor, int pivot) {
		this.sensor = sensor;
		this.pivot = pivot;
	}
	
	public Analog getAnalog() {
		return sensor;
	}
	
	public int getPivot() {
		return pivot;
	}

	public void setPivot(int pivot) {
		this.pivot = pivot;
	}

	@Override
	public boolean getValue() {
		if(sensor.getValueHigh() < pivot) return false;
		return true;
	}
}
