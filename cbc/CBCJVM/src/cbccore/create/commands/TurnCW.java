package cbccore.create.commands;

import cbccore.create.Create;

public class TurnCW implements Command {
	private int speed = 0;
	public TurnCW(int speed) {
		this.speed = speed;
	}
	public void add(Create create) {
		create.spinCW(speed);
	}
}
