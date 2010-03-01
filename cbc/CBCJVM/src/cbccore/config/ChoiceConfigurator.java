package cbccore.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import cbccore.sensors.IBooleanSensor;

public class ChoiceConfigurator implements Configurator {
	Choices choices = null;
	IBooleanSensor[] sensors = null;
	public ChoiceConfigurator(IBooleanSensor[] sensors, Choices choices) {
		this.choices = choices;
		this.sensors = sensors;
	}
	private IBooleanSensor waitForInput(Set<IBooleanSensor> sensors) {
		for(;;) {
			for(IBooleanSensor sensor : sensors) {
				if(sensor.getValue()) return sensor;
			}
		}
	}
	@Override
	public int ask() {
		HashMap<IBooleanSensor, Integer> elements = new HashMap<IBooleanSensor, Integer>();
		int i = 0;
		for(IBooleanSensor sensor : sensors) {
			elements.put(sensor, (Integer) choices.keySet().toArray()[i++]);
			if(i > choices.size() - 1) break;
		}
		
		Iterator<IBooleanSensor> it = elements.keySet().iterator();
		while(it.hasNext()) {
			IBooleanSensor sensor = it.next();
			System.out.println("Press " + sensor + " to " + choices.get(elements.get(sensor)));
		}
		IBooleanSensor sensor = waitForInput(elements.keySet());
		return elements.get(sensor);
	}
}
