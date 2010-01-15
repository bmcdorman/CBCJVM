package cbccore.simulator;

import cbccore.low.Input;
import cbccore.CBCSimulator;

public class SimulatedInput extends Input {
	
	protected CBCSimulator cbc;
	
	public boolean u;
	public boolean d;
	public boolean l;
	public boolean r;
	public boolean a;
	public boolean b;
	public boolean bl;
	
	public SimulatedInput(CBCSimulator c) {
		cbc = c;
	}
	
	public int up_button() { return u ? 1 : 0; }
	public int down_button() { return d ? 1 : 0; }
	public int left_button() { return l ? 1 : 0; }
	public int right_button() { return r ? 1 : 0; }
	public int a_button() { return a ? 1 : 0; }
	public int b_button() { return b ? 1 : 0; }
	public int black_button() { return bl ? 1 : 0; } /* returns value of hardware button on CBC */
}
