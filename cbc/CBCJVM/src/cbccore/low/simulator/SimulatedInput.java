package cbccore.low.simulator;

import cbccore.low.Input;
import cbccore.CBCSimulator;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		ub.addActionListener(sibl);
		db = new Button("d");
		db.addActionListener(sibl);
		lb = new Button("l");
		lb.addActionListener(sibl);
		rb = new Button("r");
		rb.addActionListener(sibl);
		ab = new Button("a");
		ab.addActionListener(sibl);
		bb = new Button("b");
		bb.addActionListener(sibl);
		blb = new Button("bl");
		blb.addActionListener(sibl);
	}
	
	public int up_button() { return u ? 1 : 0; }
	public int down_button() { return d ? 1 : 0; }
	public int left_button() { return l ? 1 : 0; }
	public int right_button() { return r ? 1 : 0; }
	public int a_button() { return a ? 1 : 0; }
	public int b_button() { return b ? 1 : 0; }
	public int black_button() { return bl ? 1 : 0; } /* returns value of hardware button on CBC */
}

class SimulatedInputButtonListener implements ActionListener {
	
	private SimulatedInput _inputHandler;
	
	public SimulatedInputButtonListener(SimulatedInput a_inputHandler) {
		_inputHandler = a_inputHandler;
	}
	
	public void actionPerformed(ActionEvent ae) {
		//This is very dirty code. :-( But reflection is really slow, so...
		if(ae.getActionCommand().equals("u")) {
			_inputHandler.u = !_inputHandler.u;
			return;
		} if(ae.getActionCommand().equals("d")) {
			_inputHandler.d = !_inputHandler.d;
			return;
		} if(ae.getActionCommand().equals("l")) {
			_inputHandler.l = !_inputHandler.l;
			return;
		} if(ae.getActionCommand().equals("r")) {
			_inputHandler.r = !_inputHandler.r;
			return;
		} if(ae.getActionCommand().equals("a")) {
			_inputHandler.a = !_inputHandler.a;
			//System.out.println(_inputHandler.a);
			return;
		} if(ae.getActionCommand().equals("b")) {
			_inputHandler.b = !_inputHandler.b;
			return;
		} if(ae.getActionCommand().equals("bl")) {
			_inputHandler.bl = !_inputHandler.bl;
			return;
		}
	}
}
