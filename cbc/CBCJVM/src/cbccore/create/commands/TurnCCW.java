package cbccore.create.commands;

import cbccore.create.Create;

public class TurnCCW implements Command {
	private int speed = 0;
	public TurnCCW(int speed) {
		this.speed = speed;
	}
	public void add(Create create) {
		create.spinCCW(speed);
	}
}
