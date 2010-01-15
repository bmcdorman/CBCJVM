package cbccore.events;

public class EventEmitter {
	public void emit(Event type) {
		EventDispatcher.getInstance().emit(this, type);
	}
}
