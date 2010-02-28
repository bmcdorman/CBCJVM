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

package cbccore.low.simulator;

import cbccore.low.CBCSimulator;
import cbccore.low.Input;

import java.awt.Button;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * 
 * @author Benjamin Woodruff (Based on code by Braden McDorman)
 *
 */

public class SimulatedInput extends Input {
	
	protected CBCSimulator cbc;
	
	public boolean u;
	public boolean d;
	public boolean l;
	public boolean r;
	public boolean a;
	public boolean b;
	public boolean bl;
	
	public Button ub;
	public Button db;
	public Button lb;
	public Button rb;
	public Button ab;
	public Button bb;
	public Button blb;
	
	public SimulatedInput(CBCSimulator c) {
		cbc = c;
		SimulatedInputButtonListener sibl = new SimulatedInputButtonListener(this);
		ub = new Button("u");
		ub.addMouseListener(sibl);
		db = new Button("d");
		db.addMouseListener(sibl);
		lb = new Button("l");
		lb.addMouseListener(sibl);
		rb = new Button("r");
		rb.addMouseListener(sibl);
		ab = new Button("a");
		ab.addMouseListener(sibl);
		bb = new Button("b");
		bb.addMouseListener(sibl);
		blb = new Button("bl");
		blb.addMouseListener(sibl);
	}
	
	public int up_button() { return u ? 1 : 0; }
	public int down_button() { return d ? 1 : 0; }
	public int left_button() { return l ? 1 : 0; }
	public int right_button() { return r ? 1 : 0; }
	public int a_button() { return a ? 1 : 0; }
	public int b_button() { return b ? 1 : 0; }
	public int black_button() { return bl ? 1 : 0; } /* returns value of hardware button on CBC */
}


/**
 * A helper-class used by SimulatedInput that handles the mouse presses
 */
class SimulatedInputButtonListener implements MouseListener {
	
	private SimulatedInput _inputHandler;
	
	public SimulatedInputButtonListener(SimulatedInput a_inputHandler) {
		_inputHandler = a_inputHandler;
	}
	
	
	//this system is not very robust, but does it really matter?
	public void mousePressed(MouseEvent e) {
		mouseEventHandler(e, true);
	}
	
	public void mouseReleased(MouseEvent e) {
		mouseEventHandler(e, false);
	}
	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	
	//<random>
	//    someExteamlyLongFunctionName(SomeExtreamlyLongClassName someExtreamlyLongVariableName, SomeOtherEvenLongerExtreamlyLongClassName someOtherEvenLongerExtreamlyLongVariableName)
	//</random>
	private void mouseEventHandler(MouseEvent e, boolean setTo) {
		//This is very dirty code. :-( But reflection is really slow, so...
		String et = ((Button)e.getSource()).getLabel();
		//System.out.println(et);
		if(et.equals("u")) {
			_inputHandler.u = setTo;
			return;
		} if(et.equals("d")) {
			_inputHandler.d = setTo;
			return;
		} if(et.equals("l")) {
			_inputHandler.l = setTo;
			return;
		} if(et.equals("r")) {
			_inputHandler.r = setTo;
			return;
		} if(et.equals("a")) {
			_inputHandler.a = setTo;
			return;
		} if(et.equals("b")) {
			_inputHandler.b = setTo;
			return;
		} if(et.equals("bl")) {
			_inputHandler.bl = setTo;
			return;
		}
	}
}
