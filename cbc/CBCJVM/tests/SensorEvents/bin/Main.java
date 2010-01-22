//package cbccore.tests;

import cbccore.Device;
import cbccore.events.Event;
import cbccore.events.EventManager;
//import cbccore.events.EventEmitter;
import cbccore.events.EventListenerAdapter;
import cbccore.events.IEventListener;

public class Main {
	// Dummy event
	public static int DRIVE_BEGAN_EVENT = EventManager.get().getUniqueEventType();
	public static int DESTINATION_REACHED_EVENT = EventManager.get().getUniqueEventType();
	// Dummy emitter
	public class Driver {
		public void drive() {
		    System.out.println("about to emit events");
			new Event(DRIVE_BEGAN_EVENT).emit();
			// ...
		    new Event(DESTINATION_REACHED_EVENT).emit();
		    System.out.println("Done emitting events");
		}
	}
	
	// Beeps
	protected class Beeper implements IEventListener {
		private String examplePassedVariable;
		
		public Beeper(String examplePassedVariable) {
			this.examplePassedVariable = examplePassedVariable;
		}
		
		@Override
		public void event(Event e) {
			System.out.println(examplePassedVariable);
		}
	}
	
	public Main() {
		EventManager manager = EventManager.get();
		Driver driver = new Driver();
		
		//two different ways of making event listeners
		manager.connect(DESTINATION_REACHED_EVENT, new Beeper("BEEP!"));
		//this way is more reusable
		manager.connect(DESTINATION_REACHED_EVENT, new Beeper("Another BEEP!er object"));
		
		// Inline class ftw! This way is easier for a unique event listener
		EventListenerAdapter adapter = new EventListenerAdapter() {
			@Override
			public void event(Event type) {
				System.out.println("DRIVE BEGAN!");
			}
		};
		manager.connect(DRIVE_BEGAN_EVENT, adapter);
		driver.drive();
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
