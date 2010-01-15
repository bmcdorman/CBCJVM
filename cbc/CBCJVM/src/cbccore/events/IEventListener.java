package cbccore.events;

public interface IEventListener {
	public void eventDispatched(EventEmitter emitter, Event type);
}
