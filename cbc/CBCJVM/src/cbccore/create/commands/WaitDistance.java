package cbccore.create.commands;

import cbccore.create.Create;

public class WaitDistance implements Command {
	private int mm = 0;
	public WaitDistance(int mm) {
		this.mm = mm;
	}
	public void add(Create create) {
		create.writeByte(156);
		create.writeByte((mm & 0x0000FF00) >> 8);
		create.writeByte((mm & 0x000000FF) >> 0);
	}
}
