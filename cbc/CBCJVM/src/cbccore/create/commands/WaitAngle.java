package cbccore.create.commands;

import cbccore.create.Create;

public class WaitAngle implements Command {
	private int deg = 0;
	public WaitAngle(int deg) {
		this.deg = deg;
	}
	public void add(Create create) {
		create.writeByte(157);
		create.writeByte((deg & 0x0000FF00) >> 8);
		create.writeByte((deg & 0x000000FF) >> 0);
	}
}
