package cbccore.motors.listeners;

import cbccore.events.Event;
import cbccore.events.EventEmitter;
import cbccore.events.IEventListener;

public class StopMotors implements IEventListener {
	@Override
	public void eventDispatched(EventEmitter emitter, Event type) {
		System.out.println("Stopping motors...");
		// Device.getLowMotorController().ao();
	}
}