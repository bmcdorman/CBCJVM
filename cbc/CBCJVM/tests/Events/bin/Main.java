/*
 * This file is part of CBCJVM.
 * CBCJVM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * CBCJVM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with CBCJVM.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * @author   Braden McDorman, Benjamin Woodruff
 */

//package cbccore.tests;

import cbccore.Device;
import cbccore.events.Event;
import cbccore.events.EventType;
import cbccore.events.EventManager;
//import cbccore.events.EventEmitter;
import cbccore.events.EventListenerAdapter;
import cbccore.events.IEventListener;

public class Main {
	// Dummy event
	public static EventType DRIVE_BEGAN_EVENT = EventManager.get().getUniqueEventType();
	public static EventType DESTINATION_REACHED_EVENT = EventManager.get().getUniqueEventType();
	// Dummy emitter
	public class Driver {
		public void drive() {
		    System.out.println("about to emit events");
			new Event(DRIVE_BEGAN_EVENT).emit();
			//...
			try { Thread.sleep(1000l); } catch(Exception e) { System.exit(1); }
			//...
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
		Beeper beeperOne = new Beeper("BEEP!");
		manager.connect(DESTINATION_REACHED_EVENT, beeperOne);
		//this way is more reusable
		Beeper beeperTwo = new Beeper("Another BEEP!er object");
		manager.connect(DESTINATION_REACHED_EVENT, beeperTwo);
		
		// Inline class ftw! This way is easier for a unique event listener
		EventListenerAdapter adapter = new EventListenerAdapter() {
			@Override
			public void event(Event type) {
				System.out.println("DRIVE BEGAN!");
			}
		};
		manager.connect(DRIVE_BEGAN_EVENT, adapter);
		driver.drive();
		
		//Always clean up after yourself. You may decide to write a dispose method
		//It does not hurt to remove an event that was not added. This is for convenience
		manager.disconnect(DESTINATION_REACHED_EVENT, beeperOne);
		manager.disconnect(DESTINATION_REACHED_EVENT, beeperTwo);
	    manager.disconnect(DRIVE_BEGAN_EVENT, adapter);
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
