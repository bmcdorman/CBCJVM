import cbccore.events.Event;
import cbccore.events.EventManager;
import cbccore.events.EventListenerAdapter;
import cbccore.motors.listeners.StopMotors;
import cbccore.sensors.buttons.ButtonEmitter;

public class Main {
	public void run() {
		ButtonEmitter.getThread().run();
		EventManager dispatch = EventManager.get();
		dispatch.connect(ButtonEmitter.AButtonPressed, new EventListenerAdapter() {
			@Override
			public void event(Event e) {
				System.out.println("A Button Pressed!");
			}
		});
		dispatch.connect(ButtonEmitter.AButtonReleased, new EventListenerAdapter() {
			@Override
			public void event(Event e) {
				System.out.println("A Button Released!");
			}
		});
		dispatch.connect(ButtonEmitter.BlackButtonPressed, new StopMotors());
	}
	public static void main(String[] args) {
		new Main().run();
	}
}
