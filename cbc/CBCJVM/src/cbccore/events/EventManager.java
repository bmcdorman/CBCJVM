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

package cbccore.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * An event system based roughly on ActionScript's (Adobe Flash) and awt's event
 * dispatching system. (yes, you may now toss insults at me based on your hate
 * of FlashPlayer. They will all be redirected to /dev/null). Anything that
 * dispatches an event should subclass this. Generally maintains O(1) time
 * thanks to HashMaps! Yay HashMaps<p>
 * <a href="http://www.adobe.com/livedocs/flash/9.0/ActionScriptLangRefV3/flash/events/EventDispatcher.html">http://www.adobe.com/livedocs/flash/9.0/ActionScriptLangRefV3/flash/events/EventDispatcher.html</a>
 * <p>I'm hoping this class is thread safe, I syncronized a few things,
 * but no guarentees. Shouldn't matter on the CBC, as there is only one
 * core.
 * @author Braden McDorman, Benjamin Woodruff
 */

public class EventManager {
	private HashMap<EventType, HashSet<IEventListener>> events = new HashMap<EventType, HashSet<IEventListener>>();
	private static EventManager instance = null;
	private ArrayList queue;
	private int it = 0;
	
	public static EventManager get() {
		if (instance == null)
			instance = new EventManager();
		return instance;
	}
	
	public synchronized void connect(EventType type, IEventListener l) {
		HashSet<IEventListener> listeners = getListeners(type);
		listeners.add(l);
		events.put(type, listeners);
	}
	
	/**
	 * Removes an event listener from all types in an emitter.
	 *
	 * @param type The event type that IEventListener has been listening for.
	 * @param listener The listening function/object
	 */
	public synchronized void disconnect(EventType type, IEventListener listener) {
		HashSet<IEventListener> listeners = getListeners(type);
		listeners.remove(listener);
		//events.put(e, listeners);
	}
	
	/**
	 * Do not call this directly
	 */
	public void __emit(Event e) {
		HashSet<IEventListener> listeners = getListeners(e.getType());
		for (IEventListener i : listeners) {
			i.event(e);
		}
	}
    
	private HashSet<IEventListener> getListeners(EventType type) {
		HashSet<IEventListener> listeners = events.get(type);
		if (listeners == null) {
			listeners = new HashSet<IEventListener>();
			events.put(type, listeners);
		}
		return listeners;
	}
	
	public Event getUniqueEvent() {
		return new Event(getUniqueEventType());
	}
	
	public EventType getUniqueEventType() {
		return new EventType(this);
	}
	
	public int __getUniqueHandle() {
	    return it++;
	}
	
	/**
	 * Do not call this directly
	 */
	public void __dispose(EventType e) {
		events.remove(e);
	}
}
