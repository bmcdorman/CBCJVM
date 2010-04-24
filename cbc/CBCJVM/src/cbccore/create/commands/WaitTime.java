package cbccore.create.commands;

import cbccore.create.Create;

public class WaitTime implements Command {
	private int ds = 0;
	public WaitTime(int ds) {
		this.ds = ds;
	}
	public void add(Create create) {
		create.writeByte((char)155);
		create.writeByte((char)ds);
	}
}
