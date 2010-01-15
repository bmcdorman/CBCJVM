package cbccore.sensors.buttons;

import cbccore.events.Event;
import cbccore.events.EventEmitter;

/**
 * 
 * @author beta
 * An unintuitive but necessary class to interface buttons with the event system
 *
 */
public class ButtonEmitter extends EventEmitter implements Runnable {
	public static class AButtonPressed extends Event {}
	public static class BButtonPressed extends Event {}
	public static class BlackButtonPressed extends Event {}
	public static class DownButtonPressed extends Event {}
	public static class UpButtonPressed extends Event {}
	public static class LeftButtonPressed extends Event {}
	public static class RightButtonPressed extends Event {}
	
	public static class AButtonReleased extends Event {}
	public static class BButtonReleased extends Event {}
	public static class BlackButtonReleased extends Event {}
	public static class DownButtonReleased extends Event {}
	public static class UpButtonReleased extends Event {}
	public static class LeftButtonReleased extends Event {}
	public static class RightButtonReleased extends Event {}
	
	private AButton aButton = new AButton();
	private BButton bButton = new BButton();
	private BlackButton blackButton = new BlackButton();
	private DownButton downButton = new DownButton();
	private UpButton upButton = new UpButton();
	private LeftButton leftButton = new LeftButton();
	private RightButton rightButton = new RightButton();
	
	private boolean aButtonState = false;
	private boolean bButtonState = false;
	private boolean blackButtonState = false;
	private boolean downButtonState = false;
	private boolean upButtonState = false;
	private boolean leftButtonState = false;
	private boolean rightButtonState = false;
	
	private static ButtonEmitter instance = null;
	private static Thread thread = new Thread(getInstance());
	
	private ButtonEmitter() {
		
	}
	
	public static ButtonEmitter getInstance() {
		if(instance == null) instance = new ButtonEmitter();
		return instance;
	}
	
	public static Thread getThread() {
		return thread;
	}
	
	@Override
	public void run() {
		boolean newState = aButton.getValue();
		if(newState != aButtonState) {
			if(newState) {
				emit(new AButtonPressed());
			} else {
				emit(new AButtonReleased());
			}
			aButtonState = newState;
		}
		newState = bButton.getValue();
		if(newState != bButtonState) {
			if(newState) {
				emit(new BButtonPressed());
			} else {
				emit(new BButtonReleased());
			}
			bButtonState = newState;
		}
		newState = blackButton.getValue();
		if(newState != blackButtonState) {
			if(newState) {
				emit(new BlackButtonPressed());
			} else {
				emit(new BlackButtonReleased());
			}
			blackButtonState = newState;
		}
		newState = downButton.getValue();
		if(newState != downButtonState) {
			if(newState) {
				emit(new DownButtonPressed());
			} else {
				emit(new DownButtonReleased());
			}
			downButtonState = newState;
		}
		newState = upButton.getValue();
		if(newState != upButtonState) {
			if(newState) {
				emit(new UpButtonPressed());
			} else {
				emit(new UpButtonReleased());
			}
			upButtonState = newState;
		}
		newState = leftButton.getValue();
		if(newState != leftButtonState) {
			if(newState) {
				emit(new LeftButtonPressed());
			} else {
				emit(new LeftButtonReleased());
			}
			leftButtonState = newState;
		}
		newState = rightButton.getValue();
		if(newState != rightButtonState) {
			if(newState) {
				emit(new RightButtonPressed());
			} else {
				emit(new RightButtonReleased());
			}
			rightButtonState = newState;
		}
	}
}