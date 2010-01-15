package cbccore.motors.listeners;

import cbccore.events.Event;
import cbccore.events.EventEmitter;
import cbccore.events.IEventListener;
import cbccore.motors.NonBlockingDriver;

public class StopDriver implements IEventListener {
	NonBlockingDriver driver;
	public StopDriver(NonBlockingDriver driver) {
		this.driver = driver;
	}
	@Override
	public void eventDispatched(EventEmitter emitter, Event type) {
		driver.halt();
	}
}
