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

package cbccore.sensors.buttons;

import cbccore.events.Event;
import cbccore.events.EventManager;

/**
 * 
 * @author Braden McDorman An unintuitive but necessary class to interface
 *         buttons with the event system
 * 
 */
public class ButtonEmitter implements Runnable {
	public static Event AButtonPressed = EventManager.get().getUniqueEvent();
	public static Event BButtonPressed = EventManager.get().getUniqueEvent();
	public static Event BlackButtonPressed = EventManager.get().getUniqueEvent();
	public static Event DownButtonPressed = EventManager.get().getUniqueEvent();
	public static Event UpButtonPressed = EventManager.get().getUniqueEvent();
	public static Event LeftButtonPressed = EventManager.get().getUniqueEvent();
	public static Event RightButtonPressed = EventManager.get().getUniqueEvent();
	
	public static Event AButtonReleased = EventManager.get().getUniqueEvent();
	public static Event BButtonReleased = EventManager.get().getUniqueEvent();
	public static Event BlackButtonReleased = EventManager.get().getUniqueEvent();
	public static Event DownButtonReleased = EventManager.get().getUniqueEvent();
	public static Event UpButtonReleased = EventManager.get().getUniqueEvent();
	public static Event LeftButtonReleased = EventManager.get().getUniqueEvent();
	public static Event RightButtonReleased = EventManager.get().getUniqueEvent();

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
	
	private boolean exit = false;

	private static ButtonEmitter instance = null;
	private static Thread thread = new Thread(getInstance());

	private ButtonEmitter() {

	}

	public static ButtonEmitter getInstance() {
		if (instance == null)
			instance = new ButtonEmitter();
		return instance;
	}

	public static Thread getThread() {
		return thread;
	}

	@Override
	public void run() {
		while (!exit) {
			boolean newState = aButton.getValue();
			if (newState != aButtonState) {
				if (newState) {
					AButtonPressed.emit();
				} else {
					AButtonReleased.emit();
				}
				aButtonState = newState;
			}
			newState = bButton.getValue();
			if (newState != bButtonState) {
				if (newState) {
					BButtonPressed.emit();
				} else {
					BButtonReleased.emit();
				}
				bButtonState = newState;
			}
			newState = blackButton.getValue();
			if (newState != blackButtonState) {
				if (newState) {
					BlackButtonPressed.emit();
				} else {
					BlackButtonReleased.emit();
				}
				blackButtonState = newState;
			}
			newState = downButton.getValue();
			if (newState != downButtonState) {
				if (newState) {
					DownButtonPressed.emit();
				} else {
					DownButtonReleased.emit();
				}
				downButtonState = newState;
			}
			newState = upButton.getValue();
			if (newState != upButtonState) {
				if (newState) {
					UpButtonPressed.emit();
				} else {
					UpButtonReleased.emit();
				}
				upButtonState = newState;
			}
			newState = leftButton.getValue();
			if (newState != leftButtonState) {
				if (newState) {
					LeftButtonPressed.emit();
				} else {
					LeftButtonReleased.emit();
				}
				leftButtonState = newState;
			}
			newState = rightButton.getValue();
			if (newState != rightButtonState) {
				if (newState) {
					RightButtonPressed.emit();
				} else {
					RightButtonReleased.emit();
				}
				rightButtonState = newState;
			}
			Thread.yield();
		}
		exit = false;
	}
	public void exit() {
		exit = true;
	}
}
