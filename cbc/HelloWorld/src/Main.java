import cbccore.events.Event;
import cbccore.events.EventDispatcher;
import cbccore.events.EventEmitter;
import cbccore.events.EventListenerAdapter;
import cbccore.motors.Motor;
import cbccore.sensors.buttons.AbstractButton;
import cbccore.sensors.buttons.BlackButton;
import cbccore.sensors.buttons.ButtonEmitter;
import cbccore.sensors.digital.Touch;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		// Working with left motor
		Motor left = new Motor(0);
		// A boolean controller turns a motor on/off based on a digital sensor
		BooleanMotorController controller = new BooleanMotorController(left, new Touch(8));
		Thread controllerThread = new Thread(controller);
		AbstractButton button = new BlackButton();
		// Start Button Emitter
		ButtonEmitter.getThread().start();
		ButtonEmitter emitter = ButtonEmitter.getInstance();
		EventDispatcher dispatch = EventDispatcher.getInstance();
		// Tell the dispatcher which events we want to listen to, and print a message accordingly
		dispatch.addEventListener(emitter, new ButtonEmitter.AButtonPressed(), new EventListenerAdapter() {
			@Override
			public void eventDispatched(EventEmitter emitter, Event type) {
				System.out.println("Pressed!");
			}
		});
		dispatch.addEventListener(emitter, new ButtonEmitter.AButtonReleased(), new EventListenerAdapter() {
			@Override
			public void eventDispatched(EventEmitter emitter, Event type) {
				System.out.println("Released!");
			}
		});
		// Start boolean controller
		controllerThread.start();
		while(button.isNotPushed()) {
			Thread.yield();
		}
		controller.exit();
	}
}
