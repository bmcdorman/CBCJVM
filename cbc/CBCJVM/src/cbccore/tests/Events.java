package cbccore.tests;

import cbccore.Device;
import cbccore.events.Event;
import cbccore.events.EventDispatcher;
import cbccore.events.EventEmitter;
import cbccore.events.EventListenerAdapter;
import cbccore.events.IEventListener;

public class Events {
	// Dummy event
	public final class DriveBegan extends Event {}
	public final class DestinationReached extends Event {}
	// Dummy emitter
	public class Driver extends EventEmitter {
		public void drive() {
			emit(new DriveBegan());
			// ...
			emit(new DestinationReached());
		}
	}
	
	// Beeps
	public class Beeper implements IEventListener {
		@Override
		public void eventDispatched(EventEmitter emitter, Event type) {
			System.out.println("BEEP!");
		}
	}
	
	public Events() {
		EventDispatcher dispatcher = EventDispatcher.getInstance();
		Driver driver = new Driver();
		dispatcher.addEventListener(driver, new DestinationReached(), new Beeper());
		
		// Inline class ftw!
		dispatcher.addEventListener(driver, new DriveBegan(), new EventListenerAdapter() {
			@Override
			public void eventDispatched(EventEmitter emitter, Event type) {
				System.out.println("DRIVE BEGAN!");
			}
		});
		driver.drive();
	}
	
	public static void main(String[] args) {
		new Events();
	}

}
