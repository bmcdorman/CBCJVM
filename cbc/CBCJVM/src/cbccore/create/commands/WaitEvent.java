package cbccore.create.commands;

import cbccore.create.Create;

public class WaitEvent implements Command {
	private int event = 0;
	public WaitEvent(int event) {
		if(event < 0) event += 256;
		this.event = event;
	}
	public void add(Create create) {
		create.writeByte(158);
		create.writeByte(event);
	}
}
