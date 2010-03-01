package cbccore.create.commands;

import cbccore.create.Create;

public class DriveStraight implements Command {
	private int speed = 0;
	public DriveStraight(int speed) {
		this.speed = speed;
	}
	public void add(Create create) {
		create.driveStraight(speed);
	}
}
