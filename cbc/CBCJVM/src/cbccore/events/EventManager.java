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

/*
 * Current development status:
 *      Preparing to manage EventListeners that are added during the processing
 *        of events to a separate queue. This queue will be managed after the
 *        current list of listeners by copying it to a local variable, and
 *        clearing the queue. This is done in a loop until nothing is added to
 *        the queue. Before we can do this though, we must ensure the class is
 *        fully thread-safe for this type of operations. Each thread will have
 *        its own queue via java.lang.ThreadLocal . This may seem complicated,
 *        but it is a neccicary step. In the meantime, we must attempt to keep
 *        from breaking builds, as the Event system is a central part of our
 *        libraries
 */

package cbccore.events;

import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
//import java.util.WeakHashMap
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
//ThreadLocal could be useful too.

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
	private HashMap<EventType, Set<IEventListener>> events = new HashMap<EventType, Set<IEventListener>>();
	private static EventManager instance = null;
	private ThreadLocal<EventType> currentEventType;
	private ConcurrentHashMap<EventType, Set<IEventListener>> queue; //can't be thread local- some threads may be using the same type
	private ConcurrentHashMap<EventType, Integer> refcount; //keep references of num of objects
	private ConcurrentHashMap<EventType, Integer> mergecount; //must wait for reaching 0 before continueing
	private int it = 0;
	
	public static EventManager get() {
		if (instance == null)
			instance = new EventManager();
		return instance;
	}
	
	public synchronized void connect(EventType type, IEventListener l) {
		Set<IEventListener> listeners = getListeners(type);
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
		Set<IEventListener> listeners = getListeners(type);
		listeners.remove(listener);
		//events.put(e, listeners);
	}
	
	/**
	 * Do not call this directly
	 */
	public void __emit(Event e) {
		Set<IEventListener> listeners = getListeners(e.getType());
		for (IEventListener i : listeners) {
			i.event(e);
		}
	}
	
	/**
	 * Gets all the listeners for a specific event type
	 * 
	 * @param   type  The type of event to get the listeners for
	 * @return		returnval
	 * @see		   seealso
	 */
	private Set<IEventListener> getListeners(EventType type) {
		Set<IEventListener> listeners = events.get(type);
		if (listeners == null) {
			listeners = Collections.synchronizedSet(new HashSet<IEventListener>());
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
