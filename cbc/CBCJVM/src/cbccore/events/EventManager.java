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

/**
 * An event system based roughly on ActionScript's (Adobe Flash) and awt's event
 * dispatching system. (yes, you may now toss insults at us based on your hate
 * of FlashPlayer. They will all be redirected to /dev/null). Anything that
 * dispatches an event should subclass this. Generally maintains O(1) time
 * thanks to HashMaps! Yay HashMaps<p>
 * <a href="http://www.adobe.com/livedocs/flash/9.0/ActionScriptLangRefV3/flash/events/EventDispatcher.html">http://www.adobe.com/livedocs/flash/9.0/ActionScriptLangRefV3/flash/events/EventDispatcher.html</a>
 *
 * @author Braden McDorman / Benjamin Woodruff
 */

public class EventManager {
	private HashMap<Integer, ArrayList<IEventListener>> events = new HashMap<Integer, ArrayList<IEventListener>>();
	private static EventManager instance = null;
	private int it = 0;
	
	public static EventManager get() {
		if (instance == null)
			instance = new EventManager();
		return instance;
	}
	
	public void connect(Event e, IEventListener l) {
		ArrayList<IEventListener> listeners = getListeners(e.getHandle());
		listeners.add(l);
		events.put(e.getHandle(), listeners);
	}
	
	/**
	 * Removes an event listener from all types in an emitter.
	 *
	 * @param emitter The type of emitter that IEventListener has been listening for.
	 */
	public void removeListener(Event e, IEventListener listener) {
		ArrayList<IEventListener> listeners = getListeners(e.getHandle());
		for (IEventListener i : listeners) {
			if(i.equals(listener)) listeners.remove(i);
		}
		events.put(e.getHandle(), listeners);
	}
	
	/**
	 * Do not call this directly
	 *
	 * @param Event handle
	 */
	public void __emit(Event e) {
		ArrayList<IEventListener> listeners = getListeners(e.getHandle());
		for (IEventListener i : listeners) {
			i.event(e);
		}
	}

	private ArrayList<IEventListener> getListeners(int handle) {
		ArrayList<IEventListener> listeners = events.get(handle);
		if (listeners == null) {
			listeners = new ArrayList<IEventListener>();
			events.put(handle, listeners);
		}
		return listeners;
	}
	
	public Event getUniqueEvent() {
		return new Event(it++);
	}
	
	public void __dispose(Event e) {
		events.remove(e);
	}
}
